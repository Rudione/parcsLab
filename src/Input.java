import java.io.Serializable;

public class Input implements Serializable {
    private String text;
    private String pattern;

    public Input(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
    }

    public String getText() {
        return text;
    }

    public String getPattern() {
        return pattern;
    }
}
