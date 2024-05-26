package view;

import controller.GameController;
import util.ColorMap;
import util.ImageBack;
import util.SoundEffect;
import util.addButtonBlackgroundPicture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameFrame extends JFrame {

    private GameController controller;
    private JButton restartBtn;
    private JButton loadBtn;
    private JButton saveBtn;
    private JButton undoBtn;
    private JButton menuBtn;
    private JButton modeBtn;
    private JButton backgroundBtn;

    private JLabel stepLabel;
    private JLabel userlabel;
    private GamePanel gamePanel;
    private String username;
    private String background;

    public GameFrame(int width, int height, String username, String background) {
        this.setTitle("2048 Game");
        this.setLayout(null);
        this.username = username;
        this.background = background;
        this.setSize(width, height);
        ColorMap.InitialColorMap();
        gamePanel = new GamePanel(400);
        gamePanel.setLocation(this.getHeight() / 15, 200);
        this.add(gamePanel);

        this.controller = new GameController(gamePanel, gamePanel.getModel());
        this.restartBtn = createButton("Restart", new Point(500, 280), 130, 50);
        this.undoBtn = createButton("Undo", new Point(500, 340), 130, 50);
        this.stepLabel = createLabel("Start", new Font("serif", Font.ITALIC, 22), new Point(480, 230), 180, 50);
        this.backgroundBtn = createButton("Switch Skin", new Point(500, 460), 130, 50);
        gamePanel.setStepLabel(stepLabel);

        if (username == null) {
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            this.menuBtn = createButton("Menu", new Point(500, 400), 130, 50);
            this.menuBtn.addActionListener(e -> {
                if (JOptionPane.showConfirmDialog(this, "Do you want to go back to menu?") == 0) {
                    this.dispose();
                    new WelcomeFrame(700, 900, background,false);
                }
            });
        }

        if (username != null) {
            this.loadBtn = createButton("Load", new Point(500, 520), 130, 50);
            this.saveBtn = createButton("Save", new Point(500, 580), 130, 50);
            this.modeBtn = createButton("Mode", new Point(500, 400), 130, 50);
            userlabel = createLabel("Welcome," + username, new Font("serif", Font.ITALIC, 22), new Point(480, 180), 180, 50);
            this.loadBtn.addActionListener(e -> {
                new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
                int input = JOptionPane.showConfirmDialog(this, "Do you want to load the previous game?");
                controller.loadGame(input, username);
                gamePanel.requestFocusInWindow();//enable key listener
            });
            this.saveBtn.addActionListener(e -> {
                new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
                int input = JOptionPane.showConfirmDialog(this, "Do you want to save the game?");
                controller.saveGame(input, username);
                gamePanel.requestFocusInWindow();//enable key listener
            });
            this.modeBtn.addActionListener(e -> {
                new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
                if (JOptionPane.showConfirmDialog(this, "Do you want to choose another mode?") == 0) {
                    this.dispose();
                    new ModeFrame(username, background);
                }
            });
        }


        this.restartBtn.addActionListener(e -> {
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            controller.restartGame();
            gamePanel.requestFocusInWindow();//enable key listener
        });
        this.undoBtn.addActionListener(e -> {
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            int input = JOptionPane.showConfirmDialog(this, "Do you want to move to the previous step?");
            controller.undo(input);
            gamePanel.requestFocusInWindow();
        });
        this.backgroundBtn.addActionListener(e -> {
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            Random random = new Random();
            int i,j = 0;
            switch (background){
                case "Jungle":
                    j=0;
                    break;
                case "City":
                    j=1;
                    break;
                case "Sunrise":
                    j=2;
                    break;
                case "Country":
                    j=3;
                    break;
            }
            while (true){
                i = random.nextInt(4);
                if(i!=j)
                    break;
            }
            switch (i){
                case 0:
                    this.background = "Jungle";
                    break;
                case 1:
                    this.background = "City";
                    break;
                case 2:
                    this.background = "Sunrise";
                    break;
                case 3:
                    this.background = "Country";
                    break;
            }
            this.dispose();
            GameFrame frame = new GameFrame(width, height, username, this.background);
            frame.getController().switchskin(0);
        });

        new ImageBack(this, background);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    private JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        this.add(button);
        return button;
    }

    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        this.add(label);
        return label;
    }
    public GameController getController() {
        return controller;
    }
}