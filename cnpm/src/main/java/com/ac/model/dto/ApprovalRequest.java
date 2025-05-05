package com.ac.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalRequest {
    // Giá trị hợp lệ: APPROVED hoặc REJECTED
    private String status;
}
