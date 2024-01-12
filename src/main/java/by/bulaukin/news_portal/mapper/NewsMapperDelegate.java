package by.bulaukin.news_portal.mapper;

import by.bulaukin.news_portal.model.Comment;
import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.services.CategoriesService;
import by.bulaukin.news_portal.services.CommentsService;
import by.bulaukin.news_portal.services.UsersService;
import by.bulaukin.news_portal.web.model.filter.CommentsFilter;
import by.bulaukin.news_portal.web.model.request.UpsertNewsRequest;
import by.bulaukin.news_portal.web.model.response.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class NewsMapperDelegate implements NewsMapper{

    @Autowired
    private UsersService userService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsMapper commentsMapper;


    @Override
    public News requestToNews(UpsertNewsRequest request) {
        News news = new by.bulaukin.news_portal.model.News();
        news.setNewsText(request.getText());
        news.setUser(userService.findById(request.getUserId()));
        news.setCategory(categoriesService.findCategoryByCode(request.getCodeNews()));
        return news;
    }

    @Override
    public News requestToNews(Long newsId, UpsertNewsRequest request) {
        News existedNews = requestToNews(request);
        existedNews.setId(newsId);
        return existedNews;
    }

    @Override
    public List<NewsResponse> newsListToResponsList(List<News> newsList) {
        if (newsList == null) {
            return null;
        } else return newsList.stream().map(this::newsToResponse).toList();
    }

    @Override
    public NewsResponse newsToResponse(News news) {
        NewsResponse response = new NewsResponse();
        response.setText(news.getNewsText());
        response.setNewsCategory(news.getCategory().getType().name());
        response.setUserId(news.getUser().getId());
        getComments(news);
        response.setCommentsCount(getComments(news).size());
        return response;
    }

    private List<Comment> getComments(News news) {
        CommentsFilter filter = new CommentsFilter();
        filter.setNewsId(news.getId());
        List<Comment> comments = commentsService.findAllByNewsId(filter);
        return comments;
    }

//    private List<CommentsResponse>
}
