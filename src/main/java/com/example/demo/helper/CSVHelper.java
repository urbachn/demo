package com.example.demo.helper;

import com.example.demo.model.Transaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "userId", "name", "amount","ip","response" };


    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
    public static List<Transaction> csvToTutorials(InputStream is) {
          try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Transaction> transactions = new ArrayList<Transaction>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                Transaction tutorial = new Transaction(
                //        Long.parseLong(csvRecord.get("id")),
                        csvRecord.get("id"),
                        csvRecord.get("userId"),
                        csvRecord.get("name"),
                        csvRecord.get("amount"),
                        csvRecord.get("ip")
                  //      Boolean.parseBoolean(csvRecord.get("Published"))
                );
                transactions.add(tutorial);
            }
            return transactions;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
