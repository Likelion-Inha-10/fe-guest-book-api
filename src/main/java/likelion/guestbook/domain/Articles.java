package likelion.guestbook.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Articles {
    private final List<Article> articles;
    private final OwnerId ownerId;

    public Articles(List<Article> articles, OwnerId ownerId) {
        this.articles = getSortedArticles(articles);
        this.ownerId = ownerId;
    }

    public Articles(OwnerId ownerId) {
        this(new ArrayList<>(), ownerId);
    }

    private List<Article> getSortedArticles(List<Article> articles) {
        ArrayList<Article> sorted = new ArrayList<>(articles);
        Collections.sort(sorted);

        return sorted;
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
