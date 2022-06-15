package likelion.guestbook.domain;

public class Body {
    private final String value;

    public Body(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("공백을 전달할 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Body{" +
                "value='" + value + '\'' +
                '}';
    }
}