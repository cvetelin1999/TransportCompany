package com.transportcompany.model;

import com.transportcompany.enums.VehicleType;

public class Vehicle {
    private int id;
    private VehicleType type;
    private double capacity;
    private TransportCompany company;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public void setCompany(TransportCompany company) {
        this.company = company;
    }

    public boolean isValid() {
        if (capacity <= 0) {
            System.out.println("Капацитетът на превозното средство трябва да бъде положителен.");
            return false;
        }
        if (type == null) {
            System.out.println("Типът на превозното средство е задължителен.");
            return false;
        }
        return true;
    }
}
