/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.awt.FileDialog;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Kim
 */
public class AddMovieGUIController implements Initializable
{
    private String filename;
    private String directory;

    @FXML
    private TextField titleText;
    @FXML
    private TextField imdbSiteLinkText;
    @FXML
    private Button chooseFilePathButton;
    @FXML
    private Button addMovieButton;
    @FXML
    private Label chosenFilePathtext;
    @FXML
    private TextField iMDBRating;

    public MovieModel model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleChooseFilePath(ActionEvent event)
    {
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        FileDialog fd = new java.awt.FileDialog((java.awt.Frame) null);
        fd.setDirectory("C:\\");
        fd.setFile("*.mpeg4;*.mp4");
        fd.setTitle("Add a mpeg4 or mp4 file");
        fd.setVisible(true);
        fd.setFocusable(true);
        fd.setAutoRequestFocus(true);
        filename = fd.getFile();
        directory = fd.getDirectory();
        if (filename == null)
        {
            JOptionPane.showMessageDialog(dialog, "Adding a movie has been cancelled. Try again!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else
        {
            chosenFilePathtext.setText(directory + "\\" + filename);
            System.out.println(filename);
        }
    }

    @FXML
    private void handleAddMovie(ActionEvent event)
    {
        String title = titleText.getText();
        double iMDB_Rating = Integer.parseInt(iMDBRating.getText());
        String iMDB_SiteLink = imdbSiteLinkText.getText();
        String movie_FilePath = chosenFilePathtext.getText();
        
        

    }

    void setModel(MovieModel model)
    {
        this.model = model;
    }
    
}
