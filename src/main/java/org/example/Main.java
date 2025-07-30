import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

 public class Main extends JFrame {
    private JLabel timeLabel;
    private LocalTime currentTime;
    private static final int SPEED_MULTIPLIER = 10; // Clock runs 10x faster

    public Main() {
        setTitle("Fast Digital Clock");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        timeLabel = new JLabel("", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Consolas", Font.BOLD, 48));
        timeLabel.setForeground(new Color(0, 128, 255)); // Stylish blue color
        timeLabel.setForeground(Color.WHITE); // Set text color to red

        // Set background color
        getContentPane().setBackground(Color.BLACK);

        // Use BorderLayout and add label to center
        setLayout(new BorderLayout());
        add(timeLabel, BorderLayout.CENTER);

        currentTime = LocalTime.now();
        startClock();
    }

    private void startClock() {
        Timer timer = new Timer(true); // Daemon timer
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Advance time by SPEED_MULTIPLIER seconds every second
                currentTime = currentTime.plusSeconds(SPEED_MULTIPLIER);
                SwingUtilities.invokeLater(() -> updateTimeDisplay());
            }
        }, 0, 1000); // Update every real second
    }

    private void updateTimeDisplay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel.setText(currentTime.format(formatter));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main clock = new Main();
            clock.setVisible(true);
        });
    }
}
