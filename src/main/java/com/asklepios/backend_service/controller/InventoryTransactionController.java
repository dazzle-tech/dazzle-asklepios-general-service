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
    private final ApInventoryTransferService apInventoryTransferService;
    private final ApInventoryTransferProductService apInventoryTransferProductService;
    private final ApProductsService apProductsService;

    public InventoryTransactionController(ApInventoryTransactionService apInventoryTransactionService, ApInventoryTransactionAttachmentService apInventoryTransactionAttachmentService, ApInventoryTransactionProductService apInventoryTransactionProductService, ApWarehouseProductService apWarehouseProductService, ApWarehouseService apWarehouseService, ApInventoryTransferService apInventoryTransferService, ApInventoryTransferProductService apInventoryTransferProductService, ApProductsService apProductsService) {
        this.apInventoryTransactionService = apInventoryTransactionService;
        this.apInventoryTransactionAttachmentService = apInventoryTransactionAttachmentService;
        this.apInventoryTransactionProductService = apInventoryTransactionProductService;
        this.apWarehouseProductService = apWarehouseProductService;
        this.apWarehouseService = apWarehouseService;
        this.apInventoryTransferService = apInventoryTransferService;
        this.apInventoryTransferProductService = apInventoryTransferProductService;
        this.apProductsService = apProductsService;
    }

    @GetMapping(value = "/inventory-transaction-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inventoryTransactionList(@RequestParam Map<String, String> queryParams,
                                                    @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                    // @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                                   // @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                                     // @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                                      // @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                                      // @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                                        // @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                                      // @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                apProductsService.populateLovFields(product.getProductObj(), lang);
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
                                                      // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransactionProduct> response = new ParentResponse<>();
            inventoryTransactionProduct.setStatusLkey("5959341154465084"); //Requested status //TODO Convert LOV key to code
            apInventoryTransactionProductService.saveRecord(inventoryTransactionProduct);
            response.setObject(inventoryTransactionProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-inventory-transaction-product-list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveInventoryTransactionPtroductList(@RequestBody List<ApInventoryTransactionProduct> request,
                                               @Nullable @RequestHeader String facility_id,
                                               @Nullable @RequestHeader String access_token,
                                               @Nullable @RequestHeader Integer access_level,
                                               @Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApInventoryTransactionProduct>> response = new ParentResponse<>();
            String inv_key= null ;
            for (ApInventoryTransactionProduct p : request) {
                inv_key = p.getInventoryTransKey();
                if (p.getKey() == null ) {
                    p.setCreatedBy("Administrator"); // TODO change to actual user from access token
                    p.setStatusLkey("5959341154465084"); //Requested status //TODO Convert LOV key to code
                    ApProducts apProduct = apProductsService.getRecord(p.getProductKey());
                    String uomGroup= apProduct.getUomGroupKey();
                    p.setNewQuentityBaseUom( apInventoryTransactionProductService.convert( p.getNewQuentity() , p.getTransUomKey() , apProduct.getBaseUomKey() ,uomGroup));

                    apInventoryTransactionProductService.saveRecord(p);
                } else if (p.getKey() != null ) {
                        p.setUpdatedBy("Administrator"); // TODO change to actual user from access token
                        p.setStatusLkey("5959341154465084"); //Requested status //TODO Convert LOV key to code
                    ApProducts apProduct = apProductsService.getRecord(p.getProductKey());
                    String uomGroup= apProduct.getUomGroupKey();
                    p.setNewQuentityBaseUom( apInventoryTransactionProductService.convert( p.getNewQuentity() , p.getTransUomKey() , apProduct.getBaseUomKey() ,uomGroup));
                    apInventoryTransactionProductService.saveRecord(p);
                    }

            }

            List<ApInventoryTransactionProduct> updatedList = apInventoryTransactionProductService.getList("inventory_trans_key = '" + inv_key + "' " +
                    " and deleted_at is null order by created_at desc");
            response.setObject(updatedList);
            response.setMsg("Products save successfully");
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
                                                        // @jakarta.annotation.Nullable @RequestHeader String access_token,
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
                                                             // @jakarta.annotation.Nullable @RequestHeader String access_token,
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

    @PostMapping(value = "/confirm-trans-product-stock-out", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmTransProductStockOut(@RequestHeader("Key") String Key,
                                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                        // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransaction> response = new ParentResponse<>();
            boolean isSave = apInventoryTransactionProductService.confirmProductTransactionOutWarehouse(Key);
            if(!isSave){
                response.setMsg("Transaction quantity exceeds available stock.");
            }else{
                response.setMsg("Transaction successfully confirmed.");
            }
            response.setObject(apInventoryTransactionService.getRecord(Key));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/inventory-transfer-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inventoryTransferList(@RequestParam Map<String, String> queryParams,
                                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                      // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApInventoryTransfer>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApInventoryTransfer> list = apInventoryTransferService.getList( where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_inventory_transfer where " + whereForTotal);
            for (ApInventoryTransfer transfer : list) {
                apInventoryTransferService.populateLovFields(transfer, lang);
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


    @PostMapping(value = "/save-inventory-transfer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveInventoryTransfer(@RequestBody ApInventoryTransfer inventoryTransfer,
                                                      @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                      // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                      @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                      @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransfer> response = new ParentResponse<>();
            apInventoryTransferService.saveRecord(inventoryTransfer);
            response.setObject(inventoryTransfer);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-inventory-transfer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeInventoryTransfer(@RequestBody ApInventoryTransfer inventoryTransfer,
                                                        @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                        // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                        @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                        @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransfer> response = new ParentResponse<>();
            apInventoryTransferService.deleteRecord(inventoryTransfer);
            response.setObject(inventoryTransfer);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


    @GetMapping(value = "/inventory-transfer-product-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> inventoryTransferProductList(@RequestParam Map<String, String> queryParams,
                                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                             // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                             @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<List<ApInventoryTransferProduct>> response = new ParentResponse<>();
            if (queryParams.containsKey("ignore") && queryParams.get("ignore").equals("true")) {
                response.setObject(new ArrayList<>());
                return ResponseEntity.ok(response);
            }
            ListRequest listRequest = new ListRequest(queryParams);
            String where = listRequest.buildWhereStatement();
            String whereForTotal = listRequest.buildWhereStatement(true, false, false, false);
            List<ApInventoryTransferProduct> list = apInventoryTransferProductService.getList( where);
            BigDecimal totalRecord = DS.executeDecimalResultQuery("select count(0) from ap_inventory_transfer_product where " + whereForTotal);
            for (ApInventoryTransferProduct product : list) {
                apInventoryTransferProductService.populateLovFields(product, lang);
                product.setProductObj(apWarehouseProductService.getProduct(product.getProductKey()));
                product.setTransferObj(apInventoryTransferService.getRecord(product.getTransferKey()));
                if(product.getTransferObj() != null )
                    product.setFromWarehouseObj(apWarehouseService.getRecord(product.getTransferObj().getFromWarehouseKey()));
                    product.setToWarehouseObj(apWarehouseService.getRecord(product.getTransferObj().getToWarehouseKey()));

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


    @PostMapping(value = "/save-inventory-transfer-product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveInventoryTransferProduct(@RequestBody ApInventoryTransferProduct inventoryTransferProduct,
                                                             @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                             // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                             @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                             @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransferProduct> response = new ParentResponse<>();
            ApProducts product =apProductsService.getRecord(inventoryTransferProduct.getProductKey());
            BigDecimal QtyBaseUOM = apInventoryTransactionProductService.convert(inventoryTransferProduct.getQuentityApproved(),  inventoryTransferProduct.getTransUomKey(), product.getBaseUomKey(),  product.getUomGroupKey());
            inventoryTransferProduct.setQuentityRequestedBaseUom(QtyBaseUOM);
            apInventoryTransferProductService.saveRecord(inventoryTransferProduct);
            response.setObject(inventoryTransferProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-inventory-transfer-product-approved", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveApprovedTransferProduct(@RequestBody List<ApInventoryTransferProduct> transferProducts,
                                                       @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                       // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                       @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                       @jakarta.annotation.Nullable @RequestHeader String lang) {

        try {
            ParentResponse<List<ApInventoryTransferProduct>> response = new ParentResponse<>();
            for (ApInventoryTransferProduct rec : transferProducts) {
                if(!rec.getIsEffectedWarehouse()){
                   ApInventoryTransferProduct record = apInventoryTransferProductService.getRecord(rec.getKey());

                    record.setStatusLkey("1804566422622516");//Accepted status
                    apInventoryTransferProductService.confirmProductTransferInWarehouse(record);
                    apInventoryTransferProductService.confirmProductTransferOutWarehouse(record);
                    record.setIsEffectedWarehouse(true);
                    apInventoryTransferProductService.saveRecord(record);
                }

            }
            response.setObject(transferProducts);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/save-inventory-transfer-product-rejected", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRejectedTransferProduct(@RequestBody List<ApInventoryTransferProduct> transferProducts,
                                                         @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                         // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                         @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                         @jakarta.annotation.Nullable @RequestHeader String lang) {

        try {
            ParentResponse<List<ApInventoryTransferProduct>> response = new ParentResponse<>();
            for (ApInventoryTransferProduct rec : transferProducts) {
                if(!rec.getIsEffectedWarehouse()){
                    ApInventoryTransferProduct record = apInventoryTransferProductService.getRecord(rec.getKey());
                    record.setStatusLkey("1804533730103990");//Rejected status
                    record.setIsEffectedWarehouse(true);
                    apInventoryTransferProductService.saveRecord(record);
                }

            }
            response.setObject(transferProducts);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @PostMapping(value = "/remove-inventory-transfer-Product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeInventoryTransferProduct(@RequestBody ApInventoryTransferProduct inventoryTransferProduct,
                                                               @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                                               // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                                               @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                                               @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<ApInventoryTransferProduct> response = new ParentResponse<>();
            apInventoryTransferProductService.deleteRecord(inventoryTransferProduct);
            response.setObject(inventoryTransferProduct);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }

    @GetMapping(value = "/qty_in_base_uom", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> QuantityInBaseUom(@RequestParam BigDecimal quantity,
                                               @RequestParam String transUnit,
                                               @RequestParam String toBaseUnit,
                                               @RequestParam String uomGroup,
                                               @jakarta.annotation.Nullable @RequestHeader String facility_id,
                                               // @jakarta.annotation.Nullable @RequestHeader String access_token,
                                               @jakarta.annotation.Nullable @RequestHeader Integer access_level,
                                               @jakarta.annotation.Nullable @RequestHeader String lang) {
        try {
            ParentResponse<BigDecimal> response = new ParentResponse<>();

            if (quantity == null || quantity.compareTo(BigDecimal.ZERO) == 0 || transUnit == null || toBaseUnit == null || uomGroup == null) {
                response.setObject(BigDecimal.ZERO);
                return ResponseEntity.ok(response);
            }


            BigDecimal QtyBaseUOM = apInventoryTransactionProductService.convert(quantity,  transUnit,  toBaseUnit,  uomGroup); // example number
            response.setObject(QtyBaseUOM);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.status(500).body(e);
        }
    }


}
