package by.bulaukin.news_portal.mapper;

import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.web.model.request.UpsertNewsRequest;
import by.bulaukin.news_portal.web.model.response.NewsListResponse;
import by.bulaukin.news_portal.web.model.response.NewsResponse;
import org.mapstruct.*;

import java.util.List;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommentsMapper.class})
public interface NewsMapper {

    @Mapping(source = "userId", target = "user.id")
    News requestToNews(Long userId, UpsertNewsRequest request);

    @Mappings({
            @Mapping(source = "contentId", target = "id"),
            @Mapping(source = "userId", target = "user.id")
    })
    News requestToNews(Long contentId, Long userId, UpsertNewsRequest request);

    NewsResponse newsToResponse(News news);

    List<NewsResponse> newsListToResponsList(List<News> newsList);

    default NewsListResponse newsListToNewsListResponse(List<News> newsList) {
        NewsListResponse response = new NewsListResponse();
        response.setNewsList(newsListToResponsList(newsList));
        return response;
    }

}
