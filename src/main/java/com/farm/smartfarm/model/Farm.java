package com.farm.smartfarm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Farm {
    @Column
    private String startDate;
    @Column
    private String user;
    @Id
    private String farmNum;
}
