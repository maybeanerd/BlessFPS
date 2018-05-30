
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class DemoJFileChooser extends JPanel
        implements ActionListener {

    JButton go;

    JFileChooser chooser;
    String choosertitle;

    public String path;

    public DemoJFileChooser() {
        go = new JButton("Find Bless Online Path");
        go.addActionListener(this);
        add(go);
        path = null;
    }

    public void actionPerformed(ActionEvent e) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = chooser.getCurrentDirectory().toString();
                        System.out.println("getCurrentDirectory(): "
                    + path);
            System.out.println("getSelectedFile() : "
                    + chooser.getSelectedFile());
        } else {
            path = null;
            System.out.println("No Selection ");
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }
}
