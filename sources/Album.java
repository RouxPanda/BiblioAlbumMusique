public class Album {
    private String  nom;
    private String  artiste;
    private String  date;
    private String genre;
    private String nb_pistes;
    private String duree;
    private String  image;

    public Album(String _nom, String _artiste, String _date, String _genre, String _nb_pistes, String _duree, String _image){
        nom = _nom;
        artiste = _artiste;
        date = _date;
        genre = _genre;
        nb_pistes = _nb_pistes;
        duree = _duree;
        image = _image;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getDate() {
        return date;
    }

    public String getDuree() {
        return duree;
    }

    public String getGenre() {
        return genre;
    }

    public String getNb_pistes() {
        return nb_pistes;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

}
