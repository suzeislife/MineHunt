package bean;

import javafx.scene.control.Button;

public class CellButton extends Button {

	private boolean flag;
	private boolean mine;
	private boolean open;
	private int row;
	private int col;

	public CellButton(int col, int row) {
		flag = false;
		open = false;
		mine = false;
		this.row = row;
		this.col = col;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isMine() {
		return mine;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

}
