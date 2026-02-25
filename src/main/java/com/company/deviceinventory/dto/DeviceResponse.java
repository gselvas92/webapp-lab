package com.company.deviceinventory.dto;

import com.company.deviceinventory.model.DeviceType;

public record DeviceResponse(
        Long id,
        String assetTag,
        String brand,
        String model,
        DeviceType type,
        Long ownerUserId,
        String ownerUserName,
        Long departmentId,
        String departmentName
) {
}
