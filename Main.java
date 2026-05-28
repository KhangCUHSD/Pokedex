// Core Applications
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

// Visuals & Images
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Layouts
import javafx.scene.layout.VBox; // Vertical (up & down)
import javafx.scene.layout.HBox; // Horizontal (right & left)
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;

public class Main extends Application {

    private final String[] pokemonImages = {
        // Temporary placeholders cause I haven't had time to implement them yet.
        "treecko.png",
        "grovyle.png"
    };

    private final String[] pokemonNames = {
        "treecko",
        "grovyle"
    };

    private int currentIndex = 0;
    private TextField searchBar;
    private ImageView sprite;

    @Override
    public void start(Stage appWindow) {

        // Load initial image
        sprite = new ImageView(new Image(pokemonImages[currentIndex]));
        sprite.setFitWidth(200);
        sprite.setFitHeight(200);
        sprite.setPreserveRatio(true);

        // Search bar
        final double SEARCH_BAR_HEIGHT = 40;
        searchBar = new TextField();
        searchBar.setPromptText("Search Pokémon by name...");
        searchBar.setMaxWidth(300);
        searchBar.setPrefHeight(SEARCH_BAR_HEIGHT);
        searchBar.setOnAction(e -> search());

        // Previous button
        Button prevButton = new Button("← Previous");
        prevButton.setOnAction(e -> {
            currentIndex = (currentIndex - 1 + pokemonImages.length) % pokemonImages.length;
            sprite.setImage(new Image(pokemonImages[currentIndex]));
        });

        // Next button
        Button nextButton = new Button("Next →");
        nextButton.setOnAction(e -> {
            currentIndex = (currentIndex + 1) % pokemonImages.length;
            sprite.setImage(new Image(pokemonImages[currentIndex]));
        });

        // Position the 2 Buttons
        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.getChildren().addAll(prevButton, nextButton);

        // Layout
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(searchBar, sprite, buttonRow);

        Scene startScene = new Scene(layout, 500, 500);
        appWindow.setScene(startScene);
        appWindow.setTitle("Pokédex Generation 3");
        appWindow.show();
    }

    public void search() {
        String keyword = searchBar.getText().trim().toLowerCase();
        if (keyword.isEmpty()) return;

        for (int i = 0; i < pokemonNames.length; i++) {
            if (pokemonNames[i].contains(keyword)) {
                currentIndex = i;
                sprite.setImage(new Image(pokemonImages[currentIndex]));
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
