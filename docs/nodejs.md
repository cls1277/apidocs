在本部分中，“发送http请求并得到响应”与“保存http响应图片”分别给了关键部分代码的注释，方便大家学习；也可直接查看“完整代码”部分的使用方法，快速实现该功能。

**代码中需要引入的包以及需要自行设置的参数都进行了标注，当必要的包被引入且参数修改正确后，复制粘贴修改后的代码，并使用提供的调用方法，即可完成该部分工作。**

完整测试代码见：[https://github.com/cls1277/apidocs]()

### 需要引入的包

使用```npm```导入```request```和```fs```包。

```javascript
let request = require("request");
let fs = require("fs");
```

### 发送http请求，得到响应并保存http响应图片

```request```中的```pipe```可以很方便的获取图片的文件流，**若要得到指定图片，**只需将请求地址改为 [http://cbnxzs.com/api?id=1](http://cbnxzs.com/api?id=1) ，即可实现对第一张图片的请求。

之后使用```fs```的```createWriteStream```将文件流保存到服务器上。**在使用时，需要自行设置服务器中要保存的图片的路径。**

```javascript
path = 'http://cbnxzs.com/api' // 请求的地址
request(path).pipe(fs.createWriteStream('E:\\cls1277\\Courses\\img.jpg')) //  获取图片的文件流并输出到服务器的指定路径（需要自行设置）
```

### 完整代码

当代码中的需要自行设置的参数设置完毕后，将以下内容复制粘贴到你的代码对应位置，即可完成发送http请求并保存图片的功能。

```javascript
let request = require("request");
let fs = require("fs");
path = 'http://cbnxzs.com/api' // 请求的地址
request(path).pipe(fs.createWriteStream('E:\\cls1277\\Courses\\img.jpg')) //  获取图片的文件流并输出到服务器的指定路径（需要自行设置）
```

