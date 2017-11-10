/**
 * Created by lelib on 23/10/2017.
 */
public class MMS {

    private double lambda;
    private double mu;
    private double t;
    private double Ro;
    private int S;


    public MMS(double lambda, double mu, int s)
    {
        this.lambda = lambda;
        this.mu = mu;
        S = s;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public int getS() {
        return S;
    }

    public void setS(int s) {
        S = s;
    }



    // Méthods

    /**
     * Calcul du Ro pour vérifier l'apparition ou non d'un bouchon
     * dans le systeme
     *
     * @return : La valeur de Ro
     */
    public double calculRo()
    {
        this.Ro = lambda / (S * mu);

        return Ro;
    }

    /**
     * Calcul du nombre moyen de clients dans le syteme avec file de type M|M|S
     *
     * @return L : le nombre moyen
     */
    public double nombreMoyenClientSysteme()
    {
        double L, W;

        W = dureeMoyenneAttenteSysteme();

        L = lambda * W;

        return L;
    }

    /**
     * Calcul du nombre moyen de clients dans la file d'attente d'une file de type M|M|S
     *
     * @return Lo : le nombre moyen
     */
    public double nombreMoyenClientFile()
    {
        double Lq, sFact, Qo;

        sFact = factorielle(this.S);
        Qo = calculProbabiliteQ0();

        Lq = Qo * ((Math.pow(this.Ro * S, S) * this.Ro) / (sFact * Math.pow(1 - this.Ro, 2)));

        return Lq;
    }

    /**
     * Calcul de la durée moyenne d'attente dans le système avec file de type M|M|S
     *
     * @return W : la durée moyenne
     */
    public double dureeMoyenneAttenteSysteme()
    {
        double W, Wq;

        Wq = dureeMoyenneAttenteFile();

        W = Wq + (1 / mu);

        return W;
    }

    /**
     * Calcul de la durée moyenne dans la file d'attente d'une file de type M|M|S
     *
     * @return Wq : la durée moyenne
     */
    public double dureeMoyenneAttenteFile()
    {
        double Wq, Lq;

        Lq = nombreMoyenClientFile();

        Wq = Lq / lambda;

        return Wq;
    }

    /**
     * Calcul d'une probabilite d'etre dans un état j
     *
     * @return : Qj : La propabilité
     */
    public double calculProbabiliteQ0()
    {
        double Qo, jFact, sFact, calculSomme = 0;

        for (int j = 0; j < S; j++)
        {
            jFact = factorielle(j);

            calculSomme += (Math.pow(this.Ro * S, j) / jFact);
        }

        sFact = factorielle(this.S);

        Qo = 1 / (calculSomme + ((Math.pow(this.Ro * S, S) / (sFact * (1 - this.Ro)))));

        return Qo;
    }

    /**
     * Calcul d'une probabilite d'etre dans un état j
     *
     * @param etat : Etat j donné
     *
     * @return : Qj : La propabilité
     */
    public double calculProbabilite(int etat)
    {
        double Qj = 0, Qo, sFact, jFact;

        Qo = calculProbabiliteQ0();

        if (etat > S)
        {
            sFact = factorielle(S);
            Qj = ((Math.pow(S, S) * Math.pow(Ro, etat)) / sFact) * Qo;
        }
        else if (etat >= 0)
        {
            jFact = factorielle(etat);
            Qj = (Math.pow(Ro * S, etat) / jFact) * Qo;
        }

        return Qj;
    }

    /**
     * Durée de séjour dans le systeme P(to > t) avec file M|M|S
     *
     * @param t : le temps
     *
     * @return Pto : La probabilite
     */
    public double calculProbabiliteAvecTemps(double t)
    {
        double Pto, sFact, Qo;

        sFact = factorielle(S);
        Qo = calculProbabiliteQ0();

        Pto = Math.exp(-mu * t) * (1 + ((Qo * Math.pow(Ro * S, S)) / (sFact * (1 - Ro))) * ((1 - Math.exp(-mu * t * (S - 1 - Ro * S))) / (S - 1 - Ro * S)));

        return Pto;
    }

    /**
     * Durée d'attente sans service dans le système P(Toq > 0) avec file M|M|S
     *
     * @return Ptoq : La probabilité
     */
    public double calculProbabiliteAvecTempsSansServiceTo()
    {
        double Ptoq, sFact, Qo;

        Qo = calculProbabiliteQ0();
        sFact = factorielle(S);

        Ptoq = (Qo * Math.pow(Ro * S, S)) / (sFact * (1 - Ro));

        return Ptoq;
    }

    /**
     * Durée d'attente sans service dans le système P(Toq > t) avec file M|M|S
     *
     * t : le Temps
     *
     * @return Ptoq : La probabilité
     */
    public double calculProbabiliteAvecTempsSansService(double t)
    {
        double Ptoq, PtoqTo;

        PtoqTo = calculProbabiliteAvecTempsSansServiceTo();

        Ptoq = Math.exp(-S * mu * t * (1 - Ro)) * PtoqTo;

        return Ptoq;
    }

    /**
     * Calcul d'une factorielle
     * @param n : La valeur de factoriel demandé n!
     * @return fac : Le résultat
     */
    public static int factorielle(int n)
    {
        int fac = 1;

        for (int i=1; i<=n; i++)
        {
            fac *= i;
        }

        return fac;
    }
}
