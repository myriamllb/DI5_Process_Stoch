/**
 * Created by lelib on 23/10/2017.
 */
public class MM1K {

    private double lambda;
    private double mu;
    private double ro;
    private double k;

    public MM1K(double lambda, double mu, double k)
    {
        this.lambda = lambda;
        this.mu = mu;
        this.k = k;
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

    public double getRo() {
        return ro;
    }

    public void setRo(double t) {
        this.ro = t;
    }

    public double getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    /**
     * Calcul du Ro pour vérifier l'apparition ou non d'un bouchon
     * dans le systeme
     *
     * @return : La valeur de Ro
     */
    public double calculRo()
    {
        this.ro = lambda / mu;

        return ro;
    }


    /**
     * Calcul du nombre moyen de clients dans le syteme avec file de type M|M|1
     *
     * @return L : le nombre moyen
     */
    public double nombreMoyenClientSysteme()
    {
        double L;

        if (ro == 1)
        {
            L = k / 2;
        }
        else
        {
            double a=(ro*( 1 - (k + 1) * Math.pow(ro, k) + k * Math.pow(ro, k + 1)));
            double b=((1 - ro) * (1 - Math.pow(ro, k + 1)));
            System.out.println(a);
            System.out.println(b);
            L = a / b ;
        }

        return L;
    }

    /**
     * Calcul du nombre moyen de clients dans la file d'attente d'une file de type M|M|1
     *
     * @return Lo : le nombre moyen
     */
    public double nombreMoyenClientFile()
    {
        double L, Lq, Qo;

        L = nombreMoyenClientSysteme();
        Qo = calculProbabilite(0);

        Lq = L - (1 - Qo);

        return Lq;
    }

    /**
     * Calcul de la durée moyenne d'attente dans le système avec file de type M|M|1
     *
     * @return W : la durée moyenne
     */
    public double dureeMoyenneAttenteSysteme()
    {
        double W = 1 / (mu - lambda);

        return W;
    }

    /**
     * Calcul de la durée moyenne dans la file d'attente d'une file de type M|M|1
     *
     * @return Wq : la durée moyenne
     */
    public double dureeMoyenneAttenteFile()
    {
        double Wq = lambda / (mu * (mu - lambda));

        return Wq;
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
        double Qi;

        if (ro == 1)
        {
            Qi = 1 / (k + 1);
        }
        else
        {
            Qi = ((1 - ro) * Math.pow(ro, etat)) / (1 - Math.pow(ro, k + 1));
        }


        Qi = (Math.pow(ro, etat)) * (1 - ro);

        return Qi;
    }

    /**
     * Calcul la probabilite P(to > t)
     *
     * @param t : le temps
     *
     * @return P : La probabilite
     */
    public double calculProbabiliteAvecTemps(double t)
    {
        double P = Math.exp(-mu * (1 - ro) * t);

        System.out.println(t);

        return P;
    }

}
