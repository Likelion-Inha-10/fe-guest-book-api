package likelion.guestbook.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Article implements Comparable<Article> {
    private final Long id;
    private final Title title;
    private final Body body;
    private final LocalDateTime createdAt;

    public Article(Long id, String title, String body, LocalDateTime createdAt) {
        this.id = id;
        this.title = new Title(title);
        this.body = new Body(body);
        this.createdAt = createdAt;
    }

    public Article(String title, String body, LocalDateTime createdAt) {
        this(null, title, body, createdAt);
    }

    public Article(String title, String body) {
        this(null, title, body, LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Body getBody() {
        return body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public int compareTo(Article other) {
        Timestamp timestamp = Timestamp.valueOf(other.createdAt);
        Timestamp otherTimestamp = Timestamp.valueOf(createdAt);

        return (int) (timestamp.getTime() - otherTimestamp.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Article article = (Article) o;
        return Objects.equals(title, article.title) && Objects.equals(body, article.body)
                && Objects.equals(createdAt, article.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, body, createdAt);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title=" + title +
                ", body=" + body +
                ", createdAt=" + createdAt +
                '}';
    }
}
