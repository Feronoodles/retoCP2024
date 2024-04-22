package com.example.demo.controller;


import com.example.demo.dto.client.DtoClient;
import com.example.demo.dto.client.DtoClientUpdate;
import com.example.demo.dto.client.DtoClientView;
import com.example.demo.dto.user.DtoUserLogin;
import com.example.demo.entities.Client;
import com.example.demo.entities.Product;
import com.example.demo.service.IClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class ClientController {

    IClientService clientService;

    @Operation(summary = "Crear cliente", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoClient.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @PostMapping
    public ResponseEntity<DtoClientView> addClient(@RequestBody @Valid DtoClient dtoClient, UriComponentsBuilder uriComponentsBuilder)
    {


        Client client = clientService.save(dtoClient);

        DtoClientView dtoClientView = new DtoClientView(client);

        URI url = uriComponentsBuilder.path("/client/{id}").buildAndExpand(client.getClient_id()).toUri();

        return ResponseEntity.created(url).body(dtoClientView);
    }

    @Operation(summary = "Actualizar cliente", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoClientUpdate.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @Transactional
    @PutMapping
    public ResponseEntity updateClient(@RequestBody @Valid DtoClientUpdate dtoClientUpdate)
    {
        Client client = clientService.updateClient(dtoClientUpdate);
        DtoClientView dtoClientView = new DtoClientView(client);

        return ResponseEntity.ok(dtoClientView);
    }

    @Operation(summary = "Ver clientes", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pageable.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @GetMapping
    public ResponseEntity<Page<DtoClientView>> viewClients(@PageableDefault(size = 10) Pageable paginacion)
    {
        return ResponseEntity.ok(clientService.findAll(paginacion).map(DtoClientView::new));
    }

    @Operation(summary = "Ver cliente", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<DtoClientView> viewClient(@PathVariable Long id)
    {
        Client client = clientService.findById(id);

        DtoClientView dtoClientView = new DtoClientView(client);

        return ResponseEntity.ok(dtoClientView);
    }

    @Operation(summary = "Eliminar Cliente", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deleteClient(@PathVariable Long id)
    {
        Client client = clientService.findById(id);
        client.deleteClient();
        return ResponseEntity.noContent().build();
    }
}
