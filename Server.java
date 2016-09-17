
import java.net.*;

import java.io.*;

public class Server extends Thread {
	static ServerSocket serverSocket = null;
	Socket userSocket;
	static int user = 1;//�ͻ��˱��
	int ID;

	private Server(Socket userSoc) {//����һ���µĿͻ���
		ID = user++;
		System.out.println("�û� " + ID + " �Ѿ�����");
		userSocket = userSoc;
	}

	public void run() {
		BufferedReader in;
		PrintWriter out;
		String inputLine;
		StringBuffer sb;
		try {
			out = new PrintWriter(userSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					userSocket.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("bye"))
					break;
				sb = new StringBuffer(inputLine);
					out.println("����˵:" + sb.reverse().toString());
					//System.out.println(inputLine);
			}
			in.close();
			userSocket.close();
		} catch (IOException e) {
			System.out.println("User " + ID + " Exception ! ");
		}
	}

	public static void main(String[] args) {
		System.out.println("Server...");
		try {
			serverSocket = new ServerSocket(3333);
			while (true) {
				Socket s = serverSocket.accept();
				new Server(s).start();

			}
		} catch (IOException e) {
			System.out.println("������ֹ��" + e);
			System.exit(1);
		}

	}
}

