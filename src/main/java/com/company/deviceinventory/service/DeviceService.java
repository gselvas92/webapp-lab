package com.company.deviceinventory.service;

import com.company.deviceinventory.dto.DeviceRequest;
import com.company.deviceinventory.dto.DeviceResponse;
import com.company.deviceinventory.exception.ResourceNotFoundException;
import com.company.deviceinventory.model.CompanyUser;
import com.company.deviceinventory.model.Device;
import com.company.deviceinventory.model.DeviceType;
import com.company.deviceinventory.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserService userService;

    public DeviceService(DeviceRepository deviceRepository, UserService userService) {
        this.deviceRepository = deviceRepository;
        this.userService = userService;
    }

    public List<DeviceResponse> findAll(DeviceType type) {
        List<Device> devices = type == null ? deviceRepository.findAll() : deviceRepository.findByType(type);
        return devices.stream().map(this::toResponse).toList();
    }

    public List<DeviceResponse> findByTypeJpa(DeviceType type) {
        return deviceRepository.findByType(type).stream()
                .map(this::toResponse)
                .toList();
    }

    public List<DeviceResponse> findByTypeJpql(DeviceType type) {
        return deviceRepository.findByTypeUsingJpql(type).stream()
                .map(this::toResponse)
                .toList();
    }

    public List<DeviceResponse> findByTypeNative(DeviceType type) {
        return deviceRepository.findByTypeUsingNative(type.name()).stream()
                .map(this::toResponse)
                .toList();
    }

    public DeviceResponse findById(Long id) {
        return toResponse(getEntityById(id));
    }

    public DeviceResponse create(DeviceRequest request) {
        CompanyUser owner = userService.getEntityById(request.ownerUserId());

        Device device = new Device();
        device.setAssetTag(request.assetTag());
        device.setBrand(request.brand());
        device.setModel(request.model());
        device.setType(request.type());
        device.setOwnerUser(owner);
        return toResponse(deviceRepository.save(device));
    }

    public DeviceResponse update(Long id, DeviceRequest request) {
        Device device = getEntityById(id);
        CompanyUser owner = userService.getEntityById(request.ownerUserId());

        device.setAssetTag(request.assetTag());
        device.setBrand(request.brand());
        device.setModel(request.model());
        device.setType(request.type());
        device.setOwnerUser(owner);
        return toResponse(deviceRepository.save(device));
    }

    public void delete(Long id) {
        Device device = getEntityById(id);
        deviceRepository.delete(device);
    }

    private Device getEntityById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found: " + id));
    }

    private DeviceResponse toResponse(Device device) {
        CompanyUser owner = device.getOwnerUser();
        return new DeviceResponse(
                device.getId(),
                device.getAssetTag(),
                device.getBrand(),
                device.getModel(),
                device.getType(),
                owner.getId(),
                owner.getFullName(),
                owner.getDepartment().getId(),
                owner.getDepartment().getName()
        );
    }
}
