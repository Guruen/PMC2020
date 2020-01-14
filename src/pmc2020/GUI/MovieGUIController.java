/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pmc2020.BE.Movie;
import pmc2020.DAL.DalException;
import pmc2020.GUI.Model.MovieModel;
import java.time.LocalDate;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pmc2020.BE.Category;

/**
 * FXML Controller class
 *
 * @author Guruerne
 */
public class MovieGUIController implements Initializable
{

    private Movie movieToDelete;
    private Movie movie;
    private Movie movieToOpen;
    private MovieModel model;
    private String TimeAndDate;
    JFrame f;

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
    @FXML
    private Slider maxIMDBSlider;
    @FXML
    private Slider minIMDBSlider;
    @FXML
    private Label searchIMDBRatingMax;
    @FXML
    private Label searchIMDBRatingMin;
    @FXML
    private Label searchPersonalRatingMin;
    @FXML
    private Label searchPersonalRatingMax;
    @FXML
    private Slider minPersonalSlider;
    @FXML
    private Slider maxPersonalSlider;

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
        try
        {
            popUp();
        } catch (DalException ex)
        {
            Logger.getLogger(MovieGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(MovieGUIController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex)
        {
            Logger.getLogger(MovieGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        CategoryCombobox.setItems(model.getAllCategories());
        CategoryCombobox.getItems().add(0, null);
        MovieView.setItems(model.getAllMovies());
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("Private_Rating"));
        imdbratingColumn.setCellValueFactory(new PropertyValueFactory<>("IMDB_Rating"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Categories"));
        movieList();

    }

    /**
     * Listener checking if movie is added or removed from list and refreshes
     * list accordingly
     */
    public void movieList()
    {
        model.getAllMovies().addListener((ListChangeListener<Movie>) c ->
        {
            while (c.next())
            {
                if (c.wasRemoved())
                {
                    MovieView.refresh();
                }

                if (c.wasAdded())
                {
                    MovieView.refresh();
                }

            }

        });

    }

    /**
     * Handles pop ups for checking wether movies are too old and poorly rated
     *
     * @throws DalException
     * @throws IOException
     * @throws ParseException
     */
    private void popUp() throws DalException, IOException, ParseException
    {
        if (model.checkDate().isEmpty() != true)
        {
            JOptionPane.showMessageDialog(f, "The following movie(s) has a "
                    + "personal rating lower than 6 and hasn't been watched in the last 2 years\n"
                    + model.checkDate() + "\n" + "Consider deleting them.\n", "Notice",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }

    /**
     * handles searching using the chosen category/filter(s) and the text in the
     * textfield as query
     *
     * @param event
     * @throws DalException
     */
    @FXML
    private void handleSearchButton(ActionEvent event) throws DalException, IOException
    {
        combinedSearch();
    }

    /**
     * handles opening the add movie window
     *
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
     *
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
     *
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
        } catch (NullPointerException i)
        {
            JOptionPane.showMessageDialog(f, "Please select a movie to edit!", "Notice",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (IOException ex)
        {
            throw ex;
        }

    }

    /**
     * handles opening the edit category window
     *
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
     * handles playing the selected entry on the local media player of the system
     * gets date when user opens via play button and adds to DB as last viewed.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handlePlay(ActionEvent event) throws IOException, DalException, ParseException
    {
        Movie movieToPlay = MovieView.getSelectionModel().getSelectedItem();
        try
        {
            Desktop desktop = Desktop.getDesktop();
            File f = new File(movieToPlay.getFile_location());
            desktop.open(f);

            LocalDate date = LocalDate.now();
            movieToPlay.setLast_Viewed(date + "");
            model.updateMovie(movieToPlay);

        } catch (IllegalArgumentException i)
        {
            String error = i.toString().replaceAll("java.lang.IllegalArgumentException: The file: ", "");
            error = error.replaceAll("doesn't exist.", "");
            JOptionPane.showMessageDialog(f, "Filelocation: " + error + "\nis not set correct!", "Notice",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (NullPointerException i)
        {
            JOptionPane.showMessageDialog(f, "No movie selected!\nor\nNo Filelocation set!", "Notice",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }

    /**
     * Deletes the selected movie object.
     *
     * @param event
     * @throws DalException
     */
    @FXML
    private void deleteMovieButton(ActionEvent event) throws DalException
    {
        try
        {
            movieToDelete = MovieView.getSelectionModel().getSelectedItem();
            model.deleteMovie(movieToDelete);
        } catch (NullPointerException i)
        {
            JOptionPane.showMessageDialog(f, "No movie selected!", "Notice",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * handles opening the given IMDB link, from chosen object on list
     *
     * @param event
     * @throws IOException
     * @throws URISyntaxException
     */
    @FXML
    private void handleOpenLink(ActionEvent event) throws IOException, URISyntaxException
    {
        try
        {
            movieToOpen = MovieView.getSelectionModel().getSelectedItem();
            Desktop.getDesktop().browse(new URL(movieToOpen.getIMDB_Link()).toURI());
        } catch (MalformedURLException i)
        {
            String error = i.toString().replaceAll("java.net.MalformedURLException: no protocol:", "");
            JOptionPane.showMessageDialog(f, "URL: " + error + "\n is not set correct!", "Notice",
                    JOptionPane.PLAIN_MESSAGE);
        } catch (NullPointerException i)
        {
            JOptionPane.showMessageDialog(f, "No movie selected!", "Notice",
                    JOptionPane.PLAIN_MESSAGE);
        }

    }

    /**
     * Shows what Max. IMDB Slider is set to and runs search.
     *
     * @param event
     * @throws DalException
     * @throws IOException
     */
    @FXML
    private void handleMaxIMDBSliderRating(MouseEvent event) throws DalException, IOException
    {
        searchIMDBRatingMax.setText(maxIMDBSlider.getValue() + "");
        combinedSearch();
    }

    /**
     * Shows what Min. IMDB Slider is set to and runs search.
     *
     * @param event
     * @throws DalException
     * @throws IOException
     */
    @FXML
    private void handleMinIMDBSliderRating(MouseEvent event) throws DalException, IOException
    {
        searchIMDBRatingMin.setText(minIMDBSlider.getValue() + "");
        combinedSearch();
    }

    /**
     * Shows what Min. Personal Slider is set to and runs search.
     *
     * @param event
     * @throws DalException
     * @throws IOException
     */
    @FXML
    private void handleMinPersonalSliderRating(MouseEvent event) throws DalException, IOException
    {
        searchPersonalRatingMin.setText(minPersonalSlider.getValue() + "");
        combinedSearch();
    }

    /**
     * Shows what Max. Personal Slider is set to and runs search.
     *
     * @param event
     * @throws DalException
     * @throws IOException
     */
    @FXML
    private void handleMaxPersonalSliderRating(MouseEvent event) throws DalException, IOException
    {
        searchPersonalRatingMax.setText(maxPersonalSlider.getValue() + "");
        combinedSearch();
    }

    /**
     * Searches for entries with a combination of filters
     *
     * @throws DalException
     * @throws IOException
     */
    private void combinedSearch() throws DalException, IOException
    {
        double minIMDBRating = minIMDBSlider.getValue();
        double maxIMDBRating = maxIMDBSlider.getValue();
        double minPersonRating = minPersonalSlider.getValue();
        double maxPersonRating = maxPersonalSlider.getValue();

        int categoryToSearch;
        if (CategoryCombobox.getSelectionModel().getSelectedItem() == null)
        {
            categoryToSearch = 0;
        } else
        {

            Category category = CategoryCombobox.getSelectionModel().getSelectedItem();
            categoryToSearch = category.getCategory_ID();
        }

        String query = searchBar.getText().trim();

        model.movieSearch(query, maxPersonRating, minPersonRating, maxIMDBRating, minIMDBRating, categoryToSearch);

    }

}
