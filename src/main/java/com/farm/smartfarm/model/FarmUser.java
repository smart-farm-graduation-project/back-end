package com.farm.smartfarm.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FarmUser {
    @Column(columnDefinition = "varchar(16) not null")
    private String name;
    @Id
    private String id;
    @Column(columnDefinition = "varchar(64) not null check(pw >= 8 and pw <= 64")
    private String pw;
    @Column(columnDefinition = "not null check(varchar(11) char_length(phoneNum) = 11)")
    private String phoneNum;
    @Column(columnDefinition = "constraint email_format check(email like'%@%.%')")
    private String email;
}
