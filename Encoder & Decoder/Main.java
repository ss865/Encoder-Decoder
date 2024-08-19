import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {//Display menu options
            System.out.println("\n------------Menu------------");
            System.out.println("\n1 -- Encode/Encrypt a message");
            System.out.println("\n2 -- Decode/Decrypt a message");
            System.out.println("\n3 -- Quit/Exit");
            System.out.print("\nEnter your choice: ");
            int choice = -1;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            if(choice < 1 || choice > 3){//Can only input 1 or 2 or 3
                System.err.println("Invalid input. Please enter a number between 1-3");
                continue;
            }


            if (choice == 3) {
                System.out.println("Exiting program.");
                break;
            }


            System.out.print("Enter the name of the file containing the encryption key: ");
            String keyFileName = scanner.nextLine();
            System.out.print("Enter the name for the output file: ");
            String outputFileName = scanner.nextLine();
            System.out.print("Enter the message to be processed: ");
            String message = scanner.nextLine();



            String key = "";

            try {//Read the encryption key from file
                key = readKey(keyFileName);
                if (key.length() != 52) {
                    System.out.println("The key must be exactly 52 characters long.");
                    continue;
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the key: " + e.getMessage());
                continue;
            }

            try {
                String result;//Store the processed message
                if (choice == 1) {//Check if choice is to encrypt
                    result = encrypt(message, key);
                } else {//Decrypt the message
                    result = decrypt(message, key);
                }
                writeToFile(outputFileName, result);//Write the message to output file
                System.out.println("Operation completed!");

            } catch (Exception e) {
                System.err.println("An error occurred during processing: " + e.getMessage());
            }

        }
        scanner.close();
    }

    private static String readKey(String fileName) throws IOException {//Reads the encryption key from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.readLine();
        }
    }

    private static void writeToFile(String fileName, String content) throws IOException {//Writes message to the file
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println(content);
        }
    }

    private static String encrypt(String message, String key) {//Encrypts the message
        return message(message, key, true);
    }

    private static String decrypt(String message, String key) {//Decrypts the message
        return message(message, key, false);
    }

    private static String message(String message, String key, boolean encrypt) {//Gets the message for encryption or decryption
        StringBuilder finder = new StringBuilder();
        String lowerCaseAlpha = "abcdefghijklmnopqrstuvwxyz";
        String upperCaseAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (char character : message.toCharArray()) {
            if (character >= 'a' && character <= 'z') {//Check if character is lowercase
                int index = lowerCaseAlpha.indexOf(character);
                if (encrypt) {
                    finder.append(key.charAt(index));
                } else {
                    finder.append(lowerCaseAlpha.charAt(key.indexOf(character)));
                }
            } else if (character >= 'A' && character <= 'Z') {//Check if character is uppercase
                int index = upperCaseAlpha.indexOf(character);
                if (encrypt) {
                    finder.append(key.charAt(index + 26));
                } else {
                    finder.append(upperCaseAlpha.charAt(key.indexOf(character) - 26));
                }
            } else {
                finder.append(character);
            }
        }
        return finder.toString();//Return the processed message
    }
}
