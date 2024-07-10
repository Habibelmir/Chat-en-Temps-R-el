package main;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ChatInterface extends Remote {
	public void ajouterMessage(String nomUtilisateur, String messageUtilisateur) throws RemoteException;
	
}
