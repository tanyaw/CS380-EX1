
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public final class EchoServer_Multi implements Runnable {
	Socket socket;
		
	//Constructor
	public EchoServer_Multi(Socket socket) {
		this.socket = socket;
	}
	
	//Override run method -- Multithreading
	public void run() {
	   try{
			String address = socket.getInetAddress().getHostAddress();
			System.out.printf("Client connected: %s%n", address);

			//Receive data from Client
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			//Send data to Client
			OutputStream os = socket.getOutputStream();
			PrintStream out = new PrintStream(os, true, "UTF-8");
			String clientInput = " ";

			while(clientInput != null) {
				//Read Client's message from BufferedReader 
				clientInput = br.readLine();
				
				//Send echo (Client input) back to Client on PrintStream
				out.println(clientInput);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
      		try (ServerSocket serverSocket = new ServerSocket(22222)) {
            		while (true) {
                		try {
					//Create new Client Thread 
					//Each client object creates its own socket to communicate to the Server
					Thread test = new Thread(new EchoServer_Multi(serverSocket.accept()));
					test.start();
				} catch(IOException e){
					e.printStackTrace();
				}
            	} catch(IOException e) {
			e.printStackTrace();
		} 
    	}
}


