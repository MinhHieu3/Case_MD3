package org.example.case_md3.model;

public class TypeProduct {
    private String name;
    private String producer;
    private String describe;

    public TypeProduct(String name, String producer, String describe) {
        this.name = name;
        this.producer = producer;
        this.describe = describe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
