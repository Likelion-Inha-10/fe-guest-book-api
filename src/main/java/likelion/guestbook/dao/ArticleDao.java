package likelion.guestbook.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import likelion.guestbook.domain.Article;
import likelion.guestbook.domain.Articles;
import likelion.guestbook.domain.OwnerId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDao {
    private static final RowMapper<Article> ARTICLE_ROW_MAPPER = (rs, rowNum) -> new Article(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("body"),
            rs.getTimestamp("created_at").toLocalDateTime()
    );

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public ArticleDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("article")
                .usingGeneratedKeyColumns("id");
    }

    public Long save(OwnerId ownerId, Article article) {
        Map<String, Object> params = new HashMap<>();
        params.put("title", article.getTitle().getValue());
        params.put("body", article.getBody().getValue());
        params.put("created_at", article.getCreatedAt());
        params.put("owner_id", ownerId.getValue());

        return jdbcInsert.executeAndReturnKey(params).longValue();
    }

    public Articles findAllByOwnerId(OwnerId ownerId) {
        String sql = "SELECT id, title, body, created_at FROM article WHERE owner_id = ?";
        List<Article> articles = jdbcTemplate.query(sql, ARTICLE_ROW_MAPPER, ownerId.getValue());
        return new Articles(articles, ownerId);
    }

    public Article findById(Long id) {
        String sql = "SELECT id, title, body, created_at FROM article WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, ARTICLE_ROW_MAPPER, id);
    }

    public void update(Long id, Article newArticle) {
        String sql = "UPDATE article SET title = ?, body = ? WHERE id = ?";
        jdbcTemplate.update(sql, newArticle.getTitle().getValue(), newArticle.getBody().getValue(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM article WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
