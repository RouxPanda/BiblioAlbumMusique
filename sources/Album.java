/**
 * The type Album.
 */
public class Album {
    /**
     * The Nom.
     */
    private String  nom;
    /**
     * The Artiste.
     */
    private String  artiste;
    /**
     * The Date.
     */
    private String  date;
    /**
     * The Genre.
     */
    private String genre;
    /**
     * The Nb pistes.
     */
    private String nb_pistes;
    /**
     * The Duree.
     */
    private String duree;
    /**
     * The Image.
     */
    private String  image;

    /**
     * Instantiates a new Album.
     *
     * @param _nom       the nom
     * @param _artiste   the artiste
     * @param _date      the date
     * @param _genre     the genre
     * @param _nb_pistes the nb pistes
     * @param _duree     the duree
     * @param _image     the image
     */
    public Album(String _nom, String _artiste, String _date, String _genre, String _nb_pistes, String _duree, String _image){
        nom = _nom;
        artiste = _artiste;
        date = _date;
        genre = _genre;
        nb_pistes = _nb_pistes;
        duree = _duree;
        image = _image;
    }

    /**
     * Gets artiste.
     *
     * @return the artiste
     */
    public String getArtiste() {
        return artiste;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets duree.
     *
     * @return the duree
     */
    public String getDuree() {
        return duree;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets nb pistes.
     *
     * @return the nb pistes
     */
    public String getNb_pistes() {
        return nb_pistes;
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

}
