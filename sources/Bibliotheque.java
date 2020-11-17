import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

//Contient l'entierté de nos albums
public class Bibliotheque {

    Vector<Album> tabalbum;

    Bibliotheque() {
        tabalbum = new Vector<Album>();
        BDDConnection bdd;
        Album alb;
            try {
                bdd = new BDDConnection();
                String albumres = "SELECT * FROM album INNER JOIN artiste";
                ResultSet rs = bdd.getStatement().executeQuery(albumres);
                while(rs.next()) {
                    alb = new Album(rs.getString("nom_album"),
                            rs.getString("nom_artiste"),
                    rs.getString("date_album"),
                    rs.getInt("nbr_piste"),
                    rs.getDouble("duree_album"),
                    rs.getString("img_album"));
                    tabalbum.addElement(alb);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }
}
