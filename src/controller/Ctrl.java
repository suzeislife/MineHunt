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
	public void boutonClicked(CellButton cellB, boolean rightClick) {
		// System.out.println("col " + col + " row " + row);

		if (!worker.isOpen(cellB.getRow(), cellB.getCol())) {
			if (rightClick) {
				if (worker.isFlagged(cellB.getRow(), cellB.getCol())) {
					ihm.setImageButton(cellB, 2, 0);
					worker.setFlagState(cellB.getRow(), cellB.getCol(), false);
				} else {
					ihm.setImageButton(cellB, 1, 0);
					worker.setFlagState(cellB.getRow(), cellB.getCol(), true);
				}
			} else {
				if (worker.open(cellB.getRow(), cellB.getCol())) {
					ihm.setError(worker.errors());
					ihm.setImageButton(cellB, 0, 0);
				} else if (worker.isGameOver()) {
					ihm.endGame(worker.errors());
				} else
					ihm.setImageButton(cellB, 3, worker.neighborMines(cellB.getRow(), cellB.getCol()));

				ihm.addClick();
			}
		}

	}

}
