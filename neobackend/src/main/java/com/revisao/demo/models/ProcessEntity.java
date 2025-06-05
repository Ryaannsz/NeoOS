package com.revisao.demo.models;

import java.sql.Timestamp;

import com.revisao.demo.enums.StateProcess;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProcessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private StateProcess state;

    private Timestamp dateCreation;

    private Integer priority;

    private String waitingReason;

    @ManyToOne
    private App app;

}
