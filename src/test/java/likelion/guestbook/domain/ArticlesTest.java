package likelion.guestbook.domain;

import static org.assertj.core.api.Assertions.assertThat;

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
}
