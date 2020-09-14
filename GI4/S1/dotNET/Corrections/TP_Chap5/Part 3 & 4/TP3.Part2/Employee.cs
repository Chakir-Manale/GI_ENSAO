using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TP2.Part4
{
    public class Employee
    {
        public int EmpId { get; set; }
        public string Name { get; set; }
        public string Sex { get; set; }
        public string Phone { get; set; }
        public Address Address { get; set; }

        public override string ToString()
        {
            return EmpId + "- " + Name + " (" + Sex + ") [" + Address.City + "," + Address.Country + "] - " + Phone; 
        }
    }
}
