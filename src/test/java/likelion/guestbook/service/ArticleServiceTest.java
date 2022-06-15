package likelion.guestbook.service;

import static likelion.guestbook.Fixtures.ARTICLE_REQUEST_DTO_1;
import static likelion.guestbook.Fixtures.ARTICLE_REQUEST_DTO_2;
import static likelion.guestbook.Fixtures.ARTICLE_REQUEST_DTO_3;
import static likelion.guestbook.Fixtures.OWNER_ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import javax.sql.DataSource;
import likelion.guestbook.dao.ArticleDao;
import likelion.guestbook.dto.ArticleResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class ArticleServiceTest {
    private final ArticleService articleService;

    @Autowired
    public ArticleServiceTest(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        ArticleDao articleDao = new ArticleDao(jdbcTemplate, dataSource);
        this.articleService = new ArticleService(articleDao);
    }

    @DisplayName("게시물을 생성한다.")
    @Test
    void createArticle() {
        // when
        Long id = articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_1);

        // then
        assertThat(id).isNotNull();
    }

    @DisplayName("OwnerId로 전체 게시물을 조회한다.")
    @Test
    void findAllByOwnerId() {
        // given
        articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_1);
        articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_2);
        articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_3);

        // when
        List<ArticleResponseDto> actual = articleService.findAllByOwnerId(OWNER_ID_1);

        // then
        assertThat(actual).hasSize(3);
    }

    @DisplayName("Article Id로 특정 게시물을 단건 조회한다.")
    @Test
    void findById() {
        // given
        articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_1);
        articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_2);
        Long id = articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_3);

        // when
        ArticleResponseDto actual = articleService.findById(id);

        // then
        assertAll(
                () -> assertThat(actual.getTitle()).isEqualTo(ARTICLE_REQUEST_DTO_3.getTitle()),
                () -> assertThat(actual.getBody()).isEqualTo(ARTICLE_REQUEST_DTO_3.getBody())
        );
    }

    @DisplayName("게시물을 수정한다.")
    @Test
    void updateArticle() {
        // given
        Long id = articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_1);

        // when
        articleService.updateArticle(id, ARTICLE_REQUEST_DTO_2);
        ArticleResponseDto actual = articleService.findById(id);

        // then
        assertAll(
                () -> assertThat(actual.getTitle()).isEqualTo(ARTICLE_REQUEST_DTO_2.getTitle()),
                () -> assertThat(actual.getBody()).isEqualTo(ARTICLE_REQUEST_DTO_2.getBody())
        );
    }

    @DisplayName("게시물을 제거한다.")
    @Test
    void deleteArticle() {
        // given
        articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_1);
        articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_2);
        Long id = articleService.createArticle(OWNER_ID_1, ARTICLE_REQUEST_DTO_3);

        // when
        articleService.deleteArticle(id);
        List<ArticleResponseDto> actual = articleService.findAllByOwnerId(OWNER_ID_1);

        // then
        assertThat(actual).hasSize(2);
    }
}
