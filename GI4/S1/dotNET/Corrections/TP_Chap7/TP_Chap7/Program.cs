using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TP_Chap7
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Vous cherchez quel produit ?");
            string search = Console.ReadLine();

            ProductsInfo.ProductsInfoClient client = new ProductsInfo.ProductsInfoClient();

            List<ProductsInfo.ProductDTO> result = client.GetProductsInformation(search);

            if (result.Count > 0)
                result.ForEach(p => Console.WriteLine(p.ObjectToString()));
            else
                Console.WriteLine("Aucun résultat");

            Console.Read();
        }
    }
}
