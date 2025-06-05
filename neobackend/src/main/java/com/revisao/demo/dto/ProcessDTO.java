package com.revisao.demo.dto;

import java.sql.Timestamp;

import com.revisao.demo.enums.StateProcess;
import com.revisao.demo.models.App;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessDTO {

    private String id;

    private String name;

    private StateProcess state;

    private Timestamp dateCreation;

    private Integer priority;

    private String waitingReason;

    private String instanceName;

    private App app;

}
