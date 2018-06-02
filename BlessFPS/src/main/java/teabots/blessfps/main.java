
package teabots.blessfps;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

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
        Engine = new ArrayList<>();
        BaseSystem = new ArrayList<>();
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

    public String getSteamPath() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        return WinRegistry.readString(WinRegistry.HKEY_CURRENT_USER, "Software\\Valve\\Steam", "SteamPath");
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private static ArrayList<String> Engine;
    private static ArrayList<String> BaseSystem;

    private static String blessPath;
    private static int poolSize, cores;
    private static Boolean mipmapsDisabled, decalsDisabled, distortionDisabled, dynamicShadowsDisabled, fogDisabled;

    public static void main(String s[]) {
        JFrame frame = new JFrame("BlessFPS 1.0.1 - made by T0TProduction#0001 ");
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
        //TODO get Steam path automatically and check if Bless is already found
        frame.add(panel, "Center");
        frame.setSize(new Dimension(800, 500));
        frame.setVisible(true);

        while (true) { //i know this is bad code, but i want to get it working before opimtimizing and i havent used java in a long time
            blessPath = panel.path;
            //for some reason only after printing this the if statement will work, wtf java
            System.out.println(blessPath);
            if (blessPath != null) {
                if (blessPath.toLowerCase().endsWith(("\\steamapps\\common\\Bless Online").toLowerCase())) {
                    break;
                } else {
                    infoBox("The path you chose was not a compatible path. Please try again.\nYour selected path was: " + panel.path, "Path finding error");
                    panel.path = null;
                }
            }
        }
        //TODO add that one sees whats currently set
        getAllData();

        //put the settings here again
        frame.getContentPane().removeAll();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));

        JPanel checkBoxPanel1 = new JPanel();
        //checkBoxPanel.setLayout(new GridLayout());

        mainPanel.add(new JLabel("<html>You should deactivate everything if you have performance issues,<br />though these settings lower the visual quality.</html>"));
        JCheckBox exchangingCard1 = new JCheckBox("disable mipmap fadeins/fadeouts");
        checkBoxPanel1.add(exchangingCard1);

        JPanel checkBoxPanel2 = new JPanel();
        JCheckBox exchangingCard2 = new JCheckBox("disable decals");
        checkBoxPanel2.add(exchangingCard2);

        JPanel checkBoxPanel3 = new JPanel();
        JCheckBox exchangingCard3 = new JCheckBox("disable distortions");
        checkBoxPanel3.add(exchangingCard3);

        JPanel checkBoxPanel4 = new JPanel();
        JCheckBox exchangingCard4 = new JCheckBox("disable dynamic shadows");
        checkBoxPanel4.add(exchangingCard4);

        JPanel checkBoxPanel5 = new JPanel();
        JCheckBox exchangingCard5 = new JCheckBox("disable fog");
        checkBoxPanel5.add(exchangingCard5);

        mainPanel.add(checkBoxPanel1);
        mainPanel.add(checkBoxPanel2);
        mainPanel.add(checkBoxPanel3);
        mainPanel.add(checkBoxPanel4);
        mainPanel.add(checkBoxPanel5);

        frame.add(mainPanel);//TOTO add automatic detection via JSensor
        mainPanel.add(new JLabel("Hardware allocation, this should always be adjusted because it does not make quality worse:"));

        JPanel p = new JPanel();
        TextField intFiled = new TextField("4096");
        p.add(new JLabel("<html>GPU memory pool size. This should be equal to the VRAM your GPU has, in MB<br />Examples are: 4GB=4096, 6GB=6144, 8GB=8192</html>"));
        p.add(intFiled);

        mainPanel.add(p);

        JPanel p2 = new JPanel();
        TextField intFiled2 = new TextField("4");
        p2.add(new JLabel("Threaded shader compile threshhold. This should be equal to the number of physical cores your CPU has."));
        p2.add(intFiled2);

        mainPanel.add(p2);
        JToggleButton doneBut = new JToggleButton("Apply patch");
        frame.add(doneBut);

        frame.revalidate();
        frame.repaint();

        while (true) { //i know this is bad but i had no time to see how events would work here
            System.out.println(doneBut);
            if (doneBut.getModel().isPressed()) {
                if (isInteger(intFiled.getText()) && isInteger(intFiled2.getText())) {
                    mipmapsDisabled = exchangingCard1.isSelected();
                    decalsDisabled = exchangingCard2.isSelected();
                    distortionDisabled = exchangingCard3.isSelected();
                    dynamicShadowsDisabled = exchangingCard4.isSelected();
                    fogDisabled = exchangingCard5.isSelected();
                    poolSize = Integer.parseInt(intFiled.getText());
                    cores = Integer.parseInt(intFiled2.getText());
                    break;
                } else {
                    infoBox("The values for memory pool and/or cores are not valid", "input error");
                }
            }
        }

        frame.getContentPane().removeAll();
        JLabel process = new JLabel("The settings are being applied...");
        frame.add(process);
        frame.revalidate();
        frame.repaint();

        //changes:
        //engine:
        process.setText("Engine.ini is being changed...");
        frame.revalidate();
        frame.repaint();
        Engine.set(133, "bStaticDecalsEnabled=" + !decalsDisabled);
        Engine.set(134, "bDynamicDecalsEnabled=" + !decalsDisabled);

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
        } else {

        }
        Engine.set(469, "MinTextureResidentMipCount=7");
        Engine.set(470, "PoolSize=" + poolSize);
        Engine.set(471, "MemoryMargin=" + 512);
        Engine.set(585, "ThreadedShaderCompileThreshold=" + cores);

        if (!Engine.contains("bShouldLogStatsData=False")) {
            Engine.add(240, "bShouldLogStatsData=False");
        }

        //baseSystem:
        process.setText("BaseSystem.ini is being changed...");
        frame.revalidate();
        frame.repaint();
        BaseSystem.set(19, "Distortion=" + !distortionDisabled);
        BaseSystem.set(19, "Distortion=" + !distortionDisabled);

        BaseSystem.set(7, "DynamicShadows=" + !dynamicShadowsDisabled);
        BaseSystem.set(442, "DynamicShadows=" + !dynamicShadowsDisabled);
        BaseSystem.set(459, "DynamicShadows=" + !dynamicShadowsDisabled);
        BaseSystem.set(583, "DynamicShadows=" + !dynamicShadowsDisabled);
        BaseSystem.set(599, "DynamicShadows=" + !dynamicShadowsDisabled);
        BaseSystem.set(8, "LightEnvironmentShadows=" + !dynamicShadowsDisabled);

        BaseSystem.set(27, "FogVolumes=" + !fogDisabled);

        BaseSystem.set(2, "StaticDecals=" + !decalsDisabled);
        BaseSystem.set(312, "StaticDecals=" + !decalsDisabled);

        //when done:
        process.setText("saving files...");
        frame.revalidate();
        frame.repaint();
        putAllData();
        process.setText("<html>Successfully completed performance patch.<br />You can now close the application and start Bless Online!</html>");
        frame.revalidate();
        frame.repaint();

    }
}
