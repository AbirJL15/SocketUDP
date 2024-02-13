import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

    private DatagramSocket datagramSocket;
    private byte[] buffer = new byte[256];

    // Constructeur prenant une instance de DatagramSocket en paramètre
    public Server(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    // Méthode pour recevoir les paquets du client et renvoyer la réponse
    public void receiveThenSend() {
        while (true) {
            try {
                // Création d'un DatagramPacket pour recevoir les données
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

                // Réception du paquet du client
                datagramSocket.receive(datagramPacket);

                // Récupération des informations du paquet reçu
                InetAddress clientAddress = datagramPacket.getAddress();
                int clientPort = datagramPacket.getPort();
                String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

                // Affichage du message du client
                System.out.println("Message from client: " + messageFromClient);

                // Réponse au client en renvoyant le même message
                datagramPacket = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                datagramSocket.send(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

        // Fermeture du socket à la fin de l'exécution
        datagramSocket.close();
    }

    public static void main(String[] args) throws SocketException {
        DatagramSocket datagramSocket = new DatagramSocket(1234);
        Server server = new Server(datagramSocket);
        server.receiveThenSend();
    }
}
