package controller;

import model.MineHuntModel;
import model.MineHuntModelItf;
import vue.IhmItf;

public class Ctrl implements CtrlItf {
	private IhmItf ihm;
	private MineHuntModelItf worker;

	public Ctrl() {
		worker = new MineHuntModel();
	}

	public void setIhm(IhmItf ihm) {
		this.ihm = ihm;
	}

	@Override
	public void boutonClicked(int col, int row) {
		System.out.println("col " + col + "   row " + row);

		if (!worker.isOpen(row, col)) {
			if(worker.open(row, col))
				ihm.addError();
			ihm.addClick();
		}

	}

}
