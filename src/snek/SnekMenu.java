package snek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author arthu
 */
public class SnekMenu extends JPanel {

    private final JButton stepButton;
    private final JButton playPauseButton;
    private final JButton restartButton;

    public SnekMenu(GraphicPanel panel) {

        super();

        SnekKeyListener keyListener = new SnekKeyListener(panel);
        playPauseButton = new JButton("Play");
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click play");
                boolean isPlaying = panel.playPause();
                if (isPlaying) {
                    playPauseButton.setText("Pause");
                } else {
                    playPauseButton.setText("Play");
                }
            }
        });
        playPauseButton.addKeyListener(keyListener);
        this.add(playPauseButton);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click restart");
                panel.restart();
            }
        });
        restartButton.addKeyListener(keyListener);
        this.add(restartButton);

        stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("");
                System.out.println("------------------------------------");
                System.out.println("Click step");
                panel.step();
            }
        });
        stepButton.addKeyListener(keyListener);
        this.add(stepButton);
    }

}
