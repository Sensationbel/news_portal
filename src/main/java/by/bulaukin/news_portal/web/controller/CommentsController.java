package by.bulaukin.news_portal.web.controller;

import by.bulaukin.news_portal.mapper.CommentsMapper;
import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.services.CommentsService;
import by.bulaukin.news_portal.web.model.request.UpsertCommentRequest;
import by.bulaukin.news_portal.web.model.response.CommentsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentsController {

    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;

    @PostMapping
    public ResponseEntity<CommentsResponse> create(@RequestBody @Valid UpsertCommentRequest request) {
        Comment existsComment = commentsService.save(commentsMapper.requestToComment(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                commentsMapper.commentToResponse(existsComment)
        );
    }

}
