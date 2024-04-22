package com.example.demo.dto.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {
    @NotNull
    private Long user_id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String token;
}
