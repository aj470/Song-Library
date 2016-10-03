package view;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Song;
import model.Backend;

import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    // A JavaFX TextArea element to display the song details when selected
    @FXML
    private TextArea songDetailDisplay;

    @FXML
    ListView<Song> songList = new ListView<>();

    // ObservableList for song display and selection
    private ObservableList<Song> obsList;

    // Here is an ArrayList for holding all the Song objects that are created.
    ArrayList<Song> songCollection = new ArrayList<Song>();

    class Listener implements ChangeListener<Song>
    {

        public void changed(ObservableValue<? extends Song> observable, Song oldValue, Song newValue)
        {

            songDisplay(newValue);

        }

    }


    Listener listener = new Listener();

    public void start(Stage mainStage) {
        songList.setEditable(true);
        mainStage.setTitle("Song Library");

        obsList = FXCollections.observableArrayList(songCollection);

        FXCollections.sort(obsList);

        //display list
        songList.setItems(obsList);
        songList.getSelectionModel().select(0);
    }

    Comparator<Song> compareToSong = new Comparator<Song>()
    {
        @Override
        public int compare(Song song1, Song song2)
        {
            return song1.getName().compareToIgnoreCase(song2.getName());
        }
    };
        public void click(ActionEvent e) throws IOException {
            Button x = (Button) e.getSource();
            Backend backend = new Backend();
            String n, a, header, content;
            String alb = "";
            String y = "";

            n = sName.getText();
            a = sArtist.getText();
            alb = sAlbum.getText();
            y = sYear.getText();

            if (x == add) {
                //check if name and artist were entered
                if (n.isEmpty() || a.isEmpty()) {
                    //error prompt
                    header = "Missing Information!";
                    content = "Song name and artist are required!";
                    backend.errorPrompt(header, content);
                } else {
                    //check if song is duplicate
                    if (backend.exists(n, a, obsList))
                    {
                        //error prompt
                        header = "Add Failed!";
                        content = "The song entered is already in the Library!";
                        backend.errorPrompt(header, content);
                    } else
                        {
                        Song song = new Song(n, a, alb, y);

                        obsList.add(song);
                        int ind = 0;
                        for (int i = 0; i < obsList.size(); i++) {
                            if (song.equals(obsList.get(i))) {
                                ind = i;
                            }
                        }
                        songList.getSelectionModel().select(ind);
                        songList.getSelectionModel().selectedItemProperty().removeListener(listener);
                        obsList.sort(compareToSong);
                        songList.getSelectionModel().selectedItemProperty().addListener(listener);

                        songDisplay(song);
                        backend.add(song);
                    }
                }

            } else if (x == delete) {
                if (n.isEmpty() || a.isEmpty()) {
                    //error prompt
                    header = "Missing Information!";
                    content = "Song name and artist are required!";
                    backend.errorPrompt(header, content);
                } else {
                    //check if song is duplicate
                    if (!backend.exists(n, a, obsList)) {
                        //error prompt
                        header = "Delete Failed!";
                        content = "The song entered is not in the Library!";
                        backend.errorPrompt(header, content);
                    } else {
                        Song song = new Song(n, a, alb, y);
                        if (songList.getSelectionModel().getSelectedIndex() == 0) {
                            obsList.remove(0);
                            songList.getSelectionModel().select(0);
                        } else {
                            obsList.remove(songList.getSelectionModel().getSelectedIndex());
                            songList.getSelectionModel().select(songList.getSelectionModel().getSelectedIndex() + 1);
                        }
                        backend.delete(song);
                    }
                }
            } else //x == edit
            {
                //check if exists
                if (backend.exists(n, a, obsList)) {
                    //check if all input fields are empty
                    if (n.isEmpty() && a.isEmpty() && alb.isEmpty() && y.isEmpty()) {
                        header = "Edit Failed!";
                        content = "Please input changes before clicking edit";
                    } else {
                        Song s = songList.getSelectionModel().getSelectedItem();
                        songList.getSelectionModel().getSelectedItem().setName(n);
                        songList.getSelectionModel().getSelectedItem().setArtist(a);
                        songList.getSelectionModel().getSelectedItem().setAlbum(alb);
                        songList.getSelectionModel().getSelectedItem().setYear(y);
                        songDisplay(s);
                        backend.edit(s, n, a, alb, y);
                    }

                } else {
                    //error prompt
                    header = "Error!";
                    content = "The song entered is not in the Library!";
                    backend.errorPrompt(header, content);
                }
            }
        }

        public void songListSelection(MouseEvent e) {
            songDisplay(songList.getSelectionModel().getSelectedItem());
        }

        public void songDisplay(Song s) {
            songDetailDisplay.setText
                    ("Song: " + s.getName() + "\n" +
                            "Artist: " + s.getArtist() + "\n" +
                            "Album: " + s.getAlbum() + "\n" +
                            "Year: " + s.getYear());
        }

}
