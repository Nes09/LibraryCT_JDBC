package com.cydeo.step_definitions;

import com.cydeo.pages.DashboardPage;
import com.cydeo.pages.LibLoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static com.cydeo.utility.DB_Util.*;


public class UsersStepDef {

//    LibLoginPage loginPage = new LibLoginPage();
//    DashboardPage dashboardPage = new DashboardPage();
    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        createConnection();
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
         runQuery("Select id from users");
        System.out.println("getColumnDataAsList(\"id\") = " + getColumnDataAsList("id"));
    }
    @Then("verify the result set")
    public void verify_the_result_set() {

        runQuery("Select Distinct id from users");
       // assertTrue()

    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        runQuery("Select * from users");
    }
    @Then("verify the below columns are listed in result:")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedResult) {

        List<String> actualResult = getRowDataAsList(1);
       // assertEquals

    }



}
