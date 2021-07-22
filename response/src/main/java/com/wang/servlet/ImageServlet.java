package com.wang.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //让浏览器三秒刷新一次
        resp.setHeader("refresh","3");
        //在内存中创建一个图片
        BufferedImage bufferedImage = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
        //得到图片并用画笔控制
        Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        graphics2D.setColor(Color.blue);
        graphics2D.fillRect(0,0,80,20);
        //给图片写数据
        graphics2D.setColor(Color.WHITE);
        //设置字体
        graphics2D.setFont(new Font(null,Font.BOLD,20));
        //表示这段文字在图片上的位置(x,y)
        graphics2D.drawString(test(),0,20);
        //响应以图片形式打开
        resp.setContentType("image/jpeg");
        //禁止浏览器缓存
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Program","no-cache");
        resp.setDateHeader("Expires",-1);
        //将图片写给浏览器
        boolean write = ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    private String test(){
        Random random = new Random();

        String num = "";
        for(int i =0;i<7;i++){
            num += String.valueOf(random.nextInt(10));
        }
        return num;
    }
}
