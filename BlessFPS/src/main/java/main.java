
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
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

    private static void getAllData() {
        try {
            //getting file
            String line;
            ArrayList<String> lines = new ArrayList<>();
            File f1 = new File("S:\\SteamLibrary\\steamapps\\common\\Bless Online\\Engine\\Config\\BaseEngine.ini");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            fr.close();
            br.close();

            System.out.println(lines.get(3));

            //putting file
            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for (String s : lines) {
                out.write(s);
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String blessPath;

    public static void main(String s[]) {
        //testing
        getAllData();
        //endof testing

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
            blessPath = panel.path;
            //for some reason only after printing this the if statement will work, wtf java
            System.out.println(blessPath);
            if (blessPath != null) {
                if (blessPath.endsWith("\\steamapps\\common\\Bless Online")) {
                    break;
                } else {
                    infoBox("The path you chose was not a compatible path. Please try again.\nYour selected path was: " + panel.path, "Path finding error");
                    panel.path = null;
                }
            }
        }
        frame.getContentPane().removeAll();
        frame.add(new JLabel("TODO optimization"));
        frame.revalidate();
        frame.repaint();

    }
}
