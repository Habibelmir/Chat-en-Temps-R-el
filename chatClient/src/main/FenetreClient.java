package main;

import java.awt.EventQueue;
import java.rmi.Naming;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class FenetreClient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("Nom client");
	private JTextField inputNomClient;
	private JTextField inputMessage;
	private JTextArea messagesArea;

	static Timer t;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FenetreClient frame = new FenetreClient();
				frame.setVisible(true);
				t = new Timer(40, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
							try {
								frame.messagesArea.setText(stub2.getTexte());
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
						t.restart();
					}
				});
				t.start();
			}
		});
	}
	ChatInterface stub;
	static TexteInterface stub2;
	
	public FenetreClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setBounds(10, 210, 65, 25);
		contentPane.add(lblNewLabel);
		
		inputNomClient = new JTextField();
		inputNomClient.setBounds(85, 212, 240, 20);
		contentPane.add(inputNomClient);
		inputNomClient.setColumns(10);
		
		inputMessage = new JTextField();
		inputMessage.setBounds(85, 246, 339, 88);
		contentPane.add(inputMessage);
		inputMessage.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Message");
		lblNewLabel_1.setBounds(10, 246, 65, 25);
		contentPane.add(lblNewLabel_1);
		
		JButton boutonEnvoyer = new JButton("Envoyer");
		boutonEnvoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					stub.ajouterMessage(inputNomClient.getText(), inputMessage.getText());
					inputMessage.setText("");
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		boutonEnvoyer.setBounds(335, 211, 89, 23);
		contentPane.add(boutonEnvoyer);
		messagesArea = new JTextArea();
		messagesArea.setText(getName());
		messagesArea.setBounds(10, 11, 414, 188);
		contentPane.add(messagesArea);
		
		try {
			stub = (ChatInterface)Naming.lookup("rmi://localhost:1099/objet");
			stub2 = (TexteInterface)Naming.lookup("rmi://localhost:1099/texte");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
