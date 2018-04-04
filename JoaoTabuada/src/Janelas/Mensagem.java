package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mensagem {

	private JFrame frame;
	
	static String titulo;
	static String msg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		titulo = args[0];
		msg = args[1];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mensagem window = new Mensagem();
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
	public Mensagem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				Tabuada.main(null);
			}
		});
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle(titulo);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(10, 10, 416, 116);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setText("<html>"+msg+"</html>");
		
		JButton btnNewButton = new JButton("FECHAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(10, 130, 416, 21);
		frame.getContentPane().add(btnNewButton);
		
	}
}
