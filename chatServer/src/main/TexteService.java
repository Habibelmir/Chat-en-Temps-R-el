package main;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TexteService extends UnicastRemoteObject implements TexteInterface {
	
	private static final long serialVersionUID = 1L;

	protected TexteService() throws RemoteException {
		super();
	}

	public String getTexte() {
		try {
			return ChatImplementation.getCorps();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "";
	}
}
