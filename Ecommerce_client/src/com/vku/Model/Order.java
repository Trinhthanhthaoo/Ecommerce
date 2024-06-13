package com.vku.Model;

import java.sql.Date;

public class Order {
    private int orderId;
    private int userID;
    private Date orderDate;
    private double totalAmount;

    // Constructors
    public Order() {
    }

    public Order(int orderId, int userID, Date orderDate, double totalAmount) {
        this.orderId = orderId;
        this.userID = userID;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
    public Order(int userID, Date orderDate, double totalAmount) {
    this.userID = userID;
    this.orderDate = orderDate;
    this.totalAmount = totalAmount;
}


    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
