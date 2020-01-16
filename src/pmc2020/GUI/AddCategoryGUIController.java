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
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import pmc2020.DAL.DalException;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Guruerne
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
    }
    
    /**
     * Handles adding the given data from the view to the DB
     * This handler also checks if the data can be added to the DB, by checking the window for any missing data
     * @param event
     * @throws DalException 
     */
    @FXML
    private void handleAddButton(ActionEvent event) throws DalException
    {
        String catName = categoryTextFieldText.getText();
        boolean catHasName;
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        
        if (catName != null && !catName.isEmpty()){
            categoryTextFieldText.setText(catName);
            catHasName = true;
        }
        else
        {
            JOptionPane.showMessageDialog(dialog, "Category name can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            categoryTextFieldText.setText("EDIT ME");
            catHasName = false;
        }
        
        if(catHasName == true){
            Stage stage = (Stage) addButton.getScene().getWindow();
            model.createCategory(catName);
            stage.close();
        }
        else
        {

        }
   
    }
    
    /**
     * Sets the model to be the MovieModel class.
     * @param model 
     */

    void setModel(MovieModel model)
    {
        this.model = model;
    }

}
