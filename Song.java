package model;

import javafx.scene.control.Alert;

public class Song implements Comparable<Song>
{
    //instance variables
    private String songName;
    private String songArtist;
    private String songAlbum;
    private String songYear;

    //constructor with no parameters
    public Song()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Required fields:\n*Name\n*Artist");

        alert.showAndWait();
    }

    public Song (String name, String artist, String album, String year)
    {
        songName = name;
        songArtist = artist;
        songAlbum = album;
        songYear = year;
    }

    public Song(String name, String artist)
    {
        this(name, artist, "", "");
    }

    public Song(String name, String artist, String album)
    {
        this(name, artist, album, "");
    }

    public void setName (String name)
    {
        songName = name;
    }

    public void setArtist (String artist)
    {
        songArtist = artist;
    }

    public void setAlbum (String album)
    {
        songAlbum = album;
    }

    public void setYear (String year)
    {
        songYear = year;
    }

    public String getName ()
    {
        return songName;
    }

    public String getArtist ()
    {
        return songArtist;
    }

    public String getAlbum ()
    {
        return songAlbum;
    }

    public String getYear ()
    {
        return songYear;
    }

    @Override
    public String toString()
    {
        return "Name: " + songName + "\n" + "Artist: " + songArtist + "\n" + "Album: " + songAlbum + "\n" + "Year: " + songYear;
    }

    public int compareTo(Song song)
    {
        if(songName.compareToIgnoreCase(song.getName())!=0)
        {
            return songName.compareToIgnoreCase(song.getName());
        }
        else if(songArtist.compareToIgnoreCase(song.getArtist())!=0)
        {
            return songArtist.compareToIgnoreCase(song.getArtist());
        }else
            {
            return 0;
        }
    }
}
