package com.transportcompany.dao;

import com.transportcompany.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportDAO {

    public void addTransport(Transport transport) {
        String query = "INSERT INTO transports (start_point, end_point, departure_date, arrival_date, price, company_id, client_id, vehicle_id, driver_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, transport.getStartPoint());
            stmt.setString(2, transport.getEndPoint());
            stmt.setDate(3, new Date(transport.getDepartureDate().getTime()));
            stmt.setDate(4, new Date(transport.getArrivalDate().getTime()));
            stmt.setDouble(5, transport.getPrice());
            stmt.setInt(6, transport.getCompany().getId());
            stmt.setInt(7, transport.getClient().getId());
            stmt.setInt(8, transport.getVehicle().getId());
            stmt.setInt(9, transport.getDriver().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transport getTransportById(int id) {
        String query = "SELECT * FROM transports WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Transport transport = new Transport();
                transport.setId(rs.getInt("id"));
                transport.setStartPoint(rs.getString("start_point"));
                transport.setEndPoint(rs.getString("end_point"));
                transport.setDepartureDate(rs.getDate("departure_date"));
                transport.setArrivalDate(rs.getDate("arrival_date"));
                transport.setPrice(rs.getDouble("price"));

                int companyId = rs.getInt("company_id");
                TransportCompany company = new TransportCompanyDAO().getCompanyById(companyId);
                transport.setCompany(company);

                int driverId = rs.getInt("driver_id");
                Employee driver = new EmployeeDAO().getEmployeeById(driverId);
                transport.setDriver(driver);

                int clientId = rs.getInt("client_id");
                Client client = new ClientDAO().getClientById(clientId);
                transport.setClient(client);

                int vehicleId = rs.getInt("vehicle_id");
                Vehicle vehicle = new VehicleDAO().getVehicleById(vehicleId);
                transport.setVehicle(vehicle);

                return transport;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Transport> getAllTransports() {
        List<Transport> transports = new ArrayList<>();
        String query = "SELECT * FROM transports";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transport transport = new Transport();
                transport.setId(rs.getInt("id"));
                transport.setStartPoint(rs.getString("start_point"));
                transport.setEndPoint(rs.getString("end_point"));
                transport.setDepartureDate(rs.getDate("departure_date"));
                transport.setArrivalDate(rs.getDate("arrival_date"));
                transport.setPrice(rs.getDouble("price"));

                int companyId = rs.getInt("company_id");
                TransportCompany company = new TransportCompanyDAO().getCompanyById(companyId);
                transport.setCompany(company);

                int driverId = rs.getInt("driver_id");
                Employee driver = new EmployeeDAO().getEmployeeById(driverId);
                transport.setDriver(driver);

                int clientId = rs.getInt("client_id");
                Client client = new ClientDAO().getClientById(clientId);
                transport.setClient(client);

                int vehicleId = rs.getInt("vehicle_id");
                Vehicle vehicle = new VehicleDAO().getVehicleById(vehicleId);
                transport.setVehicle(vehicle);

                transports.add(transport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transports;
    }


    public void updateTransport(Transport transport) {
        String query = "UPDATE transports SET start_point = ?, end_point = ?, departure_date = ?, arrival_date = ?, price = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, transport.getStartPoint());
            stmt.setString(2, transport.getEndPoint());
            stmt.setDate(3, new Date(transport.getDepartureDate().getTime()));
            stmt.setDate(4, new Date(transport.getArrivalDate().getTime()));
            stmt.setDouble(5, transport.getPrice());
            stmt.setInt(6, transport.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTransport(int id) {
        String query = "DELETE FROM transports WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
