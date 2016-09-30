package view;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Song;
import model.Backend;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

public class SongLibController {
    @FXML
    TextField sName;
    @FXML
    TextField sArtist;
    @FXML
    TextField sAlbum;
    @FXML
    TextField sYear;

    @FXML
    Button add;
    @FXML
    Button delete;
    @FXML
    Button edit;
    @FXML
    Button display;

    @FXML
    ListView<Song> songList = new ListView<>();

    private ObservableList<Song> obsList;


    public void start(Stage mainStage)
    {
        songList.setEditable(true);
        mainStage.setTitle("Song Library");

        obsList = FXCollections.observableArrayList();

        FXCollections.sort(obsList);

        //display list
        songList.setItems(obsList);
        songList.getSelectionModel().select(0);
    }

    public void click(ActionEvent e) throws IOException
    {
        Button x = (Button) e.getSource();
        Backend backend = new Backend();
        boolean success;
        String n, a, header, content;
        String alb = "";
        String y = "";

        n = sName.getText();
        a = sArtist.getText();
        alb = sAlbum.getText();
        y = sYear.getText();

        if (x == add)
        {
            //check if name and artist were entered
            if(n.isEmpty() || a.isEmpty())
            {
                //error prompt
                header = "Missing Information!";
                content = "Song name and artist are required!";
                backend.errorPrompt(header, content);
            }
            else
            {
                //check if song is duplicate
                if(backend.exists(n, a))
                {
                    //error prompt
                    header = "Add Failed!";
                    content = "The song entered is already in the Library!";
                    backend.errorPrompt(header, content);
                }
                else
                {
                    backend.add(n, a, alb, y);
                }
            }

        }
        else if (x == delete)
        {
            if(n.isEmpty() || a.isEmpty())
            {
                //error prompt
                header = "Missing Information!";
                content = "Song name and artist are required!";
                backend.errorPrompt(header, content);
            }
            else
            {
                //check if song is duplicate
                if(!backend.exists(n, a))
                {
                    //error prompt
                    header = "Delete Failed!";
                    content = "The song entered is not in the Library!";
                    backend.errorPrompt(header, content);
                }
                else
                {
                    backend.delete(n, a);
                }
            }
        }
        else if(x == edit)
        {
            //check if exists
            if(backend.exists(n, a))
            {
                //check if all input fields are empty
                if(n.isEmpty() && a.isEmpty() && alb.isEmpty() && y.isEmpty())
                {
                    header = "Edit Failed!";
                    content = "Please input changes before clicking edit";
                }
                else
                {
                    Song s = songList.getSelectionModel().getSelectedItem();
                    backend.edit(s, n, a, alb, y);
                }

            }
            else
            {
                //error prompt
                header = "Error!";
                content = "The song entered is not in the Library!";
                backend.errorPrompt(header, content);
            }
        }
        else //display was clicked
        {
            content = songList.getSelectionModel().getSelectedItem().toString();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Display");
            alert.setHeaderText("Song Details");
            alert.setContentText(content);

            alert.showAndWait();
        }
    }
}
