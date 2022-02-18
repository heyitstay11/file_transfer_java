import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Client{
    public static void main(String[] args) {
        
        final File[] fileToSend = new File[1];
        
        JFrame jFrame = new JFrame("Client");
        jFrame.setSize(450, 450);
        jFrame.setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        JLabel jLabelTitle = new JLabel("File Sender");
        jLabelTitle.setFont(new Font("Arial", Font.BOLD, 25));
        jLabelTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        jLabelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel jLFileName = new JLabel("Choose a File to send");
        jLFileName.setFont(new Font("Arial", Font.BOLD, 20));
        jLFileName.setBorder(new EmptyBorder(50, 0, 0, 0));
        jLFileName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel jpButton = new JPanel();
        jpButton.setBorder(new EmptyBorder(75, 0, 10, 0));
    
        JButton jbSendFile = new JButton("Send File");
        jbSendFile.setPreferredSize(new Dimension(150, 75));
        jbSendFile.setFont(new Font("Arial", Font.BOLD, 20));

        JButton jpChooseFile = new JButton("Send File");
        jpChooseFile.setPreferredSize(new Dimension(150, 75));
        jpChooseFile.setFont(new Font("Arial", Font.BOLD, 20));

        jpButton.add(jbSendFile); jpButton.add(jpChooseFile);

        jpChooseFile.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setDialogTitle("Choose a file to Send");
                
                if(jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    fileToSend[0] = jFileChooser.getSelectedFile();
                    jLFileName.setText("The file you want to send is: " + fileToSend[0].getName());
                }
            }   
        });

        jbSendFile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileToSend[0] == null){
                    jLFileName.setText("Please Choose a file first");
                }else{
                    try{
                        FileInputStream fis = new FileInputStream(fileToSend[0].getAbsolutePath());
                        try (Socket socket = new Socket("localhost", 1234)) {
                            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                            String fileName = fileToSend[0].getName();
                            byte[] fileNameBytes = fileName.getBytes();
                            byte[] fileContentBytes = new byte[(int) fileToSend[0].length()];
                            fis.read(fileContentBytes);

                            dataOutputStream.writeInt(fileNameBytes.length);
                            dataOutputStream.write(fileNameBytes);

                            dataOutputStream.writeInt(fileContentBytes.length);
                            dataOutputStream.write(fileContentBytes);
                        }

                    } catch (IOException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        });

        jFrame.add(jLabelTitle);
        jFrame.add(jLFileName);
        jFrame.add(jpButton);
        jFrame.setVisible(true);
    }

}