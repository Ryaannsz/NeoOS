package com.revisao.demo.dto;

import java.util.Map;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActionRequest {

    @NotNull
    private String actionType;
    @NotNull
    private Map<String, Object> payload;

}
