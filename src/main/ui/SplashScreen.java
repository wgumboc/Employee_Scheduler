package ui;

import model.Position;
import model.PositionList;
import model.SkillsList;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class SplashScreen extends JFrame {
    private JFrame frame;
    private String imagePath;
    private static int SPLASH_SCREEN_TIME = 5000;

    // EFFECTS: creates the splash screen for the application load
    public SplashScreen() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(445,447);

        imagePath = "src\\main\\ui\\MS Logo.png";
        ImageIcon i = new ImageIcon(imagePath);


        Image image = i.getImage();
        //Image newimg = image.getScaledInstance(640, 360,  java.awt.Image.SCALE_SMOOTH); //pepsiman version
        Image newimg = image.getScaledInstance(445, 447,  java.awt.Image.SCALE_SMOOTH);
        i = new ImageIcon(newimg);

        JLabel l = new JLabel(i);
        frame.setLocationRelativeTo(null);

        frame.add(l);

        frame.setUndecorated(true);
        frame.setVisible(true);

        // taken from https://stackoverflow.com/questions/62539867/how-to-make-a-jframe-close-itself-after-10-seconds
        Timer timer = new Timer(SPLASH_SCREEN_TIME, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ManningScheduleAppGUI app = new ManningScheduleAppGUI("s");
                frame.dispose();
                app.startProgram();
            }
        });

        timer.setRepeats(false);
        timer.start();
        playMusic("music\\Beat.wav");

    }

    // taken from https://stackoverflow.com/questions/51751680/java-audioinputstream-
    //            ioexception-cannot-read-a-single-byte-if-frame-size-1
    // EFFECTS: plays music on startup
    public static void playMusic(String filepath) {
        InputStream music;
        try {
            music = new FileInputStream(new File(filepath));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

}

