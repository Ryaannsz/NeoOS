package com.revisao.demo.models;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.revisao.demo.enums.StateProcess;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "tb_process")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProcessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private StateProcess state;

    @CreationTimestamp
    private Timestamp dateCreation;

    private Integer priority;

    private String waitingReason;

    @ManyToOne
    private App app;

}
