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
import javafx.scene.control.TextField;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Kim
 */
public class AddCategoryGUIController implements Initializable
{

    @FXML
    private Button addButton;
    @FXML
    private TextField categoryTextFieldText;

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
    private void handleAddButton(ActionEvent event)
    {
    }

    void setModel(MovieModel model)
    {
        this.model = model;
    }

}