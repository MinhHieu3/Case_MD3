package org.example.case_md3.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private User idUser;
    private double total;
    private String time;

    public Order() {
    }

    public Order( User idUser, double total, String time) {
        this.idUser = idUser;
        this.total = total;
        this.time = time;
    }

    public Order(int id, User idUser, double total, String time) {
        this.id = id;
        this.idUser = idUser;
        this.total = total;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
