package com.company.deviceinventory.service;

import com.company.deviceinventory.dto.DepartmentRequest;
import com.company.deviceinventory.dto.DepartmentResponse;
import com.company.deviceinventory.exception.ResourceNotFoundException;
import com.company.deviceinventory.model.Department;
import com.company.deviceinventory.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public DepartmentResponse findById(Long id) {
        return toResponse(getEntityById(id));
    }

    public DepartmentResponse create(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.name());
        return toResponse(departmentRepository.save(department));
    }

    public DepartmentResponse update(Long id, DepartmentRequest request) {
        Department department = getEntityById(id);
        department.setName(request.name());
        return toResponse(departmentRepository.save(department));
    }

    public void delete(Long id) {
        Department department = getEntityById(id);
        departmentRepository.delete(department);
    }

    public Department getEntityById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + id));
    }

    private DepartmentResponse toResponse(Department department) {
        return new DepartmentResponse(department.getId(), department.getName());
    }
}
