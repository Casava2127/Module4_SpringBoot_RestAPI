package com.ra.service.department.imp;

import com.ra.model.entity.Department;
import com.ra.repository.DepartmentRepository;
import com.ra.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new RuntimeException("Tên phòng ban đã tồn tại");
        }
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department department) {
        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng ban"));
        if (departmentRepository.existsByName(department.getName()) &&
                !existingDepartment.getName().equals(department.getName())) {
            throw new RuntimeException("Tên phòng ban đã tồn tại");
        }
        existingDepartment.setName(department.getName());
        existingDepartment.setDescription(department.getDescription());
        existingDepartment.setStatus(department.getStatus());
        return departmentRepository.save(existingDepartment);
    }

    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng ban"));
        if (!department.getEmployees().isEmpty()) {
            throw new RuntimeException("Phòng ban đang chứa nhân viên, không thể xóa");
        }
        departmentRepository.delete(department);
    }
}
