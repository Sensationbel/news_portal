package by.bulaukin.news_portal.mapper;

import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.web.model.request.UpsertNewsRequest;
import by.bulaukin.news_portal.web.model.response.NewsListResponse;
import by.bulaukin.news_portal.web.model.response.NewsResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommentsMapper.class})
public interface NewsMapper {

    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "contentId", target = "id")
    News requestToNews(Long contentId, UpsertNewsRequest request);

    NewsResponse newsToResponse(News news);

    List<NewsResponse> newsListToResponsList(List<News> newsList);

    default NewsListResponse newsListToNewsListResponse(List<News> newsList) {
        NewsListResponse response = new NewsListResponse();
        response.setNewsList(newsListToResponsList(newsList));
        return response;
    }

}
