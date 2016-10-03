package model;


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

    public boolean exists(String name, String artist, ArrayList<Song> s)
    {
        boolean duplicate = false;

        for(int i = 0; i < songData.size(); i++)
        {
            String a1 = s.get(i).getArtist().toLowerCase();
            String n1 = s.get(i).getName().toLowerCase();
            String a2 = name.toLowerCase();
            String n2 = artist.toLowerCase();

            if (a2.equals(a1) && n1.equals(n2))
            {
                duplicate = true;
            }
        }
            return false;
    }

    public void add(Song song)
    {
        x.format("%s%s%s%s",song.getName() + "," , song.getArtist() + ",", song.getAlbum() + ",", song.getYear() + ",");
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
