package org.example.lab2back.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lab2back.entity.CategoryEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Category API",
        description = """
                Provides CRUD operations for managing categories:
                - Retrieve all categories
                - Get a category by ID
                - Create a new category
                - Delete an existing category
                """
)
public interface CategoryControllerDocs {

    @Operation(
            summary = "Get all categories",
            description = """
                    Returns a complete list of categories stored in the database.
                    Useful for displaying category options or managing them in admin panels.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categories retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryEntity.class))
                    )
            }
    )
    ResponseEntity<List<CategoryEntity>> getCategories();

    @Operation(
            summary = "Get category by ID",
            description = """
                    Fetches a specific category based on its unique identifier (UUID).
                    Returns 404 if no category with such ID exists.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryEntity.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Category not found")
            }
    )
    ResponseEntity<CategoryEntity> getCategoryById(
            @Parameter(description = "Unique identifier of the category (UUID)", required = true)
            UUID id
    );

    @Operation(
            summary = "Create new category",
            description = """
                    Creates and saves a new category record in the database.
                    The request body must include valid category data in JSON format.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CategoryEntity.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid category data")
            }
    )
    ResponseEntity<CategoryEntity> createCategory(
            @Parameter(description = "Category object to be created", required = true)
            CategoryEntity category
    );

    @Operation(
            summary = "Delete category",
            description = """
                    Deletes an existing category using its UUID.
                    Returns a success message when deletion is complete.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Category deleted successfully",
                            content = @Content(mediaType = "text/plain")
                    ),
                    @ApiResponse(responseCode = "404", description = "Category not found")
            }
    )
    ResponseEntity<String> deleteCategory(
            @Parameter(description = "Unique identifier of the category (UUID)", required = true)
            UUID id
    );
}
