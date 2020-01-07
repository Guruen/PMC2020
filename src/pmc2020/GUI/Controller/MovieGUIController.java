/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author CSnit
 */
public class MovieGUIController implements Initializable
{
    private MovieModel model;
    @FXML
    private TextField searchBar;
    @FXML
    private Button addMovieButton;
    @FXML
    private Button addCategoryButton;
    @FXML
    private Button editMovieButton;
    @FXML
    private Button editCategoryButton;
    @FXML
    private Button playButton;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void handleSearch(KeyEvent event)
    {
    }

    @FXML
    private void handleAddMovie(ActionEvent event)
    {
    }

    @FXML
    private void handleAddCategory(ActionEvent event)
    {
    }

    @FXML
    private void handleEditMovie(ActionEvent event)
    {
    }

    @FXML
    private void handleEditCategory(ActionEvent event)
    {
    }

    @FXML
    private void handlePlay(ActionEvent event) throws IOException
    {
        Runtime.getRuntime().exec("wmplayer");
    }
    
}
