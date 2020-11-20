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
                    rs.getString("genre"),
                    rs.getString("nbr_piste"),
                    rs.getString("duree_album"),
                    rs.getString("img_album"));
                    tabalbum.addElement(alb);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    public Vector<Album> getTabalbum() {
        return tabalbum;
    }

    public void supprimeAlbum(Album album) {
        BDDConnection bdd;

        try {
            bdd = new BDDConnection();
            String supprimealbrq = "DELETE FROM album WHERE nom_album ='" + album.getNom()+"'";
            bdd.getStatement().executeUpdate(supprimealbrq);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void ajouterAlbum(Album album) {
        BDDConnection bdd;

        try {
            bdd = new BDDConnection();
            String idartiste = "SELECT id_artiste FROM artiste where nom_artiste ='" + album.getArtiste()+"'";
            ResultSet rsid = bdd.getStatement().executeQuery(idartiste);
            if (rsid.next()) {
                String ajouteralbrq = "INSERT INTO album (nom_album, id_artiste, date_album, genre, nbr_piste, duree_album, img_album) VALUES ('" + album.getNom() + "','" + rsid.getInt("id_artiste") + "','" + album.getDate() + "','" + album.getGenre() + "','" + album.getNb_pistes() + "" + album.getDuree() + "','" + album.getImage() + "')";
                bdd.getStatement().executeUpdate(ajouteralbrq);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void modifierAlbum(Album album) {
        BDDConnection bdd;

        try {
            bdd = new BDDConnection();
            String idartiste = "SELECT id_artiste FROM artiste where nom_artiste ='" + album.getArtiste()+"'";
            ResultSet rsid = bdd.getStatement().executeQuery(idartiste);
            if (rsid.next()) {
                String modifieralbrq = "UPDATE album SET nom_album = '" + album.getNom() + "', id_artiste = '" + rsid.getInt("id_artiste") + "', date_album = '" + album.getDate() + "', duree_album = '" + album.getDuree() + "', img_album = '" + album.getImage() + "' WHERE nom_album ='" + album.getNom() + "'";
                bdd.getStatement().executeUpdate(modifieralbrq);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
