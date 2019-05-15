package entry;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.*;

public class EntryFrame extends JFrame implements ActionListener {
    private Simulator Simulator;
    private JTextField height;
    private JTextField width;
    private JTextField rabbitsNumber;
    private JTextField delay;
    private JLabel info;
    private JButton button;

    public EntryFrame(Simulator Simulator) {
        this.Simulator = Simulator;

        this.setSize(400, 400);
        this.setLayout(new GridLayout(5, 2));

        this.add(new JLabel("Height : "));
        height = new JTextField();
        this.add(height);

        this.add(new JLabel("Width : "));
        width = new JTextField();
        this.add(width);

        this.add(new JLabel("Rabbits : "));
        rabbitsNumber = new JTextField();
        this.add(rabbitsNumber);

        this.add(new JLabel("Delay : "));
        delay = new JTextField();
        this.add(delay);

        button = new JButton("OK");
        button.addActionListener(this);
        this.add(button);

        info = new JLabel();
        this.add(info);

        this.setTitle("Simulator");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkData()) {
            System.out.println(":D");
        }
    }

    private boolean checkData() {
        int height;
        int width;
        int rabbitsNumber;
        int delay;

        this.info.setText("");

        try {
            height = Integer.parseInt(this.height.getText());
            width = Integer.parseInt(this.width.getText());
            rabbitsNumber = Integer.parseInt(this.rabbitsNumber.getText());
            delay = Integer.parseInt(this.delay.getText());

            if (height < 0 || height > 30) {
                this.info.setText("Incorrect height");
            } else if (width < 0 || width > 30) {
                this.info.setText("Incorrect width");
            } else if (rabbitsNumber < 0 || rabbitsNumber > height * width / 2) {
                this.info.setText("Incorrect number of rabbits");
            } else if (delay < 0 || delay > 100) {
                this.info.setText("Incorrect delay");
            }

            Simulator.setHeight(height);
            Simulator.setWidth(width);
            Simulator.setRabbitsNumber(rabbitsNumber);
            Simulator.setDelay(delay);

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