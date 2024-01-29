package by.bulaukin.news_portal.web.controller;

import by.bulaukin.news_portal.mapper.NewsMapper;
import by.bulaukin.news_portal.model.News;
import by.bulaukin.news_portal.services.NewsService;
import by.bulaukin.news_portal.web.model.filter.EntityFilter;
import by.bulaukin.news_portal.web.model.request.UpsertNewsRequest;
import by.bulaukin.news_portal.web.model.response.ErrorResponse;
import by.bulaukin.news_portal.web.model.response.NewsListResponse;
import by.bulaukin.news_portal.web.model.response.NewsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Get All news.",
            description = "Get all news, or all News by filter(page size, page num, category and users). Return a list of news",
            tags = {"news"}

    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = NewsListResponse.class),
                    mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(@Valid EntityFilter filter) {
        return ResponseEntity.ok(newsMapper.newsListToNewsListResponse(newsService.findAllWithFilter(filter)));
    }

    @Operation(
            summary = "Get news by Id.",
            description = "Get news by news id. Return user Id, category of news, text of news and count of comments.",
            tags = {"news", "id"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = NewsResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(newsMapper.newsToResponse(newsService.findById(id)));
    }

    @Operation(
            summary = "Create a new news.",
            description = "Create a new news. Return user Id, category of news, text of news and count of comments.",
            tags = {"news"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = NewsResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestParam Long userId, @RequestBody @Valid UpsertNewsRequest request) {
        News news = newsService.save(newsMapper.requestToNews(userId, request));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                newsMapper.newsToResponse(news));

    }

    @Operation(
            summary = "Update News by user id & news id.",
            description = "Update News by user id & news id. Return user Id, category of news, text of news and count of comments.",
            tags = {"news"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    content = {
                            @Content(schema = @Schema(implementation = NewsResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "406",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @PutMapping("/update")
    public ResponseEntity<NewsResponse> update(@RequestParam Long contentId, @RequestParam Long userId,
                                               @RequestBody @Valid UpsertNewsRequest request) {
       News existingNews = newsService.update(newsMapper.requestToNews(contentId, userId, request));
        return ResponseEntity.ok(
                newsMapper.newsToResponse(existingNews));
    }

    @Operation(
            summary = "Delete News by news id .",
            description = "Delete News by news id with checking user's right.",
            tags = {"news"}
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204"
            ),
            @ApiResponse(
                    responseCode = "406",
                    content = {
                            @Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")
                    }
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteById(@RequestParam Long contentId,
                                           @RequestParam Long userId) {
        newsService.deleteById(contentId);
        return ResponseEntity.noContent().build();
    }
}
