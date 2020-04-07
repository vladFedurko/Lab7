package entity;

public class ChatMessage {
    private ChatUser author;
    private long timestamp;
    private String message;
    private String recipient;

    public ChatMessage(ChatUser author, long timestamp, String message, String recipient) {
        this.author = author;
        this.timestamp = timestamp;
        this.message = message;
        this.recipient = recipient;
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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
