
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
        Engine=new ArrayList<>();
        BaseSystem=new ArrayList<>();
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

            //for testing
            ArrayList<String> test = new ArrayList<>();
            f1 = new File("S:\\SteamLibrary\\steamapps\\common\\Bless Online\\Engine\\Config\\BaseEngine.ini");
            fr = new FileReader(f1);
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                test.add(line);
            }
            fr.close();
            br.close();
            System.out.println(test.get(17));

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
        int poolSize, cores;
        Boolean mipmapsDisabled, decalsDisabled, distortionDisabled, dynamicShadowsDisabled, fogDisabled;
        frame.add(new JLabel("TODO options"));
        frame.revalidate();
        frame.repaint();

        //now, for safety
        return;

        //changes:
        //engine:
        if (decalsDisabled) {
            Engine.set(133, "bStaticDecalsEnabled=FALSE");
            Engine.set(134, "bDynamicDecalsEnabled=False");
        }

        Engine.set(192, "bDisablePhysXHardwareSupport=False");
        Engine.set(220, "MaxSmoothedFrameRate=120");
        Engine.set(556, "MaxSmoothedFrameRate=120");
        Engine.set(211, "PhysXGpuHeapSize=64");
        Engine.set(212, "PhysXMeshCacheSize=16");
        if (mipmapsDisabled) {
            Engine.set(204, "MipFadeInSpeed0=0");
            Engine.set(205, "MipFadeOutSpeed0=0");
            Engine.set(206, "MipFadeInSpeed1=0");
            Engine.set(207, "MipFadeOutSpeed1=0");
        }
        Engine.set(469, "MinTextureResidentMipCount=7");
        Engine.set(470, "PoolSize=" + poolSize);
        Engine.set(470, "MemoryMargin=512");
        Engine.set(585, "ThreadedShaderCompileThreshold=" + cores);

        Engine.add(240, "bShouldLogStatsData=False");

        //baseSystem:
        if (distortionDisabled) {
            BaseSystem.set(19, "Distortion=FALSE");
            BaseSystem.set(19, "Distortion=FALSE");
        }
        if (dynamicShadowsDisabled) {
            BaseSystem.set(7, "DynamicShadows=FALSE");
            BaseSystem.set(442, "DynamicShadows=FALSE");
            BaseSystem.set(459, "DynamicShadows=FALSE");
            BaseSystem.set(583, "DynamicShadows=FALSE");
            BaseSystem.set(599, "DynamicShadows=FALSE");
            BaseSystem.set(8, "LightEnvironmentShadows=FALSE");
        }
        if (fogDisabled) {
            BaseSystem.set(27, "FogVolumes=FALSE");
        }
        if (decalsDisabled) {
            BaseSystem.set(2, "StaticDecals=FALSE");
            BaseSystem.set(312, "StaticDecals=FALSE");
        }

        //when done:
        putAllData();
    }
}
