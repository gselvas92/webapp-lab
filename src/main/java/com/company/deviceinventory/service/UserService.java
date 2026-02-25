package com.company.deviceinventory.service;

import com.company.deviceinventory.dto.UserRequest;
import com.company.deviceinventory.dto.UserResponse;
import com.company.deviceinventory.exception.ResourceNotFoundException;
import com.company.deviceinventory.model.CompanyUser;
import com.company.deviceinventory.model.Department;
import com.company.deviceinventory.repository.CompanyUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final CompanyUserRepository companyUserRepository;
    private final DepartmentService departmentService;

    public UserService(CompanyUserRepository companyUserRepository, DepartmentService departmentService) {
        this.companyUserRepository = companyUserRepository;
        this.departmentService = departmentService;
    }

    public List<UserResponse> findAll() {
        return companyUserRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        return toResponse(getEntityById(id));
    }

    public UserResponse create(UserRequest request) {
        Department department = departmentService.getEntityById(request.departmentId());

        CompanyUser user = new CompanyUser();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setDepartment(department);
        return toResponse(companyUserRepository.save(user));
    }

    public UserResponse update(Long id, UserRequest request) {
        CompanyUser user = getEntityById(id);
        Department department = departmentService.getEntityById(request.departmentId());

        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setDepartment(department);
        return toResponse(companyUserRepository.save(user));
    }

    public void delete(Long id) {
        CompanyUser user = getEntityById(id);
        companyUserRepository.delete(user);
    }

    public CompanyUser getEntityById(Long id) {
        return companyUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
    }

    private UserResponse toResponse(CompanyUser user) {
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getDepartment().getId(),
                user.getDepartment().getName()
        );
    }
}
