package com.ac.service;

import com.ac.model.entity.QRCode;
import com.ac.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QRCodeService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    public List<QRCode> getAllQRCodes() {
        return qrCodeRepository.findAll();
    }

    public Optional<QRCode> getQRCodeById(Long id) {
        return qrCodeRepository.findById(id);
    }

    public QRCode createQRCode(QRCode qrCode) {
        return qrCodeRepository.save(qrCode);
    }

    public QRCode updateQRCode(Long id, QRCode details) {
        QRCode qr = qrCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("QRCode not found with id: " + id));
        qr.setQrCode(details.getQrCode());
        qr.setValidFrom(details.getValidFrom());
        qr.setValidUntil(details.getValidUntil());
        return qrCodeRepository.save(qr);
    }

    public void deleteQRCode(Long id) {
        qrCodeRepository.deleteById(id);
    }
}