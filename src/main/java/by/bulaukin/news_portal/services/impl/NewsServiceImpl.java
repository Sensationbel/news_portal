package by.bulaukin.news_portal.services.impl;

import by.bulaukin.news_portal.exception.EntityNotFoundException;
import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.repository.NewsRepository;
import by.bulaukin.news_portal.services.NewsService;
import by.bulaukin.news_portal.web.model.filter.NewsFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Override
    public List<News> findAll(NewsFilter filter) {
        return newsRepository.findAll(PageRequest.of(filter.getPageNum(), filter.getPageSize())).getContent();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(MessageFormat.format("News with id: {0} is not found", id)));
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @SneakyThrows
    @Override
    public News update(News news) {
        News exsistNews = findById(news.getId());

        BeanUtils.copyProperties(exsistNews, news);
        return newsRepository.save(news);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}
