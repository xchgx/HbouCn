package com.xchgx.domain.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StringToImage {

    private String imageFileSrc;
    private IdentifyingCode identifyingCode;

    public StringToImage(String imageFileSrc, IdentifyingCode identifyingCode) {
        this.imageFileSrc = imageFileSrc;
        this.identifyingCode = identifyingCode;
    }

    public String getImageFileSrc() {
        return imageFileSrc;
    }

    public BufferedImage makeBufferedImage() {
        BufferedImage image = new BufferedImage(100, 50,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Font font = new Font("黑体", Font.BOLD, 20);
        g.setFont(font);
        g.fillRect(0, 0, 100, 50);
        Color color = new Color(120, 0, 0);
        g.setColor(color);
        g.drawString(identifyingCode.expression(), 0, 35);
//		g.dispose();
        return image;
    }

    public String makeImage() {
        try {
            ImageIO.write(makeBufferedImage(), "jpg", new File(imageFileSrc));
        } catch (IOException e) {
            return "文件保存失败，请检查权限问题。";
        }
        return "文件保存成功" + imageFileSrc;
    }
}
