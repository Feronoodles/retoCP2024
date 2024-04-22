package com.example.demo.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoProductUpdate {
    @NotNull
    private Long product_id;
    private String name;
    @Positive
    private double price;
    private boolean status;
}
