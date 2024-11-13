import java.util.Date;

public class BoardMessage extends Message {
	private String subject;

//	C'tor
	public BoardMessage(String sender, String content, Date sendDate, String status, String subject)
			throws IllegalArgumentException {
		super(sender, content, sendDate, status);
		setSubject(subject);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) throws IllegalArgumentException {
		if (subject == null || subject.isEmpty()) {
			throw new IllegalArgumentException("Subject cannot be null or empty.");
		}
		this.subject = subject;
	}

	@Override
	public String getMessageType() {
		return "Board";
	}
}
