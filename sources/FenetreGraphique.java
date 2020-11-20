import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The type Fenetre graphique.
 */
public class FenetreGraphique extends JFrame implements ActionListener {
    /**
     * La fenetre principal
     */
    JFrame fenetre;
    /**
     * Le panel central pris par Montrage
     */
    private JPanel Central;
    /**
     * La Bar de recherche.
     */
    private JTextField BarRecherche;
    /**
     * Le bouton Valider.
     */
    private JButton Valider;
    /**
     * Le bouton Ajouter.
     */
    private JButton Ajouter;
    /**
     * Le bouton Retour.
     */
    private JButton Retour;
    /**
     * The Panel menu à droite
     */
    private JPanel PanelMenu;
    /**
     * The Regroupe.
     */
    private JPanel Regroupe;
    /**
     * The Pannel recherche en haut
     */
    private JPanel PannelRecherche;
    /**
     * La classe ajouter qui lance une nouvelle fenêtre formulaire pour l'ajout d'album
     */
    private Ajouter ajouter;
    /**
     * La classe Montrage qui s'occupe de tout l'affichage
     */
    private Montrage montrage;


    /**
     * Instantiates a new Fenetre graphique.
     * <p>
     * Toutes l'interface autour haut et gauche et l'instance de Montrage qui affiche la fenêtre centrale
     */
    FenetreGraphique(){
        //Initialisation
        fenetre = new JFrame("Bibliothèque");
        fenetre.setSize(1200, 720);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Central = new JPanel();
        Central.setLayout(new BorderLayout(0, 0));

        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);

        Regroupe = new JPanel();
        Regroupe.setLayout(new BorderLayout(0, 0));
        Central.add(Regroupe, BorderLayout.CENTER);

        montrage = new Montrage();

        //La barre de recherche
        PannelRecherche = new JPanel();
        PannelRecherche.setBackground(Color.darkGray);
        PannelRecherche.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        Regroupe.add(PannelRecherche, BorderLayout.NORTH);
        Valider = new JButton();
        Valider.setText("Valider");
        Valider.setActionCommand("Valider");
        Valider.addActionListener(this);
        Valider = TransformButton(Valider);
        final JLabel label1 = new JLabel();
        label1.setText("Recherche");
        label1.setForeground(Color.lightGray);
        PannelRecherche.add(label1);
        BarRecherche = new JTextField(30);
        PannelRecherche.add(BarRecherche);
        PannelRecherche.add(Valider);

        JPanel PannelMontrage = new JPanel();
        PannelMontrage.setLayout(new BorderLayout());
        //montrage.ChangementPanel(1);
        PannelMontrage.add(montrage.getPrincipal());

        Regroupe.add(PannelMontrage, BorderLayout.CENTER);


        //Le menu à droite
        PanelMenu = new JPanel();

        PanelMenu.setLayout(new GridBagLayout());
        PanelMenu.setBackground(Color.darkGray);

        GridBagConstraints gbc;

        Ajouter = new JButton();
        Ajouter.setText("Ajouter");
        Ajouter.setActionCommand("Ajouter");
        Ajouter.addActionListener(this);
        TransformButton(Ajouter);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(Ajouter, gbc);

        Retour = new JButton();
        Retour.setText("Retour");
        Retour.setActionCommand("Retour");
        Retour.addActionListener(this);
        TransformButton(Retour);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        PanelMenu.add(Retour, gbc);

        final JLabel label2 = new JLabel();
        label2.setText("     ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        PanelMenu.add(label2, gbc);

        Border bord = new EmptyBorder(0,4,160,4);
        PanelMenu.setBorder(new CompoundBorder(bord,bord));

        fenetre.add(Central);
        fenetre.setVisible(true);

    }

    /**
     * Ajouter un album. La fonction utiliser pour la classe Ajouter
     *
     * @param album the album
     */
    public void AjouterUnAlbum(Album album){
        montrage.Ajout(album);
    }

    /**
     * Transform button j button. Pour répondre aux criteres de beauté de l'interface
     *
     * @param bouton le button qui doit être retourner
     * @return le bouton modifier
     */
    JButton TransformButton(JButton bouton){
        Border line = new LineBorder(Color.WHITE);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        bouton.setForeground(Color.WHITE);
        bouton.setBackground(Color.gray);
        bouton.setBorder(compound);
        return bouton;
    }

        @Override
    public void actionPerformed(ActionEvent evenement){
        if(evenement.getActionCommand().equals("Retour"))
            montrage.RetourArrière();
        if(evenement.getActionCommand().equals("Ajouter")){
            ajouter = new Ajouter(this);
        }
        if(evenement.getActionCommand().equals("Valider")){
            String recherche = BarRecherche.getText();
            String magic = "Le J c'est le S";
            if(!recherche.isEmpty())
                montrage.Recherche(recherche);
            if(recherche.toLowerCase().contains(magic.toLowerCase())) {
                Desktop desktop = java.awt.Desktop.getDesktop();
                try {
                    //specify the protocol along with the URL
                    URI oURL = new URI(
                            "https://www.youtube.com/watch?v=-CVn3-3g_BI");
                    desktop.browse(oURL);
                } catch (URISyntaxException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
