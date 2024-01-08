package by.bulaukin.news_portal.web.model.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class NewsListResponse {

    private List<NewsResponse> newsList = new ArrayList<>();

}
