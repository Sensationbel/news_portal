package by.bulaukin.news_portal.web.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentsListResponse {

    List<CommentsResponse> commentsList = new ArrayList<>();

}
