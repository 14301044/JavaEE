
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		PrintWriter out = null;
		Socket cSocket = null;
		BufferedReader in = null;
		try {
			cSocket = new Socket("localhost", 3333);

			in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
			out = new PrintWriter(cSocket.getOutputStream(), true);

			while (true) {
				System.out.println("ÇëÊäÈë×Ö·û´®:");
				Scanner scanner = new Scanner(System.in);
				String str = scanner.nextLine();
				out.println(str);
				System.out.println(in.readLine());
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}