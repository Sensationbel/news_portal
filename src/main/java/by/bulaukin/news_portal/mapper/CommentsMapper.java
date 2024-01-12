package by.bulaukin.news_portal.mapper;

import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.web.model.response.CommentsListResponse;
import by.bulaukin.news_portal.web.model.response.CommentsResponse;
import by.bulaukin.news_portal.web.model.request.UpsertCommentRequest;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(CommentsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentsMapper {

    Comment requestToComment(UpsertCommentRequest request);

    @Mapping(source = "contentId", target = "id")
    Comment requestToComment(Long contentId, UpsertCommentRequest request);

    @Mapping(source = "text", target = "comment")
    CommentsResponse commentToResponse(Comment comment);

    List<CommentsResponse> commentsListToResponseList(List<Comment> comments);

    default CommentsListResponse commentsListToCommentsListResponse(List<Comment> comments) {
        CommentsListResponse commentsListResponse = new CommentsListResponse();
        List<CommentsResponse> commentsList = commentsListToResponseList(comments);
        commentsListResponse.setCommentsList(commentsList);
        return commentsListResponse;
    }


}
