package main;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class FenetreServeur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ChatImplementation objet;
	private static TexteService texteService;

	public void demarrerServeur() throws RemoteException, MalformedURLException, AlreadyBoundException {
		try {
			LocateRegistry.createRegistry(1099);
			LocateRegistry.getRegistry(1099);
			objet = new ChatImplementation();
			texteService = new TexteService();
			Naming.rebind("rmi://localhost:1099/objet", objet);
			Naming.rebind("rmi://localhost:1099/texte", texteService);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Erreur demarrage serveur !");
			e.printStackTrace();
		}
	}
	static Timer t = null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreServeur frame = new FenetreServeur();
					frame.setVisible(true);
					t = new Timer(40, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
								try {
									frame.messagesArea.setText(ChatImplementation.getCorps());
								} catch (RemoteException e1) {
									e1.printStackTrace();
								}
							t.restart();
						}
					});
					t.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JTextArea messagesArea;
	public FenetreServeur() throws RemoteException, MalformedURLException, AlreadyBoundException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		messagesArea = new JTextArea();
		messagesArea.setRows(10);
		messagesArea.setColumns(30);
		contentPane.add(messagesArea);
		
		demarrerServeur();
	}

}
