package com.transportcompany.model;

import java.util.Date;

public class Transport {
    private int id;
    private String startPoint;
    private String endPoint;
    private Date departureDate;
    private Date arrivalDate;
    private double price;
    private TransportCompany company;
    private Client client;
    private Vehicle vehicle;
    private Employee driver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TransportCompany getCompany() {
        return company;
    }

    public void setCompany(TransportCompany company) {
        this.company = company;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Employee getDriver() {
        return driver;
    }

    public void setDriver(Employee driver) {
        this.driver = driver;
    }

    public boolean isValid() {
        if (startPoint == null || startPoint.isEmpty()) {
            System.out.println("Началната точка не може да бъде празна.");
            return false;
        }
        if (endPoint == null || endPoint.isEmpty()) {
            System.out.println("Крайната точка не може да бъде празна.");
            return false;
        }
        if (price <= 0) {
            System.out.println("Цената трябва да бъде положителна.");
            return false;
        }
        if (departureDate == null || arrivalDate == null) {
            System.out.println("Датите на тръгване и пристигане не могат да бъдат празни.");
            return false;
        }
        if (arrivalDate.before(departureDate)) {
            System.out.println("Датата на пристигане не може да бъде преди датата на тръгване.");
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Transport {" +
                "ID = " + id +
                ", Start Point = '" + startPoint + '\'' +
                ", End Point = '" + endPoint + '\'' +
                ", Departure Date = " + departureDate +
                ", Arrival Date = " + arrivalDate +
                ", Price = " + price +
                ", Company = " + (company != null ? company.getName() : "N/A") +
                ", Client = " + (client != null ? client.getName() : "N/A") +
                ", Vehicle = " + (vehicle != null ? vehicle.getType() : "N/A") +
                ", Driver = " + (driver != null ? driver.getName() : "N/A") +
                '}';
    }
}
