package za.co.wethinkcode.weshare.app.model;

import java.util.Locale;

public class Model {
    private double ageMultiplier = 0.25;
    private String manufacturer = "samsung";
    private int modelNumber = 10;
    private int gbStorage = 32;
    private double price = 8000;
    private String series = "Galaxy F20";


    public String getManufacturer() {return manufacturer;}
    public double getAgeMultiplier() {return ageMultiplier;}
    public int getModelNumber() {return modelNumber;}
    public String getSeries() {return series;}
    public Double getPrice() {return price;}
    public int getGbStorage() {
        return gbStorage;
    }

    public Model(String manufacturer, int modelNumber, int gbStorage, double price,
                 String series) {
        this.manufacturer = manufacturer;
        this.series = series;
        this.modelNumber = modelNumber;
        this.gbStorage = gbStorage;
        this.price = price;
    }

    public Model(String type) {
        processType(type);
    }

    private void processType(String brand) {
//        this.brand = (brand=="samsung") ? "samsung": "iPhone";
//        ageMultiplier = (brand=="samsung") ? 0.25: 0.166666;

        String[] argsString = brand.split(" ");
        //this.brand = argsString[0];
        this.modelNumber = Integer.parseInt(argsString[1]);
        this.price = Integer.parseInt(argsString[1]);

        if (brand.toLowerCase().equals("apple")) {
            ageMultiplier = 0.16666;
        }
    }



}
