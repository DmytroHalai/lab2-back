package org.example.lab2back.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.lab2back.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "User API",
        description = """
                Provides CRUD operations for managing users:
                - Get all users
                - Retrieve a user by ID
                - Create a new user
                - Delete an existing user
                """
)
public interface UserControllerDocs {

    @Operation(
            summary = "Get all users",
            description = """
                    Returns a full list of all registered users in the system.
                    Useful for administrative interfaces or testing endpoints.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of users retrieved successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserEntity.class))
                    )
            }
    )
    ResponseEntity<List<UserEntity>> getAllUsers();

    @Operation(
            summary = "Get user by ID",
            description = """
                    Fetches a single user record based on its unique Long.
                    Returns full user details if found; otherwise, a 404 error.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User found successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserEntity.class))
                    ),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    ResponseEntity<UserEntity> getUserById(
            @Parameter(description = "Unique identifier of the user (Long)", required = true)
            Long id
    );

    @Operation(
            summary = "Create new user",
            description = """
                    Creates a new user entry in the database.
                    The request body must contain a valid JSON representing UserEntity.
                    Returns the created user with HTTP 201 (Created) and a location header.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "User created successfully",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserEntity.class))
                    ),
                    @ApiResponse(responseCode = "400", description = "Invalid request data")
            }
    )
    ResponseEntity<UserEntity> createUser(
            @Parameter(description = "User object containing username and other fields", required = true)
            UserEntity user
    );

    @Operation(
            summary = "Delete user",
            description = """
                    Deletes a user identified by their Long.
                    Returns a confirmation message upon successful deletion.
                    """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User deleted successfully",
                            content = @Content(mediaType = "text/plain")
                    ),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
    ResponseEntity<String> deleteUser(
            @Parameter(description = "Unique identifier of the user (Long)", required = true)
            Long id
    );
}
