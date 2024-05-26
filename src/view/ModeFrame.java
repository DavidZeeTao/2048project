package view;

import util.ImageBack;
import util.SoundEffect;

import javax.swing.*;
import java.awt.*;

public class ModeFrame extends JFrame {
    private JButton classicBtn;
    private String username;
    private JLabel userlabel;
    private String background;
    public ModeFrame(String username, String background){
        this.setTitle("2048 Game");
        this.setSize(700,900);
        this.setLayout(null);
        this.background = background;
        this.username = username;

        classicBtn = createButton("Classic Mode", new Point(275, 400), 160, 50);
        this.add(classicBtn);

        userlabel = createLabel("Welcome,"+username, new Font("serif", Font.ITALIC, 22), new Point(480, 200), 180, 50);
        this.add(userlabel);

        classicBtn.addActionListener(e->{
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            new GameFrame(700, 900,username,background);
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.dispose();
        });

        new ImageBack(this,background);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    private JButton createButton(String name, Point location, int width, int height){
        JButton Btn = new JButton(name);
        Btn.setLocation(location);
        Btn.setSize(width,height);
        return Btn;
    }

    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        return label;
    }
}
