package RPG;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import static java.lang.Integer.parseInt;

public class Window extends Application {
    private Game game;
    private Scene scene;
    private TextArea textOutput;
    private TextField textInput;

    // these are all Objects we're gonna need to access from outside Window.java
    private Label enemyName;
    private Image enemyImage;
    private HBox enemy;
    private ImageView enemyView;
    private Label enemySpecialLabel;
    private Label enemySpecialField;

    private Image selfImage;
    private ImageView selfView;
    private Label selfName;
    private Label selfHealthField;
    private Label selfManaField;


    // we want certain elements to be accessible to methods other than our main method
    // this would include HBox like stats where depending on the enemy we're facing we would change the
    // picture and the stats accordingly. To do this we make our own methods that edit these private elements

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        // setting window properties

        window.setTitle("Teymur's Blessing");
        window.getIcons().add(new Image("RPG/Media/thumbnail.png")); // this is a placeholder, it will change later
        window.setResizable(false);

        // for some reason text displays really weirdly without lcdtext turned off
        System.setProperty("prism.lcdtext", "false");
        game = new Game(this);
        // ------------ window generals --------------
        TextField textInput = new TextField();
        textOutput = new TextArea();
        textOutput.setEditable(false);
        textOutput.setDisable(true);
        textOutput.setStyle(
            "-fx-text-fill: navy; " +
            "-fx-background-color: darkslateblue;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 16;" +
            "-fx-border-color: black;" +
            "-fx-font: 13 Georgia;" +
            "-fx-text-overrun: hidden "
        );
        textOutput.setPrefHeight(350);
        textOutput.setWrapText(true);

        // ------------ window generals --------------

        // ------------ our stats ----------------
        Label selfHealthLabel = new Label();
        selfHealthLabel.setPrefHeight(25);
        selfHealthLabel.setText("Health: ");
        //selfHealthLabel.setStyle("-fx-font: 22 Arial;");

        selfHealthField = new Label();
        selfHealthField.setText("1");
        selfHealthField.setTextFill(Color.GREEN);
        selfHealthField.setStyle("-fx-font: 22 Arial;");


        Label selfManaLabel = new Label();
        selfManaLabel.setPrefHeight(25);
        selfManaLabel.setText("Mana:  ");
        //selfManaLabel.setStyle("-fx-font: 22 Arial");

        selfManaField = new Label();
        selfManaField.setText("100");
        selfManaField.setTextFill(Color.BLUE);
        selfManaField.setStyle("-fx-font: 22 Arial");

        HBox selfHealth = new HBox();
        selfHealth.getChildren().addAll(selfHealthLabel, selfHealthField);
        selfHealth.setAlignment(Pos.CENTER_LEFT);
        HBox selfMana = new HBox();
        selfMana.getChildren().addAll(selfManaLabel, selfManaField);

        selfName = new Label();
        selfName.setText("Teymur");


        VBox selfStats = new VBox();
        selfStats.getChildren().addAll(selfName, selfHealth, selfMana);

        selfView = new ImageView();
        setSelfIcon("RPG/Media/thumbnail.png");
        // ---------our stats-------------

        /*
        Right between the two stats I'm planning on putting a small dropdown window thing that displays our stats
        something like different tabs showing that would pull up stats, history and maybe inventory if we ever get
        that far. If we do get that far it would either have to be a very small inventory that would only take
        enough space for it to sit between two portraits without looking awkward.
        */


        // ---------enemy stats---------------
        // our initialized enemy portrait will be empty and it will be populated later
        // using the methods in Window called by Game
        Label enemyHealthLabel = new Label();
        enemyHealthLabel.setPrefHeight(25);
        enemyHealthLabel.setText("Health:  ");
        enemyHealthLabel.setAlignment(Pos.CENTER_LEFT);

        Label enemyHealthField = new Label();
        enemyHealthField.setText("100");
        enemyHealthField.setTextFill(Color.GREEN);
        enemyHealthField.setStyle("-fx-font: 22 Arial;");


        enemySpecialLabel = new Label();
        enemySpecialLabel.setText("Special: ");
        enemySpecialLabel.setAlignment(Pos.CENTER_LEFT);

        enemySpecialField = new Label();
        enemySpecialField.setText("100");
        enemySpecialField.setTextFill(Color.TURQUOISE);
        enemySpecialField.setStyle("-fx-font: 22 Arial;");
        //enemySpecialField.setVisible(false);

        // as default enemies will not have any special modifiers of any kind. We can choose to add some in this
        // thing if we want to though

