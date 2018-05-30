
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Basti
 */
public class main {

    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String s[]) {
        JFrame frame = new JFrame("BlessFPS");
        DemoJFileChooser panel = new DemoJFileChooser();
        frame.addWindowListener(
                new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setLayout(new FlowLayout());
        JLabel intro = new JLabel("<html>Hi there and thanks for using BlessFPS!<br/>This program will allow you to change settings inside of the configuration files of Bless Online with an easy interface.<br/>At first, you need to choose the path your game is in.<br/>Your path should look something like this:<br/>[your steam path]\\steamapps\\common\\Bless Online</html>");
        frame.add(intro);
        frame.add(panel, "Center");
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
        while (true) { //i know this is bad code, but i want to get it working before opimtimizing and i havent used java in a long time
            System.out.println(panel.path);
            if (panel.path != null) {
                if (panel.path.endsWith("\\steamapps\\common\\Bless Online")) {
                    break;
                } else {
                    infoBox("The path you chose was not a compatible path. Please try again.\nYour selected path was: " + panel.path, "Path finding error");
                    panel.path = null;
                }
            }
        }
        frame.getContentPane().removeAll();
        frame.add(new JLabel("test"));
        frame.revalidate();
        frame.repaint();

    }
}
