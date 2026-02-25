package com.company.deviceinventory.controller;

import com.company.deviceinventory.dto.DeviceRequest;
import com.company.deviceinventory.dto.DeviceResponse;
import com.company.deviceinventory.model.DeviceType;
import com.company.deviceinventory.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<DeviceResponse> getAll(@RequestParam(required = false) DeviceType type) {
        return deviceService.findAll(type);
    }

    @GetMapping("/{id}")
    public DeviceResponse getById(@PathVariable Long id) {
        return deviceService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceResponse create(@Valid @RequestBody DeviceRequest request) {
        return deviceService.create(request);
    }

    @PutMapping("/{id}")
    public DeviceResponse update(@PathVariable Long id, @Valid @RequestBody DeviceRequest request) {
        return deviceService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deviceService.delete(id);
    }
}
