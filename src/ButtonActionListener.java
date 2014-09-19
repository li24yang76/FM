import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {
	Gui gui;
	State buttonState;
	public ButtonActionListener (Gui gui) {
		this.gui = gui;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
		    gui.state = buttonState;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

 class ActionListenerOn extends ButtonActionListener implements ActionListener {

	public ActionListenerOn(Gui gui) {
		super(gui);
		buttonState = State.TOP;
	}
	
}

class ActionListenerReset extends ButtonActionListener implements ActionListener {
	
	public ActionListenerReset(Gui gui) {
		super(gui);
		buttonState = State.TOP;
	}
}

class ActionListenerLock extends ButtonActionListener implements ActionListener {
	
	public ActionListenerLock(Gui gui) {
		super(gui);
		buttonState = State.LOCK;
	}
}

class ActionListenerScan extends ButtonActionListener implements ActionListener {
	
	public ActionListenerScan(Gui gui) {
		super(gui);
		buttonState = State.SCAN;
	}
}

class ActionListenerOff extends ButtonActionListener implements ActionListener {
	
	public ActionListenerOff(Gui gui) {
		super(gui);
		buttonState = State.OFF;
	}
}
