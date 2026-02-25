package com.company.deviceinventory.repository;

import com.company.deviceinventory.model.Device;
import com.company.deviceinventory.model.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByType(DeviceType type);

    @Query("SELECT d FROM Device d WHERE d.type = :type")
    List<Device> findByTypeUsingJpql(@Param("type") DeviceType type);

    @Query(value = "SELECT * FROM devices d WHERE d.type = :type", nativeQuery = true)
    List<Device> findByTypeUsingNative(@Param("type") String type);
}
