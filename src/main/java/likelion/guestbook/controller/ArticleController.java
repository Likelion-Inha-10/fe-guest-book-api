package likelion.guestbook.controller;

import java.net.URI;
import java.util.List;
import likelion.guestbook.domain.OwnerId;
import likelion.guestbook.dto.ArticleRequestDto;
import likelion.guestbook.dto.ArticleResponseDto;
import likelion.guestbook.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/{ownerId}/articles")
    public ResponseEntity<ArticleResponseDto> createArticle(@PathVariable String ownerId,
                                                            @RequestBody ArticleRequestDto articleRequestDto) {
        Long id = articleService.createArticle(new OwnerId(ownerId), articleRequestDto);
        return ResponseEntity.created(URI.create("/" + ownerId + "/articles/" + id)).build();
    }

    @GetMapping("/{ownerId}/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllByOwnerId(@PathVariable String ownerId) {
        List<ArticleResponseDto> articleResponseDtos = articleService.findAllByOwnerId(new OwnerId(ownerId));
        return ResponseEntity.ok(articleResponseDtos);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponseDto> findById(@PathVariable String id) {
        ArticleResponseDto articleResponseDto = articleService.findById(Long.valueOf(id));
        return ResponseEntity.ok(articleResponseDto);
    }

    @PutMapping("/articles/{id}")
    public ResponseEntity<Void> updateArticle(@PathVariable String id,
                                              @RequestBody ArticleRequestDto articleRequestDto) {
        articleService.updateArticle(Long.valueOf(id), articleRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        articleService.deleteArticle(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}
