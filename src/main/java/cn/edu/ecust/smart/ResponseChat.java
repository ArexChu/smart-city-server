package cn.edu.ecust.smart;

public class ResponseChat {

    private final long id;
    private final String content;

    public ResponseChat(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
