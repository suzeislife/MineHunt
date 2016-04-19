package vue;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import bean.CellButton;
import constante.CONSTANTE;
import controller.Ctrl;
import controller.CtrlItf;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ihm extends Application implements IhmItf {

	private int clickNumber;
	private int errorNumber;
	private CtrlItf ctrl;
	private VBox root = new VBox(20);
	private ImageView title = new ImageView(CONSTANTE.TITLE);
	private ImageView mine;
	private ImageView flag;
	private HBox infoGame = new HBox(20);
	private Label clickNb = new Label("Nb clicks:");
	private Label clickCount = new Label("" + clickNumber);
	private Label errorNb = new Label("Nb errors:");
	private Label errorCount = new Label("" + errorNumber);
	// Button Table List:
	private GridPane grille = new GridPane();

	private HBox gameButton = new HBox(10);
	private Button showMines = new Button("Show Mines");
	private Button newGame = new Button("New Game");

	@Override
	public void start(Stage mainStage) throws Exception {
		Ctrl ctrl = new Ctrl();
		ctrl.setIhm(this);
		this.setCtrl(ctrl);

		mainStage.setTitle("MineHunt V1.0");

		Scene scene = new Scene(root);
		// --- Title
		root.setAlignment(Pos.CENTER);
		root.getChildren().add(title);
		root.setStyle("-fx-background-color: #faf5c8");

		clickCount.setStyle("-fx-background-color: #33CC33");
		clickCount.setMinSize(60, 20);
		clickCount.setAlignment(Pos.CENTER);
		errorCount.setStyle("-fx-background-color: #FF3333");
		errorCount.setMinSize(60, 20);
		errorCount.setAlignment(Pos.CENTER);
		// --- Add Label Info Game:
		infoGame.getChildren().addAll(clickNb, clickCount, errorNb, errorCount);
		infoGame.setAlignment(Pos.CENTER);
		// --- Info Game
		root.getChildren().add(infoGame);

		// --- Button Table List:
		root.getChildren().add(grille);
		grille.setHgap(CONSTANTE.NBR_COL);
		grille.setVgap(CONSTANTE.NBR_ROW);
		grille.setAlignment(Pos.CENTER);
		for (int i = 0; i < CONSTANTE.NBR_COL; i++) {
			for (int j = 0; j < CONSTANTE.NBR_ROW; j++) {
				CellButton cellB = new CellButton(i, j);
				cellB.setMinSize(40, 30);
				cellB.setOnMouseClicked(event -> {
					if (event.getButton() == MouseButton.SECONDARY)
						ctrl.boutonClicked(cellB, true);
					else
						ctrl.boutonClicked(cellB, false);
				});
				grille.add(cellB, i, j);

			}
		}
		// --- Add Buton Game
		gameButton.getChildren().add(showMines);
		gameButton.getChildren().add(newGame);
		gameButton.setAlignment(Pos.CENTER);
		// --- Game Button
		root.getChildren().add(gameButton);

		mainStage.setScene(scene);
		mainStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void setCtrl(CtrlItf ctrl) {
		this.ctrl = ctrl;
	}

	/*
	 * int nb = Integer.parseInt(errorCount.getText()); nb++;
	 * errorCount.setText("" + nb);
	 */

	@Override
	public void addClick() {
		int nb = Integer.parseInt(clickCount.getText());
		nb++;
		clickCount.setText("" + nb);
	}

	@Override
	public void setImageButton(CellButton cellB, int i, int nb) {
		// Error, mine
		if (i == 0) {
			mine = new ImageView(CONSTANTE.MINE);
			mine.setFitWidth(20);
			mine.setFitHeight(20);
			cellB.setStyle("-fx-background-color: #FF3333");
			cellB.setGraphic(mine);
		}
		// flag

		else if (i == 1) {
			flag = new ImageView(CONSTANTE.FLAG);
			flag.setFitWidth(20);
			flag.setFitHeight(20);
			cellB.setGraphic(flag);
		}
		// remove flag

		else if (i == 2) {
			cellB.setGraphic(null);
		}
		// blanc
		else if (i == 3) {
			cellB.setGraphic(null);
			cellB.setText("" + nb);
			cellB.setStyle("-fx-background-color: #fff27f");
		}

	}

	@Override
	public void setError(int errors) {
		errorCount.setText("" + errors);

	}

	@Override
	public void endGame(int errors) {
		Alert dialog = new Alert(AlertType.INFORMATION);
		dialog.setTitle("MineHunt - GameOver");
		dialog.setHeaderText("MineHunt");
		if (errors == 0) {
			dialog.setAlertType(AlertType.INFORMATION);
			dialog.setContentText("Congratulation !\n" + "Current game endes successfully (no error)");
			dialog.showAndWait();
		} else {
			dialog.setAlertType(AlertType.WARNING);
			dialog.setContentText("Current game ended with "+errors+ " errors");
			dialog.showAndWait();
		}
	}

}
