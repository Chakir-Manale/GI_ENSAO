using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace inventaire
{
    class bureau
    {
        int code;
        string description;
        List<Equipment> equipements;

        public bureau(int code, string description, List<Equipment> equipements)
        {
            this.code = code;
            this.description = description;
            this.equipements = equipements;
        }

        public override string ToString()
        {
            return "bureau code="+code+"description"+ description+"nombre"+equipements.Count;
        }

        public void AjouterEquipement(Equipment e)
        {
            equipements.Add(e);

        }
    }
}
