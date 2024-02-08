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
	
	JButton botaoModoClassico;
	JButton botaoModoMaluco;
	
	public CampoMinadoGUI() {
		
		Border border = BorderFactory.createLineBorder(Color.white,5);
		JLabel label = new JLabel();
		
		botaoModoClassico = new JButton();
		botaoModoClassico.setBounds(65, 150, 350, 100);
		botaoModoClassico.addActionListener(this);
		botaoModoClassico.setText("Modo Classico");
		botaoModoClassico.setFocusable(false);
		botaoModoClassico.setFont(new Font("Arial Black",Font.BOLD,25));
		botaoModoClassico.setBackground(Color.white);
		
		botaoModoMaluco = new JButton();
		botaoModoMaluco.setBounds(65, 250, 350, 100);
		botaoModoMaluco.addActionListener(e -> System.out.println("clicou"));
		botaoModoMaluco.setText("Modo Maluco");
		botaoModoMaluco.setFocusable(false);
		botaoModoMaluco.setFont(new Font("Arial Black",Font.BOLD,25));
		botaoModoMaluco.setBackground(Color.white);

		
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
		this.add(botaoModoClassico);
		this.add(botaoModoMaluco);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Clicou");
		
	}
}
