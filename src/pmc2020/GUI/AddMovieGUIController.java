/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmc2020.GUI;

import java.awt.FileDialog;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.xml.stream.events.Characters;
import pmc2020.GUI.Model.MovieModel;

/**
 * FXML Controller class
 *
 * @author Kim
 */
public class AddMovieGUIController implements Initializable
{
    private String filename;
    private String directory;

    @FXML
    private TextField titleText;
    @FXML
    private TextField imdbSiteLinkText;
    @FXML
    private Button chooseFilePathButton;
    @FXML
    private Button addMovieButton;
    @FXML
    private Label chosenFilePathtext;
    private TextField iMDBRating;

    public MovieModel model;
    @FXML
    private ComboBox<?> CatChooser;
    @FXML
    private Slider userSlider;
    @FXML
    private Label IMDBRating;
    @FXML
    private Label UserRating;
    @FXML
    private Slider imdbSlider;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void handleChooseFilePath(ActionEvent event)
    {
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        FileDialog fd = new java.awt.FileDialog((java.awt.Frame) null);
        fd.setDirectory("C:\\");
        fd.setFile("*.mpeg4;*.mp4");
        fd.setTitle("Add a mpeg4 or mp4 file");
        fd.setVisible(true);
        fd.setFocusable(true);
        fd.setAutoRequestFocus(true);
        filename = fd.getFile();
        directory = fd.getDirectory();
        if (filename == null)
        {
            JOptionPane.showMessageDialog(dialog, "Adding a movie has been cancelled. Try again!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else
        {
            chosenFilePathtext.setText(directory + "\\" + filename);
            System.out.println(filename);
        }
    }

    @FXML
    private void handleAddMovie(ActionEvent event)
    {
        String title = titleText.getText();
        String iMDB_SiteLink = imdbSiteLinkText.getText();
        String movie_FilePath = chosenFilePathtext.getText();
        double iMDBRating = Double.parseDouble(IMDBRating.getText());
        double userRating = Double.parseDouble(UserRating.getText());
        boolean movieNotEmpty;
        final JDialog dialog = new JDialog();
        dialog.setAlwaysOnTop(true);
        
        if (title != null && !title.isEmpty())
        {
            title = titleText.getText();
            titleText.setText(title);
            movieNotEmpty = true;
            System.out.println(movieNotEmpty);
        }
        else
        {
            JOptionPane.showMessageDialog(dialog, "Movie title can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            titleText.setText("EDIT ME");
            movieNotEmpty = false;
            System.out.println(movieNotEmpty);

        }
        
        if (iMDB_SiteLink != null && !iMDB_SiteLink.isEmpty())
        {
            iMDB_SiteLink = imdbSiteLinkText.getText();
            imdbSiteLinkText.setText(iMDB_SiteLink);
            movieNotEmpty = true;
            System.out.println(movieNotEmpty);
        }
        else
        {
            JOptionPane.showMessageDialog(dialog, "IMDB link can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            imdbSiteLinkText.setText("https://www.imdb.com/");
            movieNotEmpty = false;
            System.out.println(movieNotEmpty);

        }
        
        if (movie_FilePath != "Chosen File Path" && !movie_FilePath.isEmpty())
        {
            movie_FilePath = chosenFilePathtext.getText();
            chosenFilePathtext.setText(movie_FilePath);
            movieNotEmpty = true;
            System.out.println(movieNotEmpty);
        }
        else
        {
            JOptionPane.showMessageDialog(dialog, "Movie file path can not be blank!", "ERROR", JOptionPane.ERROR_MESSAGE);
            titleText.setText("Chosen File Path");
            movieNotEmpty = false;
            System.out.println(movieNotEmpty);

        }
        
        if (movieNotEmpty == true)
        {
            System.out.println(movieNotEmpty);
        } else
        {
            System.out.println(movieNotEmpty);
        }
        
    }

    void setModel(MovieModel model)
    {
        this.model = model;
    }

    @FXML
    private void SendValueIMDB(MouseEvent event)
    {
        double v = imdbSlider.getValue();
        String formatted = String.format("%.1f", v);
        IMDBRating.setText(formatted + "");
    }

    @FXML
    private void SendValueUser(MouseEvent event)
    {
        double v = userSlider.getValue();
        String formatted = String.format("%.1f", v);
        UserRating.setText(formatted + "");
    }

}
