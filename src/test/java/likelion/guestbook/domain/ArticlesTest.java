package likelion.guestbook.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArticlesTest {
    @DisplayName("Article 리스트와 OwnerId를 전달하여 생성할 수 있다.")
    @Test
    void constructor_withList() {
        // given
        List<Article> articles = List.of(new Article("hello", "world"));
        OwnerId ownerId = new OwnerId("devHudi");

        // when
        Articles actual = new Articles(articles, ownerId);

        // then
        assertThat(actual.getArticles()).hasSize(1);
    }

    @DisplayName("OwnerId만을 전달하여 생성할 수 있다.")
    @Test
    void constructor_withOutList() {
        // given
        OwnerId ownerId = new OwnerId("devHudi");

        // when
        Articles actual = new Articles(ownerId);

        // then
        assertThat(actual.getArticles()).hasSize(0);
    }

    @DisplayName("getArticles는 createdAt 기준 내림차순된 데이터를 반환한다.")
    @Test
    void sort() {
        // given
        List<Article> articlesValue = List.of(
                new Article("hello3", "world3", LocalDateTime.of(2022, 6, 15, 3, 0)),
                new Article("hello1", "world1", LocalDateTime.of(2022, 6, 15, 1, 0)),
                new Article("hello2", "world2", LocalDateTime.of(2022, 6, 15, 2, 0))
        );
        OwnerId ownerId = new OwnerId("devHudi");
        Articles articles = new Articles(articlesValue, ownerId);

        // when
        List<Article> actual = articles.getArticles();

        // then
        assertThat(actual).containsExactly(
                new Article("hello3", "world3", LocalDateTime.of(2022, 6, 15, 3, 0)),
                new Article("hello2", "world2", LocalDateTime.of(2022, 6, 15, 2, 0)),
                new Article("hello1", "world1", LocalDateTime.of(2022, 6, 15, 1, 0))
        );
    }
}
