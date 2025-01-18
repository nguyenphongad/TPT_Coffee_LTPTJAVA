package util;

import java.awt.Font;
import java.io.InputStream;

public class ImportFont {
	public Font importFontFromFile(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			return Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
