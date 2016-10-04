package model;


import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Backend
{
    //public ArrayList<Song> songData = new ArrayList<Song>();
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

    public static void writeToFile(ArrayList<Song> songData)
    {
        File fileName = new File("Library.txt");


        try
        {
            FileWriter fw = new FileWriter(fileName);
            Writer output = new BufferedWriter(fw);

            int size = songData.size();
            for(int i = 0; i < size; i++)
            {
                output.write(songData.get(i).outFile());
            }
            output.close();
        } catch(Exception e)
        {
            System.out.println(e);
        }

    }

    public ArrayList<Song> readFile()
    {
        ArrayList<Song> songList = new ArrayList<Song>();
        String line, name, artist, album, year;
        String fileName = "Library.txt";

        try
        {
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            if(!input.ready())
            {
                
            }
            while ((line = input.readLine()) != null)
            {
                String[] info = line.split("\\,");

                if(info[0] != null)
                {
                    name = info[0];
                }
                else
                {
                    name = null;
                }
                if(info[1] != null)
                {
                    artist = info[1];
                }
                else
                {
                    artist = null;
                }
                if(info[2] != null)
                {
                    album = info[2];
                }
                else
                {
                    album = null;
                }
                if(info[3] != null)
                {
                    year = info[3];
                }
                else
                {
                    year = null;
                }

                songList.add(new Song(name, artist, album, year));
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File 'Library.txt' created");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return songList;
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
