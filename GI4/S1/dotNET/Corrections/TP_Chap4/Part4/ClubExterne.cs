using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TP2
{
    public class ClubExterne : Club
    {
        private decimal tauxParticipation;

        public override decimal Solde
        {
            get
            {
                return base.Solde - ((base.Solde * tauxParticipation) / 100);
            }
        }

        public ClubExterne(int Budget, decimal TauxParticipation)
        {
            this.Budget = Budget;
            tauxParticipation = TauxParticipation;
        }

        public override void AfficherResume()
        {
            Console.WriteLine("\n*****************************************************************");
            Console.WriteLine("Nom du club externe : " + Nom);
            Console.WriteLine("Solde : " + Solde);
            Console.WriteLine("Budget : " + Budget);
            Console.WriteLine("Taux de participation : " + tauxParticipation);
            Console.WriteLine("Montant de participation : " + ((base.Solde * tauxParticipation) / 100));
            Console.WriteLine("\n\nActivités du club :\n");
            foreach (var activite in listActivites)
            {
                AfficherActivite(activite);
            }
            Console.WriteLine("*****************************************************************\n");
        }
    }
}
