/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vku.Model;


public class MoneyTransfer {
    private int id;
    private int userId;
    private String username;
    private String password;
    private String accountNo;
    private String accountType;
    private String gender;
    private String address;
    private double amount;

    public MoneyTransfer() {
    }

    public MoneyTransfer(int id, int userId, String username, String password, String accountNo, String accountType, String gender, String address, double amount) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.gender = gender;
        this.address = address;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MoneyTransfer{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", accountType='" + accountType + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", amount=" + amount +
                '}';
    }
}
