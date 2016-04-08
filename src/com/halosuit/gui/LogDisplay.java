package com.halosuit.gui;

import javax.swing.JTextArea;

import com.halosuit.utils.Logger;

public class LogDisplay extends JTextArea implements Logger{

	@Override
	public void println(String message) {
		append(message + "\n");
	}

	@Override
	public void print(String message) {
		append(message);
	}

}
