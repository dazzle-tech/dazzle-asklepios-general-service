package com.asklepios.backend_service.database;

import com.asklepios.backend_service.database.pojo.GtColumn;
import com.asklepios.backend_service.database.pojo.GtTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class TableGenerator {

    public static final String TablePrefix = "ap_";

    public static void main(String[] args) {


        URL resource = new TableGenerator().getClass().getClassLoader().getResource("db-model");
        File dbModelFolder = new File(resource.getFile());
        if (dbModelFolder.isDirectory()) {

            boolean tableExists = false;
            for (File file : dbModelFolder.listFiles(File::isFile)) {
                if (file.getName().equals("example.json"))
                    continue;


                if (   !file.getName().contains("79-"))
                    continue;

                try {
                    String actual = Files.readString(file.toPath());

                    GtTable table = new ObjectMapper().readValue(actual, GtTable.class);

                    String tableName = TablePrefix.concat(table.getTableName());

                    try (Connection innConn = DS.getConnection();
                         Statement innStat = innConn.createStatement()) {
                        innStat.executeQuery("select * from " + tableName + " limit 1");
                        tableExists = true;
                    } catch (SQLException ex) {
                        tableExists = false;
                    }

                    if (!tableExists) {
                        DS.executeQuery("create table ".concat(tableName).concat(" ( key text primary key )"));
                    }

                    for (GtColumn column : table.getColumns()) {
                        String query = "alter table "
                                .concat(tableName)
                                .concat(" add column if not exists ")
                                .concat(column.getName())
                                .concat(" ")
                                .concat(column.getType());

                        DS.executeQuery(query);

                        if (column.getReferences() != null) {
                            /* drop constraint if exists */
                            query = "alter table "
                                    .concat(tableName)
                                    .concat(" drop constraint if exists fk_")
                                    .concat(table.getTableName())
                                    .concat("_")
                                    .concat(column.getReferences());
                            DS.executeQuery(query);

                            /* create constraint */
                            query = "alter table "
                                    .concat(tableName)
                                    .concat(" add constraint fk_")
                                    .concat(table.getTableName())
                                    .concat("_")
                                    .concat(column.getReferences())
                                    .concat(" foreign key(").concat(column.getName()).concat(")").concat(" references ")
                                    .concat(tableName).concat("(key)")
                                    .concat(" on delete no action on update no action");
                            DS.executeQuery(query);

                        }
                    }

                    DS.executeQuery("alter table " + TablePrefix.concat(table.getTableName()) + " add column if not exists created_by text");
                    DS.executeQuery("alter table " + TablePrefix.concat(table.getTableName()) + " add column if not exists updated_by text");
                    DS.executeQuery("alter table " + TablePrefix.concat(table.getTableName()) + " add column if not exists deleted_by text");
                    DS.executeQuery("alter table " + TablePrefix.concat(table.getTableName()) + " add column if not exists created_at decimal(16)");
                    DS.executeQuery("alter table " + TablePrefix.concat(table.getTableName()) + " add column if not exists updated_at decimal(16)");
                    DS.executeQuery("alter table " + TablePrefix.concat(table.getTableName()) + " add column if not exists deleted_at decimal(16)");
                    DS.executeQuery("alter table " + TablePrefix.concat(table.getTableName()) + " add column if not exists is_valid boolean");

                } catch (Exception ex) {
                    log.error("Error", ex);
                    System.exit(500);
                }
            }
        }
    }

}
