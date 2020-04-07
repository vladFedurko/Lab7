package entity;

public class ChatUser {
    private String name;
    private long lastInteractionTime;
    private String sessionId;
    private boolean answering;

    public ChatUser(String name, long lastInteractionTime, String sessionId, boolean answering) {
        super();
        this.name = name;
        this.lastInteractionTime = lastInteractionTime;
        this.sessionId = sessionId;
        this.answering = answering;
    }

    public String getName() {
        return name;
    }

    public void setAnswering(boolean answering) {
        this.answering = answering;
    }

    public boolean isAnswering() {
        return answering;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLastInteractionTime() {
        return lastInteractionTime;
    }

    public void setLastInteractionTime(long lastInteractionTime) {
        this.lastInteractionTime = lastInteractionTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
