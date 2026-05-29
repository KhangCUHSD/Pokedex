// Asked for suggestions of Applications for JavaFX from Claude and Gemini 
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

// Lists
import java.util.List;

public class Main extends Application {

    // Data
    private List<Pokemon> pokemon;
    private int currentIndex = 0;


    // User Interface / UI
    private TextField searchBar;
    private ImageView sprite;
    private Label name;
    private Label type;
    private Label stats;

    @Override
    public void start(Stage appWindow) {

        // Load all Pokemon from the CSV 
        pokemon = PokemonLoader.loadFromCSV("Pokedex.csv");

        if (pokemon.isEmpty()) {
            System.err.println("Make sure that Pokedex.csv is correct");
            return;
        }

        // Sprites (mm sprite)
        sprite = new ImageView();
        sprite.setFitWidth(200);
        sprite.setFitHeight(200);
        sprite.setPreserveRatio(true);

        // Labels
        name = new Label();
        name.setFont(new Font("Times New Roman", 20));

        type = new Label();
        type.setFont(new Font("Times New Roman", 15));

        stats = new Label();
        stats.setFont(new Font("Times New Roman", 10));

        updateDisplay();
        
        // Took inspiration from Hasib Altaf Pokedex Search Function
        // Search bar
        final double SEARCH_BAR_HEIGHT = 40;
        searchBar = new TextField();
        searchBar.setPromptText("Search Pokémon by name or Dex number...");
        searchBar.setMaxWidth(300);
        searchBar.setPrefHeight(SEARCH_BAR_HEIGHT);
        searchBar.setOnAction(e -> search());

        // Previous button
        Button previous = new Button("← Previous");
        previous.setOnAction(e -> {
            currentIndex = (currentIndex - 1 + pokemon.size()) % pokemon.size();
            updateDisplay();
        });

        // Next button
        Button next = new Button("Next →");
        next.setOnAction(e -> {
            currentIndex = (currentIndex + 1) % pokemon.size();
            updateDisplay();
        });

        // Position the 2 Buttons
        HBox buttonRow = new HBox(10);
        buttonRow.setAlignment(Pos.CENTER);
        buttonRow.getChildren().addAll(previous, next);

        // Layout
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(searchBar, sprite, name, type, stats, buttonRow);

        Scene startScene = new Scene(layout, 500, 600);
        appWindow.setScene(startScene);
        appWindow.setTitle("Pokédex Generation 3");
        appWindow.show();
    }

    private void updateDisplay() {
        Pokemon p = pokemon.get(currentIndex);
        
        // I have no fucking idea how to get images from a folder pls help
        // Load initial image
        String imagePath = p.getName().toLowerCase() + ".png";
        try {
            sprite.setImage(new Image(imagePath));
        } catch (Exception e) {
            sprite.setImage(null);
        }

        name.setText("#" + p.getID() + "  " + p.getName());

        // Hide the Second Type if they don't have a Secondary Typing
        String typeText = p.getType1();
        if (!p.getType2().isEmpty()) typeText += " / " + p.getType2();
        type.setText("Type: " + typeText);

        stats.setText(
            "HP: "     + p.getHealth()  + "   |   " +
            "ATK: "    + p.getAttack()  + "   |   " +
            "DEF: "    + p.getDefense() + "\n" +
            "Sp.ATK: " + p.getSpAtk()  + "   |   " +
            "Sp.DEF: " + p.getSpDef()  + "   |   " +
            "SPD: "    + p.getSpeed()
        );
    }
    
    // Took inspiration from Hasib Altaf Pokedex Search Function pt 2
    // Searches Name or exact Pokedex number
    public void search() {
        String keyword = searchBar.getText().trim().toLowerCase();
        if (keyword.isEmpty()) return;

        for (int i = 0; i < pokemon.size(); i++) {
            Pokemon p = pokemon.get(i);
            
            // Check if name matches or is close
            boolean nameMatch = p.getName().toLowerCase().contains(keyword);
            
            // Check if number matches or is close
            boolean numberMatch = String.valueOf(p.getID()).equals(keyword);

            if (nameMatch || numberMatch) {
                currentIndex = i;
                updateDisplay();
                break;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
