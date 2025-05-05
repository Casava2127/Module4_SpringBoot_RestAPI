package com.ac.model.dto;

public class UpdateRegistrationStatusRequest {
    private String status; // Ví dụ: "APPROVED" hoặc "REJECTED"

    // Getters and Setters
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
