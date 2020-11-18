import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.Vector;

public class Montrage extends JPanel implements ActionListener {
    private JPanel principal;
    private JPanel Assemble;
    private JPanel Header;
    private JLabel Statue;
    private JComboBox Trie;
    private JButton Suppression;
    private JButton Modifier;
    private boolean recherche_active;
    private boolean recherche_vide;
    private Album temp;
    private Vector<Album> test;
    private Vector<Album> voulu;
    private Bibliotheque biblio;
    Album rien = new Album("Rien100Rien", "Jul", "2019", "rap", "45", "https://images-na.ssl-images-amazon.com/images/I/61fFT2KEbAL.jpg");//
    Album myworld = new Album("My World", "Jul", "2015", "rap", "45", "https://static.fnac-static.com/multimedia/Images/FR/NR/1b/46/74/7620123/1540-1/tsp20160205134438/My-world.jpg");
    Album cmec = new Album("Ce Monde est cruel", "Vald", "2019", "rap", "45", "https://static.fnac-static.com/multimedia/Images/FR/NR/29/94/ad/11375657/1540-1/tsp20190917101105/Ce-monde-est-cruel.jpg");
    Album monument = new Album("Monument","Alkpote", "2019","rap","45","https://static.fnac-static.com/multimedia/Images/FR/NR/68/88/b0/11569256/1540-1/tsp20191125114100/Monument.jpg");
    Album agartha = new Album("Agartha", "Vald", "2017", "rap", "66", "https://static.fnac-static.com/multimedia/Images/FR/NR/4b/e9/81/8513867/1540-1/tsp20161215132633/Agartha.jpg");

    public Montrage(){
        recherche_active = false;
        Assemble = new JPanel();
        Assemble.setLayout(new BorderLayout());
        Assemble.setBackground(Color.darkGray);

        principal = new JPanel();
        principal.setLayout(new CardLayout());

        biblio = new Bibliotheque();
        test = new Vector<>();
        test.add(rien);test.add(myworld);test.add(cmec);test.add(monument);test.add(agartha);
        //test.add(cmec);test.add(rien);test.add(myworld);test.add(cmec);test.add(rien);test.add(cmec);test.add(myworld);test.add(cmec);test.add(rien);test.add(myworld);

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

    public JPanel getPrincipal() {
        return Assemble;
    }

    public JPanel SetLigne(){
        JPanel PanelLigne = new JPanel();
        PanelLigne.setBackground(Color.darkGray);
        PanelLigne.setLayout(new BoxLayout(PanelLigne,BoxLayout.X_AXIS));
        PanelLigne.setSize(200,300);
        return PanelLigne;
    }

    public JPanel newLigne(Album card1, Album card2, Album card3, Album card4){
        JPanel PanelLigne = SetLigne();
        PanelLigne.add(newcard(card1));
        PanelLigne.add(newcard(card2));
        PanelLigne.add(newcard(card3));
        PanelLigne.add(newcard(card4));

        return PanelLigne;
    }
    public JPanel newLigne(Album card1, Album card2, Album card3){
        JPanel PanelLigne = SetLigne();
        PanelLigne.add(newcard(card1));
        PanelLigne.add(newcard(card2));
        PanelLigne.add(newcard(card3));

        return PanelLigne;
    }
    public JPanel newLigne(Album card1, Album card2){
        JPanel PanelLigne = SetLigne();
        PanelLigne.add(newcard(card1));
        PanelLigne.add(newcard(card2));

        return PanelLigne;
    }
    public JPanel newLigne(Album card1){
        JPanel PanelLigne = SetLigne();
        PanelLigne.add(newcard(card1));

        return PanelLigne;
    }

    //1 Accueil
    //2 Album
    //3 Recherche
    public void ChangementPanel(int i){
        final CardLayout cl = (CardLayout)(principal.getLayout());
        switch(i) {
            case 1:

                break;
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

    public void Refresh(){
        final CardLayout cl = (CardLayout)(principal.getLayout());
        principal.remove(0);
        principal.add(Accueil(),0);
        cl.first(principal);
    }

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

    public JScrollPane Accueil(){
        for (int i = 0; i < biblio.getTabalbum().size(); i++) {
            System.out.println(biblio.getTabalbum().elementAt(i).getNom());
        }
        return  ToutAlbum(biblio.getTabalbum());
    }

    public JScrollPane ToutAlbum(Vector<Album> liste){
        JPanel Montrage = new JPanel();
        Montrage.setBackground(Color.darkGray);
        Montrage.setLayout(new BoxLayout(Montrage,BoxLayout.Y_AXIS));

        int taille4 = liste.size() - liste.size()%4;
        int reste = liste.size()%4;
        for (int i = 0; i < taille4/4; i++) {
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

    public JPanel AlbumPresentation(Album album){
        Border bord = new EmptyBorder(40,40,10,10);
        Border bord2 = new EmptyBorder(40,0,10,10);

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
        PreAlbum.add(head, BorderLayout.PAGE_START);
        PreAlbum.add(scrool, BorderLayout.CENTER);
        return PreAlbum;
    }

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

    public Vector<Album> Filtrage(String recherche){
        Vector<Album> Stock = new Vector<>();
        for (int u = 0; u < biblio.getTabalbum().size(); u++) { //Pour tout les albums présent
            if(biblio.getTabalbum().elementAt(u).getNom().toLowerCase().contains(recherche.toLowerCase()))
                Stock.add(biblio.getTabalbum().elementAt(u));
            if(biblio.getTabalbum().elementAt(u).getGenre().toLowerCase().contains(recherche.toLowerCase()))
                Stock.add(biblio.getTabalbum().elementAt(u));
            if(biblio.getTabalbum().elementAt(u).getArtiste().toLowerCase().contains(recherche.toLowerCase()))
                Stock.add(biblio.getTabalbum().elementAt(u));
        }
        return Stock;
    }

    public void Ajout(Album album){
        biblio.getTabalbum().add(album);
        Refresh();
    }

    public void Modifier(Album album){
        System.out.println("Pute");
        for (int i = 0; i < biblio.getTabalbum().size(); i++) {
            if (temp.getNom() == biblio.getTabalbum().elementAt(i).getNom() /*&& temp.getArtiste() == biblio.getTabalbum().elementAt(i).getArtiste()*/)
                biblio.getTabalbum().set(i,album);
        }
        Refresh();
        RetourArrière();
    }

    public void TriePar(String choix){
        Vector<Album> Retour = new Vector<>();
        if (recherche_active)
            Retour = voulu;
        else
            Retour = test;

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

        test = Retour;
        Refresh();
    }

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

    public JLabel ImageSource(Album album){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(album.getImage()));
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

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Supprimer")) {
            if (JOptionPane.showConfirmDialog(Montrage.this, "Désirez-vous supprimer cet album ?")
                    == JOptionPane.YES_OPTION) {
                for (int i = 0; i < biblio.getTabalbum().size(); i++) {
                    if (temp.getNom() == biblio.getTabalbum().elementAt(i).getNom() && temp.getArtiste() == biblio.getTabalbum().elementAt(i).getArtiste())
                        biblio.getTabalbum().remove(i);
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


