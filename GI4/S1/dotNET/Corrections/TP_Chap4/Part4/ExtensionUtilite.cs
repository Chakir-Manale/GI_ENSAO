using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TP2
{
    public static class ExtensionUtilite
    {
        public static void ValeurSolde(this Club club)
        {
            string msg = club.Nom + " a un solde ";

            if (club.Solde > 0)
            {
                msg += "positif !";
            }
            else
            {
                msg += "negatif !";
            }

            Console.WriteLine(msg);
        }
    }
}
