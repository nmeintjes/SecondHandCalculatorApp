package za.co.wethinkcode.weshare.app.db;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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

    public void createWorldObstacles(List<Obstacle> obstacles, String worldName)
            throws SQLException {
        try (
                Connection connection = DriverManager.getConnection(DISK_DB_URL_PREFIX);
                final PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO Obstacles" +
                                "(world, obs_type, size, bottom_left_x, bottom_left_y) " +
                                " VALUES (?, ?, ?, ?, ?)"
                )) {
            for (Obstacle obstacle : obstacles) {
                stmt.setString(1, worldName);
                stmt.setString(2, obstacle.getType());
                stmt.setInt(3, obstacle.getSize());
                stmt.setInt(4, obstacle.getBottomLeftX());
                stmt.setInt(5, obstacle.getBottomLeftY());
                final boolean gotAResultSet = stmt.execute();

                if (gotAResultSet) {
                    throw new RuntimeException("Unexpectedly got a SQL resultset.");
                } else {
                    final int updateCount = stmt.getUpdateCount();
                    if (updateCount == 1) {
                        System.out.println("1 row INSERTED into Obstacles");
                    } else {
                        throw new RuntimeException("Expected 1 row to be inserted, but got " + updateCount);
                    }
                }
            }
        }
    }


    private void updateData( final Connection connection )
            throws SQLException
    {
        try( final PreparedStatement stmt = connection.prepareStatement(
                "UPDATE products SET name = ? WHERE id = ?"
        )){
            stmt.setString( 1, "Sourflat IPA" );
            stmt.setInt( 2, 2 );
            final boolean gotAResultSet = stmt.execute();

            if( gotAResultSet ){
                throw new RuntimeException( "Unexpectedly got a SQL resultset." );
            }else{
                final int updateCount = stmt.getUpdateCount();
                if( updateCount == 1 ){
                    System.out.println( "1 row UPDATED in products" );
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





}
