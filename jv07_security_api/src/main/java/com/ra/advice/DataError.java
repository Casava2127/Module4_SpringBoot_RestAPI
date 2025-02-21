package com.ra.advice;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataError<T> {
    private T message;
    private int status;
}
