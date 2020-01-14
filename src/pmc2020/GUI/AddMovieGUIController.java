/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.awt.FileDialog;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
     * Adds a new controller to the movie GUI, by making a new instance of the
     * class
     *
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
     * handles opening the filedialog to allow the user to open / choose a mp4
     * or mpeg4 file uses filename and directory to see where the file is to add
     * this data to the DB too also checks the filename to see if it is the
     * correct filetype(s)
     *
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
        }
    }

    /**
     * Handles adding the movie, using the supplied data from the view the
     * handler also checks if there is any data present, to prevent blank
     * entries to the DB The user gets an error message if any of the info is
     * blank, and the blank info is then replaced with placeholders lets the
     * window close if the conditions are true
     *
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
        List<Movie> allMovies = model.getAllMovies();

        boolean movieNotEmpty;
        boolean iMDB_LinkNotEmpty;
        boolean filepathNotEmpty;
        boolean movieAlreadyExist = true;

        for (Movie allMovy : allMovies)
        {
            if (title.compareTo(allMovy.getTitle()) == 0)
            {
                JOptionPane.showMessageDialog(dialog, "Movie title is already added", "ERROR", JOptionPane.ERROR_MESSAGE);
                movieAlreadyExist = false;
                break;
            }else
            {
                movieAlreadyExist = true;
            }
        }

        if (title != null && !title.isEmpty())
        {
            title = titleText.getText();

            titleText.setText(title);
            movieNotEmpty = true;
        } else
        {
            JOptionPane.showMessageDialog(dialog, "Movie title can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            titleText.setText("EDIT ME");
            movieNotEmpty = false;

        }

        if (iMDB_SiteLink != null && !iMDB_SiteLink.isEmpty())
        {
            iMDB_SiteLink = imdbSiteLinkText.getText();
            imdbSiteLinkText.setText(iMDB_SiteLink);
            iMDB_LinkNotEmpty = true;
        } else
        {
            JOptionPane.showMessageDialog(dialog, "IMDB link can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            imdbSiteLinkText.setText("https://www.imdb.com/");
            iMDB_LinkNotEmpty = false;

        }

        if (movie_FilePath != "Chosen File Path" && !movie_FilePath.isEmpty())
        {
            movie_FilePath = chosenFilePathtext.getText();
            chosenFilePathtext.setText(movie_FilePath);
            filepathNotEmpty = true;
        } else
        {
            JOptionPane.showMessageDialog(dialog, "Movie file path can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            chosenFilePathtext.setText("Chosen File Path");
            filepathNotEmpty = false;

        }

        if (movieNotEmpty == true && iMDB_LinkNotEmpty == true && filepathNotEmpty == true && movieAlreadyExist == true)
        {
            Stage stage = (Stage) addMovieButton.getScene().getWindow();
            model.addMovie(title, iMDBRating, movie_FilePath, iMDB_SiteLink, categories);
            stage.close();
        } else
        {
            
        }

    }

    void setModel(MovieModel model)
    {
        this.model = model;
    }

    /**
     * gets the value from the slider on the view and sets this value as text on
     * a label, and displays live changes
     *
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
