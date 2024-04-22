package com.example.demo.entities;

import com.example.demo.dto.product.DtoProduct;
import com.example.demo.dto.product.DtoProductUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private boolean status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private List<Order> order = new ArrayList<>();


    public Product(DtoProduct product)
    {
        this.name = product.getName();
        this.price = product.getPrice();
        this.status = true;
    }

    public void deleteProduct()
    {
        this.status = false;
    }
    public void updateProduct(DtoProductUpdate dtoProductUpdate)
    {
        if(dtoProductUpdate.getName()!=null)
            this.name = dtoProductUpdate.getName();
        if(dtoProductUpdate.getPrice()>0)
            this.price = dtoProductUpdate.getPrice();
        this.status = dtoProductUpdate.isStatus();
    }

}
