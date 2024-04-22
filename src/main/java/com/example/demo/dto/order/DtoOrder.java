package com.example.demo.dto.order;

import com.example.demo.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrder {


    @NotNull
    private Long product_id;
    @NotNull
    @Positive
    private int cantidad;
    @NotNull
    private Client client;
}
