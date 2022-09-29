package com.example.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String firstname;
    @Column(name = "second_name")
    private String secondName;
    private String email;
    @Column(name = "account_balance")
    private Integer accountBalance;
    @Column(name = "soft_delete_user")
    private Date softDelUser;

}
