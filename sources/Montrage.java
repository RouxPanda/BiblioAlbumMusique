import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

public class Montrage extends JPanel{
    private JPanel principal;
    private JPanel Ptemp;
    private Album temp;
    private Vector<Album> test;
    private Vector<Album> voulu;
    Album rien = new Album("Rien100Rien", "Jul", "2020", 15, 45, "./src/image/rien100rien.jpg");//
    Album myworld = new Album("My World", "Jul", "2017", 15, 45, "./src/image/My_World.jpg");
    Album cmec = new Album("Ce Monde est cruel", "Vald", "2019", 15, 45, "./src/image/cruel.jpg");

    public Montrage(){
        principal = new JPanel();
        principal.setLayout(new CardLayout());
        test = new Vector<>();
        test.add(cmec);test.add(rien);test.add(myworld);
        //test.add(cmec);test.add(rien);test.add(myworld);test.add(cmec);test.add(rien);test.add(cmec);test.add(myworld);test.add(cmec);test.add(rien);test.add(myworld);
        voulu = new Vector<>();
    }

    public JPanel getPrincipal() {
        return principal;
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

    //0 Accueil
    //1 Album
    //2 Recherche
    public void ChangementPanel(int i){
        final CardLayout cl = (CardLayout)(principal.getLayout());
        switch(i) {
            case 1:
                principal.add(Accueil());
                break;
            case 2:
                principal.add(AlbumPresentation(temp));
                cl.last(principal);
                break;
            case 3:
                principal.add(ToutAlbum(voulu));
                cl.last(principal);
        }

    }

    public void RetourArrière(){
        final CardLayout cl = (CardLayout)(principal.getLayout());
        //cl.removeLayoutComponent(Ptemp);
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
        titre.setForeground(Color.lightGray);
        PanelCard.add(ImageSource(album));
        PanelCard.add(titre);
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
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return PanelCard;
    }

    public JScrollPane Accueil(){
        return  ToutAlbum(test);
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
        JLabel date = new JLabel(album.getDate());
        date.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        date.setForeground(Color.WHITE);
        date.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.setBackground(Color.darkGray);
        info.add(titre);
        info.add(vide);
        info.add(artiste);
        info.add(date);

        head.add(ImageSource(album), BorderLayout.LINE_START);
        head.add(info,BorderLayout.CENTER);

        JScrollPane scrool = new JScrollPane();
        PreAlbum.add(head, BorderLayout.PAGE_START);
        PreAlbum.add(scrool, BorderLayout.CENTER);
        System.out.println("Changer");
        return PreAlbum;
    }

    public void Recherche(String recherche){
        voulu.clear();
        voulu = Filtrage(recherche);
        ChangementPanel(3);
    }

    public Vector<Album> Filtrage(String recherche){
        System.out.println(recherche);
        String [] splitRecherche = recherche.split(" ");
        Vector<Album> Stock = new Vector<>();
        for (int i = 0; i < splitRecherche.length; i++) { //Nombre de mot dans la recherche
            for (int u = 0; u < test.size(); u++) { //Pour tout les albums présent
                if(test.elementAt(u).getNom().toLowerCase().contains(splitRecherche[i].toLowerCase()))
                        Stock.add(test.elementAt(u));

            }
        }
        System.out.println(Stock.size());
        return Stock;
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
}


