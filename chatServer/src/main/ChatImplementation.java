package main;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ChatImplementation extends UnicastRemoteObject implements ChatInterface {
	private static final long serialVersionUID = 1L;
	private static String corps = "";
	public ChatImplementation() throws RemoteException{
		DateService dateService=new DateService(); 
		corps = "Debut de la messagerie "+dateService.getDate()+"\n";
	}
	public void ajouterMessage (String nomUtilisateur, String messageUtilisateur) throws RemoteException {
		DateService dateService=new DateService(); 
		corps += dateService.getDate() +" --- "+nomUtilisateur+" : "+messageUtilisateur+"\n";
	}
	public static  String getCorps() throws RemoteException {
		return corps;
	}

}
