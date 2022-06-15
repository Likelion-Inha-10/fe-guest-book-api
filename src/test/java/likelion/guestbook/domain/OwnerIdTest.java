package likelion.guestbook.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OwnerIdTest {
    @DisplayName("문자열을 전달하여 방명록 OwnerId를 생성할 수 있다.")
    @Test
    void constructor() {
        // given
        String ownerId = "devHudi";

        // when
        OwnerId actual = new OwnerId(ownerId);

        // then
        assertThat(actual).isNotNull();
    }

    @DisplayName("공백은 전달될 수 없다.")
    @Test
    void empty() {
        // given
        String ownerId = "";

        // when & then
        assertThatThrownBy(() -> new OwnerId(ownerId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공백을 전달할 수 없습니다.");
    }

    @DisplayName("영어 대소문자, 숫자 외의 문자가 포함될 수 없다.")
    @ValueSource(strings = {"he llo", "hi!", "dev-hudi"})
    @ParameterizedTest
    void invalidFormat(String input) {
        // when & then
        assertThatThrownBy(() -> new OwnerId(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("영어 대소문자, 숫자 외의 문자가 포함될 수 없습니다.");
    }
}
