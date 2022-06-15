package likelion.guestbook.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArticleTest {
    @DisplayName("제목과 본문을 String 형태로 받아 Article 객체를 생성한다.")
    @Test
    void constructor() {
        // given
        String title = "Title";
        String body = "Body";

        // when
        Article actual = new Article(title, body);

        // then
        assertThat(actual).isNotNull();
    }
}
