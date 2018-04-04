package Janelas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Banco.CRUDRanking;

public class Tabuada {

	private JFrame frame;
	public JTextField tfResposta;
	private JLabel lblAcertos;
	private JLabel lblErros;
	private JLabel lblValorA;
	private JLabel lblValorB;
	private JLabel lblNewLabel;
	public JButton btnVerificar;
	public JProgressBar progressBar;
	private JTextPane textPaneRanking;
	private JButton btnReset;
	private JLabel lblNewLabel_1;

	static String nome;
    static int id = 0;
	public int acertos=0,erros=0;
	public int errosJogador = 0;
	int AMax=10,BMax=5;
	public int valorA,valorB,respostaCorreta;
	public int resposta;
	int tempo = 300;
	boolean jogando = false;
	int jogoEmAndamento = 0;
	int enter = 0;
	int ativado = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		if(args.length>2)
		{
			nome = args[0];
//			id = Integer.parseInt(args[1]);
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabuada window = new Tabuada();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tabuada() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				tfResposta.requestFocus();
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});
		frame.setBounds(100, 100, 1075, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		lblNome = new JLabel("Nome");
		lblNome.setForeground(Color.WHITE);
		lblNome.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
		lblNome.setBounds(576, 11, 294, 43);
		frame.getContentPane().add(lblNome);
		
		
		while(nome==null || nome.length()<3 || nome.length()>10)
		{
			nome = JOptionPane.showInputDialog("\n  Entre com seu nome: \n");
		}
		
		new CRUDRanking().novoJogador(nome, acertos); //salva nome no banco
		
		ResultSet dados = new CRUDRanking().consultar();
		
		try {
			dados.last();
			id = dados.getInt("idRanking");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		
		frame.setTitle(" ..::  MaxTabuadaGame  ::..");
		lblNome.setText(nome);
		jogando = true;
		jogoEmAndamento = 1;
		

		JLabel lblAcerto = new JLabel("Acertos  :");
		lblAcerto.setForeground(Color.WHITE);
		lblAcerto.setFont(new Font("Swis721 BlkEx BT", Font.BOLD, 13));
		lblAcerto.setBounds(20, 11, 116, 20);
		frame.getContentPane().add(lblAcerto);
		
		JLabel lblErro = new JLabel("Erros     :");
		lblErro.setForeground(Color.WHITE);
		lblErro.setFont(new Font("Swis721 BlkEx BT", Font.BOLD, 13));
		lblErro.setBounds(20, 38, 116, 20);
		frame.getContentPane().add(lblErro);
		
		lblAcertos = new JLabel("0");
		lblAcertos.setForeground(Color.WHITE);
		lblAcertos.setFont(new Font("Swis721 BlkEx BT", Font.BOLD, 13));
		lblAcertos.setBounds(146, 11, 80, 20);
		frame.getContentPane().add(lblAcertos);
		
		lblErros = new JLabel("0");
		lblErros.setForeground(Color.WHITE);
		lblErros.setFont(new Font("Swis721 BlkEx BT", Font.BOLD, 13));
		lblErros.setBounds(146, 38, 80, 20);
		frame.getContentPane().add(lblErros);
		
		btnVerificar = new JButton("VERIFICAR");
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				jogando = false;
				jogoEmAndamento = 0;
				enter = 1;
				
				try {
					resposta = Integer.parseInt(tfResposta.getText());
				}catch (NumberFormatException e) {
					resposta = -1;
				}
				
					if(respostaCorreta == resposta)
					{
						acertos++;
						switch (acertos)
						{
							case 10: tempo = 200; break;
							case 20: tempo = 150; if(BMax<10)BMax++; break;
							case 30: tempo = 100; if(BMax<10)BMax++; break;
							case 40: tempo = 90; if(BMax<10)BMax++; break;
							case 50: tempo = 80; if(BMax<10)BMax++; break;
							case 60: tempo = 70; if(BMax<10)BMax++; break;
							case 70: tempo = 60; break;
							case 80: tempo = 50; break;
							case 90: tempo = 40; break;
							case 100: tempo = 30; break;
							default:
							if(acertos>110) tempo=20; break;
						}
	//					montarTela();
					}
					else
					{
						if(erros<=10) erros++;
	//					montarTela();
					}
					
					montarTela();
					
					if(erros>9 && enter == 1)
					{
						jogando = false;
						jogoEmAndamento = 0;
						
						errosJogador = erros;
						
					//	 Mensagem.main(new String[] {"Game Over","<h1>Parabéns "+nome+" você fez "+acertos+" pontos</h1>"});					
					//	frame.dispose();
					 
						while(ativado == 1);
						if(ativado == 0 )
						{
							ativado = 1;
							atualizaRanking();
							ativado = 0;
						}
						
						System.out.println(" Jogo = "+Integer.toString(jogoEmAndamento));
						
			//			int teste = 1;
						int teste = JOptionPane.showConfirmDialog(
				   				null, "\n     ACERTOS ( "+Integer.toString(acertos+errosJogador)+" ):   "
				   		        +Integer.toString(acertos)+"\n     MÉDIA   (100%):   "
				   				+Integer.toString((acertos*100/(acertos+errosJogador)))+" %"+"\n\n DESEJA REINICIAR O JOGO?               \n\n","                             "
				   				+ " ..::  "+nome+"  ::..",JOptionPane.YES_NO_OPTION);
				
						System.out.println(" Fim = "+Integer.toString(teste));
						
						erros = 0;
				   		if(teste == 0)
				   		{
//					   		Tabuada.main(new String[]{nome,Integer.toString(id)});	
				   			JOptionPane.showMessageDialog(null, "O Jogo está sendo reiniciado...");
					   		Tabuada.main(new String[]{nome});	
					   		frame.dispose();
					   		
				   		}
				   		else
				   		{
					   		frame.dispose();
				   		}
				   		

					}
					enter = 0;
					jogoEmAndamento = 1;
					tfResposta.setText("");
					tfResposta.requestFocus();
			}
		});
		btnVerificar.setFont(new Font("Swis721 BlkEx BT", Font.BOLD, 35));
		btnVerificar.setBounds(88, 344, 543, 114);
		frame.getContentPane().add(btnVerificar);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setBackground(Color.LIGHT_GRAY);
		progressBar.setBorder(new LineBorder(Color.GRAY, 2));
		progressBar.setBounds(18, 88, 1030, 35);
		frame.getContentPane().add(progressBar);
		
		JLabel lblValorAAA = new JLabel("Valor A");
		lblValorAAA.setForeground(Color.DARK_GRAY);
		lblValorAAA.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorAAA.setFont(new Font("Verdana", Font.BOLD, 30));
		lblValorAAA.setBounds(15, 155, 175, 63);
		frame.getContentPane().add(lblValorAAA);
		
		JLabel lblValorBBB = new JLabel("Valor B");
		lblValorBBB.setForeground(Color.DARK_GRAY);
		lblValorBBB.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorBBB.setFont(new Font("Verdana", Font.BOLD, 30));
		lblValorBBB.setBounds(225, 155, 175, 63);
		frame.getContentPane().add(lblValorBBB);
		
		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setForeground(Color.DARK_GRAY);
		lblResultado.setFont(new Font("Verdana", Font.BOLD, 30));
		lblResultado.setBounds(467, 155, 236, 63);
		frame.getContentPane().add(lblResultado);
		
		lblValorA = new JLabel("000");
		lblValorA.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorA.setFont(new Font("Arial Black", Font.BOLD, 40));
		lblValorA.setBounds(35, 235, 135, 60);
		frame.getContentPane().add(lblValorA);
		
		lblValorB = new JLabel("000");
		lblValorB.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorB.setFont(new Font("Arial Black", Font.BOLD, 40));
		lblValorB.setBounds(245, 235, 135, 60);
		frame.getContentPane().add(lblValorB);
		
		lblNewLabel = new JLabel("X");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 60));
		lblNewLabel.setBounds(180, 234, 55, 63);
		frame.getContentPane().add(lblNewLabel);
		
		textPaneRanking = new JTextPane();
		textPaneRanking.setBackground(Color.BLACK);
		textPaneRanking.setForeground(Color.WHITE);
		textPaneRanking.setFont(new Font("Courier New", Font.BOLD, 16));
		textPaneRanking.setEditable(false);
		textPaneRanking.setBounds(719, 177, 340, 293);
		frame.getContentPane().add(textPaneRanking);
		
		JLabel lblRanking = new JLabel("RANKING");
		lblRanking.setFont(new Font("Swis721 BlkEx BT", Font.BOLD | Font.ITALIC, 25));
		lblRanking.setBounds(719, 133, 187, 44);
		frame.getContentPane().add(lblRanking);
		
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Swis721 BlkEx BT", Font.PLAIN, 12));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(new CRUDRanking().zeraRanking(JOptionPane.showInputDialog("Senha"))){
					JOptionPane.showMessageDialog(null, "Senha invalida");
				}else {
					JOptionPane.showMessageDialog(null, "O ranking está sendo reiniciado...");
				}

				while(ativado == 1);
				if(ativado == 0 )
				{
					ativado = 1;
					atualizaRanking();
					ativado = 0;
				}
				
			}
		});
		btnReset.setBounds(974, 147, 85, 21);
		frame.getContentPane().add(btnReset);
		
		tfResposta = new JTextField();
		tfResposta.setHorizontalAlignment(SwingConstants.CENTER);
		tfResposta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {	
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					if(jogando==true) {
						jogando = false;
						if(enter == 0)
							btnVerificar.doClick();
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				 if (e.getKeyChar() == e.VK_BACK_SPACE || (e.getKeyChar() == e.VK_0)|| (e.getKeyChar() == e.VK_1)|| (e.getKeyChar() == e.VK_2) 
		            || (e.getKeyChar() == e.VK_3)|| (e.getKeyChar() == e.VK_4)|| (e.getKeyChar() == e.VK_5)|| (e.getKeyChar() == e.VK_6)|| (e.getKeyChar() == e.VK_7)
		            || (e.getKeyChar() == e.VK_8)|| (e.getKeyChar() == e.VK_9)||(e.getKeyChar() == e.VK_ENTER))
		         {     
					 //pega o valor
		         }
				 else
		         {
		            e.consume();
		         }
			}
		});
		tfResposta.setFont(new Font("Arial Black", Font.BOLD, 40));
		tfResposta.setBounds(510, 231, 151, 69);
		frame.getContentPane().add(tfResposta);
		tfResposta.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Equipe SESI SENAI - S\u00E3o Gon\u00E7alo do Sapuca\u00ED - MG");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Swis721 BlkEx BT", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 485, 706, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(0, 0, 128));
		lblNewLabel_2.setBounds(0, 481, 1069, 31);
		frame.getContentPane().add(lblNewLabel_2);
		
		label_1 = new JLabel("=");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(220, 20, 60));
		label_1.setFont(new Font("Segoe UI Black", Font.BOLD, 70));
		label_1.setBounds(415, 225, 69, 69);
		frame.getContentPane().add(label_1);
		
		lblJogador = new JLabel("Jogador :");
		lblJogador.setForeground(Color.LIGHT_GRAY);
		lblJogador.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 30));
		lblJogador.setBounds(370, 11, 187, 43);
		frame.getContentPane().add(lblJogador);
	
		
		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setBackground(new Color(0, 0, 128));
		label.setBounds(0, 0, 1069, 65);
		frame.getContentPane().add(label);
		
		new Thread(progress).start();
		new Thread(ranking).start();
		montarTela();
		
	}

	Runnable progress = new Runnable() {
	@Override
		public void run() {		
			while(jogoEmAndamento == 1)
			{
				progressBar.setValue(100);
					while(jogando == true && jogoEmAndamento == 1) {
						try {
							Thread.sleep(tempo);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						progressBar.setValue(progressBar.getValue()-1);
						if(progressBar.getValue()<=1) {
							break;
						}
					}
				jogando = false;
				if(enter == 0)
					btnVerificar.doClick();
			}	
		}
	};
	
	Runnable ranking = new Runnable() {
		@Override
			public void run()
			{	
				while(true) // (jogoEmAndamento == 1) 
				{	
					while(ativado == 1);
					if(ativado == 0 )
					{
						ativado = 1;
						atualizaRanking();
						ativado = 0;
					}
					
					try{
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
	};
	private JLabel label_1;
	private JLabel lblJogador;
	private JLabel lblNome;
	
	public void atualizaRanking()
	{

			new CRUDRanking().atualizaPontos(id, acertos);
			ResultSet ranking = new CRUDRanking().SelecionaRanking();	
			textPaneRanking.setText(null);
			try {
				int var = 0;
				if(ranking.last()) {
					
					var = Integer.parseInt(ranking.getString("pontos"));
					if(var<10)
					{
						textPaneRanking.setText("   "+ranking.getString("pontos")+" Pts - Jogador: "+ranking.getString("nome"));
					}
					else
					{
						if(var<100)
						{
							textPaneRanking.setText("  "+ranking.getString("pontos")+" Pts - Jogador: "+ranking.getString("nome"));
						} 
						else
						{
							textPaneRanking.setText(" "+ranking.getString("pontos")+" Pts - Jogador: "+ranking.getString("nome"));
						}
					}
										
				}
				
				for(int x=0;x<14;x++) {
					if(ranking.previous()) {
						
						var = Integer.parseInt(ranking.getString("pontos"));
						if(var<10)
						{
							textPaneRanking.setText(textPaneRanking.getText()+"\r   "+ranking.getString("pontos")+" Pts - Jogador: "+ranking.getString("nome"));
						}
						else
						{
							if(var<100)
							{
								textPaneRanking.setText(textPaneRanking.getText()+"\r  "+ranking.getString("pontos")+" Pts - Jogador: "+ranking.getString("nome"));
							}
							else
							{
								textPaneRanking.setText(textPaneRanking.getText()+"\r "+ranking.getString("pontos")+" Pts - Jogador: "+ranking.getString("nome"));
							}
						}
						
					}else {
						break;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void montarTela() {
		Random gerador = new Random();
		lblAcertos.setText(String.valueOf(acertos));
		lblErros.setText(String.valueOf(erros));
		if(acertos>50) {
			valorA = gerador.nextInt(AMax-2)+2;
			lblValorA.setText(String.valueOf(valorA));
			valorB = gerador.nextInt(BMax-2)+2;
			lblValorB.setText(String.valueOf(valorB));
			if(acertos>60) {
				valorA = gerador.nextInt(AMax-3)+3;
				lblValorA.setText(String.valueOf(valorA));
				valorB = gerador.nextInt(BMax-3)+3;
				lblValorB.setText(String.valueOf(valorB));
			}
		}else {
			valorA = gerador.nextInt(AMax);
			lblValorA.setText(String.valueOf(valorA));
			valorB = gerador.nextInt(BMax);
			lblValorB.setText(String.valueOf(valorB));
			if(acertos>20) {
				valorA = gerador.nextInt(AMax-1)+1;
				lblValorA.setText(String.valueOf(valorA));
				valorB = gerador.nextInt(BMax-1)+1;
				lblValorB.setText(String.valueOf(valorB));
			}
		}
		respostaCorreta = valorA * valorB;
		progressBar.setValue(100);
		jogando = true;
		tfResposta.requestFocus();
	}
}



