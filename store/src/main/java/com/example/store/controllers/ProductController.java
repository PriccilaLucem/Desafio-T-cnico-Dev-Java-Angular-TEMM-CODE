package com.example.store.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dto.ProductCategoryDTO;
import com.example.store.entities.ProductEntity;
import com.example.store.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/product")
@Tag(name = "Products", description = "Products crud")
public class ProductController {
    
    @Autowired
    private ProductService productService;


    @Operation(
        responses = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(schema = @Schema(implementation = ProductEntity.class)))
        }
    )
    @PostMapping()
    public ResponseEntity<ProductEntity> postProductController(@RequestBody ProductCategoryDTO entity) {        
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.postProductService(entity));
    }

    @Operation(
        responses = {
            @ApiResponse(description = "Ok", responseCode = "200", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductEntity.class))))
        }
    )
    @GetMapping()
    public  ResponseEntity<List<ProductEntity>>  getAllProductsController(@RequestParam(required = false) String param) {
        return ResponseEntity.ok().body(productService.filterAllProductsService());
    }
    
    @Operation(
        responses = {
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
            @ApiResponse(description = "Ok", responseCode = "200", content = @Content(schema = @Schema(implementation = ProductEntity.class)))
        }
    )
    @GetMapping("/{product_id}")
    public ResponseEntity<ProductEntity> getOneProductController(@PathVariable(name = "product_id") Long id) {
        return ResponseEntity.ok().body(productService.filterByProductIdService(id));
    }
    
    @Operation(
        responses = {
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
            @ApiResponse(description = "Accepted", responseCode = "202", content = @Content(schema = @Schema(implementation = ProductEntity.class))),

        } 
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> putProductController(@PathVariable Long id, @RequestBody ProductEntity entity) throws BadRequestException {
        entity.setId(id);
        return ResponseEntity.accepted().body(productService.putProductService(entity));
    }

    @Operation(
        responses = {
            @ApiResponse(description = "No body", responseCode = "204"),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductController(@PathVariable Long id){
        productService.deleteProductsService(id);
        return ResponseEntity.noContent().build();
    }
}
