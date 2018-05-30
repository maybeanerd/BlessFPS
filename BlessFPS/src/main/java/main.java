
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
        while (true) {
            if (panel.path != null) {
                System.out.println("we get here");
                frame.getContentPane().removeAll();
                frame.add(new JLabel("test"));
                frame.revalidate();
                frame.repaint();
                break;
            }
        }

    }
}
