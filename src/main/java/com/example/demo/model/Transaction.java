package com.example.demo.model;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction
{
    @Id
    @Column(name ="id")
    private String id;
    @Column(name ="userId")
    private String userId;
    @Column(name ="name")
    private String name;
    @Column(name ="amount")
    private String amount;
    @Column(name ="ip")
    private String ip;
    @Column(name ="response")
    private String response;

    public Transaction() {
    }

    public Transaction(String id, String userId, String name, String amount, String ip) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.amount = amount;
        this.ip = ip;
    }

    public Transaction(String id, String userId, String name, String amount, String ip, String response) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.amount = amount;
        this.ip = ip;
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", ip='" + ip + '\'' +
                ", response='" + response + '\'' +
                '}';
    }
}
