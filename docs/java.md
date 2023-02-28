在本部分中，“发送http请求并得到响应”与“保存http响应图片”分别给了关键部分代码的注释，方便大家学习；也可直接查看“完整代码”部分的使用方法，快速实现该功能。

**代码中需要引入的包以及需要自行设置的参数都进行了标注，当必要的包被引入且参数修改正确后，复制粘贴修改后的代码，并使用提供的调用方法，即可完成该部分工作。**

完整测试代码见：[https://github.com/cls1277/apidocs](https://github.com/cls1277/apidocs)

### 需要引入的包

```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
```

###  发送http请求并得到响应

调用该函数将自动向指定的地址 [http://cbnxzs.com/api](http://cbnxzs.com/api) 发送GET请求，并得到状态码和响应数据流，该函数最终返回图片的数据流内容。**若要得到指定图片，**只需将请求地址改为 [http://cbnxzs.com/api?id=1](http://cbnxzs.com/api?id=1) ，即可实现对第一张图片的请求。

```java
public static InputStream getInputStream() {
    InputStream inputStream = null;
    HttpURLConnection httpURLConnection = null;
    try {
        URL url = new URL("http://cbnxzs.com/api"); // 双引号中内容为请求的地址
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(3000); // 连接超时设置为3秒
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod("GET"); // 请求方式为GET方式
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) { // HTTP状态码200表示成功响应
            inputStream = httpURLConnection.getInputStream();
        }
    } catch (IOException e) { // 必要的异常处理
        e.printStackTrace();
    }
    return inputStream; // 返回响应数据流
}
```

###  保存http响应图片

首先调用“发送http请求并得到响应”部分，之后将响应的数据流通过byte[]的形式保存到文件流中。**在使用时，需要自行设置服务器中要保存的图片的路径。**

```java
public static void saveImageToDisk() {
    InputStream inputStream = getInputStream(); // 发送http请求并得到响应
    byte[] data = new byte[1024];
    int len = 0;
    FileOutputStream fileOutputStream = null;
    try {
        fileOutputStream = new FileOutputStream("E:\\cls1277\\Courses\\img.jpg"); // 服务器中保存图片的路径（需要自行设置）
        while ((len = inputStream.read(data)) != -1) {
            fileOutputStream.write(data, 0, len); // 将响应得到的数据输出
        }
    } catch (IOException e) { // 必要的异常处理
        e.printStackTrace();
    } finally {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 完整代码

当代码中的需要自行设置的参数设置完毕后，在使用整个这一部分时，只需要调用```saveImageToDisk();```函数即可完成发送http请求并保存图片的功能。

```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JavaTest {
    public static String URL_PATH = "http://cbnxzs.com/api"; // 接受get请求的地址

    public static InputStream getInputStream() {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(URL_PATH);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static void saveImageToDisk() {
        InputStream inputStream = getInputStream();
        byte[] data = new byte[1024];
        int len = 0;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("E:\\cls1277\\Courses\\img.jpg"); // 服务器中保存图片的路径（需要自行设置）
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        saveImageToDisk(); // 只需要在主函数中调用saveImageToDisk();即可完成该过程。
    }
}
```

