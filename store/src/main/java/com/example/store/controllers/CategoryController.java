package com.example.store.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.entities.CategoryEntity;
import com.example.store.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/category")
@Tag(name = "Categories", description = "Categories crud")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Operation(
        responses = @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryEntity.class)))
    ))
    @GetMapping()
    public ResponseEntity<List<CategoryEntity>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.filterAllCategoriesService());
    }

    @Operation(
        responses = { 
             @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CategoryEntity.class)))),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
        }
    )
    @GetMapping("/search")
    public ResponseEntity<List<CategoryEntity>> getCategoryByName(@RequestParam String nome) {
        return ResponseEntity.ok().body(categoryService.filterByNome(nome));
    }
    
    @Operation(
        responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryEntity.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
        }
    )
    @PostMapping()
    public ResponseEntity<CategoryEntity> postCategory(@RequestBody CategoryEntity entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.postCategoryService(entity));
    }

    @Operation(
        responses = {
            @ApiResponse(description = "Created", responseCode = "202", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryEntity.class))),
            @ApiResponse(description = "Bad request", responseCode = "400", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
        
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<CategoryEntity> putCategoryEntity(@PathVariable Long id, @RequestBody CategoryEntity entity) throws BadRequestException {
        entity.setId(id);
        return ResponseEntity.accepted().body(categoryService.putCategoryService(entity));
    }


    @Operation(
        responses = {
            @ApiResponse(description = "No body", responseCode = "204"),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class))),
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategoryController(@PathVariable Long id){
        categoryService.deleteCategoryService(id);
        return ResponseEntity.noContent().build();
    }
    
}
