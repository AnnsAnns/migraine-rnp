import components.Tui;

public class Main {
    public static void main(String[] args) {
        // Start TUI as a new thread
        new Thread(new Tui()).start();
    }
}