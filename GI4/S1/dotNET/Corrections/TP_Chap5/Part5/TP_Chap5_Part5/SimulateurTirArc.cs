using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap5_Part5
{
    public class SimulateurTirArc
    {
        private Cible? ancienTir;
        private int nombreDeTir;
        private Random random;

        public delegate void TirExcellentDelegate(Cible cible);
        public event TirExcellentDelegate QuandLaCibleChange;

        public SimulateurTirArc(int nombre)
        {
            random = new Random();
            ancienTir = null;
            nombreDeTir = nombre;
        }

        public void Demarrer()
        {
            for (int i = 0; i < nombreDeTir; i++)
            {
                int valeur = random.Next(0, 100);

                if (valeur < 30)
                {
                    GererCible(Cible.Mauvais);
                }
                else
                {
                    if (valeur < 60)
                    {
                        GererCible(Cible.Passable);
                    }
                    else
                    {
                        if (valeur < 90)
                        {
                            GererCible(Cible.Bien);
                        }
                        else
                        {
                            GererCible(Cible.Excellent);
                        }
                    }
                }
            }
        }

        private void GererCible(Cible cible)
        {
            if (ancienTir.HasValue && ancienTir.Value != cible && QuandLaCibleChange != null)
            {
                QuandLaCibleChange(cible);
            }

            ancienTir = cible;
        }
    }
}
