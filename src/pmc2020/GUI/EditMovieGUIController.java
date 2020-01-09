/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.awt.FileDialog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import pmc2020.BE.Movie;
import pmc2020.DAL.DalException;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Zanaxdk
 */
public class EditMovieGUIController implements Initializable
{

    private MovieModel model;
    private Movie movie;
    private String filename;
    private String directory;
    private double userRating;
    private double iMDBRating;

    @FXML
    private TextField editTitleText;
    @FXML
    private TextField editImdbRatingtext;
    @FXML
    private TextField editImdbSiteLinkText;
    @FXML
    private Button editChooseFilePathButton;
    @FXML
    private Label editChosenFilePathtext;
    @FXML
    private Button editAddMovieButton;
    @FXML
    private ComboBox<?> editCatChooser;
    @FXML
    private Slider editUserSlider;
    @FXML
    private Label editIMDBRating;
    @FXML
    private Label editUserRating;
    @FXML
    private Slider editImdbSlider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }


    void setModel(MovieModel model)
    {
        this.model = model;
    }


    @FXML
    private void SendValueUser(MouseEvent event)
    {
        double v = editUserSlider.getValue();
        String formatted = String.format("%.1f", v);
        editUserRating.setText(formatted + "");
        userRating = Double.parseDouble(editUserRating.getText());
    }

    @FXML
    private void SendValueIMDB(MouseEvent event)
    {
        double v = editImdbSlider.getValue();
        String formatted = String.format("%.1f", v);
        editIMDBRating.setText(formatted + "");
        iMDBRating = Double.parseDouble(editIMDBRating.getText());
    }
    @FXML 
    private void handleEditMovie(ActionEvent event) throws IOException, DalException
    {        
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        String title = editTitleText.getText();
        String imdbLink = editImdbSiteLinkText.getText();
        boolean notBlank;

        if (title != null && !title.isEmpty())
        {
            title = editTitleText.getText();
            movie.setTitle(title);
            notBlank = true;
        } else
        {
            JOptionPane.showMessageDialog(dialog, "Movie title can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            notBlank = false;
        }
        if (imdbLink != null && !imdbLink.isEmpty())
        {
            imdbLink = editImdbSiteLinkText.getText();
            movie.setIMDB_Link(imdbLink);
            notBlank = true;
        } else
        {
            JOptionPane.showMessageDialog(dialog, "IMDB Link can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            notBlank = false;
        }
        if (notBlank = true)
        {
            Stage stage = (Stage) editAddMovieButton.getScene().getWindow();
            model.updateMovie(movie);
            stage.close();
        }
    }

    @FXML
    private void handleEditFilePath(ActionEvent event)
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
            editChosenFilePathtext.setText(directory + "\\" + filename);
            System.out.println(filename);
        }
    }


    void setMovie(Movie movie)
    {
        this.movie = movie;


        editTitleText.setText(movie.getTitle());
        editImdbSiteLinkText.setText(movie.getIMDB_Link());
    }

}
