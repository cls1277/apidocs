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
		fmt.Println("done")
	}
}
