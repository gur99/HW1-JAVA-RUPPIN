
import java.util.Date;

public class BoardMessage extends Message {
	private String category;
	private Priority priority;

	// Place for Permanent Item--> {Instance Initializer}

//	C'tor #1
	public BoardMessage(String sender, String content, Date sendDate, String status, String category, Priority priority)
			throws IllegalArgumentException {
		super(sender, content, sendDate, status);
		setPriority(priority);
		setCategory(category);
	}

//	C'tor #2 without 'Date' argument
	public BoardMessage(String sender, String content, String status, String category, Priority priority)
			throws IllegalArgumentException {
		super(sender, content, status);
		setPriority(priority);
		setCategory(category);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) throws IllegalArgumentException {
		if (category == null || category.isEmpty() || category.isBlank()) {
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
		return this.priority == Priority.URGENT;
	}

	@Override
	public String toString() {
		return super.toString() + ", priority=" + priority + ", category='" + category + '\'' + '}';
	}

	public String getMessageType() {
		return "Board Message";
	}
}