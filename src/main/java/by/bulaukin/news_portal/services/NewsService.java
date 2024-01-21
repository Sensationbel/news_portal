package by.bulaukin.news_portal.services;

import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;

import java.util.List;

public interface NewsService {

    List<News> findAllWithFilter(EntityFilter filter);
    News findById(Long id);
    News save(News news);
    News update(News news);
    void deleteById(Long id);


}
