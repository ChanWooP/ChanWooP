package com.test3;

public class BoardMarker {
	
	int ink = 1000;
	
	String writeFonts (String Text) {
		int len = Text.length();
		
		if( len > ink) {
			return "";
		}
		ink = ink - len;
		return String.format("%s", Text);
	}
}