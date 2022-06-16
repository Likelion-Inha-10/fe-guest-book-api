package likelion.guestbook.dto;

import io.swagger.annotations.ApiModelProperty;

public class ArticleRequestDto {
    @ApiModelProperty(name = "방명록 게시글 제목", example = "안녕하세요.")
    private String title;
    @ApiModelProperty(name = "방명록 게시글 본문", example = "굉장히 오랜만이네요. 그간 잘 지내셨나요?")
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
