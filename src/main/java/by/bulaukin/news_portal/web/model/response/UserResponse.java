package by.bulaukin.news_portal.web.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponse {

    private Long userId;
    private String username;
    private String email;
    private List<NewsResponse> newsList = new ArrayList<>();
    private List<CommentsResponse> comments = new ArrayList<>();
}
