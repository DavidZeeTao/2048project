package view;

import controller.GameController;
import util.ImageBack;
import util.SoundEffect;

import javax.swing.*;
import java.awt.*;

public class MemberFrame extends JFrame {
    private JButton playBtn;
    private JButton loadBtn;
    private String username;
    private JLabel userlabel;
    private String background;
    private GameController controller;
    public MemberFrame(String username,String background){
        this.username = username;
        this.background = background;
        this.setTitle("2048 Game");
        this.setSize(700,900);
        this.setLayout(null);

        playBtn = createButton("New Game", new Point(275, 350), 160, 50);
        loadBtn = createButton("Load Previous Game",new Point(275,450), 160,50);
        this.add(playBtn);
        this.add(loadBtn);

        userlabel = createLabel("Welcome,"+username, new Font("serif", Font.ITALIC, 22), new Point(480, 200), 180, 50);
        this.add(userlabel);

        playBtn.addActionListener(e->{
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            ModeFrame modeFrame = new ModeFrame(username,background);
            this.dispose();
        });
        loadBtn.addActionListener(e->{
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            GameFrame frame = new GameFrame(700,900,username,background);
            this.dispose();
            frame.getController().loadGame(0,username);
        });

        new ImageBack(this,background);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
