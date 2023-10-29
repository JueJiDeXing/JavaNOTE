package 网络编程.Http协议;

public class 请求数据格式 {
    //超文本传输协议
    //http是基于TCP的协议,面向连接

    //HTTP-请求数据格式
    /*
    请求行:请求的第一行  格式为: 请求方式 请求地址 协议
    请求头:第二行开始     格式为: key:value
    请求体:Post请求的最后一行,与请求头以空行隔开,存放请求参数 json格式
     */
    /*
    GET:请求参数在请求行中(在地址栏可见),没有请求体,请求大小有限制
    POST:请求参数在请求体中,请求大小没有限制
     */
    /*
    请求头参数:
    Host 请求的主机＋端口
    User-Agent:浏览器版本
    Accept:浏览器能接受的资源类型,例如text/*,image/*
    Accept-Language:浏览器偏好语言
    Accept-Encoding:浏览器可支持的压缩类型,例如gzip,deflate
    Content-Type:请求主体的数据类型
    Content-Length:请求主体的大小(单位:字节)
     */

}
