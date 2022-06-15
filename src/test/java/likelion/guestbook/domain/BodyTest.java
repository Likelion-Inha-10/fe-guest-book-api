package likelion.guestbook.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BodyTest {
    @DisplayName("본문을 문자열 형태로 전달하여 Title 객체를 생성할 수 있다.")
    @Test
    void constructor() {
        // given
        String body = "hello, world";

        // when
        Body actual = new Body(body);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("본문에 공백을 전달할 수 없다.")
    @Test
    void validate_empty() {
        // given
        String body = "";

        // when & then
        assertThatThrownBy(() -> new Body(body))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공백을 전달할 수 없습니다.");
    }
}
