package com.ra.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DataError {
    private String message;
    private int status;
}
