/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pmc2020.DAL.DalException;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author CSnit
 */
public class MovieGUIController implements Initializable
{
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
    
    private MovieModel model;
    
    
    public MovieGUIController()
    {
        model = new MovieModel();
    }
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
    private void handleAddMovie(ActionEvent event) throws IOException
    {
                try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pmc2020/GUI/View/AddMovieGUI.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            AddMovieGUIController c = fxmlLoader.getController();
            c.setModel(model);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setAlwaysOnTop(true);
            stage.setTitle("Add new Playlist");
            stage.setResizable(false);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex)
        {
            throw ex;
        }
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
    private void handlePlay(ActionEvent event)
    {
    }
    
}
