/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pmc2020.BE.Movie;
import pmc2020.DAL.DalException;
import pmc2020.GUI.Model.MovieModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import pmc2020.BE.Category;

/**
 * FXML Controller class
 *
 * @author Guruerne
 */
public class MovieGUIController implements Initializable
{

    private Movie movieToDelete;
    private Movie movieToOpen;
    private MovieModel model;
    private String TimeAndDate;
    
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
    @FXML
    private TableView<Movie> MovieView;
    @FXML
    private TableColumn<Movie, String> titleColumn;
    @FXML
    private TableColumn<Movie, Double> ratingColumn;
    @FXML
    private TableColumn<Movie, Double> imdbratingColumn;
    @FXML
    private TableColumn<Movie, String> categoryColumn;
    @FXML
    private ComboBox<Category> CategoryCombobox;

    public MovieGUIController() throws IOException, DalException
    {
        try
        {
            model = new MovieModel();
        } catch (IOException | DalException ex)
        {
            Logger.getLogger(MovieGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        CategoryCombobox.setItems(model.getAllCategories());
        MovieView.setItems(model.getAllMovies());
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("Private_Rating"));
        imdbratingColumn.setCellValueFactory(new PropertyValueFactory<>("IMDB_Rating"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        

        // Add category column
        movieList();
    }

    public void movieList()
    {
        model.getAllMovies().addListener((ListChangeListener<Movie>) c ->
        {
            while (c.next())
            {
                if (c.wasRemoved())
                {
                    System.out.println("removed!");
                    MovieView.refresh();
                }

                if (c.wasAdded())
                {
                    System.out.println("added!");
                    MovieView.refresh();
                }

            }

        });

    }

    /**
     * handles searching in realtime using the chosen category/filter(s) and the text in the textfield as query
     * @param event
     * @throws DalException 
     */
    
    @FXML
    private void handleSearch(KeyEvent event) throws DalException
    {
        String query = searchBar.getText().trim();
        model.search(query);
    }
    
    /**
     * handles opening the add movie window
     * @param event
     * @throws IOException 
     */

    @FXML
    private void handleAddMovie(ActionEvent event) throws IOException
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/AddMovieGUI.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            AddMovieGUIController c = fxmlLoader.getController();
            c.setModel(model);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setAlwaysOnTop(true);
            stage.setTitle("Add Movie");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex)
        {
            throw ex;
        }
    }
    
    /**
     * handles opening the add category window
     * @param event
     * @throws IOException 
     */

    @FXML
    private void handleAddCategory(ActionEvent event) throws IOException
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/AddCategoryGUI.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            AddCategoryGUIController c = fxmlLoader.getController();
            c.setModel(model);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setAlwaysOnTop(true);
            stage.setTitle("Add Category");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex)
        {
            throw ex;
        }
    }
    
    /**
     * handles opening the edit movie window
     * @param event
     * @throws IOException 
     */

    @FXML
    private void handleEditMovie(ActionEvent event) throws IOException
    {
        Movie movieToEdit = MovieView.getSelectionModel().getSelectedItem();
        try
        {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/EditMovieGUI.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            EditMovieGUIController c = fxmlLoader.getController();
            c.setModel(model);
            c.setMovie(movieToEdit);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setAlwaysOnTop(true);
            stage.setTitle("Edit Movie");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex)
        {
            throw ex;
        }
    }
    
    /**
     * handles opening the edit movie window
     * @param event
     * @throws IOException 
     */

    @FXML
    private void handleEditCategory(ActionEvent event) throws IOException
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View/EditCategoryGUI.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            EditCategoryGUIController c = fxmlLoader.getController();
            c.setModel(model);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setAlwaysOnTop(true);
            stage.setTitle("Edit Category");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex)
        {
            throw ex;
        }
    }
    
    /**
     * handles playing the selected entry on the local media player of the user
     * gets time and date, to check if the entry has not been watched for two years, and then prompts the user to delete the entry
     * @param event
     * @throws IOException 
     */

    @FXML
    private void handlePlay(ActionEvent event) throws IOException
    {
        Movie movieToEdit = MovieView.getSelectionModel().getSelectedItem();
        Desktop desktop = Desktop.getDesktop();
        File f = new File(movieToEdit.getFile_location());
        desktop.open(f);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(formatter.format(date));
        TimeAndDate = formatter.format(date);

        //write date to DB
        //find expiry date of last watched (two years after last play)
        //check date on startup, to control expiry
        //prompt user to delete expired entries
    }
    
    /**
     * handles deleting the selected entry from the view and the DB when the button is pushed
     * @param event
     * @throws DalException 
     */

    @FXML
    private void deleteMovieButton(ActionEvent event) throws DalException
    {
        movieToDelete = MovieView.getSelectionModel().getSelectedItem();
        System.out.println(movieToDelete);
        model.deleteMovie(movieToDelete);
    }
    
    /**
     * handles searching within the chosen category / filter(s)
     * @param event 
     */

    @FXML
    private void handleCategorySearch(ActionEvent event)
    {
        //CategoryCombobox.getSelectionModel().getSelectedItem());

        Category category = CategoryCombobox.getSelectionModel().getSelectedItem();
        String categoryToSearch = category.getCategory();
        // model.searchCategory(categoryToSearch);
    }

    @FXML
    private void handleOpenLink(ActionEvent event) throws IOException, URISyntaxException
    {
        movieToOpen = MovieView.getSelectionModel().getSelectedItem();
        Desktop.getDesktop().browse(new URL(movieToOpen.getIMDB_Link()).toURI());
    }

}
