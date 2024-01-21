package by.bulaukin.news_portal.web.controller;

import by.bulaukin.news_portal.mapper.CommentsMapper;
import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.services.CommentsService;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;
import by.bulaukin.news_portal.web.model.request.UpsertCommentRequest;
import by.bulaukin.news_portal.web.model.response.CommentsListResponse;
import by.bulaukin.news_portal.web.model.response.CommentsResponse;
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

    @GetMapping
    public ResponseEntity<CommentsListResponse> findAllByNewsId(@Valid EntityFilter filter) {
        return ResponseEntity.ok(
                commentsMapper.commentsListToCommentsListResponse(commentsService.findAllByNewsId(filter))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                commentsMapper.commentToResponse(commentsService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<CommentsResponse> create(@RequestParam Long userId,
                                                   @RequestBody @Valid UpsertCommentRequest request) {
        Comment existsComment = commentsService.save(commentsMapper.requestToComment(userId, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                commentsMapper.commentToResponse(existsComment)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<CommentsResponse> update(@RequestParam Long contentId,
                                                   @RequestParam Long userId,
            @RequestBody @Valid UpsertCommentRequest request) {
        Comment existsComment = commentsService.update(commentsMapper.requestToComment(contentId, userId, request));
        return ResponseEntity.ok(
                commentsMapper.commentToResponse(existsComment)
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@RequestParam Long contentId) {
        commentsService.deleteById(contentId);
        return ResponseEntity.noContent().build();
    }


}
