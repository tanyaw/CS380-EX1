
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintStream;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 22222)) {
			//Receives data from Server
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
		   
			//Sends data to Server
			OutputStream os = socket.getOutputStream();
			PrintStream out = new PrintStream(os, true, "UTF-8");
		
			//Sets up Scanner
			Scanner sc = new Scanner(System.in);
			String userInput;
			
			System.out.printf("Client> ");
			while(sc.hasNext() == true) {
				//Reads in messages user inputs
				userInput = sc.nextLine();

				if(userInput.toUpperCase().equals("EXIT")) {
					//Disconnect from Server
					System.exit(0);
				} else {
					//Sends userInput to Server through PrintStream
					out.println(userInput);
					
					//When receives echo from Server, Prints to console
					System.out.println("Server> " + br.readLine());
					
					//Prompts user to enter message
					System.out.printf("Client> ");
				}
		   }
		}		
    }
}

