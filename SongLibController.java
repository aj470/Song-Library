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

public class SongLibController
{
	@FXML
    private TextField sName;
	
    @FXML
    private TextField sArtist;
    
    @FXML
    private TextField sAlbum;
    
    @FXML
    private TextField sYear;
    
    @FXML 
    private ListView<String> songDisplay = new ListView<>();
    
    // A JavaFX TextArea element to display the song details when selected
    @FXML 
    private TextArea songDetailDisplay; 
    
    // ObservableList for song display and selection
    private ObservableList<String> songList;
    
    // Here is an ArrayList for holding all the Song objects that are created.
    ArrayList<Song> songCollection;
    
    public void start(Stage mainStage)
    {
        songDisplay.setEditable(true);
        mainStage.setTitle("Song Library");

        songList = FXCollections.observableArrayList();

        FXCollections.sort(songList);

        //display list
        songDisplay.setItems(songList);
        songDisplay.getSelectionModel();
    }

    // Name of method = name assigned in #directive in fxml file for onAction attribute
    public void clickAdd (ActionEvent e) {
    	
    	// Here we check to see if the TextBox is empty. If so, we set the enteredSong to null.
    	// (I have no idea what kind of value getText() returns if the TextBox is empty, but it isn't null or "".)
    	String enteredSong;
    	if (sName.getText().isEmpty()) {
    		enteredSong = null;
    	} else {
    		enteredSong = sName.getText();
    	}
    	
    	// Here we check to see if the TextBox is empty. If so, we set the enteredArtist to null.
    	String enteredArtist;
    	if (sArtist.getText().isEmpty()) {
    		enteredArtist = null;
    	} else {
    		enteredArtist = sArtist.getText();
    	}
    	
    	// Here we check to see if the TextBox is empty. If so, we set the enteredAlbum to null.
    	String enteredAlbum;
    	if (sAlbum.getText().isEmpty()) {
    		enteredAlbum = null;
    	} else {
    		enteredAlbum = sAlbum.getText();
    	}
    	
    	// Here we check to see if the TextBox is empty. If so, we set the enteredYear to null.
    	String enteredYear;
    	if (sYear.getText().isEmpty()) {
    		enteredYear = null;
    	} else {
    		enteredYear = sYear.getText();
    	}
    	
    	// So, then create a Song object.
    	
    	Song newSong = new Song();
    	
    	// Now what?
    }
    
    public void clickDelete (ActionEvent e) {
    	String songSelection = songDisplay.getSelectionModel().getSelectedItem();
    	songList.remove(songSelection);

    }
    public void clickEdit (ActionEvent e) {
    	String songSelection = songDisplay.getSelectionModel().getSelectedItem();
    	

    }
    
    // Name of method = name assigned in #directive in fxml file for onAction attribute
    public void songListSelection (MouseEvent e) {
    	songDetailDisplay.setText("clicked on " + songDisplay.getSelectionModel().getSelectedItem()
    			+ "\n" + "artist:" + "\n" + "album:" + "\n" + "year");
    	
    }
}
