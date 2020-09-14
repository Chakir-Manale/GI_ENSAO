using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace inventaire
{
   public abstract class Equipment
    {
        //Attributs
        int code; 
        float prix;
        string etat;
        DateTime date;

        //Constructeur
        public Equipment(int code, string Etat)
        {
            this.code = code;
            this.etat = Etat;
        }
       public int Code
       {
            get { return code; }
            set { code = value; }
        }

        public string Etat
        {
            get { return etat; }
            set { etat = value; }
        }

        public float Prix { get; set; }
        public DateTime Date { get; set; }

        public override string ToString()
        {

            return "equipment: code=" + code + ",etat=" + etat + ",prix" + prix +
                ",date=" + date;

           
            
        }
    }
    
}
