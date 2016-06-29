package serviceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import myTime.TestTime;
import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		System.out.println("writeFile");
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
		return "OK";
	}

	@Override
	public String readFileList(String userId) {
		return "OK";
	}
	
}
