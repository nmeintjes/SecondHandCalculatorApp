package za.co.wethinkcode.weshare.app.db;

import za.co.wethinkcode.weshare.app.model.Model;
import za.co.wethinkcode.weshare.app.model.Item;

import java.sql.*;

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




    private void updateEntry( final Connection connection, String valuetoChange, String
                              newValue, int id)
            throws SQLException
    {
        try( final PreparedStatement stmt = connection.prepareStatement(
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

    private void deleteData( final Connection connection )
            throws SQLException
    {
        try( final PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM products WHERE id = ?"
        )){
            stmt.setInt( 1, 2 );
            final boolean gotAResultSet = stmt.execute();

            if( gotAResultSet ){
                throw new RuntimeException( "Unexpectedly got a SQL resultset." );
            }else{
                final int updateCount = stmt.getUpdateCount();
                if( updateCount == 1 ){
                    System.out.println( "1 row DELETED from products" );
                }else{
                    throw new RuntimeException( "Expected 1 row to be inserted, but got " + updateCount );
                }
            }
        }
    }


    public Item retrievePhone(final Connection connection)
        throws SQLException
    {
        try( final PreparedStatement stmt = connection.prepareStatement(
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
                           gbStorage);
                }
                return phone;
            }
        }
    }
}
