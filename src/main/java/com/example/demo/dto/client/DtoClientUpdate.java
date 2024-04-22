package com.example.demo.dto.client;

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
public class DtoClientUpdate {

    @NotNull
    private Long client_id;

    private String first_names;

    private String last_names;

    private String address;

    private boolean status;
}
