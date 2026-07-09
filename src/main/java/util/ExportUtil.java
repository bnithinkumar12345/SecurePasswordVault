package com.nithin.util;

import com.nithin.model.Credential;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportUtil {

    public static boolean exportToCSV(List<Credential> credentials) {

        try {

            FileWriter writer = new FileWriter("credentials.csv");

            writer.append("Website,Username,Password,Notes\n");

            for (Credential credential : credentials) {

                writer.append(credential.getWebsite()).append(",");
                writer.append(credential.getWebsiteUsername()).append(",");
                writer.append(credential.getPassword()).append(",");
                writer.append(credential.getNotes()).append("\n");

            }

            writer.flush();
            writer.close();

            return true;

        } catch (IOException e) {

            e.printStackTrace();
        }

        return false;
    }
}