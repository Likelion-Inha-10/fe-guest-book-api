package likelion.guestbook.dto;

public class ArticleRequestDto {
    private String title;
    private String body;

    public ArticleRequestDto() {
    }

    public ArticleRequestDto(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "ArticleRequestDto{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
