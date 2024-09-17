package com.transportcompany.model;

import com.transportcompany.enums.Qualification;

public class Employee {
    private int id;
    private String name;
    private Qualification qualification;
    private double salary;
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

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public void setCompany(TransportCompany company) {
        this.company = company;
    }

    public boolean isValid() {
        if (name == null || name.isEmpty()) {
            System.out.println("Името на служителя не може да бъде празно.");
            return false;
        }
        if (salary < 0) {
            System.out.println("Заплатата не може да бъде отрицателна.");
            return false;
        }
        if (qualification == null) {
            System.out.println("Квалификацията на служителя е задължителна.");
            return false;
        }
        return true;
    }
}
