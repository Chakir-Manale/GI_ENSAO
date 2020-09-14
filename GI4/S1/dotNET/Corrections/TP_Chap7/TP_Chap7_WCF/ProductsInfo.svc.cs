using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace TP_Chap7_WCF
{
    public class ProductsInfo : IProductsInfo
    {
        public List<ProductDTO> GetProductsInformation(string search)
        {
            return GetProductsList().Where(p => p.Label.Contains(search) || p.Category.ToString().Contains(search)).ToList();
        }

        private List<ProductDTO> GetProductsList()
        {
            return new List<ProductDTO>()
            {
                new ProductDTO() { ID = 1, Label = "P1", Price = 20, Category = Category.Clothes },
                new ProductDTO() { ID = 2, Label = "P2", Price = 200, Category = Category.Fruits },
                new ProductDTO() { ID = 3, Label = "P3", Price = 30, Category = Category.Shoes },
                new ProductDTO() { ID = 4, Label = "P4", Price = 2000, Category = Category.Vegetables },
                new ProductDTO() { ID = 5, Label = "P5", Price = 55, Category = Category.Clothes },
                new ProductDTO() { ID = 6, Label = "P6", Price = 20, Category = Category.Clothes },
                new ProductDTO() { ID = 7, Label = "P7", Price = 22, Category = Category.Shoes },
                new ProductDTO() { ID = 8, Label = "P8", Price = 78, Category = Category.Shoes },
                new ProductDTO() { ID = 9, Label = "P9", Price = 999, Category = Category.Vegetables },
                new ProductDTO() { ID = 10, Label = "P10", Price = 5000, Category = Category.Fruits },
            };
        }
    }
}
