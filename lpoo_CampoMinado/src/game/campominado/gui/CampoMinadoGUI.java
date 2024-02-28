package game.campominado.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

import game.campominado.main.Main;

public class CampoMinadoGUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton iniciarJogo;
	JButton pontuacao;
	
	public CampoMinadoGUI() {
		
		Border border = BorderFactory.createLineBorder(Color.white,5);
		JLabel label = new JLabel();
		
		iniciarJogo = new JButton();
		iniciarJogo.setBounds(65, 150, 350, 100);
		iniciarJogo.addActionListener(this);
		iniciarJogo.setText("iniciar");
		iniciarJogo.setFocusable(false);
		iniciarJogo.setFont(new Font("Arial Black",Font.BOLD,25));
		iniciarJogo.setBackground(Color.white);
		
		pontuacao = new JButton();
		pontuacao.setBounds(65, 250, 350, 100);
		pontuacao.addActionListener(this);
		pontuacao.setText("Pontuação");
		pontuacao.setFocusable(false);
		pontuacao.setFont(new Font("Arial Black",Font.BOLD,25));
		pontuacao.setBackground(Color.white);

		
		label.setBorder(border);
		label.setOpaque(true);
		this.setTitle("CAMPO MINADO");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.black);
		this.add(label);
		this.add(iniciarJogo);
		this.add(pontuacao);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==iniciarJogo) {
			dispose();
            new JogoFrame();
		}else if(e.getSource()==pontuacao) {
			dispose();
			new VisualizarInformacoes();
		}
	}
}
