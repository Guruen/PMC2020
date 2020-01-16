/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import pmc2020.BE.Category;
import pmc2020.DAL.DalException;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Guruerne
 */
public class EditCategoryGUIController implements Initializable
{

    private MovieModel model;

    @FXML
    private Button editButton;
    @FXML
    private TextField editCategoryTextFieldText;
    @FXML
    private ComboBox<Category> CatChooserToEdit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            model = new MovieModel();
        } catch (IOException | DalException ex)
        {
            Logger.getLogger(MovieGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        CatChooserToEdit.setItems(model.getAllCategories());
    }

    /**
     * Handles editing a category by getting the name to edit, and updating it
     * to the new name in the DB also checks for blank information, to prevent
     * blank entries lets the window close if there is data present in all
     * needed places (true condition)
     * @param event
     */
    @FXML
    private void handleEditCategoryButton(ActionEvent event) throws DalException
    {
        String catName = editCategoryTextFieldText.getText();
        boolean catHasAName;
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);

        if (catName != null && !catName.isEmpty())
        {
            editCategoryTextFieldText.setText(catName);
            catHasAName = true;
        } else
        {
            JOptionPane.showMessageDialog(dialog, "Category name can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            editCategoryTextFieldText.setText("EDIT ME");
            catHasAName = false;
        }

        if (catHasAName = true)
        {
            Category category = CatChooserToEdit.getSelectionModel().getSelectedItem();

            category.setCategory(editCategoryTextFieldText.getText());

            model.editCategory(category);

            Stage stage = (Stage) editButton.getScene().getWindow();
            stage.close();
        } else
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

    /**
     * Handles categories within a combo box
     * @param event 
     */
    
    @FXML
    private void handleCategoryComboBox(ActionEvent event)
    {
        editCategoryTextFieldText.setText(CatChooserToEdit.getSelectionModel().getSelectedItem().getCategory());
    }
    
    /**
     * Handles deleting the selected category from the Category chooser dropdown.
     * @param event
     * @throws DalException 
     */

    @FXML
    private void handleDelete(ActionEvent event) throws DalException
    {
        Category category = CatChooserToEdit.getSelectionModel().getSelectedItem();
        
        model.deleteCategory(category);
    }


}
