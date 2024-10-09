package com.asklepios.backend_service.database;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.Scanner;

@Slf4j
public class DataSeeder {
    public static void main(String[] args) {
        URL resource = new TableGenerator().getClass().getClassLoader().getResource("seeders");
        File seederFolder = new File(resource.getFile());
        if (seederFolder.isDirectory()) {

            for (File file : seederFolder.listFiles(File::isFile)) {
                System.out.println(file.getName());
                try {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String query = scanner.nextLine();
                        try {
                            DS.executeQuery(query);
                        } catch (SQLException sqlException) {
                            sqlException.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
