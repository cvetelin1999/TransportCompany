package com.transportcompany.service;

import com.transportcompany.dao.ClientDAO;
import com.transportcompany.model.Client;

import java.util.List;

public class ClientService {
    private ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    public void addClient(Client client) {
        if (client.isValid()) {
            clientDAO.addClient(client);
            System.out.println("Клиентът е добавен успешно.");
        } else {
            System.out.println("Грешка при добавянето на клиента.");
        }
    }

    public Client getClientById(int id) {
        return clientDAO.getClientById(id);
    }

    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    public void updateClient(Client client) {
        if (client.isValid()) {
            clientDAO.updateClient(client);
            System.out.println("Клиентът е актуализиран успешно.");
        } else {
            System.out.println("Грешка при актуализирането на клиента.");
        }
    }

    public void deleteClient(int id) {
        clientDAO.deleteClient(id);
        System.out.println("Клиентът е изтрит успешно.");
    }
}
