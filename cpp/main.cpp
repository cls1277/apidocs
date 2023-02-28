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