package cs1302.api;

import java.util.List;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*HTTP imports*/
import java.net.http.HttpClient;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
/*javafx application/scene imports*/
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.Runnable;
/*net imports*/
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.TilePane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
/*control imports */
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.geometry.Orientation;
/*event imports,exceptions*/
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import javafx.util.Duration;
/*fx design imports*/
import javafx.scene.paint.Color;
import javafx.scene.effect.Reflection;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

/**
 * This class is an application in order to get information about a random meal
 * in a category of your choice. User will receive information about the random meal's
 * native country and receive the meal's recipe to make as well!
 */
public class ApiApp extends Application {
    Stage stage;
    Scene scene;
    VBox root;
    private ComboBox<String> dropdown;
    private HBox mealBox;

    private VBox imageList2;
    private HBox toolBar;

    private VBox recipeBox;
    private Label footNote;
    private Text label;
    private Text label2;

    private TextField recipeText;
    private Button getFood;
    private Button getCountry;
    private Button getRecipeText;
    private ComboBox<String> dropDown;
    private ImageView iv;
    private Image defaultImage;

    private String countryName;
    private String nativeName;
    private String capital;
    private String demonym;
    private String uri = "";
    private String mealToShow = "";
    private String foodPic = "";
    private String mealInstructions = "";
    private String mealID = "";
    private String mealNationality = "";
    private Random random;

    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns a HttpClient object

    public static final HttpClient HTTP_CLIENT_2 = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns a HttpClient object

    public static final HttpClient HTTP_CLIENT_3 = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns a HttpClient object

/** Google {@code Gson} object for parsing JSON-formatted strings. */
    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()                          // enable nice output when printing
        .create();                                    // builds and returns a Gson object

     /**
     * Constructs a {@code ApiApp} object}.
     */
    public ApiApp() {
        this.stage = null;
        this.scene = null;
        this.root = new VBox();
        this.toolBar = new HBox();
        this.mealBox = new HBox();
        this.imageList2 = new VBox();
        this.recipeText = new TextField("Recipe Text will print [HERE]");
        this.recipeBox = new VBox();
        this.getFood = new Button("Get Food/Nationality Info");
        this.getRecipeText = new Button("Get Recipe in Text");
        this.label = new Text(60, 70, "Choose a category, get food/nationality!");
        this.footNote = new Label("Food Adjectives will appear [HERE] ");
        this.label2 = new Text(40, 50, "Information of the food's nationality will appear [HERE]");
        this.iv = new ImageView();
        this.random = new Random();
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
        System.out.println("init() called");
        root.getChildren().addAll(toolBar, recipeBox, imageList2);
        this.dropdown = new ComboBox<>();
        this.dropdown.setValue("Choose Category");
        this.dropdown.getItems().addAll("Beef", "Chicken", "Dessert", "Lamb",
            "Pasta", "Pork", "Seafood", "Side", "Starter", "Vegan",
            "Vegetarian", "Breakfast", "Goat", "Miscellaneous");
        recipeBox.setBackground(new Background(new BackgroundFill(Color.LAVENDER,null,null)));
        imageList2.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        Runnable task = () -> {
            getFood();
        };
        getFood.setOnAction(e -> {
            runNow(task);
        });
        label.setX(10.0f);
        label.setY(50.0f);

        label.setFill(Color.BLACK);
        label.setFont(Font.font(null, FontWeight.BOLD, 30));
        label2.setFill(Color.LAVENDER);
        label2.setFont(Font.font(null, FontWeight.BOLD, 14));
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        label.setEffect(r);

        recipeBox.getChildren().addAll(label, iv);
        recipeBox.setSpacing(30);
        recipeBox.setPadding(new Insets(10,10, 10, 10));
        recipeBox.setAlignment(Pos.CENTER);
        getRecipeText.setDisable(true);

        toolBar.getChildren().addAll(fillToolBar());
        imageList2.getChildren().addAll(label2);
        imageList2.setAlignment(Pos.CENTER);
        toolBar.setAlignment(Pos.BASELINE_CENTER);
    }

    /** {@inheritDoc} */
    @Override
     public void start(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(this.root,720,600);
        this.stage.setOnCloseRequest(event -> Platform.exit());
        this.stage.setScene(this.scene);
        this.stage.sizeToScene();
        this.stage.setTitle("ApiApp");

        iv.setFitHeight(400);
        iv.setFitWidth(420);
        this.stage.show();
        Platform.runLater(() -> this.stage.setResizable(false));
        getRecipeText.setDisable(true);
    } // start

    /** {@inheritDoc} */
    @Override
    public void stop() {
        // feel free to modify this method
        System.out.println("stop() called");
        System.exit(0);
    } // stop

