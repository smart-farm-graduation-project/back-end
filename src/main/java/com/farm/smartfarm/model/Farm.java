package com.farm.smartfarm.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Farm {
    @Column
    @CreationTimestamp
    private Timestamp startDate;
    @Column
    private String user;
    @Id
    private String farmNum;
}
