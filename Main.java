// Core Applications
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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
import javafx.geometry.Insets;

public class Main extends Application {

    @Override
    public void start(Stage appWindow) { 
        // Button
        Button testButton = new Button("Test Me");
        
        // Layout
        StackPane pane = new StackPane(); 
        pane.getChildren().add(testButton);
        
        // Positioning
        Scene mainScene = new Scene(pane, 400, 300);
        
        appWindow.setTitle("JavaFX Button Window");
        appWindow.setScene(mainScene);
        appWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