    /**
     * Used to run a task on a new thread.
     * @param target is any runnable task
     */
    private static void runNow(Runnable target) {
        Thread thread = new Thread(target);
        thread.setDaemon(true);
        thread.start();
    } //runNow

    /**
     * Creates a Toolbar type containing all choiceBtns.
     *
     * @return ToolBar the toolbar with choiceBtns.
     */
    private ToolBar fillToolBar() {
        // adds the choiceBtns to the toolbar
        ToolBar choiceBtns = new ToolBar(dropdown,getFood, getRecipeText);
        return choiceBtns;
    } //fillToolBar

     /**
     * Handles the retrieval, storage, and display of relevant information from
     *
     * MealDb  API.
     */
    private void getFood() {
        Platform.runLater(() -> {
            getFood.setDisable(true);
            label.setText("Loading Food...");
            getRecipeText.setDisable(false);
        });
        try {
            String areaUrl = "https://www.themealdb.com/api/json/v1/1/filter.php?c=";
            String category  = this.dropdown.getValue();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(areaUrl + category)).GET()
                .build();
            HttpResponse<String> response = HTTP_CLIENT.send(request, BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new IOException(response.toString());
            }
            String jsonResponse = response.body().trim();
            MealDBResponse mealDbResponse = GSON.fromJson(jsonResponse,MealDBResponse.class);
            List<Meal> mealsList = mealDbResponse.getMeals(); //retrieves list of meals
            Meal randomMeal = mealsList.get(random.nextInt(mealsList.size())); //picks random meal
            mealID = randomMeal.getIdMeal(); //save unique id of meal
            mealToShow = randomMeal.getStrMeal(); //mealName
            defaultImage = new Image(randomMeal.getStrMealThumb()); //save pic of mealToShow
            String BASEUrl = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=";
            HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(BASEUrl + mealID)).GET().build(); //use mealID to get more meal info
            HttpResponse<String> response2 = HTTP_CLIENT_2.send(request2,BodyHandlers.ofString());
            if (response2.statusCode() != 200) {
                throw new IOException(response2.toString());
            }
            String jsonResponse2 = response2.body().trim();
            MealDBResponse mealDbResponse2 = GSON.fromJson(jsonResponse2,MealDBResponse.class);
            for (Meal meal2: mealDbResponse2.getMeals()) {
                if (meal2.getIdMeal().equals(randomMeal.getIdMeal())) { //if meal is same as
                    mealInstructions = meal2.getStrInstructions();      //chosen random meal
                    mealNationality = meal2.getStrArea();        //then save recipe and demonym
                    break;
                }
            }
            getCountryInfo(); //call second api - to be parsed using saved results from mealDb Api
            Platform.runLater(() -> {
                label.setText(mealToShow);
                iv.setImage(defaultImage);
                label2.setText("Here are some facts about the food's native country!" +
                    "\nThis nationality of this food is:  " + mealNationality
                    + ". \nThe name of the country with this nationality is: " + countryName
                    + ". \nThe country's name in the native language, native name: " + nativeName +
                    ". \nThe  capital city of this country is:  "
                    + capital + ".");
                getRecipeText.setOnAction(e -> { //shows recipe in a pop up box
                    Alert alert2 = new Alert(Alert.AlertType.NONE,
                        "Recipe Instructions: " + mealInstructions,  ButtonType.OK);
                    alert2.showAndWait();

                });
                getFood.setDisable(false);
            });
        } catch (InterruptedException | IOException | IllegalArgumentException ioe) {
            System.out.println("Category of Meal Info not in database. Sorry!");
        }
    }

    /**
     * Handles the retrieval, storage, and display of relevant information from
     *
     * both Second API with Demonym/Countries information.
     */
    private void getCountryInfo() {
        try {
            String API_URL = "https://jsonmock.hackerrank.com/api/countries?demonym=";
            HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create(API_URL
                + mealNationality)) //use mealNationality saved from mealDb API
                .GET()               // to find further info about the nationality
                .build();           //using this second api
            HttpResponse<String> response1 = HTTP_CLIENT_3.send(request1,
                HttpResponse.BodyHandlers.ofString());
            if (response1.statusCode() != 200) {
                throw new IOException(response1.toString());
            }
            String jsonResponse1 = response1.body();
            CountryInfo cio = GSON.fromJson(jsonResponse1, CountryInfo.class);
            for (CountryData c: cio.getData()) {
                capital = c.getCapital(); //gets capital city of nation
                countryName = c.getName(); //gets name of nation/country
                nativeName = c.getNativeName(); //gets name in native language
                System.out.println(c.getCapital()); //print capital city to terminal
            }
        } catch (InterruptedException | IOException | IllegalArgumentException ioe) {
            System.out.println("Meal Nationality not in database. Sorry!");
        }
    }
} // ApiApp
