package model;


import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Backend
{
    public ArrayList<Song> songData = new ArrayList<Song>();
    private Formatter x;

    public boolean exists(String name, String artist, ObservableList<Song> s)
    {
        boolean duplicate = false;

        int i = 0;
        for(Song song : s)
        {
            String a1 = s.get(i).getArtist().toLowerCase();
            String n1 = s.get(i).getName().toLowerCase();
            String a2 = artist.toLowerCase();
            String n2 = name.toLowerCase();

            if (a2.equals(a1) && n1.equals(n2))
            {
                duplicate = true;
            }
            i++;
        }
            return duplicate;
    }

    public void add(Song song)
    {
       // x.format("%s%s%s%s",song.getName() + "," , song.getArtist() + ",", song.getAlbum() + ",", song.getYear() + ",");
    }

    public void delete (Song song)
    {

    }

    public void edit(Song s, String name, String artist, String album, String year)
    {

    }

    public void open()
    {
        try
        {
            x = new Formatter("SongLibrary.txt");

        }
        catch(Exception e)
        {
            System.out.println("error");
        }
    }

    public void close()
    {
        x.close();
    }
    @SuppressWarnings("resource")
    public void openFile()
    {
        try
        {
            new Scanner(new File("SongLibrary.txt"));
        }
        catch(Exception e)
        {
            System.out.println("error");
        }
    }

    ArrayList<Song> read() throws FileNotFoundException
    {
        String token = "";
        String n = "";
        int count = 0;
        ArrayList<Song> songData = new ArrayList<Song>();

        // create Scanner inFile1
        @SuppressWarnings("resource")
        Scanner inFile1 = new Scanner(new File("library.txt")).useDelimiter(",");

        // while loop
        while (inFile1.hasNext())
        {
            // find next line
            token = inFile1.next();
            n = n + token + ",";
            count++;
            if(count == 4)
            {
                String a[] = new String[n.length()];
                String temp = "";
                int j = 0;
                for(int i = 0; i < n.length(); i++)
                {
                    if(j == 4)
                    {
                        break;
                    }
                    if(n.charAt(i) == ',')
                    {
                        if(temp.equals(""))
                        {
                            a[j] = "";
                            temp = "";
                            j++;
                        }
                        else
                        {
                            a[j] = temp;
                            temp = "";
                            j++;
                        }
                    }
                    else
                    {
                        temp = temp + n.charAt(i);
                    }
                }
                String name = a[0];
                String artist = a[1];
                String album = a[2];
                String year = a[3];
                Song add = new Song(name, artist, album, year);
                songData.add(add);
                count = 0;
                n = "";
            }
        }
        inFile1.close();

        return songData;
    }

    public void errorPrompt(String header, String content)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
