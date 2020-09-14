using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap6_Part1
{
    public class StudentDTO
    {
        public int ID { get; set; }
        public string LastName { get; set; }
        public string FirstName { get; set; }
        public string CNE { get; set; }

        public override string ToString()
        {
            return ID + "- " + LastName + " " + FirstName + " (" + CNE + ")";
        }
    }
}
