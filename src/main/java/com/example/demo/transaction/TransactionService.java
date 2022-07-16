package com.example.demo.transaction;

import com.example.demo.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class TransactionService {
    private List<Transaction> transactionList;
    private static final String SEPARATOR = ",";
    private static final String TRANSACTION_PATH = "/Users/noaurbach/Desktop/springBoot/demo/template/transaction.csv";
    private static final String ACCESS_KEY = "74bc12c4ab72eb07fb3821368cefe9d0";

    public static void ipStackService(List<Transaction> transactionList) {
    }

    public List<Transaction> generateTransactionListFromFile(MultipartFile file) {
        transactionList = new ArrayList<>();
        try {
            file.transferTo(new File(TRANSACTION_PATH));
            Scanner scanner = new Scanner(new File(TRANSACTION_PATH));
            scanner.nextLine();
            scanner.useDelimiter("\n");
            while (scanner.hasNext()) {
                String[] values = scanner.next().split(",");
                Transaction transaction = new Transaction();
                transaction.setId(values[0]);
                transaction.setUserId(values[1]);
                transaction.setName(values[2]);
                transaction.setAmount(values[3]);
                transaction.setIp(values[4]);
                transactionList.add(transaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactionList;
    }

    public String ipstackService(String ip) throws Exception {

        String uri = "http://api.ipstack.com/" + ip + "?access_key=" + ACCESS_KEY;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();

    }

    public void writeToCsv(List<Transaction> transactionList) {
        try {
            String pathName = "/Users/noaurbach/Desktop/springBoot/demo/template/transactionInfo.csv";
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathName), "UTF-8"));
            for (Transaction transaction : transactionList) {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(transaction.getId());
                oneLine.append(SEPARATOR);
                oneLine.append(transaction.getUserId());
                oneLine.append(SEPARATOR);
                oneLine.append(transaction.getName());
                oneLine.append(SEPARATOR);
                oneLine.append(transaction.getAmount());
                oneLine.append(SEPARATOR);
                oneLine.append(transaction.getId());
                oneLine.append(SEPARATOR);
                oneLine.append(transaction.getResponse());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }
}
