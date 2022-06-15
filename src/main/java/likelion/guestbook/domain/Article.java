package likelion.guestbook.domain;

public class Article {
    private final Title title;
    private final Body body;

    public Article(String title, String body) {
        this.title = new Title(title);
        this.body = new Body(body);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title=" + title +
                ", body=" + body +
                '}';
    }
}
