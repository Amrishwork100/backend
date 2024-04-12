package com.itech.login.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "USER_REGISTRATION")
public class UserRegistration {

    @Id
    private String id;
    private String email;
    private String mobile;
    private String password;
}
