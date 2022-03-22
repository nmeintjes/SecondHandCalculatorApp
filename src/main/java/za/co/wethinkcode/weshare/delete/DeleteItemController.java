package za.co.wethinkcode.weshare.delete;

import io.javalin.http.Context;
import org.eclipse.jetty.http.HttpStatus;
import za.co.wethinkcode.weshare.app.db.CRUD;
import za.co.wethinkcode.weshare.app.model.Item;

import java.sql.SQLException;
import java.util.Map;

public class DeleteItemController {
    public static final String DELETE_PATH = "/delete";

    public static void deleteItem(Context context) throws SQLException {

        Object ob = context.sessionAttributeMap().get("user");

        CRUD crud = new CRUD();

        Item item = crud.retrieveItem(context.queryParam("itemId"));

        crud.deleteItem(item);
        context.status(HttpStatus.OK_200);


    }
}







