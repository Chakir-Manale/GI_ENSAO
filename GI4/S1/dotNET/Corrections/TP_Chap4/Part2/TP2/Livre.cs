using System;

namespace TP2
{
    public class Livre
    {
        public int IdL { get; set; }
        public string TitreL { get; set; }
        public DateTime AnneeL { get; set; }
        public int NbPagesL { get; set; }
        public float PrixL { get; set; }
        public Auteur AuteurL { get; set; }
        public MaisonPublication MaisonL { get; set; }

        public override string ToString()
        {
            return "------------------" + Environment.NewLine +
                   "Livre " + IdL + Environment.NewLine +
                   "Titre : " + TitreL + Environment.NewLine +
                   "------------------";
        }
    }
}
