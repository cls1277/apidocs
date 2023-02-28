let request = require("request");
let fs = require("fs");

path = 'http://cbnxzs.com/api' // 请求的地址
request(path).pipe(fs.createWriteStream('E:\\cls1277\\Courses\\img.jpg')) //  获取图片的文件流并输出到服务器的指定路径（需要自行设置）
console.log("done")