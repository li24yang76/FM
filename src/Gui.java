import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

enum State {
	
	OFF (true, false, false, false, false),
	BOTTOM (false, true, false, false, true),
	TOP (false, true, true, false, true),
	LOCK (false, true, true, false, true),
	SCAN (false, true, false, true, true);
	boolean buttonOn, buttonReset, buttonScan, buttonLock, buttonOff;
	private State (boolean buttonOn, boolean buttonReset, boolean buttonScan, boolean buttonLock, boolean buttonOff) {
		this.buttonOn = buttonOn;
		this.buttonReset = buttonReset;
		this.buttonScan = buttonScan;
		this.buttonLock = buttonLock;
		this.buttonOff = buttonOff;
	}
}

public class Gui extends Frame implements Runnable{
	Button on;
	Button reset;
	Button scan;
	Button lock;
	Button off;
	TextField moniter;
	public  Thread counter;
	double frequency = 108;
	State state = State.OFF;
	
	public static void main(String[] args) {
		Gui gui = new Gui("FM Radio");
		gui.setSize(260, 200);
		gui.setLocation(500,200);
		gui.setBackground(Color.GRAY);
		gui.setVisible(true);
	}
	
	public Gui (String title) {
		super(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setLayout(null);
		this.setResizable(false);
		on = new Button("On");
		on.setSize(40, 25);
		on.setLocation(10,170);
		on.addActionListener(new ActionListenerOn(this));
		reset = new Button("Reset");
		reset.setSize(40, 25);
		reset.setLocation(60,170);
		reset.addActionListener(new ActionListenerReset(this));
		scan = new Button("Scan");
		scan.setSize(40, 25);
		scan.setLocation(110,170);
		scan.addActionListener(new ActionListenerScan(this));
		lock = new Button("Lock");
		lock.setSize(40, 25);
		lock.setLocation(160,170);
		lock.addActionListener(new ActionListenerLock(this));
		off = new Button("Off");
		off.setSize(40, 25);
		off.setLocation(210,170);
		off.addActionListener(new ActionListenerOff(this));
		moniter = new TextField("");
		moniter.setSize(200,30);
		moniter.setLocation(35,100);
		moniter.setFont(new Font("",1,19));
		moniter.setEditable(false);
		this.add(on);
		this.add(reset);
		this.add(scan);
		this.add(lock);
		this.add(off);
		this.add(moniter);
		counter = new Thread(this);
		counter.start();
		refreshButton();
	}

	public void run() {
		while (true) {
			switch (state) {
			case TOP:
				frequency = 108;
				break;
			case BOTTOM:
				break;
			case SCAN:
				frequency = frequency - 0.1;
				BigDecimal bg = new BigDecimal(frequency);
				frequency = bg.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
				if (frequency == 88) {
					state = State.BOTTOM;
				}
				break;
			case LOCK:
				break;
			case OFF:
				break;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			refreshButton();
		}
	}
	
	public void display() {
		String content = "    " + frequency + " MHz";
		moniter.setText(content);
	}

	public void refreshButton() {
		on.setEnabled(state.buttonOn);
		reset.setEnabled(state.buttonReset);
		scan.setEnabled(state.buttonScan);
		lock.setEnabled(state.buttonLock);
		off.setEnabled(state.buttonOff);	
		display();
		if (state == State.OFF) {
			moniter.setText("");
		}
	}
}
