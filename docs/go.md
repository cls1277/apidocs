在本部分中，“发送http请求并得到响应”与“保存http响应图片”分别给了关键部分代码的注释，方便大家学习；也可直接查看“完整代码”部分的使用方法，快速实现该功能。

**代码中需要引入的包以及需要自行设置的参数都进行了标注，当必要的包被引入且参数修改正确后，复制粘贴修改后的代码，并使用提供的调用方法，即可完成该部分工作。**

完整测试代码见：[https://github.com/cls1277/apidocs]()

### 需要引入的包

```go
import (
	"fmt"
	"io/ioutil"
	"net/http"
)
```

### 发送http请求并得到响应

调用该部分将自动向指定的地址 [http://cbnxzs.com/api](http://cbnxzs.com/api) 发送GET请求，并得到响应数据流，最终将图片的数据流内容保存到变量```body```中。**若要得到指定图片，**只需将请求地址改为 [http://cbnxzs.com/api?id=1](http://cbnxzs.com/api?id=1) ，即可实现对第一张图片的请求。

```go
resp, err := http.Get("http://cbnxzs.com/api") // 向请求的地址发送GET请求
if err != nil {
	fmt.Println(err)
	return
}
defer resp.Body.Close()
body, err := ioutil.ReadAll(resp.Body) // 得到服务器响应数据
```

### 保存http响应图片

在将图片的数据流内容保存到变量```body```中之后，将响应的数据流通过```ioutil```的```WriteFile```函数保存到服务器的指定路径中。**在使用时，需要自行设置服务器中要保存的图片的路径。**

```go
if resp.StatusCode == 200 { // HTTP状态码200表示成功响应
	ioutil.WriteFile("E:\\cls1277\\Courses\\img.jpg", body, 0777) // 保存图片到服务器中的路径（需要自行设置）
}
```

### 完整代码

当代码中的需要自行设置的参数设置完毕后，将```main```函数中内容复制粘贴到你的代码对应位置，即可完成发送http请求并保存图片的功能。

```go
package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

func main() {
	resp, err := http.Get("http://cbnxzs.com/api") // 向请求的地址发送GET请求
	if err != nil {
		fmt.Println(err)
		return
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body) // 得到服务器响应数据
	if resp.StatusCode == 200 { // HTTP状态码200表示成功响应
		ioutil.WriteFile("E:\\cls1277\\Courses\\img.jpg", body, 0777) // 保存图片到服务器中的路径（需要自行设置）
	}
}
```

