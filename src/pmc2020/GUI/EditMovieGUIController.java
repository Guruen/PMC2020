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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Zanaxdk
 */
public class EditMovieGUIController implements Initializable
{

    private MovieModel model;
    
    @FXML
    private TextField editTitleText;
    @FXML
    private Button editChooseFilePathButton;
    @FXML
    private Label editChosenFilePathtext;
    @FXML
    private TextField editImdbSiteLinkText;
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
        // TODO
    }    


    void setModel(MovieModel model)
    {
        this.model = model;
    }


    @FXML
    private void SendValueUser(MouseEvent event)
    {
    }

    @FXML
    private void SendValueIMDB(MouseEvent event)
    {
    }

    @FXML
    private void handleEditFilePath(ActionEvent event)
    {
    }

    @FXML
    private void handleEditMovie(ActionEvent event)
    {
    }
    
}
