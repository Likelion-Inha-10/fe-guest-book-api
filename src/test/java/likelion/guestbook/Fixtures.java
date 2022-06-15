package likelion.guestbook;

import likelion.guestbook.domain.Article;
import likelion.guestbook.domain.OwnerId;
import likelion.guestbook.dto.ArticleRequestDto;

public class Fixtures {
    public static OwnerId OWNER_ID_1 = new OwnerId("devHudi");

    public static Article ARTICLE_1 = new Article("hello", "world");
    public static Article ARTICLE_2 = new Article("안녕", "세상아");
    public static Article ARTICLE_3 = new Article("반갑습니다", "주인장님");

    public static ArticleRequestDto ARTICLE_REQUEST_DTO_1 = new ArticleRequestDto("hello", "world");
    public static ArticleRequestDto ARTICLE_REQUEST_DTO_2 = new ArticleRequestDto("안녕", "세상아");
    public static ArticleRequestDto ARTICLE_REQUEST_DTO_3 = new ArticleRequestDto("반갑습니다", "주인장님");
}
