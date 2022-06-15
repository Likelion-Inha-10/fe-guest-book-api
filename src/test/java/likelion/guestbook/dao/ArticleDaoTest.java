package likelion.guestbook.dao;

import static likelion.guestbook.Fixtures.ARTICLE_1;
import static likelion.guestbook.Fixtures.ARTICLE_2;
import static likelion.guestbook.Fixtures.ARTICLE_3;
import static likelion.guestbook.Fixtures.OWNER_ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import javax.sql.DataSource;
import likelion.guestbook.domain.Article;
import likelion.guestbook.domain.Articles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class ArticleDaoTest {
    private final ArticleDao articleDao;

    @Autowired
    public ArticleDaoTest(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.articleDao = new ArticleDao(jdbcTemplate, dataSource);
    }

    @DisplayName("데이터베이스에 Article을 저장한다.")
    @Test
    void create() {
        // when
        Long id = articleDao.save(OWNER_ID_1, ARTICLE_1);

        // then
        assertThat(id).isNotNull();
    }

    @DisplayName("OwnerId에 해당하는 모든 게시물을 가져온다.")
    @Test
    void findAllByOwnerId() {
        // given
        articleDao.save(OWNER_ID_1, ARTICLE_1);
        articleDao.save(OWNER_ID_1, ARTICLE_2);
        articleDao.save(OWNER_ID_1, ARTICLE_3);

        // when
        Articles actual = articleDao.findAllByOwnerId(OWNER_ID_1);

        // then
        assertThat(actual.getArticles()).hasSize(3);
    }

    @DisplayName("id로 특정 게시물을 단건 조회한다.")
    @Test
    void findById() {
        // given
        articleDao.save(OWNER_ID_1, ARTICLE_1);
        articleDao.save(OWNER_ID_1, ARTICLE_2);
        Long id = articleDao.save(OWNER_ID_1, ARTICLE_3);

        // when
        Article actual = articleDao.findById(id);

        // then
        assertAll(
                () -> assertThat(actual.getTitle()).isEqualTo(ARTICLE_3.getTitle()),
                () -> assertThat(actual.getBody()).isEqualTo(ARTICLE_3.getBody())
        );
    }

    @DisplayName("게시물을 수정한다.")
    @Test
    void update() {
        // given
        Long id = articleDao.save(OWNER_ID_1, ARTICLE_1);

        // when
        articleDao.update(id, ARTICLE_2);
        Article actual = articleDao.findById(id);

        // then
        assertAll(
                () -> assertThat(actual.getTitle()).isEqualTo(ARTICLE_2.getTitle()),
                () -> assertThat(actual.getBody()).isEqualTo(ARTICLE_2.getBody())
        );
    }

    @DisplayName("게시물을 제거한다.")
    @Test
    void delete() {
        // given
        articleDao.save(OWNER_ID_1, ARTICLE_1);
        articleDao.save(OWNER_ID_1, ARTICLE_2);
        Long id = articleDao.save(OWNER_ID_1, ARTICLE_3);

        // when
        articleDao.delete(id);
        Articles actual = articleDao.findAllByOwnerId(OWNER_ID_1);

        // then
        assertThat(actual.getArticles()).hasSize(2);
    }
}
