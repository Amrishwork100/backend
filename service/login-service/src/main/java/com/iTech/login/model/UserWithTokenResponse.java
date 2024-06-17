package com.itech.login.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class UserWithTokenResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;
    private String token;


}
