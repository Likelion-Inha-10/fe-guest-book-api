package likelion.guestbook.service;

import java.util.List;
import java.util.stream.Collectors;
import likelion.guestbook.dao.ArticleDao;
import likelion.guestbook.domain.Article;
import likelion.guestbook.domain.Articles;
import likelion.guestbook.domain.OwnerId;
import likelion.guestbook.dto.ArticleRequestDto;
import likelion.guestbook.dto.ArticleResponseDto;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    public Long createArticle(OwnerId ownerId, ArticleRequestDto requestDto) {
        return articleDao.save(ownerId, convertDtoToArticle(requestDto));
    }

    public List<ArticleResponseDto> findAllByOwnerId(OwnerId ownerId) {
        Articles articles = articleDao.findAllByOwnerId(ownerId);

        return articles.getArticles()
                .stream()
                .map(ArticleResponseDto::new)
                .collect(Collectors.toList());
    }

    public ArticleResponseDto findById(Long id) {
        Article article = articleDao.findById(id);
        return new ArticleResponseDto(article);
    }

    public void updateArticle(Long id, ArticleRequestDto requestDto) {
        articleDao.update(id, convertDtoToArticle(requestDto));
    }

    public void deleteArticle(Long id) {
        articleDao.delete(id);
    }

    private Article convertDtoToArticle(ArticleRequestDto requestDto) {
        return new Article(requestDto.getTitle(), requestDto.getBody());
    }
}
