package za.co.wethinkcode.weshare.model;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.weshare.app.model.Model;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    @Test
    public void ModelHasCOrrectFields() {

        String manufacturer = "Apple";
        Double price = 10000.00;
        String series = "iPhone";
        int modelNumber = 11;
        int gbStorage = 32;

        Model testModel = new Model(manufacturer,modelNumber,gbStorage,price,series);

        assertEquals(testModel.getManufacturer(), "Apple");
        assertEquals(testModel.getModelNumber(), 11);
        assertEquals(testModel.getPrice(), 10000.00);
        assertEquals(testModel.getSeries(), "iPhone");
        assertEquals(testModel.getGbStorage(), 32);

    }
}
