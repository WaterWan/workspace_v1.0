package serviceImpl;

import java.io.File;
import java.rmi.RemoteException;

import service.UserService;
import stringDeal.StringDeal;

public class UserServiceImpl implements UserService{

	@Override
	public boolean login(String username, String password) throws RemoteException {
		File file = new File("user/" + username);
		if (!file.isDirectory()) {
			return false;
		}
		File passwordFile = new File("user/" + username + "/" + password);
		if (!(passwordFile.exists() && !passwordFile.isDirectory())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

	@Override
	public String register(String username, String password) throws RemoteException {
		if (!StringDeal.checkUserName(username)) {
			return "用户名不合法";
		}
		if (!StringDeal.checkPassword(password)) {
			return "密码不合�?";
		}
		File file = new File("user/" + username);
		if (file.isDirectory()) {
			return "该用户名已存�?";
		}
		
		if (!file.isDirectory()) {
			try {
				file.mkdir();
				File psw = new File("user/" + username + "/" + password);
				psw.createNewFile();
				return "注册成功";
			} catch (Exception e) {
			}
		}
		return null;
	}
	
		
	

}
