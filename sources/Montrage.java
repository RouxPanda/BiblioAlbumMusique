import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Vector;
import java.util.function.UnaryOperator;

/**
 * @author TRONC Clément
 * @author DI PIAZZA Hugo
 * La classe Montrage qui est le panneau principal dans le programme
 *
 * Il est particulier car il est formé en couche qui s'efface pour revenir à l'origine Accueil()
 */
public class Montrage extends JPanel implements ActionListener {
    /**
     * L'ensemble de tout
     */
    private JPanel principal;
    /**
     * L'association du Header et Statue
     */
    private JPanel Assemble;
    /**
     * The Header.
     */
    private JPanel Header;
    /**
     * The Statue.
     */
    private JLabel Statue;
    /**
     * La liste de Trie
     */
    private JComboBox Trie;
    /**
     * Le bouton Suppression.
     */
    private JButton Suppression;
    /**
     * Le bouton Modifier.
     */
    private JButton Modifier;
    /**
     * Le booleen qui donne l'indication la Recherche est-elle active ?
     */
    private boolean recherche_active;
    /**
     * Le booleen qui donne l'indication la Recherche est-elle vide ?
     */
    private boolean recherche_vide;
    /**
     * L'album en mémoire Temp.
     */
    private Album temp;
    /**
     * Le résultat de la recherche dans un Vecteur Voulu.
     */
    private Vector<Album> voulu;
    /**
     * L'instance de bibliothèque
     */
    private Bibliotheque biblio;

    /**
     * Instantiates a new Montrage.
     */
    public Montrage(){
        recherche_active = false;
        Assemble = new JPanel();
        Assemble.setLayout(new BorderLayout());
        Assemble.setBackground(Color.darkGray);

        principal = new JPanel();
        principal.setLayout(new CardLayout());

        biblio = new Bibliotheque();
        biblio.InitBDD();

        Statue = new JLabel();
        Statue.setText("Accueil");
        Statue.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        Statue.setForeground(Color.WHITE);

        Border bord = new EmptyBorder(0,1,0,50);
        Statue.setBorder(new CompoundBorder(bord,bord));

        Trie = new JComboBox();
        String s1[] = { "Titre", "Artiste", "Date"};
        Trie = new JComboBox(s1);
        Trie.setSelectedIndex(-1);
        Trie.addActionListener(this);
        Trie.addActionListener(this);

        Header = new JPanel();
        Header.setLayout(new FlowLayout());
        Header.setBackground(Color.darkGray);

        Header.add(Statue);
        Header.add(Trie);

        Assemble.add(Header, BorderLayout.PAGE_START);
        Assemble.add(principal, BorderLayout.CENTER);
        voulu = new Vector<>();
        principal.add(Accueil());
    }

    /**
     * Gets principal.
     *
     * @return the principal
     */
    public JPanel getPrincipal() {
        return Assemble;
    }

    /**
     * Set les paramètres du ligne j panel.
     *
     * @return un j panel
     */
    public JPanel SetLigne(){
        JPanel PanelLigne = new JPanel();
        PanelLigne.setBackground(Color.darkGray);
        PanelLigne.setLayout(new BoxLayout(PanelLigne,BoxLayout.X_AXIS));
        PanelLigne.setSize(200,300);
        return PanelLigne;
    }

    /**
     * Nouvelle ligne j panel complète.
     *
     * @param card1 the card 1e
     * @param card2 the card 2e
     * @param card3 the card 3e
     * @param card4 the card 4e
     * @return la ligne entière j panel
     */
    public JPanel newLigne(Album card1, Album card2, Album card3, Album card4){
        JPanel PanelLigne = SetLigne();
        PanelLigne.add(newcard(card1));
        PanelLigne.add(newcard(card2));
        PanelLigne.add(newcard(card3));
        PanelLigne.add(newcard(card4));

        return PanelLigne;
    }

    /**
     * New ligne j panel pour 3 card
     *
     * @param card1 the card 1
     * @param card2 the card 2
     * @param card3 the card 3
     * @return the j panel
     */
    public JPanel newLigne(Album card1, Album card2, Album card3){
        JPanel PanelLigne = SetLigne();
        PanelLigne.add(newcard(card1));
        PanelLigne.add(newcard(card2));
        PanelLigne.add(newcard(card3));

        return PanelLigne;
    }

    /**
     * New ligne j panel pour 2 card
     *
     * @param card1 the card 1
     * @param card2 the card 2
     * @return the j panel
     */
    public JPanel newLigne(Album card1, Album card2){
        JPanel PanelLigne = SetLigne();
        PanelLigne.add(newcard(card1));
        PanelLigne.add(newcard(card2));

        return PanelLigne;
    }

