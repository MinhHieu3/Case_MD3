package org.example.case_md3.model;

public class Admin {
    private int id;
    private String name;
    private String pass;

    public Admin(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public Admin() {

    }

    public Admin(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
