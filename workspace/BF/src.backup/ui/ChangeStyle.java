package ui;

import java.awt.Component;
import java.awt.Font;

public class ChangeStyle {
	public int changeFontSize(Component c, int size, int dsize) {
		Font temp = new Font("TimesRoman", Font.PLAIN, size + dsize);
		c.setFont(temp);
		return size + dsize;
	}
}
