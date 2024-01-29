package by.bulaukin.news_portal.web.controller;

import by.bulaukin.news_portal.mapper.UsersMapper;
import by.bulaukin.news_portal.model.User;
import by.bulaukin.news_portal.services.UsersService;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;
import by.bulaukin.news_portal.web.model.request.UpsertUserRequest;
import by.bulaukin.news_portal.web.model.response.ErrorResponse;
import by.bulaukin.news_portal.web.model.response.UserResponse;
import by.bulaukin.news_portal.web.model.response.UsersListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "User V1", description = "User API version V1")
public class UsersController {

    private final UsersService usersService;
    private final UsersMapper usersMapper;

    @Operation(
            summary = "Get users",
            description = "Get a list of users. Return a list of users.",
            tags = {"users"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UsersListResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @GetMapping
    public ResponseEntity<UsersListResponse> findAll(@Valid EntityFilter filter) {
        return ResponseEntity.ok(usersMapper.usersListToUsersListResponse(
                usersService.findAll(filter)
        ));
    }

    @Operation(
            summary = "Get user by Id.",
            description = "Get user by user id. Return user Id, user name, email and a list of news.",
            tags = {"users", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usersMapper.userToResponse(usersService.findById(id)));
    }

    @Operation(
            summary = "Create a new user",
            description = "Create a new user. Return user Id, user name, email and a list of news.",
            tags = {"users"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request) {
        User user = usersService.save(usersMapper.requestToUser(request));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usersMapper.userToResponse(user));
    }

    @Operation(
            summary = "Update user by Id.",
            description = "Update user by Id. Return user Id, user name, email and a list of news.",
            tags = {"users", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = {
                            @Content(schema = @Schema(implementation = UserResponse.class),
                                    mediaType = "application/json")
                    }

            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class))
                    }
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") Long userId,
                                               @RequestBody @Valid UpsertUserRequest request) {
        User updatedUser = usersService.update(usersMapper.requestToUser(userId, request));
        return ResponseEntity.ok(usersMapper.userToResponse(updatedUser));
    }

    @Operation(
            summary = "Delete user by Id.",
            description = "Delete user by Id.",
            tags = {"users", "id"}
    )

    @ApiResponse(
            responseCode = "204"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
