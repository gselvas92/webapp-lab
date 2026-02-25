package com.company.deviceinventory.repository;

import com.company.deviceinventory.model.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, Long> {
}
