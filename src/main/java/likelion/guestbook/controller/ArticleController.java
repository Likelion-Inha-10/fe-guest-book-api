package likelion.guestbook.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import java.util.List;
import likelion.guestbook.domain.OwnerId;
import likelion.guestbook.dto.ArticleRequestDto;
import likelion.guestbook.dto.ArticleResponseDto;
import likelion.guestbook.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "방명록", tags = "이미지와 이미지의 댓글을 관리합니다.")
@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Operation(summary = "특정 유저에 방명록 생성", description = "특정 유저에 대한 방명록을 생성합니다.")
    @PostMapping("/{ownerId}/articles")
    public ResponseEntity<ArticleResponseDto> createArticle(@PathVariable String ownerId,
                                                            @RequestBody ArticleRequestDto articleRequestDto) {
        Long id = articleService.createArticle(new OwnerId(ownerId), articleRequestDto);
        return ResponseEntity.created(URI.create("/" + ownerId + "/articles/" + id)).build();
    }

    @Operation(summary = "특정 유저 방명록 목록 조회", description = "특정 유저의 이름을 전달하여 유저의 방명록을 모두 조회합니다.")
    @GetMapping("/{ownerId}/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllByOwnerId(@PathVariable String ownerId) {
        List<ArticleResponseDto> articleResponseDtos = articleService.findAllByOwnerId(new OwnerId(ownerId));
        return ResponseEntity.ok(articleResponseDtos);
    }

    @Operation(summary = "특정 방명록 조회", description = "방명록을 ID로 단건 조회합니다.")
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponseDto> findById(@PathVariable String id) {
        ArticleResponseDto articleResponseDto = articleService.findById(Long.valueOf(id));
        return ResponseEntity.ok(articleResponseDto);
    }

    @Operation(summary = "방명록 수정", description = "방명록을 수정합니다.")
    @PutMapping("/articles/{id}")
    public ResponseEntity<Void> updateArticle(@PathVariable String id,
                                              @RequestBody ArticleRequestDto articleRequestDto) {
        articleService.updateArticle(Long.valueOf(id), articleRequestDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "방명록 제거", description = "방명록을 제거합니다.")
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        articleService.deleteArticle(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}
