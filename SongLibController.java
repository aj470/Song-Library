package view;

import javafx.beans.Observable;
import javafx.event.ActionEvent; // Need this to create an action event parameter for button click method.
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Song;

import java.util.*;
import java.util.Collections;
import java.util.Optional;

public class SongLibController
{
	// These should all be private -Lyle.
	@FXML 
    private TextField sName;
    
    @FXML 
    private TextField sArtist;
    
    @FXML 
    private TextField sAlbum;
    
    @FXML 
    private TextField sYear;

    @FXML 
    private Button add;
    
    @FXML 
    private Button delete;
    
    @FXML 
    private Button edit;

    @FXML 
    private ListView<String> songList = new ListView<>();
    
    // A JavaFX TextArea element to display the song details when selected -Lyle
    @FXML 
    private TextArea songDetailDisplay; 
    
    // ObservableList for song display and selection -Lyle
    private ObservableList<String> obsList;
    
    // ArrayList to store song artist in corresponding index -Lyle
    private ArrayList<String> artistList = new ArrayList<String>();
    
    // ArrayList to store song album in corresponding index -Lyle
    private ArrayList<String> albumList = new ArrayList<String>();
    
    // ArrayList to store song year in corresponding index -Lyle
    private ArrayList<String> yearList = new ArrayList<String>();

    public void start(Stage mainStage)
    {
        songList.setEditable(true);
        mainStage.setTitle("Song Library");

        obsList = FXCollections.observableArrayList(
                "The Devil in I",
                "Killpop",
                "Kashmir",
                "Help",
                "Helena",
                "Abigail",
                "Puppets",
                "Reincarnate",
                "Surfacing",
                "570",
                "This is Gospel",
                "White Washed",
                "Song");

        FXCollections.sort(obsList);

        //display list
        songList.setItems(obsList);
        songList.getSelectionModel();
    }

    // Name of method = name assigned in #directive in fxml file for onAction attribute -Lyle
    public void click (ActionEvent e) {
    	Button button = (Button) e.getSource();

    }
    
    // Name of method = name assigned in #directive in fxml file for onAction attribute -Lyle
    public void songListSelection (MouseEvent e) {
    	songDetailDisplay.setText("clicked on " + songList.getSelectionModel().getSelectedItem()
    			+ "\n" + "artist:" + "\n" + "album:" + "\n" + "year");
    	
    }
}