        HBox enemyHealth = new HBox();
        enemyHealth.getChildren().addAll(enemyHealthLabel, enemyHealthField);

        HBox enemySpecial = new HBox();
        enemySpecial.getChildren().addAll(enemySpecialLabel, enemySpecialField);

        // TODO: Add the special bar here upon progressing into the story or whatever

        enemyName = new Label();
        setEnemyName("Worm");
        VBox enemyStats = new VBox();
        enemyStats.getChildren().addAll(enemyName, enemyHealth, enemySpecial);
        // TODO: display enemy levels properly with
        enemyView = new ImageView();
        setEnemyIcon("RPG/Media/snake.png");
        StackPane enemyPane = new StackPane();
        enemyPane.getChildren().addAll(enemyStats);

        //enemyPane

        // ---------enemy stats---------------

        // ------- Right-side Stats---------
        VBox rightStats = new VBox();
        rightStats.setPrefWidth(100);
        rightStats.setStyle("-fx-border-color: BLACK; -fx-border-radius: 10px");


        // ----------Containers----------------
        HBox self = new HBox();
        self.getChildren().addAll(selfView, selfStats);
        self.setSpacing(10);
        self.setLayoutX(600);
        self.setPrefWidth(200);
        self.setAlignment(Pos.TOP_LEFT);
        self.setStyle("-fx-background-color: cornsilk; -fx-padding: 10px");

        HBox enemy = new HBox();
        enemy.getChildren().addAll(enemyView, enemyPane);
        enemy.setSpacing(10);
        enemy.setAlignment(Pos.TOP_RIGHT);
        enemy.setStyle("-fx-background-color: cornsilk; -fx-padding: 10px");

        HBox allStats = new HBox();
        allStats.getChildren().addAll(self,enemy);
        allStats.setSpacing(150);

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20,20, 20, 20));
        layout.getChildren().addAll(allStats, textOutput, textInput);
        layout.setFillWidth(false);
        layout.setPrefWidth(200);

        layout.setAlignment(Pos.BOTTOM_LEFT);

        // event handlers
        textInput.setOnAction((ActionEvent event) -> { // when enter is pressed on textInput
            // text clears no matter what
            String text = textInput.getText();

            if (text.trim().isEmpty()) {
                // if the text entered is only whitespace, don't do anything;
                textInput.setText("");
                return;
            }
            //test

            // this is where the input gets passed to the handler in Game.java
            // we don't want to do anything with it in Window because there no instances of Enemy or Player
            game.handler(text);

            System.out.println(text);
            textOutput.appendText(text);
            textOutput.appendText("\n");
            textInput.setText("");
        });

        scene = new Scene(layout, 750, 500);
        window.setScene(scene);
        window.show();
        // this lets us control the flow of the game through another
        switchToMain();
    }
    // all these specific methods are here to make references, they will eventually just be condensed
    // to a single (updateEnemy) method inside the constructor of an Enemy object;

    public void setSelfHealth(Integer value) {
        Integer currentHealth = Integer.parseInt(selfHealthField.getText());
        this.selfHealthField.setText(Integer.toString(currentHealth + value));
    }

    public void setSelfMana(Integer value){
        Integer currentMana = Integer.parseInt(selfManaField.getText());
        this.selfHealthField.setText(Integer.toString(currentMana + value));
    }

    public void setSelfIcon(String path){
        Image newImage = new Image(path);
        this.selfView.setImage(newImage);
        this.selfView.setFitHeight(70);
        this.selfView.setPreserveRatio(true);
    }

    public void setEnemyIcon(String path){

        Image newImage = new Image(path);
        System.out.println(newImage);
        enemyView.setImage(newImage);
        //enemyView.
        enemyView.setFitHeight(70);
        enemyView.setPreserveRatio(true);
    }

    public void print(String text) {
        textOutput.appendText(text);
        textOutput.appendText("\n");
    }

    public void setEnemyName(String name){
        enemyName.setText(name);
    }
    public void setEnemyVisible(Boolean toggle){
        enemy.setVisible(false);
    }
    
    public void enemySpecialVisible(Boolean toggle){
        if (toggle){
            enemySpecialLabel.setVisible(true);
            enemySpecialField.setVisible(true);
        }
        else{
            enemySpecialLabel.setVisible(false);
            enemySpecialField.setVisible(false);
        }
    }


    // only runs once to switch out of Window.java to identify when we're in Main.java and done
    // initializing the window. Keep this method at the bottom cuz why not
    private void switchToMain(){
        Main.game(this, game);
    }

}
