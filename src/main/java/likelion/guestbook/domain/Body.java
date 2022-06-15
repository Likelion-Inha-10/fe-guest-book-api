package likelion.guestbook.domain;

import java.util.Objects;

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

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        return Objects.equals(value, body.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Body{" +
                "value='" + value + '\'' +
                '}';
    }
}
