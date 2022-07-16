package com.example.demo.transaction;

import com.example.demo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping(path = "")
public class TransactionController {

    private final TransactionService transactionService;
    private static final String TRANSACTION_PATH = "/Users/noaurbach/Desktop/springBoot/demo/template/transactionInfo.csv";

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/fraud/transaction")
    public void postFraudTransaction(@RequestParam("file") MultipartFile file) throws Exception {
        List<Transaction> transactionList = transactionService.generateTransactionListFromFile(file);
        enrichTransactionsWithIpData(transactionList);
        File f = new File(TRANSACTION_PATH);
        transactionService.writeToCsv(transactionList);
    }

    private void enrichTransactionsWithIpData(List<Transaction> transactionList) {
        transactionList.forEach(transaction -> {
            try {
                transaction.setResponse(transactionService.ipstackService(transaction.getIp()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @GetMapping("/fraud/transaction")
    public String getFraudTransactions() throws IOException {
        Path filePath = Path.of(TRANSACTION_PATH);
        return Files.readString(filePath);
    }


}
