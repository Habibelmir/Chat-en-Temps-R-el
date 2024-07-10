package main;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TexteInterface extends Remote{
	public String getTexte() throws RemoteException;

}