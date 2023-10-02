package com.example.registertestauthorization.entity;

import com.example.registertestauthorization.autogen.MyGenerator;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@Table(name = "MOVIETHEATER.ACCOUNT")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class    Account {
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", type = MyGenerator.class)
    @Column(name = "ACCOUNT_ID", nullable = false)
    String accountId;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "DATE_OF_BIRTH")
    Date dob;

    @Column(name = "EMAIL", nullable = false)
    String email;

    @Column(name = "FULL_NAME" )
    String fullName;

    @Column(name = "GENDER" )
    String gender;

    @Column(name = "IDENTITY_CARD")
    String identityCard;

    @Column(name = "IMAGE")
    String image;

    @Column(name = "PASSWORD")
    String password;

    @Column(name = "PHONE_NUMBER")
    String phone;

    @Column(name = "REGISTER_DATE")
    Date registerDate;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID")
    Roles roles;

    @Column(name = "STATUS")
    int status;

    @Column(name = "USERNAME")
    String userName;



}
