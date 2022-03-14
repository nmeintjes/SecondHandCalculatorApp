package za.co.wethinkcode.weshare.calculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.model.Expense;
import za.co.wethinkcode.weshare.app.model.Phone;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Controller for handling API calls for Claims
 */
public class ClaimsApiController {

    public static final String CLAIMS_PATH = "/api/claims";

//    private final Person claimedBy;
//    private final Person claimedFrom;
//    private final LocalDate dueDate;
//    private final Expense expense;
//    private boolean settled;

    public static void create(Context context) {
        System.out.println("request" + context.body());

        String request = context.body();
        //create the object mapper
        ObjectMapper mapper = new ObjectMapper();

        //create jsonnode for access to values
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Double amount = mapper.convertValue(jsonNode.get("claim_amount"), Double.class);
        String email = mapper.convertValue(jsonNode.get("email"),String.class);
        String date = mapper.convertValue(jsonNode.get("due_date"),String.class);
        String id_string = mapper.convertValue(jsonNode.get("expenseId"),String.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);



        Phone debtor = new Phone(email);


        System.out.println("id string query param in api claims: " + id_string);

        //Expense expense = database.getExpense(uuid).get()
        Expense expense = DataRepository.getInstance().getExpense(UUID.fromString(id_string)).get();


        Claim claim = expense.createClaim(debtor,amount,localDate);

        DataRepository database = DataRepository.getInstance();
        database.addClaim(claim);
        //database.addClaim(new Claim())



        String claimFromWho = debtor.getName();
        Double claimAmount = expense.getAmount();



        String response = "{\"id\": " + "\"" + id_string + "\"" +
                ", \"claimFromWho\": " + "\"" + claimFromWho + "\""
                + ", \"dueDate\": " + "\"" + date + "\""
                + ", \"claimAmount\": "+ claimAmount + "}";

        System.out.println("response" + response);
        context.status(201);
        //context.json(response);
        context.result(response);

    }
}