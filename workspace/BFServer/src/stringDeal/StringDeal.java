package stringDeal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDeal {
	public static boolean checkUserName(String username) {
		String regex = "([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5]|[`~!@#$%^&*()-+_=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？])+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(username);
		return m.matches();
	}
	
	public static boolean checkPassword (String password) {
		String regex = "([a-z]|[A-Z]|[0-9]|[`~!@#$%^&*()-+_=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？])+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(password);
		return m.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(checkUserName("hehe"));
	}
}
