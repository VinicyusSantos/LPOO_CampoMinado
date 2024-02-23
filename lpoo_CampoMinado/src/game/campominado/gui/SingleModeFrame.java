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

public class SingleModeFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton modoClassico;
	JButton modoMaluco;
	JButton voltar;
	
	public SingleModeFrame() {
		
		Border border = BorderFactory.createLineBorder(Color.white,5);
		JLabel label = new JLabel();
		
		modoClassico = new JButton();
		modoClassico.setBounds(65, 50, 350, 100);
		modoClassico.addActionListener(this);
		modoClassico.setText("Modo Classico");
		modoClassico.setFocusable(false);
		modoClassico.setFont(new Font("Arial Black",Font.BOLD,25));
		modoClassico.setBackground(Color.white);
		
		modoMaluco = new JButton();
		modoMaluco.setBounds(65, 150, 350, 100);
		modoMaluco.addActionListener(this);
		modoMaluco.setText("Modo Maluco");
		modoMaluco.setFocusable(false);
		modoMaluco.setFont(new Font("Arial Black",Font.BOLD,25));
		modoMaluco.setBackground(Color.white);
		
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
		this.add(modoClassico);
		this.add(modoMaluco);
		this.add(voltar);	
	}
	
	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==modoClassico) {
			dispose();
			ModoClassicoSingle single = new ModoClassicoSingle();
		}
		if(e.getSource()==modoMaluco) {
			dispose();
			ModoMalucoSingle multi = new ModoMalucoSingle();
		}
		if(e.getSource()==voltar){
			dispose();
            new JogoFrame();
		}
	}

}
