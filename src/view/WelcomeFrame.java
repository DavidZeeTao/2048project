package view;

import util.FileUtil;
import util.ImageBack;
import util.PlayBGM;
import util.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WelcomeFrame extends JFrame {
    private WelcomeFrame frameinstance;
    private JButton logBtn;
    private JButton registerBtn;
    private JButton guestBtn;
    private int width;
    private int height;
    private String background;
    private boolean bgm;

    public WelcomeFrame(int width, int height, String background, boolean bgm) {
        frameinstance = this;
        this.setTitle("2048 Game");
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.background = background;
        this.bgm = bgm;

        logBtn = createButton("Login", new Point(275, 400), 110, 50);
        this.add(logBtn);
        registerBtn = createButton("Register", new Point(275, 470), 110, 50);
        this.add(registerBtn);
        guestBtn = createButton("GuestMode", new Point(275, 540), 110, 50);
        this.add(guestBtn);

        this.logBtn.addActionListener(new LogClickListener());
        this.registerBtn.addActionListener(new RegisterClickListener());
        this.guestBtn.addActionListener(new GuestClickListener());

        new ImageBack(this, background);
        if(bgm){
            new PlayBGM("src/file/soundeffect/BGM.wav");
        }

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private static JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        return button;
    }

    private static JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        return label;
    }

    private static JTextField createTextField(Point location, int width, int height) {
        JTextField textField = new JTextField();
        textField.setLocation(location);
        textField.setSize(width, height);
        return textField;
    }

    private static JPasswordField createPasswordField(Point location, int width, int height) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setLocation(location);
        passwordField.setSize(width, height);
        return passwordField;
    }

    private static JFrame createFrame(String name, int width, int height) {
        JFrame frame = new JFrame(name);
        frame.setLayout(null);
        frame.setSize(width, height);

        JLabel accountlabel = createLabel("Please enter your account", new Font("Arial", Font.BOLD, 14), new Point(50, 250), 300, 50);
        frame.add(accountlabel);
        JLabel passwordlabel = createLabel("Please enter your password", new Font("Arial", Font.BOLD, 14), new Point(50, 350), 300, 50);
        frame.add(passwordlabel);

        return frame;
    }

    private class LogClickListener implements ActionListener {
        private static final JTextField accountfield = createTextField(new Point(300, 250), 110, 50);
        private static final JPasswordField passwordField = createPasswordField(new Point(300, 350), 110, 50);
        private static JFrame loginframe;

        @Override
        public void actionPerformed(ActionEvent e) {
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            loginframe = createFrame("Login", width, height);
            loginframe.add(accountfield);
            loginframe.add(passwordField);
            JButton confirmbtn1 = createButton("Confirm", new Point(300, 500), 110, 50);
            loginframe.add(confirmbtn1);
            new ImageBack(loginframe,background);
            confirmbtn1.addActionListener(new WelcomeFrame.ConfirmClickListener1());
            loginframe.setVisible(true);
        }

        public static String getAccount() {
            return accountfield.getText();
        }

        public static String getPassword() {
            char[] password = passwordField.getPassword();
            return Arrays.toString(password);
        }

        public static void closeframe() {
            loginframe.dispose();
        }
    }

    private class RegisterClickListener implements ActionListener {
        private static final JTextField accountfield = createTextField(new Point(300, 250), 110, 50);
        private static final JPasswordField passwordField = createPasswordField(new Point(300, 350), 110, 50);

        @Override
        public void actionPerformed(ActionEvent e) {
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            JFrame registerFrame = createFrame("Register", width, height);
            registerFrame.add(accountfield);
            registerFrame.add(passwordField);
            JButton confirmbtn2 = createButton("Confirm", new Point(300, 500), 110, 50);
            registerFrame.add(confirmbtn2);
            new ImageBack(registerFrame,background);
            confirmbtn2.addActionListener(new WelcomeFrame.ConfirmClickListener2());
            registerFrame.setVisible(true);
        }

        public static String getAccount() {
            return accountfield.getText();
        }

        public static String getPassword() {
            char[] password = passwordField.getPassword();
            return Arrays.toString(password);
        }
    }

    private class ConfirmClickListener1 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            if (AccountCheck1(LogClickListener.getAccount(), LogClickListener.getPassword())) {
                new SoundEffect("src/file/soundeffect/SoundEffect/游戏胜利.wav");
                JOptionPane.showMessageDialog(null, "Log in successfully!");
                new MemberFrame(LogClickListener.getAccount(), background);
                frameinstance.dispose();
                LogClickListener.closeframe();
            } else {
                new SoundEffect("src/file/soundeffect/SoundEffect/交换失败.wav");
                JOptionPane.showMessageDialog(null, "Please enter correct account and password");
            }
        }
    }

    private class ConfirmClickListener2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            if (AccountCheck2(RegisterClickListener.getAccount(), RegisterClickListener.getPassword())) {
                new SoundEffect("src/file/soundeffect/SoundEffect/游戏胜利.wav");
                String account = RegisterClickListener.getAccount();
                String password = RegisterClickListener.getPassword();
                List<String> accountline = new ArrayList<>();
                List<String> passwordline = new ArrayList<>();
                accountline.add(account);
                passwordline.add(password);
                FileUtil.writeFileFromList("src/file/accounts/" + account + ".txt", accountline);
                FileUtil.writeFileFromList("src/file/password/" + account + ".txt", passwordline);
                JOptionPane.showMessageDialog(null, "Register Successfully!");
            } else {
                new SoundEffect("src/file/soundeffect/SoundEffect/交换失败.wav");
                JOptionPane.showMessageDialog(null, "Sorry, the account is used");
            }
        }
    }

    private static boolean AccountCheck1(String account, String password) {
        List<String> accountlines = FileUtil.readFileToList("src/file/accounts/" + account + ".txt");
        List<String> passwordlines = FileUtil.readFileToList("src/file/password/" + account + ".txt");
        if (accountlines == null || passwordlines == null)
            return false;
        if (accountlines.isEmpty() || passwordlines.isEmpty())
            return false;

        return accountlines.get(0).equals(account) && passwordlines.get(0).equals(password);
    }

    private static boolean AccountCheck2(String account, String password) {
        List<String> accountlines = FileUtil.readFileToList("src/file/accounts/" + account + ".txt");
        if (accountlines != null) {
            return false;
        }
        return true;
    }

    public class GuestClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
            JOptionPane.showMessageDialog(null, "Mention: You can't save or load game in guest mode");
            frameinstance.dispose();
            new GameFrame(width, height, null, background);
        }
    }

}