    /**
     * New ligne j panel pour 2 card
     *
     * @param card1 the card 1
     * @return the j panel
     */
    public JPanel newLigne(Album card1){
        JPanel PanelLigne = SetLigne();
        PanelLigne.add(newcard(card1));

        return PanelLigne;
    }

    /**
     * Changement panel.
     * Permet la transfusion de pannel entre
     * 2: l'album présentation
     * 3: La page de recherche
     *
     * @param i the
     */
    public void ChangementPanel(int i){
        final CardLayout cl = (CardLayout)(principal.getLayout());
        switch(i) {
            case 2:
                principal.add(AlbumPresentation(temp));
                Statue.setText("");
                Trie.setVisible(false);
                cl.last(principal);
                break;
            case 3:
                JLabel pas = new JLabel();
                if(recherche_vide) {
                    JPanel vide = new JPanel();
                    pas.setText("Aucun résultat");
                    pas.setFont(new Font("Comic Sans",0,24));
                    pas.setForeground(Color.lightGray);
                    vide.setBackground(Color.darkGray);
                    vide.add(pas);
                    principal.add(vide);
                }
                else
                    principal.add(ToutAlbum(voulu));
                Statue.setText("Recherche");
                cl.last(principal);
                break;
        }
    }

    /**
     * Retour arrière.
     * Permet de revenir en arrière dans les onglets et d'avancer les onglets au fur et à mesure pour ne pas surcharger la mémoire
     */
    public void RetourArrière(){
        System.out.println(principal.getComponentCount());
        final CardLayout cl = (CardLayout)(principal.getLayout());
        Trie.setVisible(true);

        if(principal.getComponentCount()>1) {
            if (principal.getComponentCount() == 2) {
                Statue.setText("Accueil");
                cl.first(principal);
                recherche_active = false;
            }
            else {
                Statue.setText("Recherche");
                cl.previous(principal);
            }
            principal.remove(principal.getComponentCount() - 1);
        }
    }

    /**
     * Refresh la page principal pour une modif ou un ajout
     */
    public void Refresh(){
        final CardLayout cl = (CardLayout)(principal.getLayout());
        principal.remove(0);
        principal.add(Accueil(),0);
        cl.first(principal);
    }

