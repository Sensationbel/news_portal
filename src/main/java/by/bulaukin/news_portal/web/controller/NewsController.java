package by.bulaukin.news_portal.web.controller;

import by.bulaukin.news_portal.mapper.NewsMapper;
import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.services.NewsService;
import by.bulaukin.news_portal.services.users_check_aspect.users_aspect.UsersChecker;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;
import by.bulaukin.news_portal.web.model.request.UpsertNewsRequest;
import by.bulaukin.news_portal.web.model.response.NewsListResponse;
import by.bulaukin.news_portal.web.model.response.NewsResponse;
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
    @UsersChecker
    public ResponseEntity<NewsListResponse> findAll(@Valid EntityFilter filter) {
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(newsService.findAllWithFilter(filter)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(newsMapper.newsToResponse(newsService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestParam Long userId, @RequestBody @Valid UpsertNewsRequest request) {
        News news = newsService.save(newsMapper.requestToNews(userId, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                newsMapper.newsToResponse(news));

    }

    @PutMapping("/update")
    public ResponseEntity<NewsResponse> update(@RequestParam Long contentId, @RequestParam Long userId,
                                               @RequestBody @Valid UpsertNewsRequest request) {
       News existingNews = newsService.update(newsMapper.requestToNews(contentId, userId, request));
        return ResponseEntity.ok(
                newsMapper.newsToResponse(existingNews));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@RequestParam Long newsId) {
        newsService.deleteById(newsId);
        return ResponseEntity.noContent().build();
    }
}
