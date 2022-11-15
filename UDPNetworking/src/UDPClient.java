import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket socket = null;
		
		try {
			
			/* */
			socket = new DatagramSocket();
			/* Buffer to store data */
			byte[] buffer = new byte[256];
			
			InetAddress ipAddress = InetAddress.getByName("127.0.0.1");
			buffer = "Hello".getBytes();
			/* this builds the packet */
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length,ipAddress,8080);
			
			/* Sends the packet */
			socket.send(packet);
				
			
			buffer = new byte[256];
			packet = new DatagramPacket(buffer,buffer.length);
			socket.receive(packet);
			System.out.print("Response from Client: ");
			System.out.print(new String(packet.getData()).trim());
			
			
		}catch(SocketException ex) {
			System.out.println(ex.getMessage());
		}finally {
			if(null!=socket)
			{
				socket.close();
			}
		}
		
	}

}
