package likelion.guestbook;

import likelion.guestbook.domain.Article;
import likelion.guestbook.domain.OwnerId;

public class Fixtures {
    public static OwnerId OWNER_ID_1 = new OwnerId("devHudi");
    public static Article ARTICLE_1 = new Article("hello", "world");
    public static Article ARTICLE_2 = new Article("안녕", "세상아");
    public static Article ARTICLE_3 = new Article("반갑습니다", "주인장님");
}
