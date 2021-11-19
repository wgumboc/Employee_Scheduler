package ui;

import model.Position;
import model.PositionList;
import model.SkillsList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SplashScreen extends JFrame {
    private JFrame frame;
    private String imagePath;

    public SplashScreen() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(640,360);

        imagePath = "C:\\Users\\wgumb\\IdeaProjects\\project_d2o8\\src\\main\\ui\\pepsiman.jpg";
        ImageIcon i = new ImageIcon(imagePath);


        Image image = i.getImage(); // transform it
        Image newimg = image.getScaledInstance(640, 360,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        i = new ImageIcon(newimg);

        JLabel l = new JLabel(i);
        frame.setLocationRelativeTo(null);

        frame.add(l);

        frame.setUndecorated(true);
        frame.setVisible(true);

        // taken from https://stackoverflow.com/questions/62539867/how-to-make-a-jframe-close-itself-after-10-seconds
        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ManningScheduleAppGUI app = new ManningScheduleAppGUI("s");
                frame.dispose();
                app.startProgram();
            }
        });

        timer.setRepeats(false);
        timer.start();

    }

}

