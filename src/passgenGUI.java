import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class passgenGUI extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            passgenGUI passgenGUI = new passgenGUI();
            passgenGUI.setVisible(true);
        });
    }

    private passwordgenbackend passwordgenBackend;


    public passgenGUI() {

        //title
        super("ProtectedPass");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 450);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // initialize password generator
        passwordgenBackend = new passwordgenbackend();

        addFeaturesToGUI();

    }


    private void addFeaturesToGUI() {

        JLabel passgenTitle = new JLabel("ProtectedPass");
        passgenTitle.setFont(new Font("Cambria", Font.BOLD, 28));
        passgenTitle.setHorizontalAlignment(SwingConstants.CENTER);
        passgenTitle.setBounds(0, 10, 675, 30);
        add(passgenTitle);


        JTextArea showPass = new JTextArea();
        showPass.setFont(new Font("Cambria", Font.PLAIN, 32));
        //showPass.setBounds(0, 80, 670, 30);
        showPass.setEditable(false);

        // add scroll
        JScrollPane showPassScroll = new JScrollPane(showPass);
        showPassScroll.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        showPassScroll.setBounds(25, 70, 650, 70);
        add(showPassScroll);


        // create password length label
        JLabel passwordLengthLabel = new JLabel("Length: ");
        passwordLengthLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        passwordLengthLabel.setBounds(35, 190, 200, 39);
        add(passwordLengthLabel);

        // create password length input
        JTextArea passwordLengthInputArea = new JTextArea();
        passwordLengthInputArea.setFont(new Font("Dialog", Font.PLAIN, 32));
        passwordLengthInputArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordLengthInputArea.setBounds(110, 190, 150, 39);
        add(passwordLengthInputArea);


        // create toggle buttons
        // uppercase
        JToggleButton uppercaseToggle = new JToggleButton("Uppercase");
        uppercaseToggle.setFont(new Font("Dialog", Font.PLAIN, 12));
        uppercaseToggle.setBounds(75, 250, 100, 56);
        add(uppercaseToggle);

        // lowercase
        JToggleButton lowercaseToggle = new JToggleButton("Lowercase");
        lowercaseToggle.setFont(new Font("Dialog", Font.PLAIN, 12));
        lowercaseToggle.setBounds(200, 250, 100, 56);
        add(lowercaseToggle);

        // numbers
        JToggleButton numbersToggle = new JToggleButton("Numbers");
        numbersToggle.setFont(new Font("Dialog", Font.PLAIN, 12));
        numbersToggle.setBounds(325, 250, 100, 56);
        add(numbersToggle);

        // symbols toggle
        JToggleButton symbolsToggle = new JToggleButton("Symbols");
        symbolsToggle.setFont(new Font("Dialog", Font.PLAIN, 12));
        symbolsToggle.setBounds(450, 250, 100, 56);
        add(symbolsToggle);

        // create generate button
        JButton generateButton = new JButton("Generate");
        generateButton.setFont(new Font("Dialog", Font.PLAIN, 12));
        generateButton.setBounds(225, 325, 150, 42);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lengthText = passwordLengthInputArea.getText().trim();
                    if (lengthText.isEmpty()) {
                        JOptionPane.showMessageDialog(passgenGUI.this, "Please enter a password length.");
                        return;
                    }

                    int passwordLength = Integer.parseInt(lengthText);

                    if (passwordLength < 8) {
                        JOptionPane.showMessageDialog(passgenGUI.this, "Password length must be greater than eight characters.");
                        return;
                    }

                    boolean anyToggleSelected = lowercaseToggle.isSelected() ||
                            uppercaseToggle.isSelected() ||
                            numbersToggle.isSelected() ||
                            symbolsToggle.isSelected();

                    if (!anyToggleSelected) {
                        JOptionPane.showMessageDialog(passgenGUI.this, "Please select at least one character type.");
                        return;
                    }

                    String generatedPassword = passwordgenBackend.generatePass(passwordLength,
                            uppercaseToggle.isSelected(),
                            lowercaseToggle.isSelected(),
                            numbersToggle.isSelected(),
                            symbolsToggle.isSelected());

                    showPass.setText(generatedPassword);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(passgenGUI.this, "Invalid input. Please enter a valid number.");
                }
            }
        });
        add(generateButton);
    }
}