    /**
     * Newcard j panel.
     * La card est pour chaque Album présenter et se compose d'un titre et d'une image clickable
     *
     * @param album the album
     * @return the j panel
     */
    public JPanel newcard(Album album){
        JPanel PanelCard = new JPanel();
        PanelCard.setLayout(new BoxLayout(PanelCard,BoxLayout.Y_AXIS));
        PanelCard.setSize(new Dimension(200,250));
        Border bord = new EmptyBorder(1,1,1,1);

        JLabel titre = new JLabel();
        titre.setText(album.getNom());
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(new CompoundBorder(bord,bord));
        titre.setFont(new Font("Comic Sans",0,15));
        titre.setForeground(Color.white);

        JLabel auteur = new JLabel();
        auteur.setText(album.getArtiste());
        auteur.setAlignmentX(Component.CENTER_ALIGNMENT);
        auteur.setBorder(new CompoundBorder(bord,bord));
        auteur.setFont(new Font("Comic Sans",0,12));
        auteur.setForeground(Color.lightGray);
        JLabel image = ImageURL(album);

        PanelCard.add(image);
        PanelCard.add(titre);
        PanelCard.add(auteur);
        PanelCard.setBackground(Color.darkGray);

        Border margin = new EmptyBorder(5, 8, 15, 8);
        Border compound = new CompoundBorder(margin,margin);
        PanelCard.setBorder(compound);
        PanelCard.setAlignmentY(100);
        PanelCard.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                temp = album;
                ChangementPanel(2);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {//Mouse survole. Noircir
                Border line = new LineBorder(Color.white);
                image.setBorder(line);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                image.setBorder(null);
            }
        });
        return PanelCard;
    }

    /**
     * Accueil. Donne la fenêtre d'accueil principal
     *
     * @return the j scroll pane
     */
    public JScrollPane Accueil(){
        return  ToutAlbum(biblio.getTabalbum());
    }

    /**
     * Affiche l'ensemble des albums à partir d'un vecteur avec les newligne
     *
     * @param liste the liste
     * @return the j scroll pane
     */
    public JScrollPane ToutAlbum(Vector<Album> liste){
        JPanel Montrage = new JPanel();
        Montrage.setBackground(Color.darkGray);
        Montrage.setLayout(new BoxLayout(Montrage,BoxLayout.Y_AXIS));

        int taille4 = liste.size() - liste.size()%4;
        int reste = liste.size()%4;

        for (int i = 0; i < taille4; i+=4) {
            Montrage.add(newLigne(liste.elementAt(i),liste.elementAt(i+1),liste.elementAt(i+2),liste.elementAt(i+3)));
        }
        if (reste != 0){
            if(reste == 3){
                Montrage.add(newLigne(liste.elementAt(taille4),liste.elementAt(taille4+1),liste.elementAt(taille4+2)));
            }
            else if(reste == 2){
                Montrage.add(newLigne(liste.elementAt(taille4),liste.elementAt(taille4+1)));
            }
            else if(reste == 1){
                Montrage.add(newLigne(liste.elementAt(taille4)));
            }
        }

        Border margin2 = new EmptyBorder(10, 15, 5, 1);
        Border compound1 = new CompoundBorder(margin2, margin2);
        Montrage.setBorder(compound1);
        JScrollPane scroll = new JScrollPane(Montrage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        return scroll;
    }

    /**
     * Album presentation j panel.
     * Ce que donne un album quand on click dessus.
     * Outre les infos on peut aussi modifier ou supprimer les albums
     *
     * @param album the album
     * @return the j panel
     */
    public JPanel AlbumPresentation(Album album){
        Border bord = new EmptyBorder(40,40,10,10);
        Border bord2 = new EmptyBorder(40,0,10,10);
        Border bord3 = new EmptyBorder(100,0,0,10);

        Suppression = new JButton();
        Suppression.setText("Supprimer");
        Suppression.setForeground(Color.WHITE);
        Suppression.setBackground(Color.gray);
        Suppression.setActionCommand("Supprimer");
        Suppression.setAlignmentX(Component.CENTER_ALIGNMENT);
        Suppression.addActionListener(this);

        Modifier = new JButton();
        Modifier.setText("Modifier");
        Modifier.setForeground(Color.WHITE);
        Modifier.setBackground(Color.gray);
        Modifier.setActionCommand("Modifier");
        Modifier.setAlignmentX(Component.CENTER_ALIGNMENT);
        Modifier.addActionListener(this);

        JLabel vide2 = new JLabel("   ");

        JPanel PreAlbum = new JPanel();
        PreAlbum.setBackground(Color.darkGray);
        PreAlbum.setLayout(new BorderLayout());

        JPanel head = new JPanel();
        head.setBackground(Color.darkGray);
        head.setLayout(new BorderLayout());

        JLabel titre = new JLabel(album.getNom());
        titre.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        titre.setForeground(Color.WHITE);
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel vide = new JLabel("   ");
        JLabel artiste = new JLabel(album.getArtiste());
        artiste.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        artiste.setForeground(Color.WHITE);
        artiste.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel date = new JLabel(album.getDate()+" • ");
        date.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        date.setForeground(Color.lightGray);
        date.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel genre = new JLabel(album.getGenre()+" • ");
        genre.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        genre.setForeground(Color.lightGray);
        genre.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel duree = new JLabel(album.getDuree()+ " minutes");
        duree.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        duree.setForeground(Color.lightGray);
        duree.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel nb = new JLabel("Nombres de pistes : " + album.getNb_pistes());
        nb.setBorder(bord3);
        nb.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        nb.setForeground(Color.WHITE);
        nb.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel ligne = new JPanel();
        ligne.setLayout(new FlowLayout());
        ligne.setBackground(Color.darkGray);
        ligne.add(date);
        ligne.add(genre);
        ligne.add(duree);

        JPanel info = new JPanel();
        info.setBorder(bord2);
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.setBackground(Color.darkGray);
        info.add(titre);
        info.add(vide);
        info.add(artiste);
        info.add(ligne);

        JLabel image = ImageURL(album);
        image.setBorder(bord);
        head.add(image, BorderLayout.LINE_START);
        head.add(info,BorderLayout.CENTER);

        JPanel bouton = new JPanel();
        bouton.setLayout(new BoxLayout(bouton,BoxLayout.Y_AXIS));
        bouton.add(Modifier);
        bouton.add(vide2);
        bouton.add(Suppression);
        bouton.setBorder(bord2);
        bouton.setBackground(Color.darkGray);
        head.add(bouton, BorderLayout.LINE_END);

        //JScrollPane scrool = new JScrollPane();
        JPanel scrool = new JPanel();
        scrool.setBackground(Color.darkGray);
        scrool.add(nb);
        PreAlbum.add(head, BorderLayout.PAGE_START);
        PreAlbum.add(scrool, BorderLayout.CENTER);
        return PreAlbum;
    }

    /**
     * Fonction Recherche qui à partir du string de la recherche voulu, donne le retour de la recherche
     *
     * @param recherche the recherche
     */
    public void Recherche(String recherche){
        voulu.clear();
        voulu = Filtrage(recherche);
        recherche_active = true;
        if(voulu.size() == 0)
            recherche_vide = true;
        else{
            recherche_vide = false;
        }
        ChangementPanel(3);
    }

    /**
     * Filtre le vecteur en fonction de la recherche désirer
     *
     * @param recherche la recherche
     * @return le vector des albums rechercher
     */
    public Vector<Album> Filtrage(String recherche){
        Vector<Album> Stock = new Vector<>();
        for (int u = 0; u < biblio.getTabalbum().size(); u++) { //Pour tout les albums présent
            if(biblio.getTabalbum().elementAt(u).getNom().toLowerCase().contains(recherche.toLowerCase()))
                Stock.add(biblio.getTabalbum().elementAt(u));
            if(biblio.getTabalbum().elementAt(u).getGenre().toLowerCase().contains(recherche.toLowerCase()))
                Stock.add(biblio.getTabalbum().elementAt(u));
            if(biblio.getTabalbum().elementAt(u).getArtiste().toLowerCase().contains(recherche.toLowerCase()))
                Stock.add(biblio.getTabalbum().elementAt(u));
            if(biblio.getTabalbum().elementAt(u).getDate().toLowerCase().contains(recherche.toLowerCase()))
                Stock.add(biblio.getTabalbum().elementAt(u));
        }
        return Stock;
    }

    /**
     * Ajout d'un album
     *
     * @param album the album
     */
    public void Ajout(Album album){
        biblio.getTabalbum().add(album);
        biblio.ajouterAlbum(album);
        Refresh();
    }

    /**
     * Modification d'un album depuis sa présentation
     *
     * @param album the album
     */
    public void Modifier(Album album){
        for (int i = 0; i < biblio.getTabalbum().size(); i++) {
            if (temp.getNom() == biblio.getTabalbum().elementAt(i).getNom() /*&& temp.getArtiste() == biblio.getTabalbum().elementAt(i).getArtiste()*/) {
                biblio.getTabalbum().set(i, album);
                biblio.modifierAlbum(biblio.getTabalbum().elementAt(i));
            }
        }
        Refresh();
        RetourArrière();
    }

    /**
     * Trie par le choix voulu. Par Artiste, Titre ou Date
     *
     * @param choix the choix
     */
    public void TriePar(String choix){
        Vector<Album> Retour;

        if (recherche_active)
            Retour = voulu;
        else
            Retour = biblio.getTabalbum();

        if (choix == "Titre"){
            Retour.sort(Comparator.comparing(Album::getNom,String.CASE_INSENSITIVE_ORDER));
        }
        else if (choix == "Artiste"){
            Retour.sort(Comparator.comparing(Album::getNom,String.CASE_INSENSITIVE_ORDER));
            Retour.sort(Comparator.comparing(Album::getArtiste,String.CASE_INSENSITIVE_ORDER));
        }
        else if (choix == "Date"){
            Retour.sort(Comparator.comparing(Album::getNom,String.CASE_INSENSITIVE_ORDER));
            Retour.sort(Comparator.comparing(Album::getDate,String.CASE_INSENSITIVE_ORDER));
        }

        for (int i = 0; i < Retour.size(); i++) {
            biblio.getTabalbum().set(i,Retour.elementAt(i));
        }
        Refresh();
    }

    /**
     * Image url j label. Télécharge et retourne une image un label contenant l'image.
     *
     * @param album the album
     * @return the j label
     */
    public JLabel ImageURL(Album album){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new URL(album.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(200, 200,Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(dimg);
        JLabel image = new JLabel();
        image.setIcon(icon);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        return image;
    }

    /**
     * S'actionne en fonction des boutons Supprimer, Modifier ou la combobox de trie
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Supprimer")) {
            if (JOptionPane.showConfirmDialog(Montrage.this, "Désirez-vous supprimer cet album ?")
                    == JOptionPane.YES_OPTION) {
                for (int i = 0; i < biblio.getTabalbum().size(); i++) {
                    if (temp.getNom() == biblio.getTabalbum().elementAt(i).getNom() && temp.getArtiste() == biblio.getTabalbum().elementAt(i).getArtiste()) {
                        biblio.supprimeAlbum(biblio.getTabalbum().elementAt(i));
                        biblio.getTabalbum().remove(i);
                    }
                }
                Refresh();
                RetourArrière();
            }
        }
        else if(e.getActionCommand().equals("Modifier")){
            Modifier modifier = new Modifier(this,temp);
        }
        else {
            JComboBox cb = (JComboBox) e.getSource();
            String petName = (String) cb.getSelectedItem();
            if (cb.getSelectedIndex() != -1)
                TriePar(petName);
        }
    }
}


