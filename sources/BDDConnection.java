import java.sql.*;

/**
 * @author TRONC Clément
 * @author DI PIAZZA Hugo
 * The type Bdd connection.
 */
public class BDDConnection {
    /**
     * The Connection.
     */
    private Connection connection = null;
    /**
     * The Statement.
     */
    private Statement statement;

    /**
     * Instantiates a new Bdd connection.
     *
     * @throws SQLException the sql exception
     */
    public BDDConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:MusiqueBDD.db");
        statement = connection.createStatement();
    }

    /**
     * Gets statement.
     *
     * @return the statement
     */
    public Statement getStatement() {
        return statement;
    }
}
