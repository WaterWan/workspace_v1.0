//需要客户端的Stub
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IOService extends Remote{
	public boolean writeFile(String file, String userId, String fileName)throws RemoteException;
	
	public String readFile(String userId, String fileName)throws RemoteException;
	
	public String readFileList(String userId)throws RemoteException;
	
	public boolean fileExists(String username, String filename) throws RemoteException;
	
	public String[] getFileNames(String path) throws RemoteException;
	
	public String[] getVersionNames(String path) throws RemoteException;
}
