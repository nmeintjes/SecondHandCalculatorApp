package za.co.wethinkcode.weshare.login;

import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.model.Item;
import za.co.wethinkcode.weshare.add.AddPhoneToDatabaseController;

public class LoginController {
    public static final String LOGIN_PATH = "/login";
    public static final String LOGOUT_PATH = "/logout";
    public static final String ROOT_PATH = "/index.html";

    public static void handleLogin(Context context) {
        String username = context.formParam("email");
        if (username == null) {
            context.redirect(ROOT_PATH);
            return;
        }

        final Item person = DataRepository.getInstance().addPerson(new Item(username));
        context.sessionAttribute("user", person);

        context.redirect(AddPhoneToDatabaseController.PATH);
    }

    public static void handleLogout(Context context) {
        context.sessionAttribute("user", null);
        context.redirect(ROOT_PATH);
    }
}