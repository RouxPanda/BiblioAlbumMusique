import java.sql.*;

public class BDDConnection {
    private Connection connection;
    private Statement statement;
    public BDDConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:MusiqueBDD.db");
        Statement statement = connection.createStatement();
        String oui = "SELECT * FROM album";
        ResultSet rs = statement.executeQuery(oui);
        System.out.println(rs.getString("nom_album"));
    }
}
