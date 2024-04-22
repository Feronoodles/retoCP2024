package com.example.demo.dto.product;

import com.example.demo.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoProductView {
    private Long product_id;
    private String name;
    private double price;

    public DtoProductView(Product product)
    {
        this.product_id = product.getProduct_id();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
