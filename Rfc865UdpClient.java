/**
 * @(#)Rfc865UdpClient.java
 *
 *
 * @author
 * @version 1.00 2021/9/16
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpClient {
	static String SERVER_NAME = "localhost";
	public static void main(String[] args) {

	DatagramSocket socket=null;
		try {
			InetAddress serverAddress = InetAddress.getByName(SERVER_NAME);
			socket = new DatagramSocket();
			socket.connect(serverAddress,17);
			System.out.println("UDP Client connected on port " + 17 + " to server: " + serverAddress.getCanonicalHostName());
		} catch (Exception e) {}

		try {
			String content ="Chockalingam Kasi, TE1, "+InetAddress.getLocalHost().getHostAddress();
			byte[] buf = content.getBytes("UTF-8");
            System.out.println("Content to send: " + content);
		//
		// 2. Send UDP request to server
		//
		DatagramPacket request = new DatagramPacket(buf,buf.length);
		System.out.println("Request sent");
		socket.send(request);
		System.out.println("Waiting for reply");

		byte[] replyBuf = new byte[512];
        DatagramPacket reply = new DatagramPacket(replyBuf, replyBuf.length);

        socket.receive(reply);


		String replyContent = new String(replyBuf);
        System.out.println("Received reply: " + replyContent);



		} catch (IOException e) {}finally {
            socket.close();
        }
	}
}