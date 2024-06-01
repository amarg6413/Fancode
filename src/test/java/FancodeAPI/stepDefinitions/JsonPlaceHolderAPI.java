package FancodeAPI.stepDefinitions;

import FancodeAPI.CommonPages.ToDosPage;
import FancodeAPI.CommonPages.UsersPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static FancodeAPI.stepDefinitions.Hooks.assertion;

/**
 *This class will contain all the test steps defined in the feature files
 */
public class JsonPlaceHolderAPI{
    private ToDosPage toDosPage = new ToDosPage();
    private UsersPage usersPage = new UsersPage();
    private Set<Integer> userID;
    private Map<String,Set<Integer>> userToCityMap=new HashMap<>();

    @Given("User has the todo tasks")
    public void userHasTheTodoTasks() throws IOException {
        userID = toDosPage.getUserIDHavingTodos();
    }

    @And("User belongs to the city {string}")
    public void userBelongsToTheCity(String city) throws IOException {
        userToCityMap.put(city,usersPage.getUserToCityMap(userID,city));
    }

    @Then("User Completed task percentage should be greater than {int}% for {string} city")
    public void userCompletedTaskPercentageShouldBeGreaterThanForCity(int minPercentage, String city) throws IOException {
        Set<Integer> userList = userToCityMap.get(city);
        Assert.assertNotNull(userList,"No user found for the city "+city);
        for (int userID: userList) {
            int totalTodoForUser = toDosPage.getTotalTodoForUser(userID);
            int completedTodoForUser= toDosPage.getCompletedTodoForUser(userID);
            double completedTaskPercentage= (completedTodoForUser*100.0)/totalTodoForUser;
            assertion.assertTrue(completedTaskPercentage>=minPercentage,"User "+userID+" has completed task percentage "+completedTaskPercentage+" should be greater than or equal to "+minPercentage);
        }
    }
}
