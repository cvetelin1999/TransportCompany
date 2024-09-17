package com.transportcompany.util;

import com.transportcompany.model.Transport;

import java.io.*;
import java.util.List;

public class FileManager {

    public void saveTransportsToFile(String fileName, List<Transport> transports) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Transport transport : transports) {
                writer.write(transportToString(transport));
                writer.newLine();
            }
            System.out.println("Данните за превозите са записани във файла успешно.");
        } catch (IOException e) {
            System.out.println("Грешка при записването на данни във файла: " + e.getMessage());
        }
    }

    public List<Transport> loadTransportsFromFile(String fileName, List<Transport> transports) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transport transport = stringToTransport(line);
                transports.add(transport);
            }
            System.out.println("Данните за превозите са заредени успешно.");
        } catch (IOException e) {
            System.out.println("Грешка при зареждането на данни от файла: " + e.getMessage());
        }
        return transports;
    }

    private String transportToString(Transport transport) {
        return transport.getId() + "," + transport.getStartPoint() + "," + transport.getEndPoint() + "," +
                transport.getDepartureDate() + "," + transport.getArrivalDate() + "," +
                transport.getPrice() + "," + transport.getCompany().getId() + "," +
                transport.getClient().getId() + "," + transport.getVehicle().getId() + "," +
                transport.getDriver().getId();
    }

    private Transport stringToTransport(String line) {
        String[] parts = line.split(",");
        Transport transport = new Transport();
        transport.setId(Integer.parseInt(parts[0]));
        transport.setStartPoint(parts[1]);
        transport.setEndPoint(parts[2]);
        transport.setPrice(Double.parseDouble(parts[5]));
        return transport;
    }
}
