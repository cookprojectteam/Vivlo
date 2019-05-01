package vivlo;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vivlo vivlo = new Vivlo();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
