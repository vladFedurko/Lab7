package entity;

public class ChatMessage {
    private ChatUser author;
    private long timestamp;
    private String message;

    public ChatMessage(String message, ChatUser author, long timestamp) {
        super();
        this.author = author;
        this.timestamp = timestamp;
        this.message = message;
    }

    public ChatUser getAuthor() {
        return author;
    }

    public void setAuthor(ChatUser author) {
        this.author = author;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
