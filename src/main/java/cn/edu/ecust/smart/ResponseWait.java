package cn.edu.ecust.smart;

public class ResponseWait {

    private final long id;
    private final long size;

    public ResponseWait(long id,  long size) {
        this.id = id;
		this.size = size;
    }

    public long getId() {
        return id;
    }

    public long getSize() {
        return size;
    }
}
