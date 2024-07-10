package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatImplementation extends UnicastRemoteObject implements ChatInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String corps = "";
	public ChatImplementation() throws RemoteException{
		corps = "Debut de la messagerie "+new java.util.Date()+"\n";
	}
	public void ajouterMessage(String nomUtilisateur, String messageUtilisateur) {
		corps += nomUtilisateur+" : "+messageUtilisateur+"\n";
	}
	public String getCorps() {
		return corps;
	}

}

