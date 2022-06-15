package likelion.guestbook.domain;

import java.time.LocalDateTime;

public class Article {
    private final Title title;
    private final Body body;
    private final LocalDateTime createdAt;

    public Article(String title, String body, LocalDateTime createdAt) {
        this.title = new Title(title);
        this.body = new Body(body);
        this.createdAt = createdAt;
    }

    public Article(String title, String body) {
        this(title, body, LocalDateTime.now());
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
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
