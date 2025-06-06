package com.revisao.demo.kafka.dto;

import java.sql.Timestamp;
import java.util.Map;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProcessEventDTO {

    private String appId;

    private Map<String, Object> payload;

    private String type;

    @CreationTimestamp
    private Timestamp timestamp;
}