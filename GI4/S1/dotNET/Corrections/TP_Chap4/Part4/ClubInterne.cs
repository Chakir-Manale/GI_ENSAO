using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TP2
{
    public class ClubInterne : Club
    {
        public ClubInterne(int Budget)
        {
            this.Budget = Budget;
        }

        public override void AfficherResume()
        {
            Console.WriteLine("\n_________________________________________________________________");
            Console.WriteLine("Nom du club interne : " + Nom);
            Console.WriteLine("Solde : " + Solde);
            Console.WriteLine("Budget : " + Budget);
            Console.WriteLine("\n\nActivités du club :\n");
            foreach (var activite in listActivites)
            {
                AfficherActivite(activite);
            }
            Console.WriteLine("_________________________________________________________________\n");
        }
    }
}
