package vue;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ihm extends Application implements IhmItf {

	private static final String TITLE = "vue/resources/minehunt.png";

	private int clickNumber;
	private int errorNumber;
	private CtrlItf ctrl;
	private VBox root = new VBox(20);
	private ImageView title = new ImageView(TITLE);
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
				cellB.setMinSize(30, 30);
				cellB.setOnAction(event -> {
					ctrl.boutonClicked(cellB.getCol(), cellB.getRow());
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

	@Override
	public void addError() {
		int nb = Integer.parseInt(errorCount.getText());
		nb++;
		errorCount.setText("" + nb);
	}

	@Override
	public void addClick() {
		int nb = Integer.parseInt(clickCount.getText());
		nb++;
		clickCount.setText("" + nb);
	}

}
