在本部分中，“发送http请求并得到响应”与“保存http响应图片”分别给了关键部分代码的注释，方便大家学习；也可直接查看“完整代码”部分的使用方法，快速实现该功能。

**代码中需要引入的包以及需要自行设置的参数都进行了标注，当必要的包被引入且参数修改正确后，复制粘贴修改后的代码，并使用提供的调用方法，即可完成该部分工作。**

完整测试代码见：[https://github.com/cls1277/apidocs](https://github.com/cls1277/apidocs)

### 需要引入的包

```HTTPRequest```包在[https://github.com/elnormous/HTTPRequest](https://github.com/elnormous/HTTPRequest)中下载。

```cpp
#include <iostream>
#include "HTTPRequest.hpp"
#include <fstream>
#include <sstream>
```

### 发送http请求并得到响应

调用该部分将自动向指定的地址 [http://cbnxzs.com/api](http://cbnxzs.com/api) 发送GET请求，并将响应的结果保存到变量```response```中。**若要得到指定图片，**只需将请求地址改为 [http://cbnxzs.com/api?id=1](http://cbnxzs.com/api?id=1) ，即可实现对第一张图片的请求。

```cpp
http::Request request{"http://cbnxzs.com/api"}; // 请求的地址
const auto response = request.send("GET"); // 请求方式为GET方式
```

### 保存http响应图片

将得到的响应数据转化为```bytes[]```后，使用```ofstream```的```write```函数将响应的内容输出到文件。**在使用时，需要自行设置服务器中要保存的图片的路径。**

如果想了解更多关于```response```属性的信息，可以查看```HTTPRequest.hpp```的源代码。

```cpp
ofstream file;
char * bytes = new char[response.body.size()];
for(int i=0; i<response.body.size(); i++) {
    bytes[i] = response.body[i];
}
file.open("cls1277.jpg" ,ios::binary); // 服务器中保存图片的路径（需要自行设置）
file.write(bytes,(long int)response.body.size()); // 将响应得到的数据输出
file.close();
```

### 完整代码

当代码中的需要自行设置的参数设置完毕后，将```main```函数中内容复制粘贴到你的代码对应位置，即可完成发送http请求并保存图片的功能。

```cpp
#include<iostream>
#include"HTTPRequest.hpp"
#include <fstream>
#include <sstream>
using namespace std;
int main() {
    try {
        http::Request request{"http://cbnxzs.com/api"}; // 请求的地址
        const auto response = request.send("GET"); // 请求方式为GET方式
        ofstream file;
        char * bytes = new char[response.body.size()];
        for(int i=0; i<response.body.size(); i++) {
            bytes[i] = response.body[i];
        }
        file.open("cls1277.jpg" ,ios::binary); // 服务器中保存图片的路径（需要自行设置）
        file.write(bytes,(long int)response.body.size()); // 将响应得到的数据输出
        file.close();
    }
    catch (const std::exception& e) { // 必要的异常处理
        cerr<<"Request failed, error: "<<e.what()<<'\n';
    }
    return 0;
}
```

