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
 * @author admin 游戏欢迎界面
 */
public class Welcome {
	private ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();

	public static ArrayList<Rectangle> list = new ArrayList<Rectangle>();

	private int select = -1;

	private int x = 830;

	private int lx = 1300;

	private int y = 40;

	private int speed = 3;// 图片移动速度

	public Welcome() {
		init();

	}

	private void init() {
		try {

			imgs.add(ImageIO.read(new File("welcome/image.jpg")));

			imgs.add(ImageIO.read(new File("welcome/image2.png")));

			// 前景图片
			imgs.add(ImageIO.read(new File("welcome/image3.png")));

			imgs.add(ImageIO.read(new File("welcome/image4.png")));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updata(int select) {
		this.select = select;
	}

	public static ArrayList<Rectangle> getRectangles() {
		return list;
	}

	// 创建画笔
	public void paint(Graphics g) {

		g.drawImage(imgs.get(0), 0, 0, null);

		// 显示前景图片

		x -= speed;// 图片速度

		lx -= speed;

		if (x == -500) {// 准备重放
			x = 830;
		}

		if (lx == -500) {
			lx = 1500;
		}
		g.drawImage(imgs.get(2), x, y, null);

		g.drawImage(imgs.get(3), lx, y, null);

		Graphics2D g2d = (Graphics2D) g;

		// 打开抗锯齿模式
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// 边框
		g2d.setColor(MainInterface.BORDER_COLOR);
		g2d.setStroke(new BasicStroke(3.0f));
		g2d.drawRoundRect(270, 150, 260, 290, 40, 40);

		// 获取字体轮廓

		g2d.setColor(Color.WHITE.brighter());

		g2d.setStroke(new BasicStroke());// 还原画笔

		Shape sh = new Font(MainInterface.FONT, Font.BOLD, 30)
				.createGlyphVector(g2d.getFontRenderContext(), "继续游戏")
				.getOutline(320, 210);

		Rectangle px = sh.getBounds();// 得到当前的宽高

		Shape sh1 = new Font(MainInterface.FONT, Font.BOLD, 30)
				.createGlyphVector(g2d.getFontRenderContext(), "新的旅程")
				.getOutline(320, (float) 210 + (float) px.getHeight() + 20);

		Rectangle px1 = sh1.getBounds();// 得到当前的宽高

		Shape sh2 = new Font(MainInterface.FONT, Font.BOLD, 30)
				.createGlyphVector(g2d.getFontRenderContext(), "游戏排行")
				.getOutline(320, (float) 210 + (float) px1.getHeight() + 75);

		Rectangle px2 = sh2.getBounds();// 得到当前的宽高

		Shape sh3 = new Font(MainInterface.FONT, Font.BOLD, 30)
				.createGlyphVector(g2d.getFontRenderContext(), "关于游戏")
				.getOutline(320, (float) 210 + (float) px2.getHeight() + 130);

		Rectangle px3 = sh3.getBounds();// 得到当前的宽高

		Shape sh4 = new Font(MainInterface.FONT, Font.BOLD, 30)
				.createGlyphVector(g2d.getFontRenderContext(), "退出游戏")
				.getOutline(320, (float) 210 + (float) px3.getHeight() + 190);

		Rectangle px4 = sh4.getBounds();// 得到当前的宽高

		g2d.draw(sh);

		g2d.draw(sh1);

		g2d.draw(sh2);

		g2d.draw(sh3);

		g2d.draw(sh4);

		// 设置图片
		switch (select) {
		case 0:
			Shape sht = new Font(MainInterface.FONT, Font.BOLD, 30)
					.createGlyphVector(g2d.getFontRenderContext(), "继续游戏")
					.getOutline(320, 210);

			g2d.fill(sht);

			g2d.drawImage(imgs.get(1), sht.getBounds().x - 30,
					sht.getBounds().y + 5, null);

			g2d.drawImage(imgs.get(1), sht.getBounds().x + 142,
					sht.getBounds().y + 5, null);

			break;
		case 1:
			Shape sh1t = new Font(MainInterface.FONT, Font.BOLD, 30)
					.createGlyphVector(g2d.getFontRenderContext(), "新的旅程")
					.getOutline(320, (float) 210 + (float) px.getHeight() + 20);

			g2d.fill(sh1t);

			g2d.drawImage(imgs.get(1), 323 - 30, sh1t.getBounds().y + 5, null);

			g2d.drawImage(imgs.get(1), 323 + 142, sh1t.getBounds().y + 5, null);

			break;
		case 2:
			Shape sh2t = new Font(MainInterface.FONT, Font.BOLD, 30)
					.createGlyphVector(g2d.getFontRenderContext(), "游戏排行")
					.getOutline(320, (float) 210 + (float) px1.getHeight() + 75);

			g2d.fill(sh2t);

			g2d.drawImage(imgs.get(1), 323 - 30, sh2t.getBounds().y + 5, null);

			g2d.drawImage(imgs.get(1), 323 + 142, sh2t.getBounds().y + 5, null);

			break;
		case 3:
			Shape sh3t = new Font(MainInterface.FONT, Font.BOLD, 30)
					.createGlyphVector(g2d.getFontRenderContext(), "关于游戏")
					.getOutline(320,
							(float) 210 + (float) px2.getHeight() + 130);

			g2d.fill(sh3t);

			g2d.drawImage(imgs.get(1), 323 - 30, sh3t.getBounds().y + 5, null);

			g2d.drawImage(imgs.get(1), 323 + 142, sh3t.getBounds().y + 5, null);

			break;
		case 4:
			Shape sh4t = new Font(MainInterface.FONT, Font.BOLD, 30)
					.createGlyphVector(g2d.getFontRenderContext(), "退出游戏")
					.getOutline(320,
							(float) 210 + (float) px3.getHeight() + 190);

			g2d.fill(sh4t);

			g2d.drawImage(imgs.get(1), 323 - 30, sh4t.getBounds().y + 5, null);

			g2d.drawImage(imgs.get(1), 323 + 142, sh4t.getBounds().y + 5, null);

			break;
		}

		if (list.isEmpty()) {// 存入
			list.add(px);
			list.add(px1);
			list.add(px2);
			list.add(px3);
			list.add(px4);
		}
	}

}
