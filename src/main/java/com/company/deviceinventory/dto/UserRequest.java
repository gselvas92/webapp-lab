package com.company.deviceinventory.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank String fullName,
        @Email @NotBlank String email,
        @NotNull Long departmentId
) {
}
