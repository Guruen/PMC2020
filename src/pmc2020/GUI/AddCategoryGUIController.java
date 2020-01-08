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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
            System.out.println(catHasName);
        }
        
        if(catHasName = true){
            //closes window
            //adds category to list  
        }
        else
        {
            System.out.println(catHasName);
        }
   
        
    }

    void setModel(MovieModel model)
    {
        this.model = model;
    }

}
