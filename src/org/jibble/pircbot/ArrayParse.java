package org.jibble.pircbot;

import java.util.Arrays;

public class ArrayParse {
	public String[] moderators;
	public String modReturn = Arrays.toString(moderators);
	public String getMods() {
		return this.modReturn;
	}
	
	public void setMods(String modReturn) {
		this.modReturn= modReturn;
		System.out.println(modReturn);
	}
	
}
