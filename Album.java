public class Album {
    private String  nom;
    private String  artiste;
    private String  date;
    private int     nb_pistes;
    private String  duree;
    private String  image;

    public Album(String _nom, String _artiste, String _date, int _nb_pistes, String _duree, String _image){
        nom = _nom;
        artiste = _artiste;
        date = _date;
        nb_pistes = _nb_pistes;
        duree = _duree;
        image = _image;
    }
}
