package likelion.guestbook.acceptance;

import static likelion.guestbook.Fixtures.ARTICLE_REQUEST_DTO_1;
import static likelion.guestbook.Fixtures.ARTICLE_REQUEST_DTO_2;
import static likelion.guestbook.Fixtures.ARTICLE_REQUEST_DTO_3;
import static likelion.guestbook.Fixtures.OWNER_ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import likelion.guestbook.dto.ArticleRequestDto;
import likelion.guestbook.dto.ArticleResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleAcceptanceTest {
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("방명록 게시물 생성")
    @Test
    void create() {
        // when
        ExtractableResponse<Response> response = 방명록_게시물_생성(OWNER_ID_1.getValue(), ARTICLE_REQUEST_DTO_1);

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(
                        response.header("Location").split("/articles/")[1]).isEqualTo("1")
        );
    }

    @DisplayName("유저의 방명록 게시물 전체 조회")
    @Test
    void findAllByOwnerId() {
        // given
        String ownerId = OWNER_ID_1.getValue();
        방명록_게시물_생성(ownerId, ARTICLE_REQUEST_DTO_1);
        방명록_게시물_생성(ownerId, ARTICLE_REQUEST_DTO_2);
        방명록_게시물_생성(ownerId, ARTICLE_REQUEST_DTO_3);

        // when
        ExtractableResponse<Response> response = 유저의_방명록_전체_조회(ownerId);

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(response.jsonPath().getList(".", ArticleResponseDto.class)).hasSize(3)
        );
    }

    @DisplayName("방명록 게시물 단건 조회")
    @Test
    void findById() {
        // given
        ExtractableResponse<Response> createdArticleResponse = 방명록_게시물_생성(OWNER_ID_1.getValue(), ARTICLE_REQUEST_DTO_1);
        String id = createdArticleResponse.header("Location").split("articles/")[1];

        // when
        ExtractableResponse<Response> response = 방명록_단건_조회(id);
        ArticleResponseDto actual = response.jsonPath().getObject(".", ArticleResponseDto.class);

        // then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(actual.getTitle()).isEqualTo(ARTICLE_REQUEST_DTO_1.getTitle()),
                () -> assertThat(actual.getBody()).isEqualTo(ARTICLE_REQUEST_DTO_1.getBody())
        );
    }

    @DisplayName("게시글 수정")
    @Test
    void update() {
        // given
        ExtractableResponse<Response> createdArticleResponse = 방명록_게시물_생성(OWNER_ID_1.getValue(), ARTICLE_REQUEST_DTO_1);
        String id = createdArticleResponse.header("Location").split("articles/")[1];

        // when
        ExtractableResponse<Response> response = 방명록_수정(id, ARTICLE_REQUEST_DTO_2);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @DisplayName("게시글 제거")
    @Test
    void delete() {
        // given
        ExtractableResponse<Response> createdArticleResponse = 방명록_게시물_생성(OWNER_ID_1.getValue(), ARTICLE_REQUEST_DTO_1);
        String id = createdArticleResponse.header("Location").split("articles/")[1];

        // when
        ExtractableResponse<Response> response = 방명록_제거(id);

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }


    private ExtractableResponse<Response> 방명록_게시물_생성(String ownerId, ArticleRequestDto articleRequestDto) {
        return RestAssured.given().log().all().body(articleRequestDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/" + ownerId + "/articles")
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 유저의_방명록_전체_조회(String ownerId) {
        return RestAssured.given().log().all()
                .when().get("/" + ownerId + "/articles")
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 방명록_단건_조회(String id) {
        return RestAssured.given().log().all()
                .when().get("/articles/" + id)
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 방명록_수정(String id, ArticleRequestDto requestDto) {
        return RestAssured.given().log().all().body(requestDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/articles/" + id)
                .then().log().all()
                .extract();
    }

    private ExtractableResponse<Response> 방명록_제거(String id) {
        return RestAssured.given().log().all()
                .when().delete("/articles/" + id)
                .then().log().all()
                .extract();
    }

}
