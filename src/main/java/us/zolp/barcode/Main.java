package us.zolp.barcode;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException ex) {
			System.err.println(ex.toString());
		} catch (ClassNotFoundException ex) {
			System.err.println(ex.toString());
		} catch (InstantiationException ex) {
			System.err.println(ex.toString());
		} catch (IllegalAccessException ex) {
			System.err.println(ex.toString());
		}

		new BarcodeGUI();
	}
}
