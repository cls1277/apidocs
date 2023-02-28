import requests

res = requests.get(url='http://cbnxzs.com/api') # 向请求的地址发送GET请求
if res.status_code == 200: # HTTP状态码200表示成功响应
    path = 'E:\\cls1277\\Courses\\img.jpg' # 服务器中保存图片的路径（需要自行设置）
    with open(path,'wb') as f:
        f.write(res.content) # 将响应得到的数据输出
        f.close()
        print('done')