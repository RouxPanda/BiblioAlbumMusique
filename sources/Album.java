public class Album {
    private String  nom;
    private String  artiste;
    private String  date;
    private String genre;
    //private int     nb_pistes;
    private String  duree;
    private String  image;

    public Album(String _nom, String _artiste, String _date, String _genre, String _duree, String _image){
        nom = _nom;
        artiste = _artiste;
        date = _date;
        genre = _genre;
        duree = _duree;
        image = _image;
    }

    public String getDuree() {
        return duree;
    }

    public String getGenre() {
        return genre;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public String getArtiste() {
        return artiste;
    }
}
