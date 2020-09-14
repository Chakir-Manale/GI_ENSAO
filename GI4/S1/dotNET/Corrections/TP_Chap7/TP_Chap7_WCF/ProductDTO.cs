using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace TP_Chap7_WCF
{
    [DataContract]
    public class ProductDTO
    {
        [DataMember]
        public int ID { get; set; }
        [DataMember]
        public string Label { get; set; }
        [DataMember]
        public decimal Price { get; set; }
        [DataMember]
        public Category Category { get; set; }
    }
}