import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
        NumberFormat nf = new DecimalFormat("0.###");
        Rho.setText("ρ= "+nf.format(Ro));
        NbMoyClientSysteme.setText("L= "+nf.format(L));
        NbMoyenClientFile.setText("Lq= "+nf.format(Lq));
        dureeAttenteMoyenneSysteme.setText("W= "+nf.format(W));
        dureeAttenteFile.setText("Wq= "+nf.format(Wq));
        PtLab.setText("P(τ>"+t+")= "+nf.format(Pt));
        PxLab.setText("P(X="+x+")= "+nf.format(Px));
    }
    public JPanel getPanel(){
        return panel;
    }

}
