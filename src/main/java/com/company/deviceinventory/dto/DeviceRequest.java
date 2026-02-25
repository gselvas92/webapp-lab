package com.company.deviceinventory.dto;

import com.company.deviceinventory.model.DeviceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DeviceRequest(
        @NotBlank String assetTag,
        @NotBlank String brand,
        @NotBlank String model,
        @NotNull DeviceType type,
        @NotNull Long ownerUserId
) {
}
