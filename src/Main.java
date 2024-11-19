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
		EmailMessage em1 = new EmailMessage("Miguel", "Mi Amore it's Miguel", d1, "sent", "Letter", files);
		files.remove(0);
		files.add(f2);
		EmailMessage em2 = new EmailMessage("Kamil", "Mi Amore it's Kamil", d1, "sent", "Letter", files);

		// Board Message Type
		Priority priority1 = Priority.REGULAR;
		Priority priority2 = Priority.URGENT;
		BoardMessage bm1 = new BoardMessage("Joey", "How you doin'?", d1, "sent", "letter", priority1);
		BoardMessage bm2 = new BoardMessage("Chandler", "Where's the ducks?", d1, "sent", "Question", priority2);

		messages.add(em1);
		messages.add(em2);
		messages.add(bm1);
		messages.add(bm2);

		Scanner scanner = new Scanner(System.in);
		int choice;

		do {
			System.out.println("\nMenu:");
			System.out.println("1. Add messages of different types");
			System.out.println("2. Print all messages");
			System.out.println("3. Delete a message");
			System.out.println("4. Search for messages by word");
			System.out.println("5. Print all digital messages");
			System.out.println("6. Another option of your choice");
			System.out.println("7. Exit");
			System.out.print("Choose an option: ");

			choice = scanner.nextInt();
//			scanner.nextLine(); // Consume the newline character

			switch (choice) {

			// #1 - Add messages of different types

			case 1:
				// Add messages of different types
				System.out.println("Choose the type of message to add:");
				System.out.println("1. Email Message");
				System.out.println("2. Board Message");
				System.out.println("3. Add new Option(New Class)");
				int messageType = scanner.nextInt();
				scanner.nextLine();

				try {
					System.out.print("Enter sender: ");
					String sender = scanner.nextLine();

					System.out.print("Enter content: ");
					String content = scanner.nextLine();

					System.out.print("Enter status (sent, draft, read, unread): ");
					String status = scanner.nextLine();

					switch (messageType) {

					case 1: // Email Message
						System.out.print("Enter subject: ");
						String subject = scanner.nextLine();

						System.out.print("How many attachments? ");
						int attachmentCount = scanner.nextInt();
						scanner.nextLine(); // Consume the newline character

						ArrayList<File> attachments = new ArrayList<>();
						for (int i = 0; i < attachmentCount; i++) {
							System.out.print("Enter attachment name: ");
							String fileName = scanner.nextLine();

							System.out.print("Enter attachment type: ");
							String fileType = scanner.nextLine();

							attachments.add(new File(fileName, fileType));
						}

						EmailMessage newEmailMessage = new EmailMessage(sender, content, status, subject, attachments);
						messages.add(newEmailMessage);
						System.out.println("Email message added successfully.");
						break;

					case 2: // Board Message
						System.out.print("Enter category (any): ");
						String category = scanner.nextLine();

						System.out.print("Choose Priority: ");
						System.out.print("1. Urgent");
						System.out.println("2. Regular");
						System.out.println("3. Advertising");
						int chosen_priority = scanner.nextInt();

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

						BoardMessage newBoardMessage = new BoardMessage(sender, content, status, category, priority);
						messages.add(newBoardMessage);
						System.out.println("Board message added successfully.");
						break;

					case 3:
						// New Class
						break;

					default:
						System.out.println("Invalid message type. Returning to menu.");
					}

				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;

			// # 2 - Print all messages
			case 2:
				// Print all messages
				System.out.println("\nAll messages:");
				for (Message message : messages) {
					System.out.println(message);
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
					System.out.println("Messages:");
					for (int i = 0; i < messages.size(); i++) {
						System.out.println((i + 1) + ". " + messages.get(i));
					}

					System.out.print("Enter the number of the message to delete: ");
					int index = scanner.nextInt();

					// Validate index
					if (index < 1 || index > messages.size()) {
						System.out.println("Invalid selection. Please choose a valid message number.");
					} else {
						Message removedMessage = messages.remove(index - 1); // Remove the message at the specified
																				// index
						System.out.println("Message deleted successfully:\n" + removedMessage);
					}
				} catch (NumberFormatException e) {
					System.out.println("Error: Please enter a valid number.");
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
				break;

			case 4:
				// Search for messages by word
				scanner.nextLine();
				System.out.print("Enter the word to search for: ");
				String searchWord = scanner.nextLine();
				int count = 0;
				for (Message message : messages) {
					if (message.find(new String[] { searchWord })) {
						count++;
					}
				}
				System.out.println("Number of messages that contain the word '" + searchWord + "': " + count);
				break;

			case 5:
				// Print all digital messages
				System.out.println("\nAll digital messages:");
				for (Message message : messages) {
					if (message instanceof IDigital) {
						System.out.println(message);
					}
				}
				break;

			case 6:
				// Another option of your choice
				System.out.println("You can add any additional functionality here.");
				// For example:
				// Print all messages by a specific sender, or filter by message status, etc.
				break;

			case 7:
				// Exit the program
				System.out.println("Exiting the program.");
				break;

			default:
				System.out.println("Invalid choice. Please choose again.");
			}

		} while (choice != 7);

		try

		{

//			Date d1 = new Date();
//
//			File f = new File("ISR TOP SALARIES", ".csv");
//			File f2 = new File("IMAGES OF ANIMALS", "IMG");
////			System.out.println(f.toString());
//			ArrayList<File> attachments = new ArrayList<File>();
//
//			attachments.add(f);
//			attachments.add(f2);
//			System.out.println(attachments);
//			System.out.println("\n\n");
//
//			EmailMessage em = new EmailMessage("Gur Yitzhaki", "Hola seniorita", d1, "sent", "LongTimeNoSee");
//			em.addAttachment(f);
//			em.addAttachment(f2);
//			em.addAttachment(f2);
//			System.out.println(em);
//			em.printCommunicationMethod();

		} catch (

		Exception e) {
			System.out.println("Exception caught: " + e.getMessage());
		}

	}

}
