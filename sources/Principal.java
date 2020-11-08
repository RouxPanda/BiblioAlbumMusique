import java.sql.SQLException;

public class Principal {
    public static void main(String[] args)
    {
        //FenetreGraphique fenetre = new FenetreGraphique();
        try {
            BDDConnection bdd = new BDDConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
