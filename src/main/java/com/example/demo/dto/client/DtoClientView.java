package com.example.demo.dto.client;

import com.example.demo.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoClientView {
    private String first_names;
    private String last_names;
    private String address;

    public DtoClientView(Client client)
    {
        this.first_names = client.getFirst_names();
        this.last_names = client.getLast_names();
        this.address = client.getAddress();
    }
}
