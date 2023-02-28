using System.Net;

namespace TestCs
{
    class Program
    {
        static void Main(string[] args)
        {
            string url = @"http://cbnxzs.com/api"; // 请求的地址
            HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
            request.Method = "GET"; // 请求方式为GET方式
            request.Timeout = 3000; // 连接超时设置为3秒
            Stream stream = request.GetResponse().GetResponseStream(); // 得到响应数据流
            byte[] bytes = new byte[8192];
            MemoryStream ms = new MemoryStream();
            int read;
            while ((read = stream.Read(bytes, 0, bytes.Length)) > 0)
            {
                ms.Write(bytes, 0, read);
            }
            FileStream fs=new FileStream("E:\\cls1277\\Courses\\img.jpg", FileMode.Create); // 服务器中保存图片的路径（需要自行设置）
            ms.WriteTo(fs);
            ms.Close();
            fs.Close();
            Console.WriteLine("done");
        }
    }
}
