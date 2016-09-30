package view;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Song;

import java.util.Collections;
import java.util.Optional;

public class SongLibController
{
    @FXML TextField sName;
    @FXML TextField sArtist;
    @FXML TextField sAlbum;
    @FXML TextField sYear;

    @FXML Button add;
    @FXML Button delete;
    @FXML Button edit;

    @FXML ListView<String> songList = new ListView<>();

    private ObservableList<String> obsList;


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
        songList.getSelectionModel().select(0);
    }
}
