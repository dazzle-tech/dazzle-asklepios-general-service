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
 private final  ApUserService apUserService;
    public ApEncounterVaccinationService(ApVaccineDoseService apVaccineDoseService, ApVaccineService apVaccineService, ApVaccineBrandsService apVaccineBrandsService, ApUserService apUserService) {
        this.apVaccineDoseService = apVaccineDoseService;
        this.apVaccineService = apVaccineService;
        this.apVaccineBrandsService = apVaccineBrandsService;

        this.apUserService = apUserService;
    }

    public List<ApVaccine> getVaccinationRecords(String where ,String lang) {
        List<ApVaccine> result = new ArrayList<>();
        if (where == null || where.isEmpty())
            where = "1=1";
        String sql = """
    SELECT
        v.vaccine_key,
        COUNT(v.vaccine_dose_key) AS dose_count,
        string_agg( v.key || '-' || v.vaccine_dose_key || '-' || v.vaccine_brand_key , ',') AS dose_brand_pairs
    FROM ap_encounter_vaccination v
    WHERE """+" " + where +" "+ """
    GROUP BY v.vaccine_key;
""";


        try (Connection con = DS.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String vaccineKey = rs.getString("vaccine_key");
                int doseCount = rs.getInt("dose_count");
                String doseBrandPairs = rs.getString("dose_brand_pairs");
                String[] doseBrandPairsArray = doseBrandPairs.split(",");

                List<ApVaccineDose> doseDetailsList = new ArrayList<>();
                for (String doseBrandPair : doseBrandPairsArray) {
                    String[] pair = doseBrandPair.split("-");
                    String key = pair[0];
                    String doseKey = pair[1];
                    String brandKey = pair[2];

                    ApVaccineDose doseDetails = apVaccineDoseService.getRecord(doseKey);
                    apVaccineDoseService.populateLovFields(doseDetails, lang);
                    doseDetails.setApVaccineBrands(apVaccineBrandsService.getRecord(brandKey));
                    apVaccineBrandsService.populateLovFields(doseDetails.getApVaccineBrands(),lang);
                    doseDetails.setApEncounterVaccination(getRecord(key));
                    doseDetails.getApEncounterVaccination().setCreateByUser(apUserService.getRecord(doseDetails.getApEncounterVaccination().getCreatedBy()));
                    doseDetails.getApEncounterVaccination().setUpdateByUser(apUserService.getRecord(doseDetails.getApEncounterVaccination().getUpdatedBy()));
                    doseDetails.getApEncounterVaccination().setDeleteByUser(apUserService.getRecord(doseDetails.getApEncounterVaccination().getDeletedBy()));
                    doseDetails.getApEncounterVaccination().setReviewedByUser(apUserService.getRecord(doseDetails.getApEncounterVaccination().getReviewedBy()));
                    populateLovFields(doseDetails.getApEncounterVaccination(), lang);
                    doseDetailsList.add(doseDetails);
                }
                ApVaccine record = apVaccineService.getRecord(vaccineKey);
                apVaccineService.populateLovFields(record, lang);
                record.setDoseCount(doseCount);
                record.setDoseDetailsList(doseDetailsList);
                result.add(record);
            }

        } catch (SQLException e) {
            log.error("Error executing vaccination query: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }

        return result;
    }


}

