package com.revisao.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppDTO {

    private String id;

    private String name;

    private String description;

    private Double memory;

    private Double cpuIntensity;

}
