package game.campominado.jogador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import game.campominado.gui.CampoMinadoGUI;

public class VisualizarInformacoes extends JFrame {
    private static final long serialVersionUID = 1L;

    public VisualizarInformacoes() {
        setTitle("Informações dos Jogadores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Informações dos Jogadores");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        try (BufferedReader reader = new BufferedReader(new FileReader("src/game/campominado/jogador/info_jogador.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String nome = parts[0];
                int pontuacao = Integer.parseInt(parts[1]);
                textArea.append("Nome: " + nome + ", Pontuação: " + pontuacao + "\n");
            }
        } catch (IOException e) {
            textArea.setText("Erro ao carregar informações dos jogadores.");
            e.printStackTrace();
        }

        add(panel, BorderLayout.CENTER);

        // Botão para voltar ao menu principal
        JButton backButton = new JButton("Voltar");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CampoMinadoGUI();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    
}
