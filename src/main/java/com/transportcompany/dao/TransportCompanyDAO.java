package com.transportcompany.dao;

import com.transportcompany.model.TransportCompany;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransportCompanyDAO {

    public void addCompany(TransportCompany company) {
        String query = "INSERT INTO transport_companies (name, revenue) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, company.getName());
            stmt.setDouble(2, company.getRevenue());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TransportCompany getCompanyById(int id) {
        String query = "SELECT * FROM transport_companies WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                TransportCompany company = new TransportCompany();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setRevenue(rs.getDouble("revenue"));
                return company;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TransportCompany> getAllCompanies() {
        List<TransportCompany> companies = new ArrayList<>();
        String query = "SELECT * FROM transport_companies";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                TransportCompany company = new TransportCompany();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setRevenue(rs.getDouble("revenue"));
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    public void updateCompany(TransportCompany company) {
        String query = "UPDATE transport_companies SET name = ?, revenue = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, company.getName());
            stmt.setDouble(2, company.getRevenue());
            stmt.setInt(3, company.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCompany(int id) {
        String query = "DELETE FROM transport_companies WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
