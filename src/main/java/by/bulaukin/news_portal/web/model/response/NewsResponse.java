package by.bulaukin.news_portal.web.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsResponse {

    private Long userId;
    private String newsCategory;
    private String text;
    private Integer commentsCount;
}
