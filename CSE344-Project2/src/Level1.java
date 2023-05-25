import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Level1 extends JPanel {
    public static boolean endgame = false;
	
	private JLabel questionLabel;
    private JButton[] options;
    private JLabel timerLabel;
    private JLabel scoreLabel;
    private JButton nextButton;
    private Timer timer;
    private int timeRemaining = 30;
    private int score = 0;
    private int questionIndex = 0;

    private String[] questions = {"Which species does a whale belong to?",
            "Which mammal can fly?",
            "Which is the largest land animal?",
            "Which animal has no continent?",
            "Which animal can cut off its tail?",
            "Which mammal is poisonous?",
            "Which animal makes loud noises?",
            "Which is the fastest running animal?",
            "Which is the longest living animal?",
            "Which is the largest mammal?"};

    private String[][] optionsList = {{"Invertebrate", "Reptile", "Bird", "Mammal"},
            {"Bat", "Dog", "Cat", "Bird"},
            {"Elephant", "Lion", "Rhinoceros", "Giraffe"},
            {"Dolphin", "Shark", "Kangaroo", "Crocodile"},
            {"Turtle", "Koala", "Panda", "Monkey"},
            {"Penguin", "Bat", "Shark", "Snake"},
            {"Owl", "Eagle", "Pigeon", "Fox"},
            {"Leopard", "Horse", "Cheetah", "Wolf"},
            {"Jellyfish", "Sea Otter", "Galapagos Tortoise", "Parrot"},
            {"Kilimanjaro Spider", "Shark", "Tiger", "Blue Whale"}};

    private String[] correctAnswers = {"Mammal", "Bat", "Elephant", "Dolphin", "Turtle", "Bat", "Owl", "Cheetah",
            "Galapagos Tortoise", "Blue Whale"};

    public Level1() {

        // Create the Question Label
        questionLabel = new JLabel(questions[questionIndex]);
        questionLabel.setBounds(200, 50, 400, 50);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(questionLabel);

        // Create Buttons for Options
        options = new JButton[4];
        for (int i = 0; i < options.length; i++) {
            options[i] = new JButton(optionsList[questionIndex][i]);
            options[i].setBounds(200, 150 + i * 60, 400, 50);
            options[i].setBackground(new Color(120, 180, 220));
            options[i].setForeground(Color.WHITE);
            options[i].setFont(new Font("Arial", Font.PLAIN, 18));
            add(options[i]);

            options[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton option = (JButton) e.getSource();
                    answer(option);
                    timer.stop();
                }
            });
        }

        // Create the Score Label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(200, 20, 100, 30);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(scoreLabel);

        // Create the Timer Label
        timerLabel = new JLabel("30");
        timerLabel.setBounds(550, 20, 200, 50);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(timerLabel);

        // Create the Next Question Button
        nextButton = new JButton("Next Question");
        nextButton.setBounds(300, 420, 200, 40);
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.setVisible(false);
        add(nextButton);
        
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                questionIndex++;
                if (questionIndex < questions.length) {
                    nextQuestion();
                } else {
                    showResult();
                    end(true);
                }
            }
        });

        // Create a Timer and update the timer label every 1 second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText(String.valueOf(timeRemaining));
                if (timeRemaining == 0) {
                    timer.stop();
                    timerLabel.setText("Time's Up!");
                    disableOptions();
                    nextButton.setVisible(true);
                }
            }
        });
        timer.start();

        setVisible(true);
    }

    private void answer(JButton option) {
        disableOptions();

        for (int i = 0; i < options.length; i++) {
            if (options[i] == option) {
                if (option.getText().equals(correctAnswers[questionIndex])) {
                    option.setBackground(Color.GREEN);
                    score += 10;
                    scoreLabel.setText("Score: " + score);
                } else {
                    option.setBackground(Color.RED);
                    for (int j = 0; j < options.length; j++) {
                        if (options[j].getText().equals(correctAnswers[questionIndex])) {
                            options[j].setBackground(Color.GREEN);
                            break;
                        }
                    }
                }
            }
        }
        nextButton.setVisible(true);
    }

    private void disableOptions() {
        for (JButton option : options) {
            option.setEnabled(false);
        }
    }

    private void nextQuestion() {
        questionLabel.setText(questions[questionIndex]);
        for (int i = 0; i < options.length; i++) {
            options[i].setText(optionsList[questionIndex][i]);
            options[i].setEnabled(true);
            options[i].setBackground(new Color(120, 180, 220));
        }
        timeRemaining = 30;
        timerLabel.setText(String.valueOf(timeRemaining));
        timer.restart();
        nextButton.setVisible(false);
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Completed!\nTotal Score: " + score, "Result", JOptionPane.INFORMATION_MESSAGE);
        
    }

    private void end(boolean state) {
    	endgame = state;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Level1());
    }
}
