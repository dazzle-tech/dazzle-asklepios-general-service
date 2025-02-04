package com.asklepios.backend_service.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class KeyGenerator {
    public static void main(String[] args) {
        String csvFile = "C:/Users/User/Notes/R&D/Book.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write("key\n");
            for (int i = 0; i < 217; i++) {
                long key = System.nanoTime(); // Generate unique key
                writer.write(key + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}