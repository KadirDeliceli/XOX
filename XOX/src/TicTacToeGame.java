import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGame extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];  // 9 kare için düğmeler
    private boolean playerXTurn = true;  // İlk sırada X oyuncusu var
    private String currentPlayer;

    public TicTacToeGame() {
        // Pencere Başlığı
        setTitle("Tic-Tac-Toe Game");

        // 3x3 Oyun Alanı (GridLayout)
        setLayout(new GridLayout(3, 3));

        // Butonları oluştur ve panele ekle
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));  // Büyük boy yazı
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(this);  // Olay dinleyicisi ekle
            add(buttons[i]);  // Butonu ekle
        }

        // Pencere ayarları
        setSize(400, 400);  // Pencere boyutu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Ortada açılmasını sağlar
        setVisible(true);  // Pencereyi görünür yapar
    }

    // Buton tıklama olaylarını yönetme
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        // Eğer buton boşsa, X veya O yaz
        if (clickedButton.getText().equals("")) {
            currentPlayer = playerXTurn ? "X" : "O";
            clickedButton.setText(currentPlayer);
            playerXTurn = !playerXTurn;  // Sıra değiştir

            // Oyunu kontrol et, kazanan var mı?
            checkForWin();
        }
    }

    // Oyunu kazananı kontrol etme
    private void checkForWin() {
        // Kazanma kombinasyonları (3x3 tabloda satırlar, sütunlar ve köşegenler)
        int[][] winningPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // Satırlar
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // Sütunlar
                {0, 4, 8}, {2, 4, 6}  // Köşegenler
        };

        // Kazanan olup olmadığını kontrol et
        for (int[] position : winningPositions) {
            if (buttons[position[0]].getText().equals(currentPlayer) &&
                    buttons[position[1]].getText().equals(currentPlayer) &&
                    buttons[position[2]].getText().equals(currentPlayer)) {
                // Kazanan bulundu, mesaj göster ve oyunu sonlandır
                JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
                resetBoard();  // Tahtayı sıfırla
                return;
            }
        }

        // Eğer tüm kareler doluysa ve kazanan yoksa, beraberlik
        boolean allFilled = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                allFilled = false;
                break;
            }
        }

        if (allFilled) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    // Oyun tahtasını sıfırlama (yeni oyun başlat)
    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");  // Tüm butonları temizle
        }
        playerXTurn = true;  // İlk oyuncu yine X
    }
}