package br.pucsp.comp;

import br.pucsp.comp.fsm.Fsm1;

public class Main {

	public static void main(String[] args) {
		Fsm1 fsm = new Fsm1();
		fsm.run();
		
		fsm.reset();
		fsm.run();
	}

}
