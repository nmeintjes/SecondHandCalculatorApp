package za.co.wethinkcode.weshare.app.db;

import za.co.wethinkcode.weshare.app.model.Model;
import za.co.wethinkcode.weshare.app.model.Item;

import java.sql.*;
import java.util.List;

public class CRUD {

    public static final String DISK_DB_URL_PREFIX = "jdbc:sqlite:database.db";
    public static final String SEPARATOR = "\t";

    private String dbUrl = null;

    public Connection connect() {

        try (Connection connection = DriverManager.getConnection(DISK_DB_URL_PREFIX)) {
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    private void updateEntry( String valuetoChange, String
                              newValue, int id)
            throws SQLException
    {
        try(    Connection connection = DriverManager.getConnection(DISK_DB_URL_PREFIX);
                final PreparedStatement stmt = connection.prepareStatement(
                "UPDATE items SET ? = ? WHERE id = ?"
        )){
            stmt.setString( 1, valuetoChange);
            stmt.setString( 2, newValue);
            stmt.setInt( 3, id );

            final boolean gotAResultSet = stmt.execute();

            if( gotAResultSet ){
                throw new RuntimeException( "Unexpectedly got a SQL resultset." );
            }else{
                final int updateCount = stmt.getUpdateCount();
                if( updateCount == 1 ){
                    System.out.println( "1 row UPDATED in items" );
                }else{
                    throw new RuntimeException( "Expected 1 row to be inserted, but got " + updateCount );
                }
            }
        }
    }

    public void deleteItem(Item item)
            throws SQLException
    {
        try( Connection connection = DriverManager.getConnection(DISK_DB_URL_PREFIX);
             final PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM items WHERE uuid = ?"
        )){
            stmt.setString( 1, item.getItemId().toString() );
            final boolean gotAResultSet = stmt.execute();

            if( gotAResultSet ){
                throw new RuntimeException( "Unexpectedly got a SQL resultset." );
            }else{
                final int updateCount = stmt.getUpdateCount();
                if( updateCount == 1 ){
                    System.out.println( "1 row DELETED from items" );
                }else{
                    throw new RuntimeException( "Expected 1 row to be deleted, but got " + updateCount );
                }
            }
        }
    }


    public Item retrieveItem(String itemId)
        throws SQLException
    {
        try(    Connection connection = DriverManager.getConnection(DISK_DB_URL_PREFIX);
                final PreparedStatement stmt = connection.prepareStatement(
                "SELECT FROM items WHERE id = ?"
        )){
            stmt.setInt( 1, 2 );
            final boolean gotAResultSet = stmt.execute();

            if( !gotAResultSet ){
                throw new RuntimeException("Expected a SQL resultset, but we got an update count instead!");
            } try (ResultSet results = stmt.getResultSet()) {
                
                Item phone = null;
                
                while (results.next()) {
                    //loop through every column in obstacles column

                   int yearsOld = results.getInt("years");
                   String brandType = results.getString("brand");
                   boolean scratched = results.getBoolean("scratched");
                   boolean charger = results.getBoolean("charger");;
                   boolean sealed = results.getBoolean("sealed");;
                   boolean crackedScreen = results.getBoolean("cracked");;
                   boolean batteryDead = results.getBoolean("batteryDead");;
                   int gbStorage = results.getInt("gbStorage");

                   Model model = new Model(brandType);
                   phone = new Item(yearsOld,model,scratched,
                           charger,sealed,crackedScreen,batteryDead,
                           gbStorage, itemId);
                }
                return phone;
            }
        }
    }

    public List<Item> retrieveItems()
            throws SQLException
    {
        try(    Connection connection = DriverManager.getConnection(DISK_DB_URL_PREFIX);
                final PreparedStatement stmt = connection.prepareStatement(
                        "SELECT * FROM items"
                )){
//            stmt.setInt( 1, 2 );
            final boolean gotAResultSet = stmt.execute();

            if( !gotAResultSet ){
                throw new RuntimeException("Expected a SQL resultset, but we got an update count instead!");
            } try (ResultSet results = stmt.getResultSet()) {

                List<Item> items = null;

                try {
                    while (results.next()) {
                        //loop through every column in obstacles column

                        int yearsOld = results.getInt("years");
                        String brandType = results.getString("brand");
                        boolean scratched = results.getBoolean("scratched");
                        boolean charger = results.getBoolean("charger");;
                        boolean sealed = results.getBoolean("sealed");;
                        boolean crackedScreen = results.getBoolean("cracked");;
                        boolean batteryDead = results.getBoolean("batteryDead");;
                        int gbStorage = results.getInt("gbStorage");

                        Model model = new Model(brandType);
                        Item item = new Item(yearsOld,model,scratched,
                                charger,sealed,crackedScreen,batteryDead,
                                gbStorage);
                        items.add(item);
                    }
                } catch (NullPointerException n) {
                    ;
                }

                return items;
            }
        }
    }

    public List<Model> retrieveModels()
            throws SQLException
    {
        try(    Connection connection = DriverManager.getConnection(DISK_DB_URL_PREFIX);
                final PreparedStatement stmt = connection.prepareStatement(
                        "SELECT * FROM models"
                )){
//            stmt.setInt( 1, 2 );
            final boolean gotAResultSet = stmt.execute();

            if( !gotAResultSet ){
                throw new RuntimeException("Expected a SQL resultset, but we got an update count instead!");
            } try (ResultSet results = stmt.getResultSet()) {

                List<Model> listOfModels = null;


                while (results.next()) {
                    //loop through every column in obstacles column

                    String series = results.getString("series");
                    double price = results.getDouble("price");
                    int modelNumber = results.getInt("modelNumber");
                    String manufacturer = results.getString("manufacturer");
                    int gbStorage = results.getInt("gbStorage");

                    Model model = new Model(manufacturer,modelNumber,gbStorage,price,series);

                    listOfModels.add(model);
                }
                return listOfModels;
            }
        }
    }

    public void addItem(Item item)
            throws SQLException
    {
        try(    Connection connection = DriverManager.getConnection(DISK_DB_URL_PREFIX);
                final PreparedStatement stmt = connection.prepareStatement(
                "INSERT into items (yearsOld, brandType, scratched," +
                        ", charger, sealed, crackedScreen, batteryDead)" +
                        " VALUES (?,?,?,?,?,?,?)"
        )){
            stmt.setInt( 1, item.getYearsOld());
            stmt.setString( 2, item.getBrand().toString());

            stmt.setInt( 3, item.isScratched() ? 1: 0);

            stmt.setInt( 4, item.hasCharger() ? 1: 0);

            stmt.setInt( 5, item.isSealed() ? 1:0);

            stmt.setInt( 6, item.isCrackedScreen() ? 1:0);
            stmt.setInt( 7, item.isBatteryDead() ? 1:0);


            final boolean gotAResultSet = stmt.execute();

            if( !gotAResultSet ){
                throw new RuntimeException("No resultset expected, but we got one!");
            } try (ResultSet results = stmt.getResultSet()) {

            }
        }
    }
}
