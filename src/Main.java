import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameHanoi app = new GameHanoi();
        app.setVisible(true);
        app.setTitle("Torre de hanoi");
        app.setSize(800, 471);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}