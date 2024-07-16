package com.patika.kredinbizdeservice.entity;

import com.patika.kredinbizdeservice.entity.constant.UserEntityColumnConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = UserEntityColumnConstants.NAME, unique = false, nullable = false)
    private String name;

    @Column(name = UserEntityColumnConstants.SURNAME, unique = false, nullable = false)
    private String surname;

    @Column(name = UserEntityColumnConstants.BIRTH_DATE, unique = false, nullable = true)
    private LocalDate birthDate;

    @Column(name = UserEntityColumnConstants.EMAIL, unique = true, nullable = false)
    private String email;

    @Column(name = UserEntityColumnConstants.PASSWORD, unique = false, nullable = false)
    private String password;

    @Column(name = UserEntityColumnConstants.PHONE_NUMBER, unique = true, nullable = true)
    private String phoneNumber;

    @Column(name = UserEntityColumnConstants.IS_ACTIVE, unique = false, nullable = true)
    private Boolean isActive;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Application> applicationList;

    public void addApplication(Application application){
        applicationList.add(application);
    }


}

