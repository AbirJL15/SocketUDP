import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private DatagramSocket datagramSocket;
    private InetAddress inetAddress;
    private byte[] buffer;

    // Constructeur prenant une instance de DatagramSocket et InetAddress en paramètres
    public Client(DatagramSocket datagramSocket, InetAddress inetAddress) {
        this.datagramSocket = datagramSocket;
        this.inetAddress = inetAddress;
    }

    // Méthode pour envoyer des paquets au serveur et recevoir la réponse
    public void sendThenReceive() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                // Lecture de l'entrée utilisateur
                String messageToSend = scanner.nextLine();

                // Conversion du message en tableau de bytes
                buffer = messageToSend.getBytes();

                // Création d'un DatagramPacket pour l'envoi au serveur
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);

                // Création d'un DatagramPacket pour la réception de la réponse du serveur
                datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);

                // Conversion de la réponse en String et affichage
                String messageFromServer = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("The server says you said: " + messageFromServer);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

        // Fermeture du socket à la fin de l'exécution
        datagramSocket.close();
    }

    public static void main(String[] args) throws UnknownHostException, SocketException {
        // Création d'une nouvelle instance de DatagramSocket sans spécifier de port
        DatagramSocket datagramSocket = new DatagramSocket();

        // Récupération de l'adresse IP du serveur (dans cet exemple, "localhost")
        InetAddress inetAddress = InetAddress.getByName("localhost");

        // Création de l'instance Client
        Client client = new Client(datagramSocket, inetAddress);

        // Message d'invite pour l'utilisateur
        System.out.println("Send datagram packets to a server: ");

        // Appel de la méthode pour envoyer et recevoir des paquets
        client.sendThenReceive();
    }
}
