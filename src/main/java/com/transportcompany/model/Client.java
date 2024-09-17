package com.transportcompany.model;

public class Client {
    private int id;
    private String name;
    private boolean isPaid;
    private TransportCompany company;

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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public void setCompany(TransportCompany company) {
        this.company = company;
    }

    public boolean isValid() {
        if (name == null || name.isEmpty()) {
            System.out.println("Името на клиента не може да бъде празно.");
            return false;
        }
        return true;
    }
}
