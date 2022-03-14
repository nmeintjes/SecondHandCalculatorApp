package za.co.wethinkcode.weshare.app.model;

import za.co.wethinkcode.weshare.util.Strings;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * This represents a phone object, for a second hand phone for which we are calculating the value.
 *
 */
public class Phone {

    private int yearsOld;
    private int price;
    private String brand;
    private boolean scratched;
    private boolean charger;
    private boolean newPhone;
    private boolean sealed;



    public Phone() {
        this.brand = null;
        this.price = 0;
        this.yearsOld = 0;
        this.scratched = false;
        this.charger = false;
        this.newPhone = false;
        this.sealed = false;
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




    private int getPrice() {
        return price;
    }

    private int getYearsOld() {
        return yearsOld;
    }

    private boolean isScratched() {
        return scratched;
    }

    private boolean HasCharger() {
        return charger;
    }

    private boolean isNewPhone() {
        return newPhone;
    }

    private boolean isSealed() {
        return sealed;
    }
}