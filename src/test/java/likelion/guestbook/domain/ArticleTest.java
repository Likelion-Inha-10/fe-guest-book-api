package likelion.guestbook.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
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

    @DisplayName("Article이 생성된 시점을 가져올 수 있다.")
    @Test
    void getCreatedAt() {
        // given
        Article article = new Article("title", "body");
        LocalDateTime now = LocalDateTime.now();

        // when
        LocalDateTime actual = article.getCreatedAt();

        // then
        assertAll(
                () -> assertThat(now.getYear()).isEqualTo(actual.getYear()),
                () -> assertThat(now.getMonth()).isEqualTo(actual.getMonth()),
                () -> assertThat(now.getDayOfMonth()).isEqualTo(actual.getDayOfMonth()),
                () -> assertThat(now.getHour()).isEqualTo(actual.getHour()),
                () -> assertThat(now.getMinute()).isEqualTo(actual.getMinute())
        );
    }
}
