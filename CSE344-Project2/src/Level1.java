
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level1 extends JPanel {
    private JLabel soruLabel;
    private JButton[] secenekler;
    private JLabel sayacLabel;
    private JLabel puanLabel;
    private JButton sonrakiButton;
    private Timer timer;
    private int sure = 30;
    private int puan = 0;
    private int soruIndex = 0;

  private String[] sorular = {"Which species does a whale belong to?",
        "Which mammal can fly?",
        "Which is the largest land animal?",
        "Which animal has no continent?",
        "Which animal can cut off its tail?",
        "Which mammal is poisonous?",
        "Which animal makes loud noises?",
        "Which is the fastest running animal?",
        "Which is the longest living animal?",
        "Which is the largest mammal?"};

private String[][] seceneklerListesi = {{"Invertebrate", "Reptile", "Bird", "Mammal"},
        {"Bat", "Dog", "Cat", "Bird"},
        {"Elephant", "Lion", "Rhinoceros", "Giraffe"},
        {"Dolphin", "Shark", "Kangaroo", "Crocodile"},
        {"Turtle", "Koala", "Panda", "Monkey"},
        {"Penguin", "Bat", "Shark", "Snake"},
        {"Owl", "Eagle", "Pigeon", "Fox"},
        {"Leopard", "Horse", "Cheetah", "Wolf"},
        {"Jellyfish", "Sea Otter", "Galapagos Tortoise", "Parrot"},
        {"Kilimanjaro Spider", "Shark", "Tiger", "Blue Whale"}};

private String[] dogruCevaplar = {"Mammal", "Bat", "Elephant", "Dolphin", "Turtle", "Bat", "Owl", "Cheetah",
        "Galapagos Tortoise", "Blue Whale"};


    public Level1() {

        // Soru Label'ı oluştur
        soruLabel = new JLabel(sorular[soruIndex]);
        soruLabel.setBounds(200, 50, 400, 50);
        soruLabel.setHorizontalAlignment(SwingConstants.CENTER); // Soru Label'ını ortala
        soruLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(soruLabel);

        // Seçenekler için Button'ları oluştur
        secenekler = new JButton[4];
        for (int i = 0; i < secenekler.length; i++) {
            secenekler[i] = new JButton(seceneklerListesi[soruIndex][i]);
            secenekler[i].setBounds(200, 150 + i * 60, 400, 50);
            secenekler[i].setBackground(new Color(120, 180, 220)); // Butonların rengini ayarla
            secenekler[i].setForeground(Color.WHITE); // Buton metin rengini ayarla
            secenekler[i].setFont(new Font("Arial", Font.PLAIN, 18));
            add(secenekler[i]);

            secenekler[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton secenek = (JButton) e.getSource();
                    cevapla(secenek); // Cevabı kontrol et
                    timer.stop(); // Sayaç durduruldu
                }
            });
        }

        // Puan Label'ı oluştur
        puanLabel = new JLabel("Puan: 0");
        puanLabel.setBounds(200, 20, 100, 30);
        puanLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(puanLabel);

        // Sayac Label'ı oluştur
        sayacLabel = new JLabel("30");
        sayacLabel.setBounds(550, 20, 50, 50);
        sayacLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(sayacLabel);

        // Sonraki soru için Buton oluştur
        sonrakiButton = new JButton("Sonraki Soru");
        sonrakiButton.setBounds(330, 420, 140, 40);
        sonrakiButton.setFont(new Font("Arial", Font.BOLD, 16));
        sonrakiButton.setVisible(false);
        add(sonrakiButton);

        sonrakiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soruIndex++;
                if (soruIndex < sorular.length) {
                    yeniSoru();
                } else {
                    sonucuGoster();
                }
            }
        });

        // Timer oluştur ve her 1 saniyede bir sayacı güncelle
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sure--;
                sayacLabel.setText(String.valueOf(sure));
                if (sure == 0) {
                    timer.stop();
                    sayacLabel.setText("Süre Bitti!");
                    disableSecenekler(); // Zaman dolduğunda seçenek butonlarını devre dışı bırak
                    sonrakiButton.setVisible(true); // Sonraki soru butonunu göster
                }
            }
        });
        timer.start();

        setVisible(true);
    }

    private void cevapla(JButton secenek) {
        disableSecenekler(); // Seçenek butonlarını devre dışı bırak

        for (int i = 0; i < secenekler.length; i++) {
            if (secenekler[i] == secenek) { // Tıklanan butonu kontrol et
                if (secenek.getText().equals(dogruCevaplar[soruIndex])) { // Doğru cevap
                    secenek.setBackground(Color.GREEN); // Yeşil arkaplan
                    puan += 10;
                    puanLabel.setText("Puan: " + puan);
                } else { // Yanlış cevap
                    secenek.setBackground(Color.RED); // Kırmızı arkaplan
                    for (int j = 0; j < secenekler.length; j++) {
                        if (secenekler[j].getText().equals(dogruCevaplar[soruIndex])) {
                            secenekler[j].setBackground(Color.GREEN); // Doğru cevabı yeşil yap
                            break;
                        }
                    }
                }
            }
        }
        sonrakiButton.setVisible(true); // Sonraki soru butonunu göster
    }

    private void disableSecenekler() {
        for (JButton secenek : secenekler) {
            secenek.setEnabled(false);
        }
    }

    private void yeniSoru() {
        soruLabel.setText(sorular[soruIndex]);
        for (int i = 0; i < secenekler.length; i++) {
            secenekler[i].setText(seceneklerListesi[soruIndex][i]);
            secenekler[i].setEnabled(true);
            secenekler[i].setBackground(new Color(120, 180, 220));
        }
        sure = 30;
        sayacLabel.setText(String.valueOf(sure));
        timer.restart();
        sonrakiButton.setVisible(false);
    }

    private void sonucuGoster() {
        JOptionPane.showMessageDialog(this, "Quiz Tamamlandı!\nToplam Puan: " + puan, "Sonuç", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Level1());
    }
}
