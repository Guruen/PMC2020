/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.stream.events.Characters;
import pmc2020.BE.Category;
import pmc2020.BE.Movie;
import pmc2020.DAL.DalException;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Guruerne
 */
public class AddMovieGUIController implements Initializable
{
    /**
     * @param filename filename of the file that has been chosen
     * @param directory the directory of the file that has been chosen
     * @param userRating the users personal rating of the movie
     * @param iMDBRating the rating of the movie according to IMDB
     */

    private String filename;
    private String directory;
    private Movie movie;
    private double userRating;
    private double iMDBRating;

    public MovieModel model;

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
    private Label IMDBRating;
    @FXML
    private Slider imdbSlider;
    @FXML
    private ListView<Category> categoryList;
    
    /**
     * Adds a new controller to the movie GUI, by making a new instance of the class
     * @throws IOException
     * @throws DalException 
     */

    public AddMovieGUIController() throws IOException, DalException
    {
        try
        {
            model = new MovieModel();
        } catch (IOException | DalException ex)
        {
            Logger.getLogger(MovieGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        categoryList.setItems(model.getAllCategories());
        categoryList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
            chosenFilePathtext.setText(directory + filename);
            System.out.println(filename);
        }
    }
    
    /**
     * Handles adding the movie, using the suppied data from the view
     * the handler also checks if there is any data present, to prevent blank entries to the DB
     * The user gets an error message if any of the info is blank, and the blank info is then replaced with placeholders
     * lets the window close if the conditions are true
     * @param event
     * @throws DalException 
     */

    @FXML
    private void handleAddMovie(ActionEvent event) throws DalException, IOException
    {
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        String title = titleText.getText();
        String iMDB_SiteLink = imdbSiteLinkText.getText();
        String movie_FilePath = chosenFilePathtext.getText();

        List<Category> categories = categoryList.getSelectionModel().getSelectedItems();

        boolean movieNotEmpty;

        if (title != null && !title.isEmpty())
        {
            title = titleText.getText();
            titleText.setText(title);
            movieNotEmpty = true;
            System.out.println(movieNotEmpty);
        } else
        {
            JOptionPane.showMessageDialog(dialog, "Movie title can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            titleText.setText("EDIT ME");
            movieNotEmpty = false;
            System.out.println(movieNotEmpty);

        }

        if (iMDB_SiteLink != null && !iMDB_SiteLink.isEmpty())
        {
            iMDB_SiteLink = imdbSiteLinkText.getText();
            imdbSiteLinkText.setText(iMDB_SiteLink);
            movieNotEmpty = true;
            System.out.println(movieNotEmpty);
        } else
        {
            JOptionPane.showMessageDialog(dialog, "IMDB link can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            imdbSiteLinkText.setText("https://www.imdb.com/");
            movieNotEmpty = false;
            System.out.println(movieNotEmpty);

        }

        if (movie_FilePath != "Chosen File Path" && !movie_FilePath.isEmpty())
        {
            movie_FilePath = chosenFilePathtext.getText();
            chosenFilePathtext.setText(movie_FilePath);
            movieNotEmpty = true;
            System.out.println(movieNotEmpty);
        } else
        {
            JOptionPane.showMessageDialog(dialog, "Movie file path can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            chosenFilePathtext.setText("Chosen File Path");
            movieNotEmpty = false;
            System.out.println(movieNotEmpty);

        }

        if (movieNotEmpty == true)
        {
            System.out.println(movieNotEmpty);
            Stage stage = (Stage) addMovieButton.getScene().getWindow();
            model.addMovie(title, iMDBRating, movie_FilePath, iMDB_SiteLink, categories);
            stage.close();
        } else
        {
            System.out.println(movieNotEmpty);
        }

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
    private void SendValueIMDB(MouseEvent event)
    {
        double v = imdbSlider.getValue();
        v = Math.round(v * 10) / 10.0;
        IMDBRating.setText(v + "");
        iMDBRating = v;
    }

}
