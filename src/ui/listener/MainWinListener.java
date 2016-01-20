package ui.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import ui.main.Sys;
import ui.merchandises.EditMerchandise;
import ui.merchandises.MerchandiseIn;
import ui.merchandises.MerchandiseManager;
import ui.merchandises.MerchandiseRetail;

public class MainWinListener implements WindowListener {

	private Sys sys;
	private MerchandiseIn merchandiseIn;

	private MerchandiseManager merchandiseManager;

	private MerchandiseRetail merchandiseRetail;

	private EditMerchandise editMerchandise;

	public MainWinListener(Sys sys) {
		this.sys = sys;
	}

	public MainWinListener(MerchandiseIn merchandiseIn) {
		this.merchandiseIn = merchandiseIn;
	}

	public MainWinListener(MerchandiseManager merchandiseManager) {
		this.merchandiseManager = merchandiseManager;
	}

	public MainWinListener(MerchandiseRetail merchandiseRetail) {
		this.merchandiseRetail = merchandiseRetail;
	}

	public MainWinListener(EditMerchandise editMerchandise, MerchandiseManager merchandiseManager) {
		this.editMerchandise = editMerchandise;
		this.merchandiseManager = merchandiseManager;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		if (sys != null) {
			sys.setEnabled(true);
			sys.setAlwaysOnTop(true);

		}
		if (merchandiseIn != null) {
			merchandiseIn.setEnabled(true);
			merchandiseIn.initTableInfo();
		}
		if (merchandiseManager != null) {
			merchandiseManager.setEnabled(true);
			merchandiseManager.initTbMerInfo();
		}
		if (merchandiseRetail != null) {
			merchandiseRetail.setEnabled(true);
		}
		if (editMerchandise != null) {
			merchandiseManager.setEnabled(true);
			merchandiseManager.initTbMerInfo();
			merchandiseManager.setAlwaysOnTop(true);
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

}
