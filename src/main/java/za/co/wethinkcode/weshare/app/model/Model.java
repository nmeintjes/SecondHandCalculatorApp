package za.co.wethinkcode.weshare.app.model;

public class Model {
    private double ageMultiplier = 0.25;
    private String brand = "samsung";
    private int modelNumber = 10;
    private int gbStorage = 32;
    private double price = 8000;

    public String getType() {return brand;}
    public double getAgeMultiplier() {return ageMultiplier;}
    public int getModelNumber() {return modelNumber;}

    public Model(String brand, int modelNumber, int gbStorage, double price) {
        this.brand = brand;
        this.modelNumber = modelNumber;
        this.gbStorage = gbStorage;
        this.price = price;
    }

    public Model(String type) {
        processType(type);
    }

    private void processType(String brand) {
        this.brand = (brand=="samsung") ? "samsung": "iPhone";
        ageMultiplier = (brand=="samsung") ? 0.25: 0.166666;

        String[] argsString = brand.split(" ");
        this.brand = argsString[0];
        this.modelNumber = Integer.parseInt(argsString[1]);
        this.price = Integer.parseInt(argsString[1]);

        if (brand.equals("apple")) {
            ageMultiplier = 0.16666;
        }
    }



    public double getPrice() {
        return price;
    }
}
