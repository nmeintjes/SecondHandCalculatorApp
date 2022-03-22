package za.co.wethinkcode.weshare.app.model;

import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This represents a phone object, for a second hand phone for which we are calculating the value.
 *
 */
public class Item {

    private int yearsOld;
    private double value;
    private Model model;
    private boolean scratched;
    private boolean charger;
    private boolean sealed;
    private boolean crackedScreen;
    private boolean batteryDead;
    private int gbStorage;
    private UUID itemId;


    public Item() {
        this.model = null;
        this.value = 0.0;
        this.yearsOld = 0;
        this.scratched = false;
        this.charger = false;
        this.sealed = false;
        this.crackedScreen = false;
        this.batteryDead = false;
        this.gbStorage = 0;
        calculateMarketValue();
        itemId = UUID.randomUUID();
    }

    public Item(int yearsOld, Model model, boolean scratched,
                boolean charger, boolean sealed, boolean crackedScreen,
                boolean batteryDead, int gbStorage) {
        this.yearsOld = yearsOld;
        this.model = model;
        this.scratched = scratched;
        this.charger = charger;
        this.sealed = sealed;
        this.crackedScreen = crackedScreen;
        this.batteryDead = batteryDead;
        this.gbStorage = gbStorage;
        calculateMarketValue();
        itemId = UUID.randomUUID();
    }

    public Item(int yearsOld, Model model, boolean scratched, boolean charger, boolean sealed,
                boolean crackedScreen, boolean batteryDead, int gbStorage, String itemId) {

        this.yearsOld = yearsOld;
        this.model = model;
        this.scratched = scratched;
        this.charger = charger;
        this.sealed = sealed;
        this.crackedScreen = crackedScreen;
        this.batteryDead = batteryDead;
        this.gbStorage = gbStorage;
        calculateMarketValue();
        this.itemId = UUID.fromString(itemId);
    }

    @Override
    public String toString(){
        return "Phone{" +
            "phone='" + this + '\'' +
            '}';
    }

//    @Override
//    public boolean equals( Object o ){
//        if( this == o ) return true;
//        if( o == null || getClass() != o.getClass() ) return false;
//        Phone person = (Phone) o;
//        return email.equalsIgnoreCase( person.email );
//    }
//
//    @Override
//    public int hashCode(){
//        return Objects.hash( email );
//    }





    public int getYearsOld() {
        return yearsOld;
    }

    public boolean isScratched() {
        return scratched;
    }

    public boolean hasCharger() {
        return charger;
    }

    public boolean isSealed() {
        return sealed;
    }

    public boolean isCrackedScreen() {return crackedScreen;}

    public boolean isBatteryDead() {return batteryDead;}

    public Model getBrand() {return model;}

    public double calculateMarketValue() {
        double value = model.getPrice();


        value = value * ((6-yearsOld)/6);
        value = (scratched) ? value - 1000: value;
        value = (charger) ? value - 300: value;

        value = (sealed) ? value *0.8 : value;
        value = (crackedScreen) ? value - 1000: value;
        value = (batteryDead) ? value - 1000: value;


        return value;
    }

    public double getValue() {return value;}

    public UUID getItemId() {return itemId;}


}