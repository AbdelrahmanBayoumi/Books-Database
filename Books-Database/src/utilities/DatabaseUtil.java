package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String CLASS_NAME = DatabaseUtil.class.getName();
    public static Connection con = null;
    public static ResultSet result = null;
    public static PreparedStatement stat = null;

    public static void init() {
        connectToDataBase();
        createTables();
    }

    private static void connectToDataBase() {
        try {
            if (con == null) {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:database.db");
                con.prepareStatement(" PRAGMA foreign_keys = ON").execute();
            }
        } catch (Exception ex) {
            con = null;
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".connectToDataBase()");
        }
    }

    private static void createTables() {
        String field = "CREATE TABLE if not EXISTS Field (field_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " field_name VARCHAR(255) UNIQUE NOT NULL)";
        String book = "CREATE TABLE if not EXISTS Book (book_id INTEGER PRIMARY KEY AUTOINCREMENT"
                + ", title VARCHAR(255) UNIQUE NOT NULL, Author VARCHAR(255), publisher VARCHAR(255)"
                + ", Description TEXT ,Location TEXT ,Book_Field_id INTEGER references Field(field_id) ON DELETE CASCADE)";
        createTable(field);
        createTable(book);
    }

    private static boolean createTable(String sql) {
        try {
            return con.prepareStatement(sql).execute();
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".createTable()");
            return false;
        }
    }

    public static boolean AddField(String field_name) {
        String sqlString = "INSERT INTO Field (field_name) values (?)";
        try {
            PreparedStatement prepareStatement = con.prepareStatement(sqlString);
            prepareStatement.setString(1, field_name);
            prepareStatement.execute();
            return true;
        } catch (SQLException ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".AddField(field_name: " + field_name + ")");
            return false;
        }
    }

    public static int getFieldID(String field_name) {
        int x = -1;
        try {
            stat = con.prepareStatement(
                    "SELECT field_id FROM Field WHERE field_name = '" + field_name + "'");
            result = stat.executeQuery();
            while (result.next()) {
                x = result.getInt(1);
            }
        } catch (Exception ex) {
            Logger.log(Logger.Level.ERROR, "Exception[" + ex + "] in " + CLASS_NAME + ".getFieldID(field_name:" + field_name + ")");
        }
        return x;
    }

}
