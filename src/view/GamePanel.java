package view;

import model.GridNumber;
import util.FileUtil;
import util.SoundEffect;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class GamePanel extends ListenerPanel {
    private final int COUNT = 4;
    private GridComponent[][] grids;

    private GridNumber model;
    private JLabel stepLabel;
    private int steps;
    private final int GRID_SIZE;

    public GamePanel(int size) {
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
        this.setSize(size, size);
        this.GRID_SIZE = size / COUNT;
        this.grids = new GridComponent[COUNT][COUNT];
        this.model = new GridNumber(COUNT, COUNT);
        initialGame();

    }

    public GridNumber getModel() {
        return model;
    }

    public int getSteps() { return steps; }
    public void setSteps(int steps){
        this.steps = steps;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
    }

    public void initialGame() {
        this.steps = 0;
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j] = new GridComponent(i, j, model.getNumber(i, j), this.GRID_SIZE);
                grids[i][j].setLocation(j * GRID_SIZE, i * GRID_SIZE);
                this.add(grids[i][j]);
            }
        }
        model.printNumber();//check the 4*4 numbers in game
        this.repaint();
    }

    public void updateGridsNumber() {
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[i].length; j++) {
                grids[i][j].setNumber(model.getNumber(i, j));
            }
        }
        repaint();
    }

    public void autosave(String name){
        String path = "src/file/game/"+name+".txt";
        List<String> lines = model.convertGameToList();
        lines.add(Integer.toString(steps));
        FileUtil.writeFileFromList(path,lines);
    }

    @Override
    public void doMoveRight() {
        new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
        System.out.println("Click VK_RIGHT");
        autosave("undo");
        this.model.moveRight();
        this.model.creatgrid();
        this.updateGridsNumber();
        this.afterMove();
        autosave("switchskin");
    }
    public void doMoveLeft() {
        new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
        System.out.println("Click VK_LEFT");
        autosave("undo");
        this.model.moveLeft();
        this.model.creatgrid();
        this.updateGridsNumber();
        this.afterMove();
        autosave("switchskin");
    }
    public void doMoveUp() {
        new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
        System.out.println("Click VK_UP");
        autosave("undo");
        this.model.moveUp();
        this.model.creatgrid();
        this.updateGridsNumber();
        this.afterMove();
        autosave("switchskin");
    }
    public void doMoveDown() {
        new SoundEffect("src/file/soundeffect/SoundEffect/ChessClick.wav");
        System.out.println("Click VK_DOWN");
        autosave("undo");
        this.model.moveDown();
        this.model.creatgrid();
        this.updateGridsNumber();
        this.afterMove();
        autosave("switchskin");
    }
    public void afterMove() {
        this.steps++;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
    }

    public void setStepLabel(JLabel stepLabel) {
        this.stepLabel = stepLabel;
    }

    public void clearSteps() {
        this.steps = 0;
        this.stepLabel.setText(String.format("Step: %d", this.steps));
    }
}
