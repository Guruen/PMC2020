/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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

    @FXML
    private TextField EditTitleText;
    @FXML
    private TextField EditImdbRatingtext;
    @FXML
    private TextField EditImdbSiteLinkText;
    @FXML
    private Button EditFilePathButton;
    @FXML
    private Button EditMovieButton;
    @FXML
    private Label EditChosenFilePathtext;
    @FXML
    private ComboBox<?> EditCatChooser;
    @FXML
    private TextField EditUserRatingtext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    }

    @FXML
    private void handleEditChooseFilePath(ActionEvent event)
    {
    }

    @FXML
    private void handleEditMovie(ActionEvent event) throws IOException, DalException
    {
        String title = EditTitleText.getText();
        String imdbLink = EditImdbSiteLinkText.getText();
        boolean notBlank;

        if (title != null && !title.isEmpty())
        {
            title = EditTitleText.getText();
            movie.setTitle(title);
            notBlank = true;
        } else
        {
            notBlank = false;
        }
        if (imdbLink != null && !imdbLink.isEmpty())
        {
            imdbLink = EditImdbSiteLinkText.getText();
            movie.setIMDB_Link(imdbLink);
            notBlank = true;
        } else
        {
            notBlank = false;
        }
        if (notBlank = true)
        {
            Stage stage = (Stage) EditMovieButton.getScene().getWindow();
            model.updateMovie(movie);
            stage.close();
        }
    }

    void setModel(MovieModel model)
    {
        this.model = model;
    }

    void setMovie(Movie movie)
    {
        this.movie = movie;

        EditTitleText.setText(movie.getTitle());
        EditImdbRatingtext.setText(movie.getIMDB_Rating() + "");
        EditUserRatingtext.setText(movie.getPrivate_rating() + "");
        EditImdbSiteLinkText.setText(movie.getIMDB_Link());
    }

}
