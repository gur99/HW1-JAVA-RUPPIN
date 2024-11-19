import java.util.ArrayList;
import java.util.Date;

public class EmailMessage extends Message implements IDigital {
//	private File 
	private String subject;
	private ArrayList<File> attachments;

//	C'tor
	public EmailMessage(String sender, String content, Date sendDate, String status, String subject,
			ArrayList<File> attachments) throws IllegalArgumentException {
		super(sender, content, sendDate, status);
		setSubject(subject);
		this.attachments = new ArrayList<File>(attachments);
	}

// C'tor #2 - without Date input
	public EmailMessage(String sender, String content, String status, String subject, ArrayList<File> attachments)
			throws IllegalArgumentException {
		super(sender, content, status);
		setSubject(subject);
		this.attachments = new ArrayList<File>(attachments);
	}

//	Getters & Setter for subject

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) throws IllegalArgumentException {
		if (subject == null || subject.isEmpty()) {
			throw new IllegalArgumentException("Subject cannot be null or empty.");
		}
		this.subject = subject;
	}

//	Methods to add an attachment

	public void addAttachment(File file) throws AttachmentException {
		if (file == null) {
			throw new IllegalArgumentException("File cannot be null.");
		}
		attachments.add(file);
	}

// Method to remove an attachment
	public void removeAttachment(File file) throws AttachmentException {
		if (!attachments.remove(file)) {
			throw new AttachmentException("Attachment not found: " + file);
		}
	}

	// Getter for Attachments
	public ArrayList<File> getAttachments() {
		return new ArrayList<>(attachments); // Return a copy to ensure immutability
	}

	@Override
	public String getMessageType() {
		return "This is an Email-Message.";
	}

	@Override
	public String toString() {

		return "EmailMessage{" + "sender='" + getSender() + '\'' + ", content='" + getContent() + '\'' + ", sendDate="
				+ getSendDate() + ", status='" + getStatus() + '\'' + ", subject='" + subject + '\''
				+ ", attachments=\n " + attachments + '}';
	}

	// Implements for IDigital abstract method
	@Override
	public void printCommunicationMethod() {
		System.out.println("This message is sent via Email.");
	}

}
