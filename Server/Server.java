import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static ArrayList<MyFile> myFiles = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        int fileId = 0;

        JFrame jFrame = new JFrame("Server");
        jFrame.setSize(400, 400);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JLabel jLTitle = new JLabel("File Reciever");
        jLTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jLTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        jLTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        jFrame.add(jLTitle);
        jFrame.add(jScrollPane);
        jFrame.setVisible(true);

        ServerSocket serverSocket = new ServerSocket(1234);

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                
            } catch (Exception e) {
                //TODO: handle exception
            }
        }

    }

}