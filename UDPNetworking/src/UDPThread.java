
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


public class UDPThread extends Thread {

	private DatagramSocket socket = null;
	private boolean keepRunning = true;
	
	public UDPThread() throws SocketException  {
		this("Hello Thread");
	}
	
	public UDPThread(String name) throws SocketException {
		socket = new DatagramSocket(8080);
	}
	
	@Override
	public void run() {
		
		System.out.print("Server ready and waiting for client requests");
		
		while(keepRunning) {
			
			/* Create a buffer for data */
			byte[] buf = new byte[256];
			/* Initate a random number*/
			double db = Math.random() * 5;
			/* COnvert to String */
			int rd = (int)db;
			String rnd = String.valueOf(rd);

			/* Build a Packet Using data  Packet */
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
			
            /* get the buffer and read it into bytes*/
			buf = rnd.getBytes();

            try {
            	
            	/* wait for response from client*/
				socket.receive(packet);

				//String input = new String(packet.getData()).trim();

				InetAddress address = packet.getAddress();
                
				int port = packet.getPort();
                
                System.out.println("\nsending back " +new String(buf));

                packet = new DatagramPacket(buf, buf.length, address, port);

                socket.send(packet);
                
				
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		socket.close();
	}
	
	
}
