package com.transportcompany.service;

import com.transportcompany.dao.TransportDAO;
import com.transportcompany.model.Transport;

import java.util.List;

public class TransportService {
    private TransportDAO transportDAO;

    public TransportService() {
        this.transportDAO = new TransportDAO();
    }

    public void addTransport(Transport transport) {
        if (transport.isValid()) {
            transportDAO.addTransport(transport);
            System.out.println("Превозът е добавен успешно.");
        } else {
            System.out.println("Грешка при добавянето на превоза.");
        }
    }

    public Transport getTransportById(int id) {
        return transportDAO.getTransportById(id);
    }

    public List<Transport> getAllTransports() {
        return transportDAO.getAllTransports();
    }

    public void updateTransport(Transport transport) {
        if (transport.isValid()) {
            transportDAO.updateTransport(transport);
            System.out.println("Превозът е актуализиран успешно.");
        } else {
            System.out.println("Грешка при актуализирането на превоза.");
        }
    }

    public void deleteTransport(int id) {
        transportDAO.deleteTransport(id);
        System.out.println("Превозът е изтрит успешно.");
    }
}
