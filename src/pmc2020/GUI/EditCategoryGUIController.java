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
 * @author Zanaxdk
 */
public class EditCategoryGUIController implements Initializable
{

    private MovieModel model;
    
    @FXML
    private Button editButton;
    @FXML
    private TextField editCategoryTextFieldText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void handleEditCategoryButton(ActionEvent event)
    {
        String catName = editCategoryTextFieldText.getText();
        boolean catHasAName;
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        
        if (catName != null && !catName.isEmpty()){
            editCategoryTextFieldText.setText(catName);
            catHasAName = true;
        }
        else
        {
            JOptionPane.showMessageDialog(dialog, "Category name can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            editCategoryTextFieldText.setText("EDIT ME");
            catHasAName = false;
            System.out.println(catHasAName);
        }
        
        if(catHasAName = true){
            //closes window
            //Edits chosen category on list  
        }
        else
        {
            System.out.println(catHasAName);
        }
        
    }

    void setModel(MovieModel model)
    {
        this.model = model;
    }
    
}
