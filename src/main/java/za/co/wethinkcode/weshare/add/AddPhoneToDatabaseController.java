package za.co.wethinkcode.weshare.add;

import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.model.Phone;

import java.util.Map;

public class AddPhoneToDatabaseController {
    public static final String PATH = "/home";

    public static void renderHomePage(Context context){

        Object ob = context.sessionAttributeMap().get("user");
        Phone per = (Phone) ob;

        Map<String, Object> viewModel = Map.of(

        );

        context.render("home.html", viewModel);
    }
}