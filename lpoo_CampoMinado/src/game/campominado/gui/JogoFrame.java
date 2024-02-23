package game.campominado.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.JFrame;

public class JogoFrame extends JFrame implements ActionListener {
	
	JButton singlePlayer;
	JButton multiPlayer;
	JButton voltar;
	
	public JogoFrame() {
		
		Border border = BorderFactory.createLineBorder(Color.white,5);
		JLabel label = new JLabel();
		
		singlePlayer = new JButton();
		singlePlayer.setBounds(65, 50, 350, 100);
		singlePlayer.addActionListener(this);
		singlePlayer.setText("Um jogador");
		singlePlayer.setFocusable(false);
		singlePlayer.setFont(new Font("Arial Black",Font.BOLD,25));
		singlePlayer.setBackground(Color.white);
		
		multiPlayer = new JButton();
		multiPlayer.setBounds(65, 150, 350, 100);
		multiPlayer.addActionListener(this);
		multiPlayer.setText("Dois Jogadores");
		multiPlayer.setFocusable(false);
		multiPlayer.setFont(new Font("Arial Black",Font.BOLD,25));
		multiPlayer.setBackground(Color.white);
		
		voltar = new JButton();
		voltar.setBounds(65, 250, 350, 100);
		voltar.addActionListener(this);
		voltar.setText("Voltar");
		voltar.setFocusable(false);
		voltar.setFont(new Font("Arial Black",Font.BOLD,25));
		voltar.setBackground(Color.white);

		
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
		this.add(singlePlayer);
		this.add(multiPlayer);
		this.add(voltar);	
	}
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==singlePlayer) {
			dispose();
			SingleModeFrame single = new SingleModeFrame();
		}
		if(e.getSource()==multiPlayer) {
			dispose();
			MultiModeFrame multi = new MultiModeFrame();
		}
		if(e.getSource()==voltar){
			dispose();
            new CampoMinadoGUI();
		}
	}

}
