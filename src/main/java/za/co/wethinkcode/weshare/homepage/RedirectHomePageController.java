package za.co.wethinkcode.weshare.homepage;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.db.CRUD;
import za.co.wethinkcode.weshare.app.model.Item;
import kong.unirest.Unirest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RedirectHomePageController {

    public final static String REDIRECT_PATH = "/gotohomepage";

    public static void redirectToHomePage(Context ctx) throws SQLException {
        System.out.println("hit");

        CRUD crud = new CRUD();
        List<Item> items = (crud.retrieveItems() != null) ? crud.retrieveItems(): new ArrayList<Item>();


        String name = "name";

            Map<String, Object> viewModel = Map.of(
                    "name",name,
                    "userAddedItems",items
            );
            ctx.render("home.html", viewModel);


    }

}
