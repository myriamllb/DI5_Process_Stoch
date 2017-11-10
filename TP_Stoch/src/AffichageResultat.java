import javax.swing.*;
import java.awt.event.*;

/**
 * Created by lelib on 23/10/2017.
 */
public class AffichageResultat {
    private JLabel Rho;
    private JLabel NbMoyClientSysteme;
    private JLabel NbMoyenClientFile;
    private JLabel dureeAttenteMoyenneSysteme;
    private JLabel dureeAttenteFile;
    private JLabel PtLab;
    private JLabel PxLab;
    private JPanel panel;

    public AffichageResultat(double Ro,double L, double Lq, double W, double Wq, double Pt,double t, double Px,double x) {
        Rho.setText("ρ="+Double.toString(Ro));
        NbMoyClientSysteme.setText("L="+Double.toString(L));
        NbMoyenClientFile.setText("Lq="+Double.toString(Lq));
        dureeAttenteMoyenneSysteme.setText("W="+Double.toString(W));
        dureeAttenteFile.setText("Wq="+Double.toString(Wq));
        PtLab.setText("P(τ>"+t+")="+Double.toString(Pt));
        PxLab.setText("P(X="+x+")="+Double.toString(Px));
    }
    public JPanel getPanel(){
        return panel;
    }

}
