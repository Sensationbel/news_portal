package by.bulaukin.news_portal.web.model.request;

import lombok.Data;

@Data
public class UpsertCommentRequest {

    private Long userId;
    private Long newsId;
    private String text;

}
