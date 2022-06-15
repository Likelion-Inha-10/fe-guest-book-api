package likelion.guestbook.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import likelion.guestbook.domain.Article;

public class ArticleResponseDto {
    private final long id;
    private final String title;
    private final String body;
    private final LocalDateTime createdAt;

    public ArticleResponseDto(long id, String title, String body, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    public ArticleResponseDto(Article article) {
        this(article.getId(), article.getTitle().getValue(), article.getBody().getValue(), article.getCreatedAt());
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArticleResponseDto that = (ArticleResponseDto) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(body, that.body)
                && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, body, createdAt);
    }
}
