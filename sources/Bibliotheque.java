import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * The type Bibliotheque.
 */
//Contient l'entierté de nos albums
public class Bibliotheque {

    /**
     * The Tabalbum.
     */
    private Vector<Album> tabalbum;
    /**
     * The Bdd.
     */
    private BDDConnection bdd;

    /**
     * Instantiates a new Bibliotheque.
     */
    Bibliotheque() {
        tabalbum = new Vector<Album>();
    }

    /**
     * Init bdd.
     */
    public void InitBDD(){
        Album alb;
        try {
            bdd = new BDDConnection();
            String albumres = "SELECT * FROM album A INNER JOIN artiste T ON  A.id_artiste = T.id_artiste";
            ResultSet rs = bdd.getStatement().executeQuery(albumres);
            System.out.println(rs.toString());
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
        for (int i = 0; i < tabalbum.size(); i++) {
            System.out.println(tabalbum.elementAt(i).getNom());
        }
    }

    /**
     * Gets tabalbum.
     *
     * @return the tabalbum
     */
    public Vector<Album> getTabalbum() {
        return tabalbum;
    }

    /**
     * Supprime album.
     *
     * @param album the album
     */
    public void supprimeAlbum(Album album) {
        try {
            String supprimealbrq = "DELETE FROM album WHERE nom_album ='" + album.getNom()+"'";
            bdd.getStatement().executeUpdate(supprimealbrq);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Ajouter album.
     *
     * @param album the album
     */
    public void ajouterAlbum(Album album) {
        try {
            String nom_artiste = "SELECT nom_artiste, id_artiste FROM artiste WHERE LOWER(nom_artiste) = LOWER('" + album.getArtiste()+"')";
            ResultSet rsnom = bdd.getStatement().executeQuery(nom_artiste);
            //System.out.println(!rsnom.isBeforeFirst());
            if(!rsnom.isBeforeFirst()){
                String nvartiste = "INSERT INTO artiste (nom_artiste) VALUES ('" + album.getArtiste() + "');";
                bdd.getStatement().executeUpdate(nvartiste);
                rsnom = bdd.getStatement().executeQuery(nom_artiste);
            }

            String ajouteralbrq = "INSERT INTO album (id_artiste, nom_album, img_album, nbr_piste, duree_album, date_album, genre) VALUES ('" + rsnom.getInt("id_artiste") + "','" + album.getNom() + "','" + album.getImage() + "','" + album.getNb_pistes() + "','" + album.getDuree() + "','" + album.getDate() + "','" + album.getGenre() + "')";
            bdd.getStatement().executeUpdate(ajouteralbrq);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Modifier album.
     *
     * @param album the album
     */
    public void modifierAlbum(Album album) {
        try {
            String nom_artiste = "SELECT nom_artiste, id_artiste FROM artiste WHERE LOWER(nom_artiste) = LOWER('" + album.getArtiste()+"')";
            ResultSet rsnom = bdd.getStatement().executeQuery(nom_artiste);

            if(!rsnom.isBeforeFirst()){
                String nvartiste = "INSERT INTO artiste (nom_artiste) VALUES ('" + album.getArtiste() + "');";
                bdd.getStatement().executeUpdate(nvartiste);
                rsnom = bdd.getStatement().executeQuery(nom_artiste);
            }

            String modifieralbrq = "UPDATE album SET nom_album = '" + album.getNom() + "', id_artiste = '" + rsnom.getInt("id_artiste") + "', date_album = '" + album.getDate() + "', duree_album = '" + album.getDuree() + "', img_album = '" + album.getImage() + "' WHERE nom_album ='" + album.getNom() + "'";
            bdd.getStatement().executeUpdate(modifieralbrq);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
