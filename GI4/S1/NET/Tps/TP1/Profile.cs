using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP1
{
    class Profile
    {

        public int id { get; set; }
        public string code { get; set; }
        public string libelle { get; set; }


        public Profile(int id , string code , string libelle)
        {
            this.id = id;
            this.code = code;
            this.libelle = libelle;
        }
    }
}
