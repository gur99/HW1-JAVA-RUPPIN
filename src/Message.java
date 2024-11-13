import java.util.Date;

public abstract class Message {

	private String sender;
	private String content;
	private Date sendDate;
	// My NEW field
//	
	private String status;

	// C'tor #1
	public Message(String sender, String content, Date sendDate, String status) throws IllegalArgumentException {

		setSender(sender);
		setContent(content);
		setSendDate(sendDate);
		setStatus(status);
	}

	// C'tor #2 - without Date input
	public Message(String sender, String content, String status) throws IllegalArgumentException {

		setSender(sender);
		setContent(content);
		setStatus(status);
		sendDate = new Date();
	}

	public void setSender(String sender) throws IllegalArgumentException {
		// if null/empty throw exception
		if (sender == null || sender.isEmpty()) {
			throw new IllegalArgumentException("Sender cannot be null or empty");
		}
		this.sender = sender;
	}

	public String getSender() {
		return sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) throws IllegalArgumentException {
		// if null/empty throw exception
		if (content == null || content.isEmpty()) {
			throw new IllegalArgumentException("Content cannot be null or empty");
		}
		this.content = content;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) throws IllegalArgumentException {
		if (sendDate == null) {
			throw new IllegalArgumentException("Send date cannot be null.");
		}
		this.sendDate = sendDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) throws IllegalArgumentException {

		if (status == null || (!status.equals("sent") && !status.equals("draft") && !status.equals("read")
				&& !status.equals("unread"))) {
			throw new IllegalArgumentException("Invalid status. Allowed values: sent, draft, read, unread.");
		}
		this.status = status;
	}

	public boolean find(String[] words) {
		if (words == null || words.length == 0) {
			return false;
		}
		for (String word : words) {
			if (word != null && content.contains(word)) {
				return true;
			}
		}
		return false;
	}

	// New summarize method
	public String summarize(int limit) {
		if (limit <= 0) {
			return "";
		}
		if (content.length() <= limit) {
			return content;
		}
		return content.substring(0, limit) + "...";
	}

	public String toString() {
		return "This is a Message";
	}

	// Abstract Method
	public abstract String getMessageType();

}
