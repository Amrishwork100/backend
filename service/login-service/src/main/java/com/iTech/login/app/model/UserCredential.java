package com.itech.login.app.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserCredential {

    private String email;
    private String password;
}
