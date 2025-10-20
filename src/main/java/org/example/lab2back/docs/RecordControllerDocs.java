package org.example.lab2back.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lab2back.entity.RecordEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "Record API",
        description = """
                Provides CRUD operations for managing financial or user records.
                Allows filtering records by user ID or category ID.
                """
)
public interface RecordControllerDocs {

    @Operation(
            summary = "Get records (optional filtering)",
            description = """
                    Returns a list of records.
                    You can optionally provide.
                    - **userId** → to get all records for a specific user
                    - **categoryId** → to get all records belonging to a category
                    If no parameters are provided, throws error.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of records retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RecordEntity.class))
                    )
            }
    )
    ResponseEntity<List<RecordEntity>> getRecordsByUserIdAndCategoryId(
            @Parameter(description = "Optional: filter records by user UUID") UUID userId,
            @Parameter(description = "Optional: filter records by category UUID") UUID categoryId
    );

    @Operation(
            summary = "Get record by ID",
            description = """
                    Fetches a record from the database using its unique UUID.
                    Returns the full record details if found.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Record retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RecordEntity.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "Record not found")
            }
    )
    ResponseEntity<RecordEntity> getRecordById(
            @Parameter(description = "Unique identifier of the record (UUID)", required = true)
            UUID id
    );

    @Operation(
            summary = "Delete record",
            description = """
                    Deletes a record identified by its UUID.
                    Returns HTTP 204 (No Content) when successful,
                    or HTTP 404 if no record with such ID exists.
                    """,
            responses = {
                    @ApiResponse(responseCode = "204", description = "Record deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Record not found")
            }
    )
    ResponseEntity<String> deleteRecordById(
            @Parameter(description = "Unique identifier of the record (UUID)", required = true)
            UUID id
    );

    @Operation(
            summary = "Create new record",
            description = """
                    Creates a new record and stores it in the database.
                    The request body must include valid JSON representing a RecordEntity.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Record created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RecordEntity.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request body or missing fields")
            }
    )
    ResponseEntity<RecordEntity> createRecord(
            @Parameter(description = "Record object to be created", required = true)
            RecordEntity oneRecord
    );
}
