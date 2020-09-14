using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap4_Part3
{
    public abstract class Voiture
    {
        public string Nom { get; set; }
        public string Couleur { get; set; }
        public string Modele { get; set; }
        public string Constructeur { get; set; }

        public abstract void AfficherCaracteristiques();

        public virtual int CalculerVitesse()
        {
            return 200;
        }
    }
}
