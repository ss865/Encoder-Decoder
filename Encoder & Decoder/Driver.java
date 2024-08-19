import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        while(true){
            System.out.println("\n----Menu---");
            System.out.println("1 -- Encode/Encrypt a message\n");
            System.out.println("2 -- Decode/Decrypt a message\n");
            System.out.println("3 -- Quit/Exit\n");
            System.out.println("Enter your choice: ");
            int choice = scan.nextInt();
            scan.nextLine(); // Consume the newline


            if(choice == 3){
                System.out.println("Goodbye");
                break;
            }

            System.out.print("Enter the name of the file containing the encryption key: ");
            String keyFile = scan.nextLine();
            System.out.print("Enter the name for the output file: ");
            String outputFile = scan.nextLine();
            System.out.print("Enter the message to be processed: ");
            String message = scan.nextLine();

            String key = "";








        }









    }




   /* private static String keyFile(String message, String key){
        String encryptedAlphabet = "guwyrmqpsaeicbnozlfhd kjxtvGUWYRMQPSAEICBNOZLFHD KJXTV";
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        System.out.print("Enter the encrypted message: ");
        //String encryptedMessage = scan.nextLine();


    }

*/
}
