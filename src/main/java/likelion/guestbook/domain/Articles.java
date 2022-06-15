package likelion.guestbook.domain;

import java.util.ArrayList;
import java.util.List;

public class Articles {
    private final List<Article> articles;
    private final OwnerId ownerId;

    public Articles(List<Article> articles, OwnerId ownerId) {
        this.articles = articles;
        this.ownerId = ownerId;
    }

    public Articles(OwnerId ownerId) {
        this.articles = new ArrayList<>();
        this.ownerId = ownerId;
    }

    public List<Article> getArticles() {
        return List.copyOf(articles);
    }

    @Override
    public String toString() {
        return "Articles{" +
                "articles=" + articles +
                ", ownerId=" + ownerId +
                '}';
    }
}
