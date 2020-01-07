/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Kim
 */
public class EditMovieGUIController implements Initializable
{

    private MovieModel model;

    @FXML
    private TextField editTitleText;
    @FXML
    private TextField editIMDBRatingtext;
    @FXML
    private TextField editIMDBSiteLinkText;
    @FXML
    private Button editChooseFilePathButton;
    @FXML
    private Button editMovieButton;
    @FXML
    private Label editChosenFilePathtext;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleEditChooseFilePath(ActionEvent event)
    {
    }

    @FXML
    private void handleEditMovie(ActionEvent event)
    {
    }

    void setModel(MovieModel model)
    {
        this.model = model;
    }

}
