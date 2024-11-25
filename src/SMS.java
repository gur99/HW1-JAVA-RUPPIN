
import java.util.Date;

public class SMS extends Message implements IDigital {
	private String phoneNumber;
	private final double pricePerChar;

	{
		pricePerChar = 0.05;
	}

	// Constructor
	public SMS(String sender, String content, String status, String phoneNumber) throws SMSException {
		super(sender, content, status);
		setPhoneNumber(phoneNumber);
	}

	// Constructor #2 - with Date input
	public SMS(String sender, String content, String status, String phoneNumber, Date sendDate) throws SMSException {
		super(sender, content, sendDate, status);
		setPhoneNumber(phoneNumber);
	}

	// Getters & Setters
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) throws SMSException {
		validatePhoneNumber(phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	public void validatePhoneNumber(String phoneNumber) throws SMSException {
		try {
			if (!isValidPhoneNumber(phoneNumber)) {
				throw new IllegalArgumentException("Phone number format is invalid.");
			}
		} catch (IllegalArgumentException e) {
			throw new SMSException(
					"Invalid phone number: " + phoneNumber + "\nPhone number must include exactly 10 digits\n", e);
		}
	}

	public boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber.matches("\\+?[0-9]{10}");
	}

//	public void editContent(String newContent) {
//		if (isSent) {
//			System.out.println("Cannot edit the content. The SMS has already been sent.");
//		} else {
//			setContent(newContent); // setContent is a method from the parent class-(Message)
//			System.out.println("SMS content updated successfully.");
//		}
//	}
	public double calculateCost() {
		if (getStatus().equals("send")) {
			return getContent().length() * pricePerChar;
		}
		return 0;
	}

	@Override
	public void printCommunicationMethod() {
		System.out.println("This message is sent via SMS.");
	}

	@Override
	public String toString() {
		return super.toString() + ", Phone Number: " + phoneNumber + "}";
	}

	@Override
	public String getMessageType() {
		return "SMS Message";
	}
}
