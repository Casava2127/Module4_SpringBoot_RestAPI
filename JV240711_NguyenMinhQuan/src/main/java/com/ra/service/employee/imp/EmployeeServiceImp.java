package com.ra.service.employee.imp;

import com.ra.model.entity.Employee;
import com.ra.repository.EmployeeRepository;
import com.ra.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAllByOrderByNameAsc();
    }

    public Employee addEmployee(Employee employee) {
        // Validate email và số điện thoại
        if (!isValidEmail(employee.getEmail())) {
            throw new RuntimeException("Email không hợp lệ");
        }
        if (!isValidPhone(employee.getPhone())) {
            throw new RuntimeException("Số điện thoại không hợp lệ");
        }
        if (employee.getEmpBirthOfDate() == null) {
            throw new RuntimeException("Ngày sinh không được để trống");
        }

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));

        // Validate email và số điện thoại
        if (!isValidEmail(employee.getEmail())) {
            throw new RuntimeException("Email không hợp lệ");
        }
        if (!isValidPhone(employee.getPhone())) {
            throw new RuntimeException("Số điện thoại không hợp lệ");
        }
        if (employee.getEmpBirthOfDate() == null) {
            throw new RuntimeException("Ngày sinh không được để trống");
        }

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setAvatar(employee.getAvatar());
        existingEmployee.setEmpBirthOfDate(employee.getEmpBirthOfDate());

        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        employee.setStatus(0); // Cập nhật trạng thái thành nghỉ việc
        employeeRepository.save(employee);
    }

    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByNameContainingOrAddressContainingOrEmailContainingOrPhoneContaining(
                keyword, keyword, keyword, keyword);
    }

    private boolean isValidEmail(String email) {
        // Regex để validate email
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean isValidPhone(String phone) {
        // Regex để validate số điện thoại Việt Nam
        return phone.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$");
    }
}
