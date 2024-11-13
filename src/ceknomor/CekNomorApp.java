package ceknomor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class CekNomorApp extends JFrame {
    private JTextField inputField;
    private JButton cekButton;
    private JLabel resultLabel;

    public CekNomorApp() {
        // Pengaturan JFrame
        setTitle("Cek Nomor Genap/Ganjil dan Bilangan Prima");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Membuat panel untuk menampung komponen
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Menambahkan komponen
        JLabel inputLabel = new JLabel("Masukkan Angka:");
        inputField = new JTextField(10);
        cekButton = new JButton("Cek");
        resultLabel = new JLabel("");

        panel.add(inputLabel);
        panel.add(inputField);
        panel.add(cekButton);
        panel.add(resultLabel);

        // Menambahkan panel ke JFrame
        add(panel);

        // ActionListener untuk tombol cek
        cekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cekNomor();
            }
        });

        // KeyAdapter untuk membatasi input hanya angka
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Mengabaikan input yang bukan angka
                }
            }
        });

        // FocusListener untuk membersihkan JTextField saat mendapatkan fokus
        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                inputField.setText("");
                resultLabel.setText(""); // Membersihkan label hasil
            }
        });
    }

    private void cekNomor() {
        String inputText = inputField.getText();
        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Silakan masukkan angka!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int number = Integer.parseInt(inputText);
            String result = "Angka " + number + " adalah ";

            // Cek genap atau ganjil
            if (number % 2 == 0) {
                result += "Genap.";
            } else {
                result += "Ganjil.";
            }

            // Cek bilangan prima
            if (isPrime(number)) {
                result += " Dan juga merupakan bilangan prima.";
            } else {
                result += " Dan bukan bilangan prima.";
            }

            resultLabel.setText(result);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid! Harap masukkan angka yang benar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CekNomorApp app = new CekNomorApp();
            app.setVisible(true);
        });
    }
}