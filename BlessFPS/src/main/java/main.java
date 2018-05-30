
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
            File f1 = new File(blessPath + "\\Engine\\Config\\BaseEngine.ini");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                Engine.add(line);
            }
            fr.close();
            br.close();

            f1 = new File(blessPath + "\\Engine\\Config\\BaseSystemSettings.ini");
            fr = new FileReader(f1);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                BaseSystem.add(line);
            }
            fr.close();
            br.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void putAllData() {
        try {
            //putting file
            File f1 = new File(blessPath + "\\Engine\\Config\\BaseEngine.ini");

            FileWriter fw = new FileWriter(f1);
            BufferedWriter out = new BufferedWriter(fw);
            for (String s : Engine) {
                out.write(s);
                out.newLine();

            }
            out.flush();
            out.close();

            f1 = new File(blessPath + "\\Engine\\Config\\BaseSystemSettings.ini");

            fw = new FileWriter(f1);
            out = new BufferedWriter(fw);
            for (String s : BaseSystem) {
                out.write(s);
                out.newLine();
            }
            out.flush();
            out.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static ArrayList<String> Engine;
    private static ArrayList<String> BaseSystem;

    private static String blessPath;

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
        getAllData();
        frame.add(new JLabel("TODO optimization"));
        frame.revalidate();
        frame.repaint();

        //idea to add zeile Engine.add(241, "bShouldLogStatsData=False")
        //when done:
        //putAllData();
    }
}
