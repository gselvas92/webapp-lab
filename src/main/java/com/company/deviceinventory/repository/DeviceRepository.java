package com.company.deviceinventory.repository;

import com.company.deviceinventory.model.Device;
import com.company.deviceinventory.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByType(DeviceType type);
}
