package by.bulaukin.news_portal.web.model.request;

import by.bulaukin.news_portal.validator.EntityFilterValid;
import lombok.Data;

@Data
@EntityFilterValid
public class UpsertCommentRequest {
    private Long newsId;
    private String text;

}
