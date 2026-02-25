package com.company.deviceinventory.repository;

import com.company.deviceinventory.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
