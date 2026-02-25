package com.company.deviceinventory.dto;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        Long departmentId,
        String departmentName
) {
}
