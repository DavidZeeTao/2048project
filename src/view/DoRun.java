package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class DoRun extends JPanel {
    private static final long serialVersionUID = 1L;

    private final int DELAY = 50;// 转动快慢设置
    //	private final static Long time = (long) 5000;	//窗体关闭事件
    private static Timer timer;	//动画计时器
    private int x = 0;
    /**
     * 调用
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("正转");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //本类为Panel
        frame.add(new DoRun());
        frame.setSize(300, 300);
        frame.setLocation(400, 400);
        frame.setVisible(true);
        //窗体定时关闭
		/*try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
		// 停止 Timer，使它停止向其侦听器发送动作事件。
		timer.stop();
		frame.setVisible(false);
		frame.dispose();*/

    }

    /**
     * 面板构造函数，初始化面板。包括Timer 的场景。
     */
    public DoRun() {
        timer = new Timer(DELAY, new ReboundListener());
        timer.start();
    }

    /**
     * 动画效果：不断的更新图像的位置，以达到动画的效果。
     */
    private class ReboundListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (x < 360) {
                //控制每个DELAY周期旋转的角度，+ 为逆时针  - 为顺时针
                x = x - 5;
            } else {
                x = 0;
            }
            repaint();
        }
    }

    /**
     * 绘出图像在面板中的位置
     */
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        drawArc(page);
    }

    /**
     * 画图形
     */
    private void drawArc(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        //抗锯齿
        //JDK文档：http://tool.oschina.net/uploads/apidocs/jdk-zh/java/awt/RenderingHints.html
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        //设置画笔颜色
        g2d.setColor(Color.BLACK);
        g2d.drawArc(width / 2 - 110, height / 2 - 110, 10 + 200, 10 + 200, 0, 360);
        g2d.setColor(Color.RED);
        g2d.fillArc(width / 2 - 110, height / 2 - 110, 10 + 200, 10 + 200, x, 240);
        g2d.setColor(Color.BLACK);
        g2d.fillArc(width / 2 - 90, height / 2 - 90, 10 + 160, 10 + 160, 0, 360);
        g2d.dispose();
    }
}

