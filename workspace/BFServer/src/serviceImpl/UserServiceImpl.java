package serviceImpl;

import java.io.File;
import java.rmi.RemoteException;

import service.UserService;
import stringDeal.StringDeal;

public class UserServiceImpl implements UserService{

	@Override
	public boolean login(String username, String password) throws RemoteException {
		return true;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

	@Override
	public String register(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		if (!StringDeal.checkUserName(username)) {
			return "用户名不合法";
		}
		if (!StringDeal.checkPassword(password)) {
			return "密码不合法";
		}
		File file = new File("user/" + username);
		if (file.isDirectory()) {
			return "该用户名已存在";
		}
		
		if (!file.isDirectory()) {
			try {
				file.mkdir();
				File psw = new File("user/" + username + "/" + password);
				psw.createNewFile();
				return "注册成功";
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		UserServiceImpl usi = new UserServiceImpl();
		try {
			System.out.println(usi.register("haimian宝宝", "123456"));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
