import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    // Размеры окна, включая заголовок
    private static final int WINDOW_WIDTH = 576;
    private static final int WINDOW_HEIGHT = 606;
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
    private final int OBJECT_WIDTH = 64;
    private final int OBJECT_HEIGHT = 64;
    // Координаты пули
    private int bulletX = -100;
    private int bulletY = -100;
    // Объекты карты
    private final String BRICK = "BRICK";
    private final String EARTH = "EARTH";
    private final String WATER = "WATER";
    private final String GREEN = "GREEN";
    // Массив объектов карты; поле карты представляет собой матрицу 56x9
    private String[][] objects = {
            {EARTH, BRICK, BRICK, EARTH, BRICK, EARTH, GREEN, BRICK, BRICK},
            {BRICK, EARTH, EARTH, EARTH, EARTH, EARTH, EARTH, EARTH, WATER},
            {BRICK, EARTH, EARTH, BRICK, EARTH, EARTH, BRICK, EARTH, WATER},
            {EARTH, EARTH, EARTH, EARTH, EARTH, EARTH, EARTH, BRICK, BRICK},
            {BRICK, BRICK, EARTH, GREEN, EARTH, EARTH, EARTH, EARTH, BRICK},
            {WATER, EARTH, EARTH, EARTH, EARTH, EARTH, BRICK, EARTH, BRICK},
            {BRICK, EARTH, EARTH, BRICK, EARTH, EARTH, BRICK, EARTH, GREEN},
            {EARTH, EARTH, EARTH, EARTH, EARTH, EARTH, EARTH, EARTH, BRICK},
            {BRICK, GREEN, BRICK, BRICK, EARTH, BRICK, WATER, BRICK, WATER}
    };

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

        while (true) {
            fire();
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
        // Рисуем объекты карты
        for (int x = 0; x < objects.length; x++) {
            for (int y = 0; y < objects.length; y++) {
                switch (objects[y][x]) {
                    case BRICK:
                        g.setColor(new Color(246, 84, 20));
                        break;
                    case EARTH:
                        g.setColor(new Color(244, 233, 246));
                        break;
                    case WATER:
                        g.setColor(new Color(021, 172, 246));
                        break;
                    case GREEN:
                        g.setColor(new Color(17, 82, 21, 255));
                        break;
                }
                g.fillRect(x * OBJECT_WIDTH, y * OBJECT_WIDTH, OBJECT_WIDTH, OBJECT_HEIGHT);
            }
        }

        // Рисуем танк
        g.setColor(Color.DARK_GRAY);
        g.fillRect(tankX, tankY, OBJECT_WIDTH, OBJECT_HEIGHT);
        // Рисуем пушку
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

        // Рисуем пулю
        g.setColor(Color.ORANGE);
        g.fillRect(bulletX, bulletY, 14, 14);
    }
}
