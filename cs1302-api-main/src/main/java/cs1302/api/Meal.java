package cs1302.api;

/**
 * This class instantiates and creates all necessary
 * class components in order to get access to a Meal's
 * relevant information from the MealDb API.
 */
public class Meal {
    private String strMeal;
    private String strArea;
    private String idMeal;
    private String strCategory;
    private String strMealThumb;
    private String strInstructions;
    private String strIngredient1;
    private String strYoutube;
    private String strTags;

    /**
     * Constructs an {@code Meal} object.
     * @param idMeal the meal's unique id.
     * @param strMeal the meal itself.
     * @param strCategory the category the meal falls under.
     * @param strArea the nationality of the meal.
     * @param strMealThumb the picture of the meal.
     * @param strInstructions the recipe of the meal.
     */
    public Meal(String idMeal, String strMeal, String strCategory, String strArea,
        String strMealThumb, String strInstructions) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strMealThumb = strMealThumb;
        this.strInstructions = strInstructions;
    }

    /**
     * This method returns the meal.
     * @return strMeal the meal itself.
     */
    public String getStrMeal() {
        return strMeal;
    }

    /**
     * This method returns the meal's nationality.
     *
     * @return strArea the meal's nationality.
     */
    public String getStrArea() {
        return strArea;
    }

    /**
     * This method returns the unique id of the meal.
     *
     * @return idMeal the id of the meal chosen.
     */
    public String getIdMeal() {
        return idMeal;
    }

    /**
     * This method gets and returns the category of meal.
     *
     * @return strCategory the category of meal.
     */
    public String getStrCategory() {
        return strCategory;
    }

    /**
     * This method returns the picture of the meal chosen.
     *
     * @return strMealThumb the picture of the meal.
     */
    public String getStrMealThumb() {
        return strMealThumb;
    }

    /**
     * This method returns the recipe of the meal.
     * @return strInstructions the variable containing the meal's recipe.
     */
    public String getStrInstructions() {
        return strInstructions;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "idMeal='" + idMeal + '\'' +
                ", strMeal='" + strMeal + '\'' +
                ", strCategory='" + strCategory + '\'' +
            ", strArea='" + strArea + '\'' + ", strInstructions= " + strInstructions +
                '}';
    }
}
