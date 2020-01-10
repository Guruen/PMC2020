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
 * @author Guruerne
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
    
    /**
     * gets the value from the slider on the view and sets this value as text on a label, and displays live changes
     * @param event 
     */

    @FXML
    private void SendValueUser(MouseEvent event)
    {
        double v = editUserSlider.getValue();
        String formatted = String.format("%.1f", v);
        editUserRating.setText(formatted + "");
        userRating = v;
    }
    
    /**
     * gets the value from the slider on the view and sets this value as text on a label, and displays live changes
     * @param event 
     */

    @FXML
    private void SendValueIMDB(MouseEvent event)
    {
        double v = editImdbSlider.getValue();
        String formatted = String.format("%.1f", v);
        editIMDBRating.setText(formatted + "");
        iMDBRating = v;
    }
    
    /**
     * gets all available data on the selected entry from the DB, and lets the user edit it accordingly
     * handles updating the new data with the DB
     * window only closes when data is present, to prevent blank entries (true condition)
     * @param event
     * @throws IOException
     * @throws DalException 
     */

    @FXML
    private void handleEditMovie(ActionEvent event) throws IOException, DalException
    {
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        double imdbRating = editImdbSlider.getValue();
        double userRating = editUserSlider.getValue();
        String title = editTitleText.getText();
        String imdbLink = editImdbSiteLinkText.getText();

        movie.setIMDB_Rating(imdbRating);
        movie.setPrivate_Rating(userRating);
        movie.setTitle(title);
        movie.setIMDB_Link(imdbLink);
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
            movie.setFile_location(editChosenFilePathtext.getText());
            Stage stage = (Stage) editAddMovieButton.getScene().getWindow();
            model.updateMovie(movie);
            //model.refreshMovies(); For at refresh efter man har trykket Edit
            stage.close();
        }
    }

    /**
     * handles opening the filedialog to allow the user to open / choose a mp4 or mpeg4 file
     * uses filename and directory to see where the file is to add this data to the DB too
     * also checks the filename to see if it is the correct filetype(s)
     * @param filename
     * @param directory
     * @param event 
     */
    
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
            editChosenFilePathtext.setText(directory + filename);
            System.out.println(filename);
        }
    }
    
    /**
     * sets the movie to the newly edited one
     * @param movie 
     */

    void setMovie(Movie movie)
    {
        this.movie = movie;

        editTitleText.setText(movie.getTitle());
        editImdbSiteLinkText.setText(movie.getIMDB_Link());
        editChosenFilePathtext.setText(movie.getFile_location());
    }
}
