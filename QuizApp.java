package uas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp {
    private static final String[] QUESTIONS = {
        "Apa warna langit pada hari cerah?",
        "Berapa 2 + 2?",
        "Siapa presiden pertama Indonesia?",
        "Apa ibu kota Prancis?",
        "Ctrl C untuk apa?"
    };

    private static final String[][] OPTIONS = {
        {"Merah", "Biru", "Hijau", "Kuning"},
        {"3", "4", "5", "6"},
        {"Soekarno", "Sukarno", "Jokowi", "Habibie"},
        {"Paris", "London", "Berlin", "Madrid"},
        {"Paste", "Copy", "Delete", "Screenshot"}
    };

    private static final int[] ANSWERS = {1, 1, 0, 0, 1}; // Indeks jawaban yang benar
    private static final int POINTS_PER_QUESTION = 20; // Skor per pertanyaan

    private static int score = 0;
    private static int questionIndex = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Quiz App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel questionLabel = new JLabel();
        panel.add(questionLabel);

        ButtonGroup optionsGroup = new ButtonGroup();
        JRadioButton[] optionButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsGroup.add(optionButtons[i]);
            panel.add(optionButtons[i]);
        }

        JButton nextButton = new JButton("Next");
        panel.add(nextButton);

        frame.add(panel, BorderLayout.CENTER);

        showQuestion(questionLabel, optionButtons);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = getSelectedOptionIndex(optionButtons);
                if (selectedIndex == ANSWERS[questionIndex]) {
                    score += POINTS_PER_QUESTION;
                }
                questionIndex++;
                if (questionIndex < QUESTIONS.length) {
                    showQuestion(questionLabel, optionButtons);
                } else {
                    showResult(frame);
                }
            }
        });

        frame.setVisible(true);
    }

    private static void showQuestion(JLabel questionLabel, JRadioButton[] optionButtons) {
        questionLabel.setText(QUESTIONS[questionIndex]);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(OPTIONS[questionIndex][i]);
        }
    }

    private static int getSelectedOptionIndex(JRadioButton[] optionButtons) {
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                return i;
            }
        }
        return -1;
    }

    private static void showResult(JFrame frame) {
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());

        JLabel resultLabel = new JLabel("Nilai Anda: " + score + "/100", SwingConstants.CENTER);
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Tutup");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        resultPanel.add(closeButton, BorderLayout.SOUTH);

        frame.setContentPane(resultPanel);
        frame.revalidate();
    }
}
