package za.co.wethinkcode.weshare.add;

import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.db.CRUD;
import za.co.wethinkcode.weshare.app.model.Item;

import java.util.Map;

public class AddItemToDatabaseController {
    public static final String PATH = "/add";

    public static void renderCalculatedResultPage(Context context){

        Object ob = context.sessionAttributeMap().get("user");


        Item item = context.bodyAsClass(Item.class);

        CRUD crud = new CRUD();


        double value = item.getValue();

        Map<String, Object> viewModel = Map.of(

                "value",value
        );
        context.render("calculatedresult.html", viewModel);
    }
}