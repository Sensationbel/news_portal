package by.bulaukin.news_portal.web.controller;

import by.bulaukin.news_portal.mapper.NewsMapper;
import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.services.NewsService;
import by.bulaukin.news_portal.web.model.response.NewsListResponse;
import by.bulaukin.news_portal.web.model.response.NewsResponse;
import by.bulaukin.news_portal.web.model.request.UpsertNewsRequest;
import by.bulaukin.news_portal.web.model.filter.NewsFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;
    private final NewsMapper newsMapper;

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid NewsFilter filter) {
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(newsService.findAll(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(newsMapper.newsToResponse(newsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid UpsertNewsRequest request) {
        News news = newsService.save(newsMapper.requestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                newsMapper.newsToResponse(news));

    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsResponse> update(@PathVariable("id") Long newsId,
                                               @RequestBody @Valid UpsertNewsRequest request) {
        News existingNews = newsService.update(newsMapper.requestToNews(newsId, request));
        return ResponseEntity.ok(
                newsMapper.newsToResponse(existingNews));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long newsId) {
        newsService.deleteById(newsId);
        return ResponseEntity.noContent().build();
    }
}
