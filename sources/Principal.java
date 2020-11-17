import java.sql.SQLException;

public class Principal {
    public static void main(String[] args)
    {
        try {
            BDDConnection bdd = new BDDConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        FenetreGraphique fenetre = new FenetreGraphique();
    }
}
