package com.farm.smartfarm.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

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
    @Column(name = "user_id")
    private String id;

//    varchar(64) not null check(char_length(pw) >= 8 and char_length(pw) <= 64
    @Column(length = 64, nullable = false)
    @Check(constraints = "char_length(pw) >= 8 and char_length(pw) <= 64")
    private String pw;

//    "varchar(16) not null check(char_length(phoneNum) = 11)"
    @Column(length = 64, nullable = false)
    @Check(constraints = "check(char_length(phoneNum) = 11)")
    private String phoneNum;

//    constraint email_format check(email like'%@%.%')
    @Column
    @Check(constraints = "email like '%@%.%")
    private String email;
}
