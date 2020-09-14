using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap7
{
    public static class Extension
    {
        public static string ObjectToString(this ProductsInfo.ProductDTO dto)
        {
            return dto.ID + "- " + dto.Label + " " + dto.Price + " € (" + dto.Category + ")";
        }
    }
}
