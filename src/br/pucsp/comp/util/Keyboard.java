package br.pucsp.comp.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Keyboard {
	private Scanner _scanner;
	private Queue<Character> _queue;

	public Keyboard() {
		_scanner = new Scanner(System.in);
		_queue = new LinkedList<Character>();
	}
	
	public void reset() {
		_queue.clear();
	}

	public char getCurrentChar(){
		Character ch = _queue.peek();
		if (ch == null) {
			getNextChars();
			ch = _queue.peek();
		}
		return ch;
	}

	public char getNextChar() {
		Character ch = _queue.poll();
		if (ch == null) {
			getNextChars();			 
			ch = _queue.poll();
		}
		return ch;
	}
	
	private void getNextChars() {
		String s =  _scanner.nextLine();
		while (s.length() == 0) {
			s =  _scanner.nextLine();
		}
		for (int i = 0; i < s.length(); i++) {
			Character ch = s.charAt(i);
			_queue.add(ch);
		}
	}
}
