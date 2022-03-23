package za.co.wethinkcode.weshare.add;

import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.db.CRUD;
import za.co.wethinkcode.weshare.app.model.Item;
import za.co.wethinkcode.weshare.app.model.Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AddItemRevealForm {
    public static final String FORM_PATH = "/add/form";

    public static void renderPhoneFormPage(Context context) throws SQLException {


        CRUD crud = new CRUD();
        List<Model> models = crud.retrieveModels();

        Map<String, Object> viewModel = Map.of(

                "models", models
        );
        context.render("phoneform.html", viewModel);


    }
}
