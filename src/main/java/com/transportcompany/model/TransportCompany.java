package com.transportcompany.model;

import java.util.ArrayList;
import java.util.List;

public class TransportCompany {
    private int id;
    private String name;
    private double revenue;
    private List<Employee> employees;
    private List<Vehicle> vehicles;
    private List<Client> clients;

    public TransportCompany() {
        employees = new ArrayList<>();
        vehicles = new ArrayList<>();
        clients = new ArrayList<>();
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

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        this.clients.add(client);
        client.setCompany(this);
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
        vehicle.setCompany(this);
    }

    public boolean isValid() {
        if (name == null || name.isEmpty()) {
            System.out.println("Името на компанията не може да бъде празно.");
            return false;
        }
        if (revenue < 0) {
            System.out.println("Приходите на компанията не могат да бъдат отрицателни.");
            return false;
        }
        return true;
    }
}
