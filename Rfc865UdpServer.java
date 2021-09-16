
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class Rfc865UdpServer {

	static String QUOTE = "Life is like riding a bicycle. To keep your balance you must keep moving - Albert Einstein";
	public static void main(String[] argv) {

		DatagramSocket socket =null;
			try {
				socket = new DatagramSocket(17);
				} catch (SocketException e) {}
			while (true) {
			try {
				while(true)
				{
					byte[] buf = new byte[512];
					//
					// 2. Listen for UDP request from client
					//
					DatagramPacket request = new DatagramPacket(buf,buf.length);
					System.out.println("Waiting for request...");
					socket.receive(request);
					String Content=new String(buf);
					System.out.println(Content);
					//
					// 3. Send UDP reply to client
					//
					InetAddress clientAddress =request.getAddress();
					int clientPort =request.getPort();
					System.out.println("From client: "+ clientAddress.getCanonicalHostName());

					String replyContent = QUOTE;
               		byte[] replyBuf = replyContent.getBytes("UTF-8");
                	System.out.println("Reply content: " + replyContent);


					DatagramPacket reply = new DatagramPacket(replyBuf,replyBuf.length,clientAddress,clientPort);
					System.out.println("Sending reply");
					socket.send(reply);
					}
				}catch (IOException e) {}

		 }
	}
}