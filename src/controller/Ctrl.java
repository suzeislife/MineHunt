package controller;

import bean.CellButton;
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
	public void boutonClicked(CellButton cellB) {
		// System.out.println("col " + col + " row " + row);

		if (!worker.isOpen(cellB.getRow(), cellB.getCol())) {
			ihm.setImageButton(cellB, 2, 0);
			if (worker.open(cellB.getRow(), cellB.getCol())) {
				ihm.addError();
				ihm.setImageButton(cellB, 0, 0);
			} else {
				ihm.setImageButton(cellB, 2, worker.neighborMines(cellB.getRow(), cellB.getCol()));
			}
			ihm.addClick();
		}

	}

}
