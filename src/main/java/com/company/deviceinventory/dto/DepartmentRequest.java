package com.company.deviceinventory.dto;

import jakarta.validation.constraints.NotBlank;

public record DepartmentRequest(@NotBlank String name) {
}
