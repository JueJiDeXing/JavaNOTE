package 网络编程.Http协议;

public class 响应数据格式 {
    //HTTP响应数据格式
    /*
    响应行: 协议 状态码 描述    例:HTTP/1.1 200 OK
    响应头: key:value
    响应体: json数据
     */

    /*状态码
    1xx 响应中-临时状态码,表示请求已接收,告诉客户端应该继续请求,如果已经完成则忽略
    2xx 成功-表示请求成功接收,处理已完成
    3xx 重定向-重定向到其他地方(资源已移至其他地方);让客户端再发起一次请求以完成整个处理
    4xx 客户端错误-处理发送错误,责任在客户端,如:请求不存在的资源,客户端未被授权
    5xx 服务端错误-处理发送错误,责任在服务端,如:程序抛出异常
     */
    /*响应头
    Content-Type:表示响应内容的类型,例如text/html,application/json
    Content-Length:表示响应内容的长度(单位:字节)
    Content-Encoding:表示响应压缩算法,例如gzip
    Cache-Control:指示客户端应如何缓存,例如max-age=300表示最多缓存300秒
    Set-cookie:告诉浏览器为当前页面所在的域设置cookie


     */
}
