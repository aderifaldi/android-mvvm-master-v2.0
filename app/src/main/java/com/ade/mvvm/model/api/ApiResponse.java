package com.ade.mvvm.model.api;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by RadyaLabs PC on 16/01/2017.
 */
@NoArgsConstructor
@Data
public class ApiResponse<T> {
    private T data;
    private T error;
}
