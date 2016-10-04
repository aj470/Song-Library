package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Backend;
import model.Song;
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

	public static void main(String[] args) throws FileNotFoundException
	{
		launch(args);



		// This is a shutdown hook to create and write to a .txt before it closes.
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {

				Backend.writeToFile(SongLibController.songCollection);

			}
		}));

	}
}
