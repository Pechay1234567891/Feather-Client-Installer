import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;

public class calc extends JFrame {
    private JPanel contentPane;
    private JTextField textField;
    private double num1;
    private double num2;
    private String operator;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    calc frame = new calc();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public calc() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 250, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(10, 10, 210, 40);
        contentPane.add(textField);
        textField.setColumns(10);

        String[] buttonLabels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        JButton[] buttons = new JButton[buttonLabels.length];
        int x = 10;
        int y = 60;
        int buttonWidth = 50;
        int buttonHeight = 50;

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    onButtonClick(e);
                }
            });
            buttons[i].setBounds(x, y, buttonWidth, buttonHeight);
            contentPane.add(buttons[i]);

            x += buttonWidth;
            if (x >= 210) {
                x = 10;
                y += buttonHeight;
            }
        }
    }

    private void onButtonClick(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText();
        if ("0123456789.".contains(command)) {
            textField.setText(textField.getText() + command);
        } else if ("+-*/".contains(command)) {
            num1 = Double.parseDouble(textField.getText());
            operator = command;
            textField.setText("");
        } else if ("=".equals(command)) {
            num2 = Double.parseDouble(textField.getText());
            double result = calculate();
            textField.setText(Double.toString(result));
        }
    }

    private double calculate() {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    return Double.NaN; 
                }
                return num1 / num2;
            default:
                return num2;
        }
    }
}
