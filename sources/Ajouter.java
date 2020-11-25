import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author TRONC Clément
 * @author DI PIAZZA Hugo
 * La fenetre Ajouter qui permet l'ajout d'un album
 * La fenetre est le formulaire permettant l'ajout
 */
public class Ajouter extends JDialog implements ActionListener {
    /**
     * La fenetre principal du formulaire
     */
    private final JFrame fenetre_ajouter;
    /**
     * L'instance de FenetreGraphique depuis son apparition
     */
    private final FenetreGraphique fenetre_principal;
    /**
     * Le bouton Valider.
     */
    private final JButton Valider;
    /**
     * L'Album qui s'ajout
     */
    Album ajout;
    /**
     * The Txt titre.
     */
    private final JTextField txtTitre;
    /**
     * The Txt artiste.
     */
    private final JTextField txtArtiste;
    /**
     * The Txt annee.
     */
    private final JTextField txtAnnee;
    /**
     * The Txt nbpiste.
     */
    private final JTextField txtNbpiste;
    /**
     * The Txt duree.
     */
    private final JTextField txtDuree;
    /**
     * The Txt image.
     */
    private final JTextField txtImage;
    /**
     * The Txt genre.
     */
    private JComboBox txtGenre;

    /**
     * Instantiates a new Ajouter.
     *
     * @param principal the principal
     */
    public Ajouter(FenetreGraphique principal){
        fenetre_ajouter = new JFrame("Ajouter un album");
        ajout = null;
        fenetre_principal = principal;
        txtTitre = new JTextField(10);
        txtArtiste = new JTextField(10);
        txtAnnee = new JTextField(10);
        txtNbpiste = new JTextField(10);
        txtDuree = new JTextField(10);
        txtImage = new JTextField(10);
        txtGenre = new JComboBox();
        String s1[] = { "Classique", "Rap", "Rock", "Electro", "Pop" };
        txtGenre = new JComboBox(s1);

        JLabel lblTitre = new JLabel("Titre", JLabel.RIGHT);
        lblTitre.setDisplayedMnemonic('N');
        lblTitre.setLabelFor(txtTitre);

        JLabel lblArtiste = new JLabel("Artiste:", JLabel.RIGHT);
        lblArtiste.setDisplayedMnemonic('s');
        lblArtiste.setDisplayedMnemonicIndex(5);
        lblArtiste.setLabelFor(txtArtiste);

        JLabel lblAnnee = new JLabel("Année", JLabel.RIGHT);
        lblAnnee.setDisplayedMnemonic('V');
        lblAnnee.setLabelFor(txtAnnee);

        JLabel lblNbpiste = new JLabel("Nombre de piste", JLabel.RIGHT);
        lblAnnee.setDisplayedMnemonic('V');
        lblAnnee.setLabelFor(txtNbpiste);

        JLabel lblGenre = new JLabel("Genre", JLabel.RIGHT);
        lblGenre.setDisplayedMnemonic('V');
        lblGenre.setLabelFor(txtGenre);

        JLabel lblDuree = new JLabel("Durée", JLabel.RIGHT);
        lblDuree.setDisplayedMnemonic('P');
        lblDuree.setLabelFor(txtDuree);

        JLabel lblImage = new JLabel("Image (url)", JLabel.RIGHT);
        lblImage.setDisplayedMnemonic('E');
        lblImage.setLabelFor(txtImage);

        Valider = new JButton();
        Valider.setText("Valider");
        Valider.setActionCommand("Valider");
        Valider.addActionListener(this);

        JPanel p = new JPanel( );
        p.setLayout(new GridLayout(8, 4, 8, 9));
        p.add(lblTitre);
        p.add(txtTitre);
        p.add(lblArtiste);
        p.add(txtArtiste);
        p.add(lblAnnee);
        p.add(txtAnnee);
        p.add(lblNbpiste);
        p.add(txtNbpiste);
        p.add(lblGenre);
        p.add(txtGenre);
        p.add(lblDuree);
        p.add(txtDuree);
        p.add(lblImage);
        p.add(txtImage);
        p.add(Valider);

        fenetre_ajouter.setContentPane(p);
        fenetre_ajouter.setLocationRelativeTo(null);
        fenetre_ajouter.pack( );
        fenetre_ajouter.setVisible(true);
    }

    /**
     * Réalise l'envoie de l'album vers la classe FenetreGraphique lors de la pression du bouton valider
     * @param evenement
     */
    @Override
    public void actionPerformed(ActionEvent evenement){
        if(evenement.getActionCommand().equals("Valider")){
            String titre = txtTitre.getText();
            String artiste = txtArtiste.getText();
            String annee = txtAnnee.getText();
            String nbpiste = txtNbpiste.getText();
            String duree = txtDuree.getText();
            String image = txtImage.getText();
            String genre = txtGenre.getSelectedItem().toString();
            if(!titre.isEmpty() || !artiste.isEmpty() || !annee.isEmpty() || !duree.isEmpty()) {
                if(!testImage(image) && !image.isEmpty())
                    JOptionPane.showMessageDialog(null,"URL non valide",
                            "Erreur",
                            JOptionPane.WARNING_MESSAGE);
                else {
                    if(image.isEmpty())
                        image = "https://images-eu.ssl-images-amazon.com/images/I/61SGCYpjz-L._AC_UL600_SR597,600_.jpg";
                    ajout = new Album(titre, artiste, annee, genre, nbpiste, duree, image);
                    fenetre_principal.AjouterUnAlbum(ajout);

                    fenetre_ajouter.dispose();
                }
            }
        }
    }

    /**
     * Fonction qui test si l'url d'une image est valide ou pas.
     * Retourne un boolean en fonction si l'url est bien une image ou pas
     *
     * @param url the url
     * @return the boolean
     */
    public Boolean testImage(String url){
        try {
            BufferedImage image = ImageIO.read(new URL(url));
            //BufferedImage image = ImageIO.read(new URL("http://someimage.jpg"));
            return image != null;

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            System.err.println("URL error with image");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            System.err.println("IO error with image");
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }
}
