using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace inventaire
{
    class Ordinateur : Equipment
    {
        //attributs
        string marque;
        float taille;
        public Ordinateur(string marque, float taille) : base(123,"operationnel")
        {
            this.marque = marque;
            this.taille = taille;
        }
        
        public  override string ToString()
        {
            return "ordinateur: marque=" + marque + "taille" + taille;
        }
    }
}
