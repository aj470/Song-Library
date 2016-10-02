package model;


import javafx.scene.control.Alert;

public class backend {
	
    public boolean exists (String name, String artist) {
        return false;
    }

    public void add (String name, String artist, String album, String year) {

    }

    public void delete (String name, String Artist) {

    }

    public void edit(Song s, String name, String artist, String album, String year) {

    }

    public void errorPrompt(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
