package br.pucsp.comp.fsm;

import br.pucsp.comp.util.Keyboard;

public class Fsm1 {
	StringBuilder _input;
	StringBuilder _auxiliarInput;
	Keyboard _kbd;
	State _currentState;

	public Fsm1() {
		_input = new StringBuilder();
        _auxiliarInput = new StringBuilder();
		_kbd = new Keyboard();
		_currentState = State.Q_NO_OPEN_BRACKET;
	}

	public void reset() {
		_currentState = State.Q_NO_OPEN_BRACKET;
		_input =  new StringBuilder();
		_auxiliarInput = new StringBuilder();
	}

	public void run() {
		alert("Fsm1: Iniciando os servicos ...");
		_kbd.getCurrentChar();
		while(_currentState != State.Q_END) {
			State nextState = getNextState();
			_currentState = nextState;
		}
		alert("Inputs: " + _input.toString());
		alert("Fsm1: Finalizando os servicos.");
	}

	public Event getEvent() {
		Character ch = _kbd.getCurrentChar();

		switch (ch) {
		case '[':
			return Event.INPUT_OPEN_BRACKET;
		case ']':
			return Event.INPUT_CLOSE_BRACKET;
		case '#':
			return Event.INPUT_STOP;
		default:
			return Event.INPUT_CHAR;
		}
	}

	public State getNextState() {
		Event event = getEvent();
		State nextState = _currentState; //Autotransição automática
		char newCharInSequence = _kbd.getNextChar();

		switch (_currentState) {
			case Q_NO_OPEN_BRACKET: {
				switch(event){
					case INPUT_CHAR:
					case INPUT_CLOSE_BRACKET:{
						_input.append(newCharInSequence);
						_auxiliarInput.append(newCharInSequence);
						break;
					}

					case INPUT_OPEN_BRACKET:{
						_input.append(newCharInSequence);
						nextState = State.Q_BRACKET_OPENED;
						break;
					}
                    case INPUT_STOP:{
                        nextState = State.Q_END;
                        break;
                    }
				}
				break;
			}

			case Q_BRACKET_OPENED:{
				switch (event){
					case INPUT_CHAR:
					case INPUT_OPEN_BRACKET:{
						_input.append(newCharInSequence);
						break;
					}
					case INPUT_CLOSE_BRACKET:{
                        removeBrackets();
						nextState = State.Q_BRACKET_CLOSED;
						break;
					}
                    case INPUT_STOP:{
                        nextState = State.Q_END;
                        break;
                    }
				}
				break;
			}

			case Q_BRACKET_CLOSED:{
					switch (event){
						case INPUT_CHAR:
						case INPUT_CLOSE_BRACKET:{
							_input.append(newCharInSequence);
							_auxiliarInput.append(newCharInSequence);
							nextState = State.Q_NO_OPEN_BRACKET;
							break;
						}
						case INPUT_OPEN_BRACKET:{
							_input.append(newCharInSequence);
							nextState = State.Q_BRACKET_OPENED;
							break;
						}
                        case INPUT_STOP:{
                            nextState = State.Q_END;
                            break;
                        }
					}
				break;
			}
		}

		return nextState;
	}

	void alert(String msg) {
		System.out.printf("Mensagem: %s\n", msg);
	}

	private void removeBrackets(){
        _input.setLength(_auxiliarInput.length());
    }

}
