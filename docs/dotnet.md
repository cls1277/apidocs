在本部分中，“发送http请求并得到响应”与“保存http响应图片”分别给了关键部分代码的注释，方便大家学习；也可直接查看“完整代码”部分的使用方法，快速实现该功能。

**代码中需要引入的包以及需要自行设置的参数都进行了标注，当必要的包被引入且参数修改正确后，复制粘贴修改后的代码，并使用提供的调用方法，即可完成该部分工作。**

完整测试代码见：[https://github.com/cls1277/apidocs]()

### 需要引入的包

```c#
using System.Net;
```

### 发送http请求并得到响应

调用该部分将自动向指定的地址 [http://cbnxzs.com/api](http://cbnxzs.com/api) 发送GET请求，并得到响应数据流，最终将图片的数据流内容保存到变量```stream```中。**若要得到指定图片，**只需将请求地址改为 [http://cbnxzs.com/api?id=1](http://cbnxzs.com/api?id=1) ，即可实现对第一张图片的请求。

```c#
string url = @"http://cbnxzs.com/api"; // 请求的地址
HttpWebRequest request = (HttpWebRequest)WebRequest.Create(url);
request.Method = "GET"; // 请求方式为GET方式
request.Timeout = 3000; // 连接超时设置为3秒
Stream stream = request.GetResponse().GetResponseStream(); // 得到响应数据流
```

### 保存http响应图片

在将图片的数据流内容保存到变量```stream```中之后，将响应的数据流通过byte[]的形式保存到文件流中。**在使用时，需要自行设置服务器中要保存的图片的路径。**

```c#
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
```

### 完整代码

当代码中的需要自行设置的参数设置完毕后，将以下内容复制粘贴到你的代码对应位置，即可完成发送http请求并保存图片的功能。

```c#
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
```

