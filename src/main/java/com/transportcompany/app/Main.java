package com.transportcompany.app;

import com.transportcompany.enums.Qualification;
import com.transportcompany.enums.VehicleType;
import com.transportcompany.model.*;
import com.transportcompany.service.*;
import com.transportcompany.util.FileManager;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransportCompanyService companyService = new TransportCompanyService();
        ClientService clientService = new ClientService();
        EmployeeService employeeService = new EmployeeService();
        VehicleService vehicleService = new VehicleService();
        TransportService transportService = new TransportService();
        FileManager fileManager = new FileManager();

        List<Transport> transports = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("Изберете опция:");
            System.out.println("11: Добавяне на транспортна компания");
            System.out.println("12: Редактиране на транспортна компания");
            System.out.println("13: Изтриване на транспортна компания");
            System.out.println("14: Показване на всички компании");
            System.out.println("21: Добавяне на клиент");
            System.out.println("22: Редактиране на клиент");
            System.out.println("23: Изтриване на клиент");
            System.out.println("24: Показване на всички клиенти");
            System.out.println("31: Добавяне на превозно средство");
            System.out.println("32: Редактиране на превозно средство");
            System.out.println("33: Изтриване на превозно средство");
            System.out.println("34: Показване на всички превозни средства");
            System.out.println("41: Добавяне на служител");
            System.out.println("42: Редактиране на служител");
            System.out.println("43: Изтриване на служител");
            System.out.println("44: Показване на всички служители");
            System.out.println("51: Добавяне на превоз");
            System.out.println("52: Записване на данни за превозите във файл");
            System.out.println("53: Зареждане на данни за превозите от файл");
            System.out.println("61: Показване на общия брой извършени превози");
            System.out.println("62: Показване на общата сума на извършените превози");
            System.out.println("63: Справка за броя превози на шофьор");
            System.out.println("0: Изход");

            int option = getValidIntInput(scanner, "Изберете валидна опция:");

            switch (option) {
                case 11:
                    System.out.println("Въведете име на компанията:");
                    String companyName = scanner.nextLine();
                    double revenue = getValidDoubleInput(scanner, "Въведете приход на компанията:");
                    TransportCompany company = new TransportCompany();
                    company.setName(companyName);
                    company.setRevenue(revenue);
                    companyService.addCompany(company);
                    break;

                case 12:
                    int companyId = getValidCompanyId(scanner, companyService, "Въведете ID на компанията, която искате да редактирате:");
                    System.out.println("Въведете ново име на компанията:");
                    String newCompanyName = scanner.nextLine();
                    double newRevenue = getValidDoubleInput(scanner, "Въведете нов приход на компанията:");
                    TransportCompany companyToUpdate = companyService.getCompanyById(companyId);
                    companyToUpdate.setName(newCompanyName);
                    companyToUpdate.setRevenue(newRevenue);
                    companyService.updateCompany(companyToUpdate);
                    break;

                case 13:
                    int companyIdToDelete = getValidCompanyId(scanner, companyService, "Въведете ID на компанията, която искате да изтриете:");
                    companyService.deleteCompany(companyIdToDelete);
                    break;

                case 14:
                    List<TransportCompany> companies = companyService.getAllCompanies();
                    for (TransportCompany c : companies) {
                        System.out.println(c.getId() + " - " + c.getName() + ", Приход: " + c.getRevenue());
                    }
                    break;

                case 21:
                    System.out.println("Въведете име на клиента:");
                    String clientName = scanner.nextLine();
                    boolean isPaid = getValidBooleanInput(scanner, "Клиентът платил ли е? (true/false):");

                    int companyIdForClient = getValidCompanyId(scanner, companyService, "Въведете ID на транспортната компания, към която принадлежи клиентът:");
                    TransportCompany companyForClient = companyService.getCompanyById(companyIdForClient);

                    Client client = new Client();
                    client.setName(clientName);
                    client.setPaid(isPaid);
                    client.setCompany(companyForClient);  // Свързваме клиента с компанията
                    clientService.addClient(client);
                    break;

                case 22:
                    int clientId = getValidIntInput(scanner, "Въведете ID на клиента, който искате да редактирате:");
                    Client clientToUpdate = clientService.getClientById(clientId);
                    if (clientToUpdate != null) {
                        System.out.println("Въведете ново име на клиента:");
                        String newClientName = scanner.nextLine();
                        boolean newIsPaid = getValidBooleanInput(scanner, "Клиентът платил ли е? (true/false):");

                        int companyIdForClientUpdate = getValidCompanyId(scanner, companyService, "Въведете ID на транспортната компания, към която принадлежи клиентът:");
                        TransportCompany companyForClientUpdate = companyService.getCompanyById(companyIdForClientUpdate);

                        clientToUpdate.setName(newClientName);
                        clientToUpdate.setPaid(newIsPaid);
                        clientToUpdate.setCompany(companyForClientUpdate);
                        clientService.updateClient(clientToUpdate);
                    } else {
                        System.out.println("Клиентът не беше намерен.");
                    }
                    break;

                case 23:
                    int clientIdToDelete = getValidIntInput(scanner, "Въведете ID на клиента, който искате да изтриете:");
                    clientService.deleteClient(clientIdToDelete);
                    break;

                case 24:
                    List<Client> clients = clientService.getAllClients();
                    for (Client cl : clients) {
                        System.out.println(cl.getId() + " - " + cl.getName() + ", Платил: " + cl.isPaid());
                    }
                    break;

                case 31:
                    System.out.println("Въведете тип на превозното средство (BUS, TRUCK, TANKER):");
                    String vehicleType = getValidVehicleType(scanner);
                    double capacity = getValidDoubleInput(scanner, "Въведете капацитет на превозното средство:");

                    int companyIdForVehicle = getValidCompanyId(scanner, companyService, "Въведете ID на транспортната компания, към която принадлежи превозното средство:");
                    TransportCompany companyForVehicle = companyService.getCompanyById(companyIdForVehicle);

                    Vehicle vehicle = new Vehicle();
                    vehicle.setType(VehicleType.valueOf(vehicleType.toUpperCase()));
                    vehicle.setCapacity(capacity);
                    vehicle.setCompany(companyForVehicle);  // Свързваме превозното средство с компанията
                    vehicleService.addVehicle(vehicle);
                    break;

                case 32:
                    int vehicleId = getValidIntInput(scanner, "Въведете ID на превозното средство, което искате да редактирате:");
                    Vehicle vehicleToUpdate = vehicleService.getVehicleById(vehicleId);
                    if (vehicleToUpdate != null) {
                        System.out.println("Въведете нов тип на превозното средство (BUS, TRUCK, TANKER):");
                        String newVehicleType = getValidVehicleType(scanner);
                        double newCapacity = getValidDoubleInput(scanner, "Въведете нов капацитет на превозното средство:");

                        int companyIdForVehicleUpdate = getValidCompanyId(scanner, companyService, "Въведете ID на транспортната компания, към която принадлежи превозното средство:");
                        TransportCompany companyForVehicleUpdate = companyService.getCompanyById(companyIdForVehicleUpdate);

                        vehicleToUpdate.setType(VehicleType.valueOf(newVehicleType.toUpperCase()));
                        vehicleToUpdate.setCapacity(newCapacity);
                        vehicleToUpdate.setCompany(companyForVehicleUpdate);
                        vehicleService.updateVehicle(vehicleToUpdate);
                    } else {
                        System.out.println("Превозното средство не беше намерено.");
                    }
                    break;

                case 33:
                    int vehicleIdToDelete = getValidIntInput(scanner, "Въведете ID на превозното средство, което искате да изтриете:");
                    vehicleService.deleteVehicle(vehicleIdToDelete);
                    break;

                case 34:
                    List<Vehicle> vehicles = vehicleService.getAllVehicles();
                    for (Vehicle v : vehicles) {
                        System.out.println(v.getId() + " - " + v.getType() + ", Капацитет: " + v.getCapacity());
                    }
                    break;

                case 41:
                    System.out.println("Въведете име на служителя:");
                    String employeeName = scanner.nextLine();
                    String qualification = getValidQualification(scanner);
                    double salary = getValidDoubleInput(scanner, "Въведете заплата на служителя:");

                    int companyIdForEmployee = getValidCompanyId(scanner, companyService, "Въведете ID на транспортната компания, към която принадлежи служителят:");
                    TransportCompany companyForEmployee = companyService.getCompanyById(companyIdForEmployee);

                    Employee employee = new Employee();
                    employee.setName(employeeName);
                    employee.setQualification(Qualification.valueOf(qualification.toUpperCase()));
                    employee.setSalary(salary);
                    employee.setCompany(companyForEmployee);  // Свързваме служителя с компанията
                    employeeService.addEmployee(employee);
                    break;

                case 42:
                    int employeeId = getValidIntInput(scanner, "Въведете ID на служителя, който искате да редактирате:");
                    Employee employeeToUpdate = employeeService.getEmployeeById(employeeId);
                    if (employeeToUpdate != null) {
                        System.out.println("Въведете ново име на служителя:");
                        String newEmployeeName = scanner.nextLine();
                        String newQualification = getValidQualification(scanner);
                        double newSalary = getValidDoubleInput(scanner, "Въведете нова заплата на служителя:");

                        int companyIdForEmployeeUpdate = getValidCompanyId(scanner, companyService, "Въведете ID на транспортната компания, към която принадлежи служителят:");
                        TransportCompany companyForEmployeeUpdate = companyService.getCompanyById(companyIdForEmployeeUpdate);

                        employeeToUpdate.setName(newEmployeeName);
                        employeeToUpdate.setQualification(Qualification.valueOf(newQualification.toUpperCase()));
                        employeeToUpdate.setSalary(newSalary);
                        employeeToUpdate.setCompany(companyForEmployeeUpdate);
                        employeeService.updateEmployee(employeeToUpdate);
                    } else {
                        System.out.println("Служителят не беше намерен.");
                    }
                    break;

                case 43:
                    int employeeIdToDelete = getValidIntInput(scanner, "Въведете ID на служителя, който искате да изтриете:");
                    employeeService.deleteEmployee(employeeIdToDelete);
                    break;

                case 44:
                    List<Employee> employees = employeeService.getAllEmployees();
                    for (Employee e : employees) {
                        System.out.println(e.getId() + " - " + e.getName() + ", Квалификация: " + e.getQualification() + ", Заплата: " + e.getSalary());
                    }
                    break;

                case 51:
                    System.out.println("Въведете начална точка на превоза:");
                    String startPoint = scanner.nextLine();
                    System.out.println("Въведете крайна точка на превоза:");
                    String endPoint = scanner.nextLine();
                    String departureDateStr = getValidDate(scanner, "Въведете дата на тръгване (yyyy-MM-dd):");
                    String arrivalDateStr = getValidDate(scanner, "Въведете дата на пристигане (yyyy-MM-dd):");
                    double price = getValidDoubleInput(scanner, "Въведете цена на превоза:");

                    int companyIdForTransport = getValidCompanyId(scanner, companyService, "Въведете ID на транспортната компания за превоза:");
                    TransportCompany companyForTransport = companyService.getCompanyById(companyIdForTransport);
                    Client clientForTransport = getValidClientById(scanner, clientService, "Въведете ID на клиента:");
                    Vehicle vehicleForTransport = getValidVehicleById(scanner, vehicleService, "Въведете ID на превозното средство:");
                    Employee driverForTransport = getValidDriverById(scanner, employeeService, "Въведете ID на шофьора:");

                    Transport transport = new Transport();
                    transport.setStartPoint(startPoint);
                    transport.setEndPoint(endPoint);
                    transport.setDepartureDate(java.sql.Date.valueOf(departureDateStr));
                    transport.setArrivalDate(java.sql.Date.valueOf(arrivalDateStr));
                    transport.setPrice(price);
                    transport.setCompany(companyForTransport);
                    transport.setClient(clientForTransport);
                    transport.setVehicle(vehicleForTransport);
                    transport.setDriver(driverForTransport);
                    transportService.addTransport(transport);
                    break;

                case 52:
                    System.out.println("Въведете име на файла за запис:");
                    String saveFileName = scanner.nextLine();

                    transports = transportService.getAllTransports();

                    if (!transports.isEmpty()) {
                        fileManager.saveTransportsToFile(saveFileName, transports);
                        System.out.println("Превозите са записани успешно във файла.");
                    } else {
                        System.out.println("Няма превози за записване.");
                    }
                    break;


                case 53:
                    System.out.println("Въведете име на файла за зареждане:");
                    String loadFileName = scanner.nextLine();

                    transports = fileManager.loadTransportsFromFile(loadFileName, transports);

                    if (!transports.isEmpty()) {
                        System.out.println("Данните за превозите са заредени успешно:");
                        for (Transport t : transports) {
                            System.out.println(t);
                        }
                    } else {
                        System.out.println("Файлът не съдържа превози или файлът не беше намерен.");
                    }
                    break;


                case 61:
                    transports = transportService.getAllTransports();

                    if (!transports.isEmpty()) {
                        System.out.println("Общ брой превози: " + transports.size());
                    } else {
                        System.out.println("Няма извършени превози.");
                    }
                    break;


                case 62:
                    transports = transportService.getAllTransports();

                    if (!transports.isEmpty()) {
                        double totalIncome = transports.stream().mapToDouble(Transport::getPrice).sum();
                        System.out.println("Обща сума на извършените превози: " + totalIncome);
                    } else {
                        System.out.println("Няма извършени превози.");
                    }
                    break;


                case 63:
                    transports = transportService.getAllTransports();

                    if (!transports.isEmpty()) {
                        System.out.println("Справка за шофьорите и броя на превозите:");
                        Map<Employee, Long> transportsByDriver = new HashMap<>();
                        for (Transport t : transports) {
                            Employee driver = t.getDriver();
                            transportsByDriver.put(driver, transportsByDriver.getOrDefault(driver, 0L) + 1);
                        }
                        for (Map.Entry<Employee, Long> entry : transportsByDriver.entrySet()) {
                            System.out.println("Шофьор: " + entry.getKey().getName() + ", Брой превози: " + entry.getValue());
                        }
                    } else {
                        System.out.println("Няма извършени превози.");
                    }
                    break;


                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Невалидна опция. Опитайте отново.");
            }
        }
        scanner.close();
    }

    // Методи за валидация на входовете

    private static int getValidIntInput(Scanner scanner, String message) {
        int value;
        while (true) {
            try {
                System.out.println(message);
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Невалидно число. Моля, опитайте отново.");
            }
        }
        return value;
    }

    private static double getValidDoubleInput(Scanner scanner, String message) {
        double value;
        while (true) {
            try {
                System.out.println(message);
                value = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Невалидно число. Моля, опитайте отново.");
            }
        }
        return value;
    }

    private static boolean getValidBooleanInput(Scanner scanner, String message) {
        String value;
        while (true) {
            System.out.println(message);
            value = scanner.nextLine().toLowerCase(); // Конвертираме въведеното в малки букви
            if (value.equals("true")) {
                return true;
            } else if (value.equals("false")) {
                return false;
            } else {
                System.out.println("Моля, въведете 'true' или 'false'.");
            }
        }
    }

    private static String getValidVehicleType(Scanner scanner) {
        String vehicleType;
        while (true) {
            System.out.println("Въведете тип на превозното средство (BUS, TRUCK, TANKER):");
            vehicleType = scanner.nextLine().toUpperCase();
            if (vehicleType.equals("BUS") || vehicleType.equals("TRUCK") || vehicleType.equals("TANKER")) {
                break;
            } else {
                System.out.println("Невалиден тип. Моля, въведете BUS, TRUCK или TANKER.");
            }
        }
        return vehicleType;
    }

    private static String getValidQualification(Scanner scanner) {
        String qualification;
        while (true) {
            System.out.println("Въведете квалификация (GENERAL_DRIVER, SPECIAL_CARGO, FLAMMABLE_CARGO, PASSENGER_ABOVE_12):");
            qualification = scanner.nextLine().toUpperCase();
            if (qualification.equals("GENERAL_DRIVER") || qualification.equals("SPECIAL_CARGO") ||
                    qualification.equals("FLAMMABLE_CARGO") || qualification.equals("PASSENGER_ABOVE_12")) {
                break;
            } else {
                System.out.println("Невалидна квалификация. Моля, опитайте отново.");
            }
        }
        return qualification;
    }

    private static String getValidDate(Scanner scanner, String message) {
        String dateStr;
        while (true) {
            try {
                System.out.println(message);
                dateStr = scanner.nextLine();
                java.sql.Date.valueOf(dateStr); // Validate the date format
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Невалиден формат на датата. Моля, опитайте отново (формат yyyy-MM-dd).");
            }
        }
        return dateStr;
    }

    private static Client getValidClientById(Scanner scanner, ClientService clientService, String message) {
        Client client;
        while (true) {
            int clientId = getValidIntInput(scanner, message);
            client = clientService.getClientById(clientId);
            if (client != null) {
                break;
            } else {
                System.out.println("Няма клиент с такова ID. Опитайте отново.");
            }
        }
        return client;
    }

    private static Vehicle getValidVehicleById(Scanner scanner, VehicleService vehicleService, String message) {
        Vehicle vehicle;
        while (true) {
            int vehicleId = getValidIntInput(scanner, message);
            vehicle = vehicleService.getVehicleById(vehicleId);
            if (vehicle != null) {
                break;
            } else {
                System.out.println("Няма превозно средство с такова ID. Опитайте отново.");
            }
        }
        return vehicle;
    }

    private static Employee getValidDriverById(Scanner scanner, EmployeeService employeeService, String message) {
        Employee driver;
        while (true) {
            int driverId = getValidIntInput(scanner, message);
            driver = employeeService.getEmployeeById(driverId);
            if (driver != null) {
                break;
            } else {
                System.out.println("Няма шофьор с такова ID. Опитайте отново.");
            }
        }
        return driver;
    }

    private static int getValidCompanyId(Scanner scanner, TransportCompanyService companyService, String message) {
        int companyId;
        TransportCompany company;
        while (true) {
            companyId = getValidIntInput(scanner, message);
            company = companyService.getCompanyById(companyId);
            if (company != null) {
                break;
            } else {
                System.out.println("Няма транспортна компания с такова ID. Опитайте отново.");
            }
        }
        return companyId;
    }
}
