package entry;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import surface.Surface;

/**
 * Frame. Gets parameters from user.
 * @author Paweł Data
 */
public class EntryFrame extends JFrame implements ActionListener {
    private JTextField heightTextField;
    private JTextField widthTextField;
    private JTextField rabbitsNumberTextField;
    private JTextField delayTextField;
    private JLabel info;
    private JButton button;

    int height;
    int width;
    int rabbitsNumber;
    int delay;

    /**
     * Class constructor.
     */
    public EntryFrame() {
        this.setSize(400, 400);
        this.setLayout(new GridLayout(5, 2));

        this.add(new JLabel("Height (from 3 to 20) :"));
        heightTextField = new JTextField();
        this.add(heightTextField);

        this.add(new JLabel("Width (from 3 to 20) :"));
        widthTextField = new JTextField();
        this.add(widthTextField);

        this.add(new JLabel("Rabbits (max half of field) :"));
        rabbitsNumberTextField = new JTextField();
        this.add(rabbitsNumberTextField);

        this.add(new JLabel("Delay (from 300 to 1000) :"));
        delayTextField = new JTextField();
        this.add(delayTextField);

        button = new JButton("OK");
        button.addActionListener(this);
        this.add(button);

        info = new JLabel();
        this.add(info);

        this.setTitle("Simulator");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Run, when all data is corect.
     * Create Surface
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkData()) {
            this.setVisible(false);
            new Surface(rabbitsNumber, delay, height, width);
        }
    }

    /**
     * Checks correctness data.
     * @return true if data are correct. False otherwise.
     */
    private boolean checkData() {
        this.info.setText("");

        try {
            height = Integer.parseInt(this.heightTextField.getText());
            width = Integer.parseInt(this.widthTextField.getText());
            rabbitsNumber = Integer.parseInt(this.rabbitsNumberTextField.getText());
            delay = Integer.parseInt(this.delayTextField.getText());

            if (height < 3 || height > 20) {
                this.info.setText("Incorrect height");
            } else if (width < 3 || width > 20) {
                this.info.setText("Incorrect width");
            } else if (rabbitsNumber < 0 || rabbitsNumber > height * width / 2) {
                this.info.setText("Incorrect number of rabbits");
            } else if (delay < 300 || delay > 1000) {
                this.info.setText("Incorrect delay");
            }

        } catch (NumberFormatException ex) {
            this.info.setText("Incorrect data");
        } catch (NullPointerException ex) {
            this.info.setText("No data");
        } 

        if (this.info.getText() == "")
            return true;
        return false;
    }
}