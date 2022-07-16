import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    // Размеры окна, включая заголовок
    private static final int WINDOW_WIDTH = 576;
    private static final int WINDOW_HEIGHT = 576;
    // Направление: 1 - Up, 2 - Down, 3 - Left, 4 - Right
    private int direction;
    // Положение окна
    private int x = 256;
    private int y = 256;
    // Размеры танка
    private final int TANK_WIDTH = 64;
    private final int TANK_HEIGHT = 64;
    // Направление перемещения
    private final int UP = 1;
    private final int DOWN = 2;
    private final int LEFT = 3;
    private final int RIGHT = 4;
    // Пуля
    private int bulletX = 320;
    private int bulletY = 320;

    // Движение танка
    private void move(int direction) throws Exception {
        this.direction = direction;

        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }

        Thread.sleep(30);
        repaint();
    }

    // Запуск игры
    private void runTheGame() throws Exception {
        while (y != WINDOW_HEIGHT - TANK_HEIGHT - 44) {
            move(DOWN);
        }
    }

    public static void main(String[] args) throws Exception {
        GameField gameField = new GameField();
        gameField.runTheGame();
    }

    GameField() {
        JFrame frame = new JFrame("Dandy tanks");
        frame.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.getContentPane().add(this);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Рисование на игровом поле
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Танк
        g.setColor(Color.RED);
        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
        // Пушка
        g.setColor(Color.GREEN);
        switch (direction) {
            case UP:
                g.fillRect(x + 20, y, 24, 34);
                break;
            case DOWN:
                g.fillRect(x + 20, y + 30, 24, 34);
                break;
            case LEFT:
                g.fillRect(x, y + 20, 34, 24);
                break;
            case RIGHT:
                g.fillRect(x + 30, y + 20, 34, 24);
                break;
        }

        g.setColor(Color.ORANGE);
        g.fillRect(bulletX, bulletY, 14, 14);
    }
}
