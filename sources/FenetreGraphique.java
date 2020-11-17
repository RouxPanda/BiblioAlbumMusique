import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class FenetreGraphique extends JPanel {
    JFrame fenetre;
    private JPanel Central;
    private JTextField BarRecherche;
    private JButton Valider;
    private JButton MesAlbums;
    private JButton MesArtistes;
    private JButton MesPlaylists;
    private JComboBox comboBox1;
    private JPanel PanelMenu;
    private JPanel Regroupe;
    private JPanel PannelRecherche;
    private JPanel PannelMontrage;
    Album rien = new Album("Rien100Rien", "Jul", "2020", 15, 45, "image/rien100rien.jpg");
    Album myworld = new Album("My World", "Jul", "2017", 15, 45, "image/My_World.jpg");
    Album cmec = new Album("Ce Monde est cruel", "Vald", "2019", 15, 45, "image/cruel.jpg");
    BDDConnection bdd;
    Bibliotheque bibli;

    FenetreGraphique(){
        System.out.println("test 2");
        bibli = new Bibliotheque();
        System.out.println("test 3");
        fenetre = new JFrame("Bibliothèque");
        fenetre.setSize(1080, 720);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Central = new JPanel();
        Central.setLayout(new BorderLayout(0, 0));
        Regroupe = new JPanel();
        Regroupe.setLayout(new BorderLayout(0, 0));
        Central.add(Regroupe, BorderLayout.CENTER);

        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);

        PannelRecherche = new JPanel();
        PannelRecherche.setBackground(Color.darkGray);
        PannelRecherche.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        Regroupe.add(PannelRecherche, BorderLayout.NORTH);
        final JLabel label1 = new JLabel();
        label1.setText("Recherche");
        label1.setForeground(Color.lightGray);
        PannelRecherche.add(label1);
        BarRecherche = new JTextField();
        BarRecherche.setEditable(true);
        BarRecherche.setText("");
        BarRecherche.putClientProperty("caretWidth", new Integer(300));
        PannelRecherche.add(BarRecherche);

        PannelMontrage = new JPanel();
        PannelMontrage.setBackground(Color.darkGray);
        PannelMontrage.setLayout(new BoxLayout(PannelMontrage,BoxLayout.Y_AXIS));

        PannelMontrage.add(newLigne(rien,cmec,myworld,myworld));
        PannelMontrage.add(newLigne(rien,cmec,rien,cmec));
        PannelMontrage.add(newLigne(rien,cmec,rien,cmec));
        PannelMontrage.add(newLigne(rien,cmec,rien,cmec));
        PannelMontrage.add(newLigne(rien,cmec,rien,cmec));
        Border margin2 = new EmptyBorder(10, 15, 5, 1);
        Border compound1 = new CompoundBorder(margin2, margin2);
        PannelMontrage.setBorder(compound1);
        JScrollPane scroll = new JScrollPane(PannelMontrage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        Regroupe.add(scroll, BorderLayout.CENTER);

        Valider = new JButton();
        Valider.setText("Valider");
        Valider.setForeground(Color.BLACK);
        Valider.setBackground(Color.WHITE);
        Valider.setBorder(compound);
        PannelRecherche.add(Valider);

        PanelMenu = new JPanel();
        PanelMenu.setBackground(Color.darkGray);
        PanelMenu.setLayout(new BoxLayout(PanelMenu,BoxLayout.Y_AXIS));
        MesAlbums = new JButton();
        MesArtistes = new JButton();
        MesPlaylists = new JButton();
        MesAlbums.setBorder(compound);
        MesArtistes.setBorder(compound);
        MesPlaylists.setBorder(compound);
        MesAlbums.setText("Mes Albums");
        MesArtistes.setText("Mes Artistes");
        MesPlaylists.setText("Mes Playlists");
        PanelMenu.add(MesAlbums);
        PanelMenu.add(MesArtistes);
        PanelMenu.add(MesPlaylists);
        Central.add(PanelMenu, BorderLayout.WEST);
        MesAlbums.setForeground(Color.WHITE);
        MesAlbums.setBackground(Color.gray);
        MesAlbums.setBorder(compound);
        MesArtistes.setForeground(Color.WHITE);
        MesArtistes.setBackground(Color.gray);
        MesArtistes.setBorder(compound);
        MesPlaylists.setForeground(Color.WHITE);
        MesPlaylists.setBackground(Color.gray);
        MesPlaylists.setBorder(compound);

        fenetre.add(Central);
        fenetre.setVisible(true);
    }

    public JPanel newLigne(Album card1, Album card2, Album card3, Album card4){
        JPanel PanelLigne = new JPanel();
        PanelLigne.setBackground(Color.darkGray);
        PanelLigne.setLayout(new BoxLayout(PanelLigne,BoxLayout.X_AXIS));
        PanelLigne.setSize(200,300);
        PanelLigne.add(newcard(card1));
        PanelLigne.add(newcard(card2));
        PanelLigne.add(newcard(card3));
        PanelLigne.add(newcard(card4));

        return PanelLigne;
    }

    public JPanel newcard(Album album){
        JPanel PanelCard = new JPanel();
        PanelCard.setLayout(new BoxLayout(PanelCard,BoxLayout.Y_AXIS));
        PanelCard.setSize(new Dimension(200,250));
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(album.getImage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Border bord = new EmptyBorder(1,1,1,1);
        Image dimg = img.getScaledInstance(200, 200,Image.SCALE_SMOOTH);
        Icon icon = new ImageIcon(dimg);
        JLabel image = new JLabel();
        image.setIcon(icon);
        image.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel titre = new JLabel();
        titre.setText(album.getNom());
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        titre.setBorder(new CompoundBorder(bord,bord));
        titre.setFont(new Font("Comic Sans",0,15));
        titre.setForeground(Color.lightGray);
        PanelCard.add(image);
        PanelCard.add(titre);
        PanelCard.setBackground(Color.darkGray);

        Border margin = new EmptyBorder(5, 8, 15, 8);
        Border compound = new CompoundBorder(margin,margin);
        PanelCard.setBorder(compound);
        PanelCard.setAlignmentY(100);

        return PanelCard;
    }
}
