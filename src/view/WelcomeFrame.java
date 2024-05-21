package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serial;

public class WelcomeFrame extends JFrame{
    private WelcomeFrame frameinstance;
    private JButton logBtn;
    private JButton registerBtn;
    private JButton guestBtn;
    private JButton playBtn;
    private int width;
    private int height;
    private boolean loginstage = false;
    private final File accountfolder = new File("src/fileload/accounts");

    public WelcomeFrame(int width, int height) {
        frameinstance = this;
        this.setTitle("2048 Game");
        this.setLayout(null);
        this.width = width;
        this.height = height;
        this.setSize(width, height);

        logBtn = createButton("Login",new Point(275,250),110,50);this.add(logBtn);
        registerBtn = createButton("Register", new Point(275,320),110,50);this.add(registerBtn);
        guestBtn = createButton("GuestMode", new Point(275, 390), 110, 50);this.add(guestBtn);

        this.logBtn.addActionListener(new WelcomeFrame.LogClickListener());
        this.registerBtn.addActionListener(new WelcomeFrame.RegisterClickListener());
        this.guestBtn.addActionListener(new WelcomeFrame.PlayClickListener());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private JButton createButton(String name, Point location, int width, int height) {
        JButton button = new JButton(name);
        button.setLocation(location);
        button.setSize(width, height);
        return button;
    }
    private JLabel createLabel(String name, Font font, Point location, int width, int height) {
        JLabel label = new JLabel(name);
        label.setFont(font);
        label.setLocation(location);
        label.setSize(width, height);
        return label;
    }
    private JTextField createTextField(Point location,int width, int height){
        JTextField textField = new JTextField();
        textField.setLocation(location);
        textField.setSize(width,height);
        return textField;
    }
    private JPasswordField createPasswordField(Point location,int width, int height){
        JPasswordField passwordField = new JPasswordField();
        passwordField.setLocation(location);
        passwordField.setSize(width,height);
        return passwordField;
    }
    private JPanel createPanel(int size){
        JPanel panel = new JPanel();
        panel.setVisible(true);
        panel.setFocusable(true);
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        panel.setSize(size, size);
        return panel;
    }
    private JFrame createFrame(String name,int width, int height){
        JFrame frame = new JFrame(name);
        frame.setLayout(null);
        frame.setSize(width, height);

        JTextField account = createTextField(new Point(300,100),110,50);frame.add(account);
        JPasswordField password = createPasswordField(new Point(300,200),110,50);frame.add(password);

        JLabel accountlabel = createLabel("Please enter your account", new Font("Arial",Font.BOLD,14), new Point(50,100),300, 50 );
        frame.add(accountlabel);
        JLabel passwordlabel = createLabel("Please enter your password", new Font("Arial",Font.BOLD,14),new Point(50,200), 300, 50 );
        frame.add(passwordlabel);

        return frame;
    }
    private boolean AccountCheck(String account, char[] password){
        return true;
    }
    private class LogClickListener implements ActionListener {
        private static String account;
        private static char[] password;
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame loginframe = createFrame("Login",width,height);
            JButton confirmbtn1 = createButton("Confirm", new Point(300,350),110,50);loginframe.add(confirmbtn1);
//            confirmbtn1.addActionListener(new WelcomeFrame.ConfirmClickListener1());
            loginframe.setVisible(true);

            if(loginframe.getComponentAt(300,100) instanceof JTextField){
                JTextField account = (JTextField) loginframe.getComponentAt(300,100);
                this.account = account.getText();
            }
            if(loginframe.getComponentAt(300,200) instanceof JPasswordField){
                JPasswordField password = (JPasswordField) loginframe.getComponentAt(300,200);
                this.password = password.getPassword();
            }
        }

        public String getAccount() {
            return account;
        }

        public char[] getPassword() {
            return password;
        }
    }

    private class RegisterClickListener implements ActionListener {
        private static String account;
        private static char[] password;
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame registerFrame = createFrame("Register", width, height);
            JButton confirmbtn2 = createButton("Confirm", new Point(300,350),110,50);registerFrame.add(confirmbtn2);
//            confirmbtn2.addActionListener(new WelcomeFrame.ConfirmClickListener2());
            registerFrame.setVisible(true);

            if(registerFrame.getComponentAt(300,100) instanceof JTextField){
                JTextField account = (JTextField) registerFrame.getComponentAt(300,100);
                this.account = account.getText();
            }
            if(registerFrame.getComponentAt(300,200) instanceof JPasswordField){
                JPasswordField password = (JPasswordField) registerFrame.getComponentAt(300,200);
                this.password = password.getPassword();
            }
        }

        public String getAccount() {
            return account;
        }

        public char[] getPassword() {
            return password;
        }
    }
//    private class ConfirmClickListener1 implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//        }
//    }
//    private class ConfirmClickListener2 implements ActionListener{

//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if(!AccountCheck(RegisterClickListener.account,RegisterClickListener.password)){
//
//            }
//        }
//    }
    public class PlayClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!loginstage){
                frameinstance.dispose();
                new GameFrame(width,height,false);
            }
            else if(loginstage){
                frameinstance.dispose();
                new GameFrame(width,height,true);
            }
        }
    }
}
