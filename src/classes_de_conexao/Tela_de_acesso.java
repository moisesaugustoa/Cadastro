package classes_de_conexao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_de_acesso extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_de_acesso frame = new Tela_de_acesso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_de_acesso() {
		setResizable(false);
		setTitle("Tela de Acesso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsuario.setBounds(30, 38, 69, 49);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSenha.setBounds(30, 136, 69, 49);
		contentPane.add(lblSenha);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(109, 54, 218, 33);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(109, 144, 79, 36);
		contentPane.add(tfSenha);
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Connection con = conexao.faz_conexao();
					
					String sql = "select *from dados_senhas where usuario=? and senha=?";
					
					PreparedStatement stnt = con.prepareStatement(sql);
					stnt.setString(1, tfUsuario.getText());
					stnt.setString(2, new String(tfSenha.getPassword()));
					
					ResultSet rs = stnt.executeQuery();
					
					if (rs.next()) {
						Tela_cadastro exibir = new Tela_cadastro();
						exibir.setVisible(true);
						
						setVisible(false);
						//JOptionPane.showMessageDialog(null, "Esse usuário existe");
					}
						else {
							JOptionPane.showMessageDialog(null,"Esse usuario não existe");
							
							
						}
					stnt.close();
					con.close();
					}
					
									
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(146, 201, 89, 23);
		contentPane.add(btnNewButton);
	}
}
