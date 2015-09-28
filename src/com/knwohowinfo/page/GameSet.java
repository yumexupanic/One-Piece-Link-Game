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
 * @author Administrator 游戏设置类
 */
public class GameSet {

	private static ArrayList<Rectangle> list = new ArrayList<Rectangle>();
	private BufferedImage img;
	private int fontSize = 20;
	private int fontSize2 = 20;
	private boolean isDown = true;
	private boolean isDown2 = true;

	public GameSet() {
		init();
	}

	private void init() {

		try {
			img = ImageIO.read(new File("set/image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<Rectangle> getRectangle() {
		return list;
	}

	// 设置游戏更新操作
	public void updata(int select) {
		switch (select) {
		case 0:// 确定
			fontSize = 25;
			break;
		case 1:// 返回
			fontSize2 = 25;
			break;
		default:
			fontSize = 20;
			fontSize2 = 20;
			break;
		}
	}

	public void Updata(int select) {
		switch (select) {
		case 2:// 开启音效
			if (isDown) {
				isDown = false;
			} else {
				isDown = true;
			}
			break;
		case 3:// 开启背景音
			if (isDown2) {
				isDown2 = false;
			} else {
				isDown2 = true;
			}
			break;
		}
	}

	// 获得开启音效
	public boolean getIsDown() {
		return isDown;
	}

	// 获得开启背景音
	public boolean getIsDown2() {
		return isDown2;
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		// 打开抗锯齿模式
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// 画背景图
		g2d.drawImage(img, 0, 0, null);

		// 绘制功能边框
		Color c = new Color(255, 255, 255, 60);

		g2d.setColor(c);

		g2d.setStroke(new BasicStroke(2.0f));// 设置画笔大小

		g2d.fillRoundRect(90, 120, 600, 300, 30, 40);

		g2d.setColor(Color.RED);

		g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 20));

		g2d.drawString("开启音效", 320, 200);

		g2d.drawString("开启背景音乐", 320, 260);

		// 开启音效
		g2d.drawRect(290, 185, 15, 15);

		// 开启背景音乐
		g2d.drawRect(290, 245, 15, 15);

		Shape sh = new Font(MainInterface.FONT, Font.BOLD, fontSize)
				.createGlyphVector(g2d.getFontRenderContext(), "确定")
				.getOutline(290, 380);
		Rectangle resh = sh.getBounds();

		Shape sh2 = new Font(MainInterface.FONT, Font.BOLD, fontSize2)
				.createGlyphVector(g2d.getFontRenderContext(), "返回")
				.getOutline(430, 380);
		Rectangle resh2 = sh2.getBounds();

		g2d.fill(sh);
		g2d.fill(sh2);

		if (isDown) {
			g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
			g2d.drawString("√", 280, 203);
		}
		if (isDown2) {
			g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 30));
			g2d.drawString("√", 280, 263);
		}

		// 存入
		if (list.isEmpty()) {
			list.add(resh);
			list.add(resh2);

			list.add(new Rectangle(290, 185, 15 + 4, 15 + 10));// 开启音效

			list.add(new Rectangle(290, 245, 15 + 4, 15 + 10));// 开启背景音乐

		}

	}

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public boolean isDown2() {
		return isDown2;
	}

	public void setDown2(boolean isDown2) {
		this.isDown2 = isDown2;
	}

}
