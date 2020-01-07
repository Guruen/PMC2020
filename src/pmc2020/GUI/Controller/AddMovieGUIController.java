/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI.Controller;

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
public class AddMovieGUIController implements Initializable
{
    @FXML
    private TextField titleText;
    @FXML
    private TextField imdbRatingtext;
    @FXML
    private TextField imdbSiteLinkText;
    @FXML
    private Button chooseFilePathButton;
    @FXML
    private Button addMovieButton;
    @FXML
    private Label chosenFilePathtext;
    
    private MovieModel model;
    
    
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
    }

    @FXML
    private void handleAddMovie(ActionEvent event)
    {
    }

    void setModel(MovieModel model)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
