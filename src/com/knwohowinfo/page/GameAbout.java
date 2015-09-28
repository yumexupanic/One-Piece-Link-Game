package com.knwohowinfo.page;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import com.knowhowinfo.interfece.MainInterface;

/**
 * @author Administrator 游戏关于
 */
public class GameAbout {
	private static ArrayList<Rectangle> list = new ArrayList<Rectangle>();
	private int fontSize = 30;
	private File f = new File("image/image10.jpg");
	private BufferedImage img;

	public GameAbout() {
		init();
	}

	private void init() {
		try {
			img = ImageIO.read(f);// 读取关于游戏背景图片
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Rectangle> getRectangle() {
		return list;
	}

	public void updata(int select) {
		switch (select) {
		case -1:
			fontSize = 30;
			break;
		case 0:
			fontSize = 35;
			break;
		}

	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		// 打开抗锯齿模式
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.black);// 黑色背景
		g2d.fillRect(0, 0, MainInterface.GAMEMAP_SC_WIDTH,
				MainInterface.GAMEMAP_SC_HEIGHT);

		g.drawImage(img, 190, 0, null);

		// 设置字体颜色
		g2d.setColor(Color.WHITE.brighter());// 更明亮的

		Shape about = new Font(MainInterface.FONT, Font.BOLD, 35)
				.createGlyphVector(g2d.getFontRenderContext(), "关于游戏")
				.getOutline(300, 100);

		Rectangle reAbout = about.getBounds();// 获取字体的宽高

		Shape back = new Font(MainInterface.FONT, Font.BOLD, fontSize)
				.createGlyphVector(g2d.getFontRenderContext(), "Return")
				.getOutline(340, 490);

		Rectangle reback = back.getBounds();

		g2d.fill(about);

		g2d.fill(back);

		Color c = new Color(255, 255, 255, 60);

		g2d.setColor(c);

		g2d.setStroke(new BasicStroke(2.0f));// 设置画笔大小

		g2d.fillRoundRect(90, 120, 600, 300, 30, 40);

		// 游戏关于

		g2d.setFont(MainInterface.F);
		g2d.setColor(Color.RED);

		g2d.drawString("游戏名称:", 200, 150);

		g2d.drawString("游戏语言:", 200, 180);

		g2d.drawString("游戏简介:", 200, 210);

		g2d.setColor(Color.white);
		// 游戏名称:
		g2d.drawString("海贼王版连连看", 280, 150);

		// 游戏语言:
		g2d.drawString("简体中文", 280, 180);

		// 游戏简介:
		g2d.drawString("你有梦想吗?你喜欢冒险吗?", 280, 210);

		g2d.drawString("希望这款游戏可以帮你实现这个愿望,游戏旅途", 200, 235);

		g2d.drawString("中总共要经历7个岛屿,相信无论是新手还是", 200, 260);

		g2d.drawString("高手,都能给你带来无穷的乐趣", 200, 285);

		g2d.drawString("祝愿你能早日实现自己的梦想!", 200, 330);

		// 对应的宽高存入变长数组
		if (list.isEmpty()) {
			list.add(reback);// return
			// list.add(reAbout);
		}
	}

}
