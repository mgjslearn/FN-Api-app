package cs1302.api;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains a list of the meals from the MealDb API.
 * It also contains a relevant getter method to access the meals List.
 */
public class MealDBResponse {
    private List<Meal>  meals;

    /**
     * This method gets and returns the list of meals from the API.
     *
     * @return meals the List of meals from API.
     */
    public List<Meal>  getMeals() {
        return meals;
    }

    /**
     * This method sets the meals instance of this class
     * to the meals List.
     *
     * @param meals the list of meals.
     */
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
