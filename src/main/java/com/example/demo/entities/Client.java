package com.example.demo.entities;

import com.example.demo.dto.client.DtoClient;
import com.example.demo.dto.client.DtoClientUpdate;
import com.example.demo.dto.product.DtoProduct;
import com.example.demo.dto.product.DtoProductUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;
    @Column(nullable = false)
    private String first_names;
    @Column(nullable = false)
    private String last_names;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private boolean status;

    @PrePersist
    public void preStatusPersist(){
        status=true;
    }

    public Client(DtoClient client)
    {
        this.first_names = client.getFirst_names();
        this.last_names = client.getLast_names();
        this.address = client.getAddress();

    }

    public void deleteClient()
    {
        this.status = false;
    }
    public void updateProduct(DtoClientUpdate dtoClientUpdate)
    {
        if(dtoClientUpdate.getFirst_names()!=null)
            this.first_names = dtoClientUpdate.getFirst_names();
        if(dtoClientUpdate.getLast_names()!=null)
            this.last_names = dtoClientUpdate.getLast_names();
        if(dtoClientUpdate.getAddress()!=null)
            this.address = dtoClientUpdate.getAddress();
        this.status = dtoClientUpdate.isStatus();
    }
}
