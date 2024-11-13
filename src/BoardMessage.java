import java.util.Date;

public class BoardMessage extends Message {
	private String category;
	private Priority priority;

	public enum Priority {
		urgent, regular, spam;
	}

//	C'tor #1
	public BoardMessage(String sender, String content, Date sendDate, String status, String category)
			throws IllegalArgumentException {
		super(sender, content, sendDate, status);
		setCategory(category);
	}

//	C'tor #2
	public BoardMessage(String sender, String content, String status, String category) throws IllegalArgumentException {
		super(sender, content, status);
		setCategory(category);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) throws IllegalArgumentException {
		if (category == null || category.isEmpty()) {
			throw new IllegalArgumentException("Subject cannot be null or empty.");
		}
		this.category = category;
	}

	public void setPriority(Priority priority) throws IllegalArgumentException {

		if (priority == null) {
			throw new IllegalArgumentException("Subject cannot be null or empty.");
		}
		this.priority = priority;
	}

	public Priority getPriority() {
		return priority;
	}

	// New method to check if the message is high-priority
	public boolean isHighPriority() {
		return this.priority == Priority.urgent;
	}

	@Override
	public String toString() {
		return "BoardMessage {" + "sender='" + getSender() + '\'' + ", content='" + getContent() + '\'' + ", sendDate="
				+ getSendDate() + ", status='" + getStatus() + '\'' + ", priority=" + priority + ", category='"
				+ category + '\'' + '}';
	}

	public String getMessageType() {
		return "Board Message";
	}
}
