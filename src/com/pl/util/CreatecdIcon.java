package com.pl.util;

import java.net.URL;

import javax.swing.ImageIcon;

import com.pl.PLsystem;

public class CreatecdIcon {
	public static ImageIcon add(String ImageName){
		URL IconUrl = PLsystem.class.getResource("/"+ImageName);
		ImageIcon icon=new ImageIcon(IconUrl);
		return icon;
	}
}
