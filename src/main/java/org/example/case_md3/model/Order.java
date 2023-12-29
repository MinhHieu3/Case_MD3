package org.example.case_md3.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private User idUser;
    private double total;
    private LocalDateTime time;

    public Order() {
    }

    public Order(int id, User idUser, double total, LocalDateTime time) {
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
