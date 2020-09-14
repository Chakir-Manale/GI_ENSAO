using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TP2
{
    public abstract class Club : IFaireActivite
    {
        public string Nom { get; set; }
        public int Budget { get; set; }
        public virtual decimal Solde
        {
            get
            {
                decimal total = Budget;

                foreach (Activite a in listActivites)
                {
                    total += a.Revenus - a.Charges;
                }

                return total;
            }
        }
        protected List<Activite> listActivites;

        public Club()
        {
            listActivites = new List<Activite>();
        }

        public abstract void AfficherResume();
        
        public void FaireActivite(Activite a)
        {
            listActivites.Add(a);
        }

        public void AfficherActivite(Activite a)
        {
            Console.WriteLine("#########################");
            Console.WriteLine("Activité : " + a.Type.ToString());
            Console.WriteLine("-Revenus : " + a.Revenus);
            Console.WriteLine("-Charges : " + a.Charges);
            Console.WriteLine("#########################\n");
        }
    }
}
