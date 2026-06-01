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
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

// Layouts
import javafx.scene.layout.VBox; // Vertical (up & down)
import javafx.scene.layout.HBox; // Horizontal (right & left)
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

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
    private Label bstLabel;

    @Override
    public void start(Stage appWindow) {

        // Load all Pokemon from the CSV 
        pokemon = PokemonLoader.loadFromCSV("Pokedex.csv");

        if (pokemon.isEmpty()) {
            System.err.println("Make sure that Pokedex.csv is correct");
            return;
        }

        // Big title at the top of the Pokedex
        Label title = new Label("Pokédex of Box-Art\nLegendaries & Mythics");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 26));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setAlignment(Pos.CENTER);
        title.setWrapText(true);

        // Sprites (mm sprite)
        sprite = new ImageView();
        sprite.setFitWidth(200);
        sprite.setFitHeight(200);
        sprite.setPreserveRatio(true);

        // Centre the sprite horizontally inside the VBox
        VBox spriteBox = new VBox(sprite);
        spriteBox.setAlignment(Pos.CENTER);

        // Labels
        name = new Label();
        name.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        type = new Label();
        type.setFont(new Font("Times New Roman", 15));

        stats = new Label();
        stats.setFont(new Font("Times New Roman", 12)); 

        bstLabel = new Label();
        bstLabel.setFont(new Font("Times New Roman", 20));

        updateDisplay();

        // Took inspiration from Hasib Altaf Pokedex Search Function
        // Search bar
        final double SEARCH_BAR_HEIGHT = 50;
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

        // Added BST to the far right
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox statsRow = new HBox();
        statsRow.setAlignment(Pos.CENTER_LEFT);
        statsRow.setMaxWidth(400);
        statsRow.getChildren().addAll(stats, spacer, bstLabel);

        // Layout
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(title, searchBar, spriteBox, name, type, statsRow, buttonRow);

        Scene startScene = new Scene(layout, 500, 680);
        appWindow.setScene(startScene);
        appWindow.setTitle("Pokédex of Box-Art Legendaries");
        appWindow.show();
    }

    private void updateDisplay() {
        Pokemon p = pokemon.get(currentIndex);

        // Load image 
        String imagePath = switch (p.getID()) {
            case 249  -> "249_lugia.png";
            case 250  -> "250_ho-oh.png";
            case 251  -> "251_celebi.png";
            case 382  -> "382_kyogre.png";
            case 383  -> "383_groundon.png";
            case 384  -> "384_rayquaza.png";
            case 483  -> "483_dialga.png";
            case 484  -> "484_palkia.png";
            case 487  -> "487_giratina.png";
            case 643  -> "643_reshiram.png";
            case 644  -> "644_zekrom.png";
            case 716  -> "716_xerneas.png";
            case 717  -> "717_yveltal.png";
            case 791  -> "791_solgaleo.png";
            case 792  -> "792_lunala.png";
            case 888  -> "888_zacian.png";
            case 889  -> "889_zamazenta.png";
            case 1007 -> "1007_koraidon.png";
            case 1008 -> "1008_miraidon.png";
            default   -> null;
        };

        // Display the sprite if an image path was matched (genuinely didn't know what the error was)
        if (imagePath != null) {
            try {
                Image img = new Image("file:" + imagePath);
                sprite.setImage(img);
            } catch (Exception e) {
                System.err.println("Could not load image: " + imagePath);
                sprite.setImage(null);
            }
        } else {
            sprite.setImage(null);
        }

        // Display the Pokemon's name
        name.setText(p.getName());

        // Hide the Second Type if they don't have a Secondary Typing
        String typeText = p.getType1();
        if (!p.getType2().isEmpty()) typeText += " / " + p.getType2();
        type.setText("Type: " + typeText);

        // Base Stat Total is the sum of all 6 stats
        int bst = p.getHealth() + p.getAttack() + p.getDefense()
                + p.getSpAtk() + p.getSpDef() + p.getSpeed();

        stats.setText(String.format(
            "HP:       %-3d   |  ATK:      %-3d  |  DEF:     %-3d%n" +
            "Sp.ATK: %-3d  |  Sp.DEF: %-3d  |  Speed:  %-3d",
            p.getHealth(),  p.getAttack(),  p.getDefense(),
            p.getSpAtk(),   p.getSpDef(),   p.getSpeed()
        ));

        // Base Stat Total label 
        bstLabel.setText("BST: " + bst);
    }

    // Took inspiration from Hasib Altaf Pokedex Search Function pt 2
    // Searches Name or exact Pokedex number'
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
