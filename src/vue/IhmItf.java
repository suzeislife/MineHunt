package vue;

import bean.CellButton;

public interface IhmItf {

	void addClick();

	void setImageButton(CellButton cellB, int i, int nb);

	void setError(int errors);

	void endGame(int errors);


}
