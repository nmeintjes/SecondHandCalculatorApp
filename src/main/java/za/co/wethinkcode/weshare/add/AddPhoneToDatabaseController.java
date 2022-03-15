package za.co.wethinkcode.weshare.add;

import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.model.Item;

import java.util.Map;

public class AddPhoneToDatabaseController {
    public static final String PATH = "/add/{name}";

    public static void renderHomePage(Context context){

        Object ob = context.sessionAttributeMap().get("user");
        Item per = (Item) ob;

        Map<String, Object> viewModel = Map.of(

        );

        context.render("home.html", viewModel);
    }
}