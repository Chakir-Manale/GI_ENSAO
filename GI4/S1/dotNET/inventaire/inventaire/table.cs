using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace inventaire
{
    class table : Equipment
    {
        float Longuere;
        float largeur;
        public table (float longuere, float largeur):base(123, "operationnel")
        {
            this.Longuere = longuere;
            this.largeur = largeur;
        }

        public override string ToString()
        {
            return "table:Longuere=" + Longuere + "largeur" + largeur;
        }
    }

}
