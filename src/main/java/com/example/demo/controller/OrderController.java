package com.example.demo.controller;

import com.example.demo.dto.order.DtoOrder;
import com.example.demo.dto.order.DtoOrderUpdate;
import com.example.demo.dto.order.DtoOrderView;
import com.example.demo.dto.product.DtoProduct;
import com.example.demo.dto.product.DtoProductUpdate;
import com.example.demo.dto.product.DtoProductView;
import com.example.demo.dto.user.DtoUserLogin;
import com.example.demo.entities.Order;
import com.example.demo.entities.Product;
import com.example.demo.service.IOrderService;
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
@RequestMapping("/order")
@AllArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class OrderController {


    IOrderService orderService;

    @Operation(summary = "Añadir pedido", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoOrder.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @PostMapping
    public ResponseEntity<DtoOrderView> addOrder(@RequestBody @Valid DtoOrder dtoOrder, UriComponentsBuilder uriComponentsBuilder)
    {
        Order order = orderService.save(dtoOrder);

        DtoOrderView dtoOrderView = new DtoOrderView(order);

        URI url = uriComponentsBuilder.path("/order/{id}").buildAndExpand(order.getOrders_id()).toUri();

        return ResponseEntity.created(url).body(dtoOrderView);
    }

    @Operation(summary = "Actualizar pedido", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DtoOrderUpdate.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @Transactional
    @PutMapping
    public ResponseEntity updateOrder(@RequestBody @Valid DtoOrderUpdate dtoOrderUpdate)
    {
        Order order = orderService.updateOrder(dtoOrderUpdate);
        DtoOrderView dtoOrderView = new DtoOrderView(order);

        return ResponseEntity.ok(dtoOrderView);
    }

    @Operation(summary = "Ver pedidos", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pageable.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @GetMapping
    public ResponseEntity<Page<DtoOrderView>> viewOrders(@PageableDefault(size = 10) Pageable paginacion)
    {
        return ResponseEntity.ok(orderService.findAll(paginacion).map(DtoOrderView::new));
    }

    @Operation(summary = "Ver pedido", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @GetMapping("{id}")
    public ResponseEntity<DtoOrderView> viewOrder(@PathVariable Long id)
    {
        Order order = orderService.findById(id);

        DtoOrderView dtoOrderView = new DtoOrderView(order);

        return ResponseEntity.ok(dtoOrderView);
    }

    @Operation(summary = "Eliminar pedido", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity deleteOrder(@PathVariable Long id)
    {
        Order order = orderService.findById(id);
        order.deleteOrder();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Ver pedidos por producto", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pageable.class ),additionalPropertiesSchema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content) })
    @GetMapping("/search_by_product/{id}")
    public ResponseEntity<Page<DtoOrderView>> viewOrdersByProduct(@PathVariable Long id,@PageableDefault(size = 10) Pageable paginacion)
    {
        return ResponseEntity.ok(orderService.findAllByProduct(paginacion,id).map(DtoOrderView::new));
    }
}
