package likelion.guestbook.domain;

public class OwnerId {
    private static final String REGEX = "[a-zA-Z0-9]+";

    private final String value;

    public OwnerId(String value) {
        validateNotEmpty(value);
        validateFormat(value);
        this.value = value;
    }

    private void validateNotEmpty(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("공백을 전달할 수 없습니다.");
        }
    }

    private void validateFormat(String value) {
        if (!value.matches(REGEX)) {
            throw new IllegalArgumentException("영어 대소문자, 숫자 외의 문자가 포함될 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "OwnerId{" +
                "value='" + value + '\'' +
                '}';
    }
}
