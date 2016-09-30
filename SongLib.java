package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import view.SongLibController;


public class SongLib extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/SongLib.fxml"));
		AnchorPane root = (AnchorPane) loader.load();

		SongLibController controller = loader.getController();
		controller.start(primaryStage);

		Scene scene = new Scene(root, 570,350);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
	}

	public static void main(String[] args) 
	{
		launch(args);
	}
}