package com.ac.controller;

import com.ac.model.entity.QRCode;
import com.ac.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/qrcodes")
public class QRCodeController {

    @Autowired
    private QRCodeService service;

    @GetMapping
    public List<QRCode> getAllQRCodes() {
        return service.getAllQRCodes();
    }

    @GetMapping("/{id}")
    public Optional<QRCode> getQRCodeById(@PathVariable Long id) {
        return service.getQRCodeById(id);
    }

    @PostMapping
    public QRCode createQRCode(@RequestBody QRCode qr) {
        return service.createQRCode(qr);
    }

    @PutMapping("/{id}")
    public QRCode updateQRCode(@PathVariable Long id, @RequestBody QRCode qr) {
        return service.updateQRCode(id, qr);
    }

    @DeleteMapping("/{id}")
    public void deleteQRCode(@PathVariable Long id) {
        service.deleteQRCode(id);
    }
}