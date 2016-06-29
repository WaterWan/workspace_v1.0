package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import myTime.TestTime;
import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		File f = new File("user/" + userId + "/" + fileName + "/" + TestTime.getDate());
		File pack = new File("user/" + userId + "/" + fileName);
		try {
			if (!(pack.exists() && pack.isDirectory())) {
				pack.mkdir();
			}
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String readFile(String userId, String fileName) {
		String path = "user/" + userId +"/" + fileName;
		File file = new File(path);
		String code = "";
		String sepe = System.getProperty("line.separator");
		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String tempString;
			try {
				while ((tempString = bufferedReader.readLine()) != null) {
					code += tempString;
					code += sepe;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}

	@Override
	public String readFileList(String userId) {
		return "OK";
	}

	@Override
	public boolean fileExists(String username, String filename) throws RemoteException {
		File pack = new File("user/" + username + "/" + filename);
		if (pack.exists() && pack.isDirectory()) {
			return true;
		}
		return false;
	}

	@Override
	public String[] getFileNames(String path) throws RemoteException {
		ArrayList<String> temp = new ArrayList<>();
		File file = new File(path);
		File[] array = file.listFiles();

		for (int i = 0; i < array.length; i++) {
			if (array[i].isDirectory()) {
				temp.add(array[i].getName());
			} 
		}
		String[] directoryNames = new String[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			directoryNames[i] = (String)(temp.get(i));
		}
		return directoryNames;
	}

	@Override
	public String[] getVersionNames(String path) throws RemoteException {
		ArrayList<String> temp = new ArrayList<>();
		File file = new File(path);
		File[] array = file.listFiles();

		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				temp.add(array[i].getName());
			} 
		}
		String[] versionNames = new String[temp.size()];
		for (int i = 0; i < temp.size(); i++) {
			versionNames[i] = (String)(temp.get(i));
		}
		return versionNames;
	}
	
}
