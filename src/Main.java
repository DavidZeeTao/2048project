import view.GameFrame;
import view.WelcomeFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeFrame welcomeFrame = new WelcomeFrame(700, 500);

        });
    }
}
