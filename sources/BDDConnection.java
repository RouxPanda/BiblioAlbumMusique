import java.sql.*;

public class BDDConnection {
    private Connection connection = null;
    private Statement statement;
    public BDDConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:MusiqueBDD.db");
        Statement statement = connection.createStatement();
    }
    public Statement getStatement() {
        return statement;
    }
}
