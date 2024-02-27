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

import game.campominado.tabuleiro.TabuleiroAvancado;
import game.campominado.tabuleiro.TabuleiroIniciante;
import game.campominado.tabuleiro.TabuleiroIntermediario;

import javax.swing.JFrame;

public class MultiModeFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton modoClassico;
	JButton modoMaluco;
	JButton tabuleiroIniciante;
    JButton tabuleiroIntermediario;
    JButton tabuleiroDificil;
	JButton voltar;
	
	boolean modoClassicoSelecionado = false;
	
	public MultiModeFrame() {
		
		Border border = BorderFactory.createLineBorder(Color.white,5);
		JLabel label = new JLabel();
		
		modoClassico = new JButton();
		modoClassico.setBounds(65, 0, 350, 50);
		modoClassico.addActionListener(this);
		modoClassico.setText("Modo Classico");
		modoClassico.setFocusable(false);
		modoClassico.setFont(new Font("Arial Black",Font.BOLD,25));
		modoClassico.setBackground(Color.white);
		
		modoMaluco = new JButton();
		modoMaluco.setBounds(65, 50, 350, 50);
		modoMaluco.addActionListener(this);
		modoMaluco.setText("Modo Maluco");
		modoMaluco.setFocusable(false);
		modoMaluco.setFont(new Font("Arial Black",Font.BOLD,25));
		modoMaluco.setBackground(Color.white);
		
		tabuleiroIniciante = new JButton();
        	tabuleiroIniciante.setBounds(65, 100, 350, 50);
        	tabuleiroIniciante.addActionListener(this);
       	 	tabuleiroIniciante.setText("Tabuleiro Iniciante");
        	tabuleiroIniciante.setFocusable(false);
        	tabuleiroIniciante.setFont(new Font("Arial Black", Font.BOLD, 25));
        	tabuleiroIniciante.setBackground(Color.white);
        	tabuleiroIniciante.setEnabled(false); // Desabilita até que o Modo Clássico seja selecionado

        	tabuleiroIntermediario = new JButton();
        	tabuleiroIntermediario.setBounds(65, 150, 350, 50);
        	tabuleiroIntermediario.addActionListener(this);
        	tabuleiroIntermediario.setText("Tabuleiro Intermediário");
        	tabuleiroIntermediario.setFocusable(false);
        	tabuleiroIntermediario.setFont(new Font("Arial Black", Font.BOLD, 25));
        	tabuleiroIntermediario.setBackground(Color.white);
        	tabuleiroIntermediario.setEnabled(false); // Desabilita até que o Modo Clássico seja selecionado

        	tabuleiroDificil = new JButton();
        	tabuleiroDificil.setBounds(65, 200, 350, 50);
        	tabuleiroDificil.addActionListener(this);
        	tabuleiroDificil.setText("Tabuleiro Difícil");
        	tabuleiroDificil.setFocusable(false);
        	tabuleiroDificil.setFont(new Font("Arial Black", Font.BOLD, 25));
        	tabuleiroDificil.setBackground(Color.white);
        	tabuleiroDificil.setEnabled(false); // Desabilita até que o Modo Clássico seja selecionado
		
		voltar = new JButton();
		voltar.setBounds(65, 250, 350, 50);
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
        	this.add(tabuleiroIniciante);
        	this.add(tabuleiroIntermediario);
        	this.add(tabuleiroDificil);
		this.add(voltar);	
	}

	@SuppressWarnings("unused")
	@Override
    	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modoClassico) {
            modoClassicoSelecionado = true;
            modoClassico.setEnabled(false); // Desabilita o botão de Modo Clássico após ser selecionado
            tabuleiroIniciante.setEnabled(true); // Habilita os botões de tabuleiro
            tabuleiroIntermediario.setEnabled(true);
            tabuleiroDificil.setEnabled(true);
            modoMaluco.setEnabled(false);
        } else if (modoClassicoSelecionado) {
            if (e.getSource() == tabuleiroIniciante) {
                dispose();
                TabuleiroModoClassicoMulti multi = new TabuleiroModoClassicoMulti(new TabuleiroIniciante(9,9,20));
            } else if (e.getSource() == tabuleiroIntermediario) {
                dispose();
                TabuleiroModoClassicoMulti multi = new TabuleiroModoClassicoMulti(new TabuleiroIntermediario(16,16,70));
            } else if (e.getSource() == tabuleiroDificil) {
                dispose();
                TabuleiroModoClassicoMulti multi = new TabuleiroModoClassicoMulti(new TabuleiroAvancado(16,30,145));
            }
        }

        if (e.getSource() == voltar) {
            dispose();
            new JogoFrame();
        }
    }

}
