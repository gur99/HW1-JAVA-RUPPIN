
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Create and Define 2 objects of each type
		Date d1 = new Date();
		ArrayList<Message> messages = new ArrayList<>();
		ArrayList<File> files = new ArrayList<File>();

		// Files
		File f1 = new File("Lion-photo", "png");
		File f2 = new File("ISR-TOP-SALARIES", ".csv");

		// Email Message Type
		files.add(f1);
		EmailMessage em1 = new EmailMessage("Miguel", "Mi Amore it's Miguel", d1, "send", "Letter", files);
		files.remove(0);
		files.add(f2);
		EmailMessage em2 = new EmailMessage("Kamil", "Mi Amore it's Kamil", d1, "send", "Letter", files);

		// Board Message Type
//		Priority priority1 = Priority.REGULAR;
//		Priority priority2 = Priority.URGENT;

		BoardMessage bm1 = new BoardMessage("Joey", "How you doin'?", d1, "send", "letter", Priority.REGULAR);
		BoardMessage bm2 = new BoardMessage("Chandler", "Where's the ducks?", d1, "send", "Question", Priority.URGENT);

		messages.add(em1);
		messages.add(em2);
		messages.add(bm1);
		messages.add(bm2);

		// SMS Message Type
		try {
			SMS sms1 = new SMS("jack", "hello", "draft", "0545586666");
			SMS sms2 = new SMS("Dan", "whatsappppp", "send", "0549123123");
			messages.add(sms1);
			messages.add(sms2);

		} catch (SMSException e) {
			System.out.println("Error adding SMS message: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\nMenu:");
			System.out.println("1. Add messages of different types");
			System.out.println("2. Print all messages");
			System.out.println("3. Delete a message");
			System.out.println("4. Search for messages by word");
			System.out.println("5. Print all digital messages");
			System.out.println("6. Calculate SMS cost (of all SMSs 0.05 per char)");
			System.out.println("7. Exit");
			System.out.print("Choose an option: ");

			try {

				choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {

				// # 1 - Add messages of different types

				case 1:
					try {

						System.out.println("\nChoose the type of message to add:");
						System.out.println("1. Email Message");
						System.out.println("2. Board Message");
						System.out.println("3. SMS");
						int messageType = Integer.parseInt(scanner.nextLine());

						System.out.print("Enter sender: ");
						String sender = scanner.nextLine();

						System.out.print("Enter content: ");
						String content = scanner.nextLine();

						System.out.print("Enter status:(send/draft) ");
						String status = scanner.nextLine();

						switch (messageType) {

						case 1: // Email Message
							System.out.print("Enter subject: ");
							String subject = scanner.nextLine();

							System.out.print("How many attachments? ");
							int attachmentCount = Integer.parseInt(scanner.nextLine());

							ArrayList<File> attachments = new ArrayList<>();
							for (int i = 0; i < attachmentCount; i++) {
								System.out.print("Enter attachment name: ");
								String fileName = scanner.nextLine();

								System.out.print("Enter attachment type: ");
								String fileType = scanner.nextLine();

								attachments.add(new File(fileName, fileType));
							}

							EmailMessage newEmailMessage = new EmailMessage(sender, content, status, subject,
									attachments);
							messages.add(newEmailMessage);
							System.out.println("Email message added successfully.");
							break;

						case 2: // Board Message
							System.out.print("Enter category: ");
							String category = scanner.nextLine();

							System.out.println("Choose Priority: ");
							System.out.println("1. Urgent");
							System.out.println("2. Regular");
							System.out.println("3. Advertising");

							int chosen_priority = Integer.parseInt(scanner.nextLine());

							Priority priority = null;
							do {

								switch (chosen_priority) {
								case 1:
									priority = Priority.URGENT;
									break;
								case 2:
									priority = Priority.REGULAR;
									break;
								case 3:
									priority = Priority.ADVERTISING;
									break;
								default:
									System.out.println("Wrong input,try agian (enter a number...)");
									break;
								}
							} while (priority == null);

							BoardMessage newBoardMessage = new BoardMessage(sender, content, status, category,
									priority);
							messages.add(newBoardMessage);
							System.out.println("Board message added successfully.");
							break;

						case 3: // SMS
							System.out.print("Enter phone number: ");
							String phoneNumber = scanner.nextLine();

							try {
								SMS newSMSMessage = new SMS(sender, content, status, phoneNumber);
								messages.add(newSMSMessage);
								System.out.println("SMS message added successfully.");
								System.out.println("Details:\n" + newSMSMessage);
							} catch (SMSException e) {
								System.out.println("Error adding SMS message: " + e.getMessage());
							}
							break;

						default:
							System.out.println("Invalid message type. Returning to menu.");
						}

					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage() + "\nexiting to menu");
					}

					break;

				// # 2 - Print all messages
				case 2:
					if (messages.isEmpty()) {
						System.out.println("No Messages available.");
					} else {

						System.out.println("\nAll messages:");
						for (Message message : messages) {
							System.out.println(message.getMessageType());
							System.out.println(message + "\n");

						}
					}
					break;

				// # 3 - Delete a message
				case 3:
					try {
						if (messages.isEmpty()) {
							System.out.println("No messages available to delete.");
							break;
						}
						// Print all messages with their index
						System.out.println("\nMessages:\n");
						for (int i = 0; i < messages.size(); i++) {
							System.out.println((i + 1) + ". " + messages.get(i) + "\n");
						}

						System.out.print("Enter the number of the message to delete: \n");
						int index = Integer.parseInt(scanner.nextLine());

						// Validate index
						if (index < 1 || index > messages.size()) {
							System.out.println("Invalid selection. Please choose a valid message number.");
						} else {
							Message removedMessage = messages.remove(index - 1); // Remove the message at the specified
																					// index
							System.out.println("Message deleted successfully:\n\n" + removedMessage);
						}
					} catch (Exception e) {
						System.out.println("Error: " + e.getMessage());
					}
					break;

				// # 4 - Search for messages by word
				case 4:
					try {
						System.out.print("Enter the word to search for: ");
						String searchWord = scanner.nextLine();
						int count = 0;
						for (Message message : messages) {
							if (message.find(new String[] { searchWord })) {
								count++;
							}
						}
						System.out.println("Number of messages that contain the word '" + searchWord + "': " + count);
					} catch (Exception e) {
						System.out.println("Error searching messages: " + e.getMessage());
					}
					break;

				// # 5 Print all digital messages
				case 5:
					if (messages.isEmpty()) {
						System.out.println("No message available.");
					} else {
						System.out.println("\nDigital Messages:\n");
						boolean hasDigitalMessages = false;

						for (Message message : messages) {
							if (message instanceof IDigital) {
								hasDigitalMessages = true;
								System.out.println(message);
								((IDigital) message).printCommunicationMethod();
							}
							System.out.println();
						}
						if (!hasDigitalMessages) {
							System.out.println("No Digital messages found.");
						}
						break;
					}

				case 6:
					if (messages.isEmpty()) {
						System.out.println("\nNo messages available.");
					} else {
						double totalCost = 0.0;
						boolean hasSMS = false;

						System.out.println("\nSMS Cost Analysis:");
						for (Message message : messages) {
							if (message instanceof SMS) {
								hasSMS = true;
								SMS sms = (SMS) message;
								double cost = sms.calculateCost();
								totalCost += cost;

								System.out.println(sms);
								System.out.println("Cost: $" + String.format("%.2f", cost));
							}
						}
						if (!hasSMS) {
							System.out.println("\nNo SMS messages found.");
						} else {
							System.out.println("\nTotal Cost of All SMS: $" + String.format("%.2f", totalCost));
						}
					}
					break;

				case 7:
					// Exit the program
					System.out.println("Exiting the program.");
					break;

				default:
					System.out.println("Invalid choice. Please choose again.");
				}

			} catch (Exception e) {
				System.out.println("Incorrect Format entered ");
				choice = -1;
			}
		} while (choice != 7);

		scanner.close();
		System.out.println("Program ended.");
	}

}