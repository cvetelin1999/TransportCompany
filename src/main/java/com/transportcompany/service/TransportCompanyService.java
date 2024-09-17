package com.transportcompany.service;

import com.transportcompany.dao.TransportCompanyDAO;
import com.transportcompany.model.TransportCompany;

import java.util.List;

public class TransportCompanyService {
    private TransportCompanyDAO transportCompanyDAO;

    public TransportCompanyService() {
        this.transportCompanyDAO = new TransportCompanyDAO();
    }

    public void addCompany(TransportCompany company) {
        if (company.isValid()) {
            transportCompanyDAO.addCompany(company);
            System.out.println("Транспортната компания е добавена успешно.");
        } else {
            System.out.println("Грешка при добавянето на компанията.");
        }
    }

    public TransportCompany getCompanyById(int id) {
        return transportCompanyDAO.getCompanyById(id);
    }

    public List<TransportCompany> getAllCompanies() {
        return transportCompanyDAO.getAllCompanies();
    }

    public void updateCompany(TransportCompany company) {
        if (company.isValid()) {
            transportCompanyDAO.updateCompany(company);
            System.out.println("Транспортната компания е актуализирана успешно.");
        } else {
            System.out.println("Грешка при актуализирането на компанията.");
        }
    }

    public void deleteCompany(int id) {
        transportCompanyDAO.deleteCompany(id);
        System.out.println("Транспортната компания е изтрита успешно.");
    }
}
