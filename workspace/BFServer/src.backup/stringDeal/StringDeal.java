package stringDeal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringDeal {
	public static boolean checkUserName(String username) {
		String regex = "([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5]|[`~!@#$%^&*()-+_=|{}':;',\\[\\].<>/?~ï¼@#ï¿?%â€¦â??&*ï¼ˆï¼‰â€”â??+|{}ã€ã?‘â?˜ï¼›ï¼šâ?â?œâ?™ã?‚ï¼Œã€ï¼Ÿ])+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(username);
		return m.matches();
	}
	
	public static boolean checkPassword (String password) {
		String regex = "([a-z]|[A-Z]|[0-9]|[`~!@#$%^&*()-+_=|{}':;',\\[\\].<>/?~ï¼@#ï¿?%â€¦â??&*ï¼ˆï¼‰â€”â??+|{}ã€ã?‘â?˜ï¼›ï¼šâ?â?œâ?™ã?‚ï¼Œã€ï¼Ÿ])+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(password);
		return m.matches();
	}
	
}
