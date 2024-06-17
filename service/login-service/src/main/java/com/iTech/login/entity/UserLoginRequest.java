package com.itech.login.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserLoginRequest {

    private String email;
    private String password;
}
