package com.asklepios.backend_service.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.ApEncounterVaccination;
import com.asklepios.backend_service.model.generated.pojo.ApVaccine;
import com.asklepios.backend_service.model.generated.pojo.ApVaccineBrands;
import com.asklepios.backend_service.model.generated.pojo.ApVaccineDose;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.asklepios.backend_service.model.generated.dao.ApEncounterVaccinationDAO;

import javax.management.Query;

@Service
@Slf4j
public class ApEncounterVaccinationService extends ApEncounterVaccinationDAO implements Serializable {

    private final ApVaccineDoseService apVaccineDoseService;
    private final ApVaccineService apVaccineService;
    private final ApVaccineBrandsService apVaccineBrandsService;
    private final ApUserService apUserService;

    public ApEncounterVaccinationService(
            ApVaccineDoseService apVaccineDoseService,
            ApVaccineService apVaccineService,
            ApVaccineBrandsService apVaccineBrandsService,
            ApUserService apUserService
    ) {
        this.apVaccineDoseService = apVaccineDoseService;
        this.apVaccineService = apVaccineService;
        this.apVaccineBrandsService = apVaccineBrandsService;
        this.apUserService = apUserService;
    }

    public List<ApVaccine> getVaccinationRecords(String where, String lang) {
        List<ApVaccine> result = new ArrayList<>();
        if (where == null || where.isEmpty()) where = "1=1";

        String sql = """
        SELECT
            v.vaccine_id,
            COUNT(v.vaccine_dose_id) AS dose_count,
            string_agg(v.key || '-' || v.vaccine_dose_id || '-' || v.vaccine_brand_id, ',') AS dose_brand_pairs
        FROM ap_encounter_vaccination v
        WHERE """ + " " + where + " AND v.vaccine_dose_id IS NOT NULL " + """
        GROUP BY v.vaccine_id;
    """;


        try (Connection con = DS.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String vaccineId = rs.getString("vaccine_id");
                int doseCount = rs.getInt("dose_count");
                String doseBrandPairs = rs.getString("dose_brand_pairs");

                List<ApVaccineDose> doseDetailsList = new ArrayList<>();

                if (doseBrandPairs != null && !doseBrandPairs.isEmpty()) {
                    String[] doseBrandPairsArray = doseBrandPairs.split(",");

                    for (String doseBrandPair : doseBrandPairsArray) {
                        String[] pair = doseBrandPair.split("-");
                        if (pair.length != 3) {
                            log.warn("Skipping malformed pair: {}", doseBrandPair);
                            continue;
                        }

                        String vaccinationKey = pair[0]; // ap_encounter_vaccination.key
                        String doseId = pair[1];         // ap_encounter_vaccination.vaccine_dose_id
                        String brandId = pair[2];        // ap_encounter_vaccination.vaccine_brand_id

                        // ✅ Guard against null/empty strings
                        if (doseId == null || doseId.isBlank() || "null".equalsIgnoreCase(doseId)) {
                            log.warn("Skipping vaccination key={} because doseId is null/empty", vaccinationKey);
                            continue;
                        }

                        ApVaccineDose doseDetails = apVaccineDoseService.getRecord(doseId);

                        // ✅ dose record might not exist
                        if (doseDetails == null) {
                            log.warn("VaccineDose not found for dose_id={} (vaccination key={})", doseId, vaccinationKey);
                            continue;
                        }

                        // ✅ safe now
                        apVaccineDoseService.populateLovFields(doseDetails, lang);

                        // ✅ brand can be null or missing too
                        if (brandId != null && !brandId.isBlank() && !"null".equalsIgnoreCase(brandId)) {
                            ApVaccineBrands brand = apVaccineBrandsService.getRecord(brandId);
                            if (brand != null) {
                                apVaccineBrandsService.populateLovFields(brand, lang);
                                doseDetails.setApVaccineBrands(brand);
                            } else {
                                log.warn("VaccineBrand not found for brand_id={} (dose_id={})", brandId, doseId);
                            }
                        }

                        // ✅ link encounter vaccination record
                        ApEncounterVaccination encVac = getRecord(vaccinationKey);
                        doseDetails.setApEncounterVaccination(encVac);

                        if (encVac != null) {
                            // users might be null too; apUserService.getRecord(null) should ideally return null safely
                            encVac.setCreateByUser(apUserService.getRecord(encVac.getCreatedBy()));
                            encVac.setUpdateByUser(apUserService.getRecord(encVac.getUpdatedBy()));
                            encVac.setDeleteByUser(apUserService.getRecord(encVac.getDeletedBy()));
                            encVac.setReviewedByUser(apUserService.getRecord(encVac.getReviewedBy()));
                            populateLovFields(encVac, lang);
                        } else {
                            log.warn("ApEncounterVaccination not found for key={}", vaccinationKey);
                        }

                        doseDetailsList.add(doseDetails);
                    }
                }

                ApVaccine record = apVaccineService.getRecord(vaccineId);
                if (record != null) {
                    apVaccineService.populateLovFields(record, lang);
                    record.setDoseCount(doseCount);
                    record.setDoseDetailsList(doseDetailsList);
                    result.add(record);
                } else {
                    log.warn("Vaccine not found for vaccine_id={}", vaccineId);
                }
            }

        } catch (SQLException e) {
            log.error("Error executing vaccination query: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return result;
    }
}

