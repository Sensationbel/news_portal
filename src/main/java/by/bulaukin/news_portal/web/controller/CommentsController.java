package by.bulaukin.news_portal.web.controller;

import by.bulaukin.news_portal.mapper.CommentsMapper;
import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.services.CommentsService;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;
import by.bulaukin.news_portal.web.model.request.UpsertCommentRequest;
import by.bulaukin.news_portal.web.model.response.CommentsListResponse;
import by.bulaukin.news_portal.web.model.response.CommentsResponse;
import by.bulaukin.news_portal.web.model.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;

    @Operation(
            summary = "Get All comments.",
            description = "Get all comments, or all comments by filter(page size, page num, category and users)." +
                    " Return a list of comments.",
            tags = {"comments"}

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = CommentsListResponse.class),
                            mediaType = "application/json")
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
    public ResponseEntity<CommentsListResponse> findAllByFilter(@Valid EntityFilter filter) {
        return ResponseEntity.ok(
                commentsMapper.commentsListToCommentsListResponse(commentsService.findAllByFilter(filter))
        );
    }

    @Operation(
            summary = "Get comment by Id.",
            description = "Get comment by comment id. Return text of comments.",
            tags = {"comments", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = CommentsResponse.class),
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
    public ResponseEntity<CommentsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentsMapper.commentToResponse(commentsService.findById(id))
        );
    }

    @Operation(
            summary = "Create a new comment.",
            description = "Create a new comment. Return a text of comments.",
            tags = {"comments"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = CommentsResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
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
    @PostMapping
    public ResponseEntity<CommentsResponse> create(@RequestParam Long userId,
                                                   @RequestBody @Valid UpsertCommentRequest request) {
        Comment existsComment = commentsService.save(commentsMapper.requestToComment(userId, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                commentsMapper.commentToResponse(existsComment)
        );
    }

    @Operation(
            summary = "Update comments by user id & news id.",
            description = "Update comments by user id & news id. Return text of comments.",
            tags = {"comments"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = {
                            @Content(schema = @Schema(implementation = CommentsResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
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
                    responseCode = "406",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @PutMapping("/update")
    public ResponseEntity<CommentsResponse> update(@RequestParam Long contentId,
                                                   @RequestParam Long userId,
            @RequestBody @Valid UpsertCommentRequest request) {
        Comment existsComment = commentsService.update(commentsMapper.requestToComment(contentId, userId, request));
        return ResponseEntity.ok(
                commentsMapper.commentToResponse(existsComment)
        );
    }

    @Operation(
            summary = "Delete a comment by comment id.",
            description = "Delete comments by comment id with checking user's right",
            tags = {"comments"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204"
            ),
            @ApiResponse(
                    responseCode = "406",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@RequestParam Long contentId,
                                           @RequestParam Long userId) {
        commentsService.deleteById(contentId);
        return ResponseEntity.noContent().build();
    }


}
