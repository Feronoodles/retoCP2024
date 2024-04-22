package com.example.demo.controller;

import com.example.demo.dto.product.DtoProduct;
import com.example.demo.dto.product.DtoProductUpdate;
import com.example.demo.dto.product.DtoProductView;
import com.example.demo.dto.user.DtoUserLogin;
import com.example.demo.entities.Product;
import com.example.demo.service.IProductService;
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
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class ProductController {

    IProductService productService;

    @Operation(summary = "Añadir producto", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoProduct.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @PostMapping
    public ResponseEntity<DtoProductView> addProduct(@RequestBody @Valid DtoProduct dtoProduct, UriComponentsBuilder uriComponentsBuilder)
    {


        Product product = productService.save(dtoProduct);

        DtoProductView dtoProductView = new DtoProductView(product);

        URI url = uriComponentsBuilder.path("/product/{id}").buildAndExpand(product.getProduct_id()).toUri();

        return ResponseEntity.created(url).body(dtoProductView);
    }

    @Operation(summary = "actualizar producto", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoProductUpdate.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @Transactional
    @PutMapping
    public ResponseEntity updateProduct(@RequestBody @Valid DtoProductUpdate dtoProductUpdate)
    {
        Product product = productService.updateProduct(dtoProductUpdate);
        DtoProductView dtoProductView = new DtoProductView(product);

        return ResponseEntity.ok(dtoProductView);
    }

    @Operation(summary = "ver productos", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pageable.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @GetMapping
    public ResponseEntity<Page<DtoProductView>> viewProducts(@PageableDefault(size = 10) Pageable paginacion)
    {
        return ResponseEntity.ok(productService.findAll(paginacion).map(DtoProductView::new));
    }

    @Operation(summary = "ver producto", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<DtoProductView> viewProduct(@PathVariable Long id)
    {
        Product product = productService.findById(id);

        DtoProductView dtoProductView = new DtoProductView(product);

        return ResponseEntity.ok(dtoProductView);
    }

    @Operation(summary = "Eliminat producto", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deleteProduct(@PathVariable Long id)
    {
        Product product = productService.findById(id);
        product.deleteProduct();
        return ResponseEntity.noContent().build();
    }
}
