package likelion.guestbook.domain;

public class Title {
    private final String value;

    public Title(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String title) {
        if (title.isBlank()) {
            throw new IllegalArgumentException("공백을 전달할 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Title{" +
                "title='" + value + '\'' +
                '}';
    }
}
