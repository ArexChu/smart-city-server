package hello;

public class Greeting {

    private final long id;
    private final String content;
    private final long size;

    public Greeting(long id, String content, long size) {
        this.id = id;
        this.content = content;
		this.size = size;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public long getSize() {
        return size;
    }
}
