package com.asklepios.backend_service.controller;


import com.asklepios.backend_service.database.DS;
import com.asklepios.backend_service.model.generated.pojo.*;
import com.asklepios.backend_service.model.pojo.request.ListRequest;
import com.asklepios.backend_service.model.pojo.response.ParentResponse;
import com.asklepios.backend_service.service.*;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
//@CrossOrigin
@Slf4j
public class InventoryTransactionController  implements Serializable {

    private final ApInventoryTransactionService apInventoryTransactionService;
    private final ApInventoryTransactionAttachmentService apInventoryTransactionAttachmentService;
    private final ApInventoryTransactionProductService apInventoryTransactionProductService;
    private final ApWarehouseProductService apWarehouseProductService;
    private final ApWarehouseService apWarehouseService;

    public InventoryTransactionController(ApInventoryTransactionService apInventoryTransactionService, ApInventoryTransactionAttachmentService apInventoryTransactionAttachmentService, ApInventoryTransactionProductService apInventoryTransactionProductService, ApWarehouseProductService apWarehouseProductService, ApWarehouseService apWarehouseService) {
        this.apInventoryTransactionService = apInventoryTransactionService;
        this.apInventoryTransactionAttachmentService = apInventoryTransactionAttachmentService;
        this.apInventoryTransactionProductService = apInventoryTransactionProductService;
        this.apWarehouseProductService = apWarehouseProductService;
        this.apWarehouseService = apWarehouseService;
    }

    @GetMapping(value = "/inventory-transaction-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inventoryTransactionList(@RequestParam Map<String, String> queryParams,
                                                    @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                    @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                    @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                    @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApInventoryTransaction>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApInventoryTransaction> list = apInventoryTransactionService.getList( where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_inventory_transaction where " + whereForTotal);
            for (ApInventoryTransaction warehouseTransaction : list) {
                apInventoryTransactionService.populateLovFields(warehouseTransaction, lang);
                   }

            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/save-inventory-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveInventoryTransaction(@RequestBody ApInventoryTransaction inventoryTransaction,
                                                   @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                   @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                   @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                   @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransaction> response = new ParentResponse<>();
            apInventoryTransactionService.saveRecord(inventoryTransaction);
            response.setObject(inventoryTransaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-inventory-transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeInventoryTransaction(@RequestBody ApInventoryTransaction inventoryTransaction,
                                                     @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                     @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                     @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                     @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransaction> response = new ParentResponse<>();
            apInventoryTransactionService.deleteRecord(inventoryTransaction);
            response.setObject(inventoryTransaction);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/inventory-transaction-attachment-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inventoryTransactionAttachmentList(@RequestParam Map<String, String> queryParams,
                                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                      @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApInventoryTransactionAttachment>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApInventoryTransactionAttachment> list = apInventoryTransactionAttachmentService.getList( where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_inventory_transaction_attachment where " + whereForTotal);
            for (ApInventoryTransactionAttachment warehouseTransactionAttachment : list) {
                apInventoryTransactionAttachmentService.populateLovFields(warehouseTransactionAttachment, lang);
            }

            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/save-inventory-transaction-attachment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveInventoryTransactionAttachment(@RequestBody ApInventoryTransactionAttachment inventoryTransactionAttachment,
                                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                      @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransactionAttachment> response = new ParentResponse<>();
            apInventoryTransactionAttachmentService.saveRecord(inventoryTransactionAttachment);
            response.setObject(inventoryTransactionAttachment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-inventory-transaction-attachment", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeInventoryTransactionAttachment(@RequestBody ApInventoryTransactionAttachment inventoryTransactionAttachment,
                                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransactionAttachment> response = new ParentResponse<>();
            apInventoryTransactionAttachmentService.deleteRecord(inventoryTransactionAttachment);
            response.setObject(inventoryTransactionAttachment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/attachment-bykey", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAttachmentByKey( @RequestHeader("Key") String Key,
                                                 @Nullable @RequestHeader String facility_id,
                                                 @Nullable @RequestHeader String access_token,
                                                 @Nullable @RequestHeader Integer access_level,
                                                 @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransactionAttachment> response = new ParentResponse<>();
            ApInventoryTransactionAttachment attachment = apInventoryTransactionAttachmentService.getRecord(Key);
            System.out.println("Key "+ Key);
            if (attachment != null) {
                response.setObject(attachment);
            } else {
                response.setObject(new ApInventoryTransactionAttachment());
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    @GetMapping(value = "/inventory-transaction-product-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inventoryTransactionProductList(@RequestParam Map<String, String> queryParams,
                                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                      @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApInventoryTransactionProduct>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApInventoryTransactionProduct> list = apInventoryTransactionProductService.getList( where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_inventory_transaction_product where " + whereForTotal);
            for (ApInventoryTransactionProduct product : list) {
                apInventoryTransactionProductService.populateLovFields(product, lang);
                product.setProductObj(apWarehouseProductService.getProduct(product.getProductKey()));
                product.setTransactionObj(apInventoryTransactionService.getRecord(product.getInventoryTransKey()));
                if(product.getTransactionObj() != null )
                product.setWarehouseObj(apWarehouseService.getRecord(product.getTransactionObj().getWarehouseKey()));
            }

            response.setObject(list);
            response.setExtraNumeric(totalRecord);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @PostMapping(value = "/save-inventory-transaction-product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveInventoryTransactionProduct(@RequestBody ApInventoryTransactionProduct inventoryTransactionProduct,
                                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                      @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransactionProduct> response = new ParentResponse<>();
            apInventoryTransactionProductService.saveRecord(inventoryTransactionProduct);
            response.setObject(inventoryTransactionProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-inventory-transaction-Product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeInventoryTransactionProduct(@RequestBody ApInventoryTransactionProduct inventoryTransactionProduct,
                                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                        @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransactionProduct> response = new ParentResponse<>();
            apInventoryTransactionProductService.deleteRecord(inventoryTransactionProduct);
            response.setObject(inventoryTransactionProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/confirm-trans-product-stock-in", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmTransProductStockIn(@RequestHeader("Key") String Key,
                                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                             @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                             @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransaction> response = new ParentResponse<>();
            apInventoryTransactionProductService.confirmProductTransactionOnWarehouse(Key);
            response.setObject(apInventoryTransactionService.getRecord(Key));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


}
