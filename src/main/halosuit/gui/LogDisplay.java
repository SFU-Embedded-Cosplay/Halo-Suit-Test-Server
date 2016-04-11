package main.halosuit.gui;

import javax.swing.JTextArea;

import main.halosuit.utils.Logger;

public class LogDisplay extends JTextArea implements Logger{

	@Override
	public void println(String message) {
		append(message + "\n");
		System.out.println(message);
	}

	@Override
	public void print(String message) {
		append(message);
		System.out.print(message);
	}

}
