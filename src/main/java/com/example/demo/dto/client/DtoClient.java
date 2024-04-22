package com.example.demo.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoClient {
    @NotBlank
    private String first_names;
    @NotBlank
    private String last_names;
    @NotBlank
    private String address;

}
