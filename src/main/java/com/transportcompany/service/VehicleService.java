package com.transportcompany.service;

import com.transportcompany.dao.VehicleDAO;
import com.transportcompany.model.Vehicle;

import java.util.List;

public class VehicleService {
    private VehicleDAO vehicleDAO;

    public VehicleService() {
        this.vehicleDAO = new VehicleDAO();
    }

    public void addVehicle(Vehicle vehicle) {
        if (vehicle.isValid()) {
            vehicleDAO.addVehicle(vehicle);
            System.out.println("Превозното средство е добавено успешно.");
        } else {
            System.out.println("Грешка при добавянето на превозното средство.");
        }
    }

    public Vehicle getVehicleById(int id) {
        return vehicleDAO.getVehicleById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDAO.getAllVehicles();
    }

    public void updateVehicle(Vehicle vehicle) {
        if (vehicle.isValid()) {
            vehicleDAO.updateVehicle(vehicle);
            System.out.println("Превозното средство е актуализирано успешно.");
        } else {
            System.out.println("Грешка при актуализирането на превозното средство.");
        }
    }

    public void deleteVehicle(int id) {
        vehicleDAO.deleteVehicle(id);
        System.out.println("Превозното средство е изтрито успешно.");
    }
}
