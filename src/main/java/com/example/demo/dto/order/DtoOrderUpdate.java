package com.example.demo.dto.order;

import com.example.demo.entities.Client;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
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
public class DtoOrderUpdate {

    @NotNull
    private Long orders_id;
    private Long product_id;
    @Positive
    private int cantidad;

    private boolean status;


}
