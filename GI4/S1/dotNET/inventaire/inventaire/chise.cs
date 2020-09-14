using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace inventaire
{
    class chise : Equipment
    {
        string couleur;
        public chise(string couleur): base(123, "operationnel")
        {
            this.couleur = couleur;
        }
        public override string ToString()
        {
            return "chaise couleur:"+couleur;
        }

    }
}
