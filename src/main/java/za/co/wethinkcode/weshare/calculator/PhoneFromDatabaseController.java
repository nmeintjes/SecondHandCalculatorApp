package za.co.wethinkcode.weshare.calculator;

import io.javalin.http.Context;
import za.co.wethinkcode.server.CRUD;
import za.co.wethinkcode.weshare.app.model.Expense;
import za.co.wethinkcode.weshare.app.model.Phone;

import java.time.LocalDate;
import java.util.*;

/**
 * Controller for retrieving data on selected phone
 */
public class PhoneFromDatabaseController {

    public static final String PHONE_PATH = "/phone/{name}";

    public static void renderPhonePage(Context context){

        CRUD crud = new CRUD();
        Phone phone = crud.retrievePhone();





        Map<String, Object> viewModel = Map.of(


        );

        context.render("claimexpense.html", viewModel);
    }
}