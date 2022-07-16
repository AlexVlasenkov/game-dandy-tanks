import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    // Размеры окна, включая заголовок
    private static final int WINDOW_WIDTH = 576;
    private static final int WINDOW_HEIGHT = 576;
    // Направление перемещения
    private final int UP = 1;
    private final int DOWN = 2;
    private final int LEFT = 3;
    private final int RIGHT = 4;
    // Направление: 1 - Up, 2 - Down, 3 - Left, 4 - Right
    private int direction = UP;
    // Положение окна
    private int tankX = 256;
    private int tankY = 256;
    // Размеры танка
    private final int TANK_WIDTH = 64;
    private final int TANK_HEIGHT = 64;
    // Координаты пули
    private int bulletX = 320;
    private int bulletY = 320;

    // Движение танка
    private void move(int direction) throws Exception {
        this.direction = direction;

        switch (direction) {
            case UP:
                tankY--;
                break;
            case DOWN:
                tankY++;
                break;
            case LEFT:
                tankX--;
                break;
            case RIGHT:
                tankX++;
                break;
        }

        Thread.sleep(30);
        repaint();
    }

    // Выстрел из танка
    private void fire() throws Exception {
        bulletX = tankX + 25;
        bulletY = tankY + 25;

        while (bulletX > 0 && bulletX < WINDOW_WIDTH && bulletY > 0 && bulletY < WINDOW_HEIGHT) {
            switch (direction) {
                case 1:
                    bulletY--;
                    break;
                case 2:
                    bulletY++;
                    break;
                case 3:
                    bulletX--;
                    break;
                case 4:
                    bulletX++;
                    break;
            }
            Thread.sleep(10);
            repaint();
        }

        // По мере удаления пуля исчезает
        bulletX = -100;
    }

    // Запуск игры
    private void runTheGame() throws Exception {
        /*while (tankY != WINDOW_HEIGHT - TANK_HEIGHT - 44) {
            move(DOWN);
        }*/
        fire();
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
        g.fillRect(tankX, tankY, TANK_WIDTH, TANK_HEIGHT);
        // Пушка
        g.setColor(Color.GREEN);
        switch (direction) {
            case UP:
                g.fillRect(tankX + 20, tankY, 24, 34);
                break;
            case DOWN:
                g.fillRect(tankX + 20, tankY + 30, 24, 34);
                break;
            case LEFT:
                g.fillRect(tankX, tankY + 20, 34, 24);
                break;
            case RIGHT:
                g.fillRect(tankX + 30, tankY + 20, 34, 24);
                break;
        }

        g.setColor(Color.ORANGE);
        g.fillRect(bulletX, bulletY, 14, 14);
    }
}
