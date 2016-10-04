package view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Song;
import model.Backend;

public class SongLibController
{
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

    Backend backend = new Backend();

    // ObservableList for song display and selection
    private ObservableList<Song> obsList;

    // Here is an ArrayList for holding all the Song objects that are created.
    public static ArrayList<Song> songCollection = new ArrayList<Song>();


    public void start(Stage mainStage)
    {
        songList.setEditable(true);
        mainStage.setTitle("Song Library");

        songCollection = backend.readFile();

        Collections.sort(songCollection,compareToSong);
        obsList = FXCollections.observableArrayList(songCollection);

        FXCollections.sort(obsList);

        //display list
        songList.setItems(obsList);
        songList.getSelectionModel().select(0);
    }


    //comparator for sorting observable list
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
                if (n.isEmpty() || a.isEmpty())
                {
                    //error prompt
                    header = "Missing Information!";
                    content = "Song name and artist are required!";
                    backend.errorPrompt(header, content);
                }
                else
                    {
                    //check if song is duplicate
                    if (backend.exists(n, a, obsList))
                    {
                        //error prompt
                        header = "Add Failed!";
                        content = "The song entered is already in the Library!";
                        backend.errorPrompt(header, content);
                    }
                    else
                    {
                        Song song = new Song(n, a, alb, y);

                        songCollection.add(song);
                        obsList.add(song);
                        int ind = 0;
                        for (int i = 0; i < obsList.size(); i++) {
                            if (song.equals(obsList.get(i))) {
                                ind = i;
                            }
                        }
                        songList.getSelectionModel().select(ind);
                        songCollection.sort(compareToSong);
                        obsList.sort(compareToSong);

                        songDisplay(song);
                    }
                }

            }
            else if (x == delete)
            {
                if (songList.getSelectionModel().getSelectedItem() == null)
                {
                    //error prompt
                    header = "Error!";
                    content = "Library is empty!";
                    backend.errorPrompt(header, content);
                }
                else
                {
                    Song song = new Song(n, a, alb, y);
                    if (songList.getSelectionModel().getSelectedIndex() == 0)
                    {
                        songCollection.remove(0);
                        obsList.remove(0);
                        songList.getSelectionModel().select(0);
                    } else
                    {
                        songCollection.remove(songList.getSelectionModel().getSelectedIndex());
                        obsList.remove(songList.getSelectionModel().getSelectedIndex());
                        songList.getSelectionModel().select(songList.getSelectionModel().getSelectedIndex() + 1);
                    }
                }
            }
            else //x == edit
            {
                //check if exists
                if (songList.getSelectionModel().getSelectedItem() != null)
                {
                    if (backend.exists(n, a, obsList))
                    {
                        header = "Edit Failed!";
                        content = "Song is a duplicate!";
                        backend.errorPrompt(header, content);
                    }
                    //check if all input fields are empty
                    else if (n.isEmpty() && a.isEmpty() && alb.isEmpty() && y.isEmpty())
                    {
                        header = "Edit Failed!";
                        content = "Please input changes before clicking edit";
                        backend.errorPrompt(header, content);
                    }
                    else
                    {
                        Song s = songList.getSelectionModel().getSelectedItem();
                        if(n.isEmpty())
                        {
                            songList.getSelectionModel().getSelectedItem().setArtist(a);
                            songList.getSelectionModel().getSelectedItem().setAlbum(alb);
                            songList.getSelectionModel().getSelectedItem().setYear(y);
                        }
                        else if(a.isEmpty())
                        {
                            songList.getSelectionModel().getSelectedItem().setName(n);
                            songList.getSelectionModel().getSelectedItem().setAlbum(alb);
                            songList.getSelectionModel().getSelectedItem().setYear(y);
                        }
                        else
                        {
                            songList.getSelectionModel().getSelectedItem().setName(n);
                            songList.getSelectionModel().getSelectedItem().setArtist(a);
                            songList.getSelectionModel().getSelectedItem().setAlbum(alb);
                            songList.getSelectionModel().getSelectedItem().setYear(y);
                        }

                        songCollection.set(songList.getSelectionModel().getSelectedIndex(), s);
                        obsList.sort(compareToSong);
                        songDisplay(s);
                    }

                }
                else
                {
                    //error prompt
                    header = "Error!";
                    content = "Library is empty!";
                    backend.errorPrompt(header, content);
                }
            }
        }

        public void songListSelection(MouseEvent e)
        {
            sName.clear();
            sArtist.clear();
            sAlbum.clear();
            sYear.clear();

            if(songList.getSelectionModel().getSelectedItem() != null)
            {
                songDisplay(songList.getSelectionModel().getSelectedItem());
            }

        }

        public void songDisplay(Song s)
        {
            songDetailDisplay.setText
                    ("Song: " + s.getName() + "\n" +
                            "Artist: " + s.getArtist() + "\n" +
                            "Album: " + s.getAlbum() + "\n" +
                            "Year: " + s.getYear());
        }

}
