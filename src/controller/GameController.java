package controller;

import javafx.scene.paint.LinearGradient;
import model.GridNumber;
import util.FileUtil;
import view.GamePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used for interactive with JButton in GameFrame.
 */
public class GameController {
    private GamePanel view;
    private GridNumber model;


    public GameController(GamePanel view, GridNumber model) {
        this.view = view;
        this.model = model;

    }

    public void restartGame() {
        System.out.println("Do restart game here");
        model.clearnumbers();
        model.initialNumbers();
        view.updateGridsNumber();
        view.clearSteps();
    }

    public void saveGame(int input, String username) {
        if (input == 0) {
            String path = "src/file/game/" + username + ".txt";
            List<String> lines = model.convertGameToList();
            lines.add(Integer.toString(view.getSteps()));
            FileUtil.writeFileFromList(path, lines);
        }
    }

    public void loadGame(int input, String username) {
        String path = "src/file/game/" + username + ".txt";
        if (input == 0) {
            List<String> lines = FileUtil.readFileToList(path);
            if(lines == null){
                JOptionPane.showMessageDialog(null, """
                            FileType Error
                            Error Code:101""");
                return;
            }
            for (int i = 0; i < lines.size() - 1; i++) {
                String[] pieces = lines.get(i).split(" ");
                if (pieces.length != 4 || lines.size() - 1 != 4) {
                    JOptionPane.showMessageDialog(null, """
                        ChessBoard Error
                        Error Code:102""");
                    return;
                }
                for (int j = 0; j < pieces.length; j++) {
                    if (Integer.parseInt(pieces[j]) % 2 != 0) {
                        JOptionPane.showMessageDialog(null, """
                                Number Error
                                Error Code:103""");
                        return;
                    }
                }
            }
            view.setSteps(model.convertListToGame(lines));
            view.updateGridsNumber();
        }
    }

    public void undo(int input) {
        String path = "src/file/game/undo.txt";
        if (input == 0) {
            List<String> lines = FileUtil.readFileToList(path);
            if (lines != null) {
                view.setSteps(model.convertListToGame(lines));
                view.updateGridsNumber();
            }
        }
    }

    public void switchskin(int input) {
        String path = "src/file/game/switchskin.txt";
        if (input == 0) {
            List<String> lines = FileUtil.readFileToList(path);
            if (lines != null) {
                view.setSteps(model.convertListToGame(lines));
                view.updateGridsNumber();
            }
        }
    }

    //todo: add other methods such as loadGame, saveGame...

}
