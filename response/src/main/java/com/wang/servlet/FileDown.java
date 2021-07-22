package com.wang.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileDown extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取文件路径
        //String realPath = this.getServletContext().getRealPath("/1.jpg");
        String realPath = "D:\\Java_practice\\javaweb-02-servlet\\response\\src\\main\\resources\\身份证反面.jpg";
        //2.获取文件名
        String fileName = realPath.substring(realPath.lastIndexOf("//") + 1);
        //3.配置浏览器能够支持下载文件  Content-Disposition为属性名  disposition-type是以什么方式下载，如attachment为以附件方式下载disposition-parm为默认保存时的文件名
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));
        //4.获取下载文件输入流FileInputStream
        FileInputStream fileInputStream = new FileInputStream(realPath);
        //5.配置缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        //6.获取OutputStream
        ServletOutputStream outputStream = resp.getOutputStream();
        //7.将FileInputStream写入缓冲区，并用OutputStream讲缓冲区的数据输出至客户端
        while ((len =fileInputStream.read(buffer))>0){
            outputStream.write(buffer,0,len);
        }
        outputStream.close();
        fileInputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
