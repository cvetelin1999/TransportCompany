package com.transportcompany.service;

import com.transportcompany.dao.EmployeeDAO;
import com.transportcompany.model.Employee;

import java.util.List;

public class EmployeeService {
    private EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public void addEmployee(Employee employee) {
        if (employee.isValid()) {
            employeeDAO.addEmployee(employee);
            System.out.println("Служителят е добавен успешно.");
        } else {
            System.out.println("Грешка при добавянето на служителя.");
        }
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public void updateEmployee(Employee employee) {
        if (employee.isValid()) {
            employeeDAO.updateEmployee(employee);
            System.out.println("Служителят е актуализиран успешно.");
        } else {
            System.out.println("Грешка при актуализирането на служителя.");
        }
    }

    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
        System.out.println("Служителят е изтрит успешно.");
    }
}
