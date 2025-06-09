package com.ac.controller;

import com.ac.model.entity.Certificate;
import com.ac.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/certificates")
public class CertificateController {

    @Autowired
    private CertificateService service;

    @GetMapping
    public List<Certificate> getAllCertificates() {
        return service.getAllCertificates();
    }

    @GetMapping("/{id}")
    public Optional<Certificate> getCertificateById(@PathVariable Long id) {
        return service.getCertificateById(id);
    }

    @PostMapping
    public Certificate createCertificate(@RequestBody Certificate cert) {
        return service.createCertificate(cert);
    }

    @PutMapping("/{id}")
    public Certificate updateCertificate(@PathVariable Long id, @RequestBody Certificate cert) {
        return service.updateCertificate(id, cert);
    }

    @DeleteMapping("/{id}")
    public void deleteCertificate(@PathVariable Long id) {
        service.deleteCertificate(id);
    }
}