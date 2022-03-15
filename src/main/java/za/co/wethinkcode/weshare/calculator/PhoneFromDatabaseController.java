package za.co.wethinkcode.weshare.calculator;

import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.db.CRUD;
import za.co.wethinkcode.weshare.app.model.Item;

import java.util.*;

/**
 * Controller for retrieving data on selected phone
 */
public class PhoneFromDatabaseController {

    public static final String PHONE_PATH = "/phone/{name}";

    public static void renderPhonePage(Context context){

        CRUD crud = new CRUD();
        Item phone = crud.retrievePhone();



        double value = phone.getMarketValue();


        Map<String, Object> viewModel = Map.of(

            "value",value
        );

        context.render("calculatedresult.html", viewModel);
    }
}