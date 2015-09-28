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
import com.knowhowinfo.ui.MainPanel;
import com.knowhowinfo.util.ReadDataUtil;

/**
 * @author admin 游戏地图
 * 
 *         需要读取数据库操作
 */
public class GameMap {
	private static ArrayList<Rectangle> list = new ArrayList<Rectangle>();
	private String command;
	private ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
	private int fontSize1 = 20;
	private int fontSize2 = 20;
	private int flag;// 继续游戏 返回菜单 两个button的操作
	private Rectangle re = new Rectangle(30, 35, 160, 120);
	private MainPanel panel = MainPanel.getInstance();

	// 图片修改的下标
	private int imIndex1;
	private int imIndex2;
	private int imIndex3;
	private int imIndex4;
	private int imIndex5;
	private int imIndex6;
	private int imIndex7;

	public GameMap() {
		init();
		inire();
	}

	// 重新设置边框
	public void inire() {
		re = new Rectangle(30, 35, 160, 120);// 设置边框
	}

	// 实例块
	{
	}

	private void init() {
		try {
			imgs.add(ImageIO.read(new File("map/image3.jpg")));

			// 1
			imgs.add(ImageIO.read(new File("pass/fcc.jpg")));// 1

			// 2
			imgs.add(ImageIO.read(new File("pass/jyqd.jpg")));// 2
			imgs.add(ImageIO.read(new File("pass/jyqd2.jpg")));// 3

			// 3
			imgs.add(ImageIO.read(new File("pass/kkyxc.jpg")));// 4
			imgs.add(ImageIO.read(new File("pass/kkyxc2.jpg")));// 5

			// 4
			imgs.add(ImageIO.read(new File("pass/lgz.jpg")));// 6
			imgs.add(ImageIO.read(new File("pass/lgz2.jpg")));// 7

			// 5
			imgs.add(ImageIO.read(new File("pass/mzh.jpg")));// 8
			imgs.add(ImageIO.read(new File("pass/mzh2.jpg")));// 9

			// 6
			imgs.add(ImageIO.read(new File("pass/mlqy.jpg")));// 10
			imgs.add(ImageIO.read(new File("pass/mlqy2.jpg")));// 11

			// 7
			imgs.add(ImageIO.read(new File("pass/lfdl.jpg")));// 12
			imgs.add(ImageIO.read(new File("pass/lfdl2.jpg")));// 13

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updata(int select) {
		switch (select) {
		case -1:
			command = MainInterface.COMMAND9;
			flag = 0;
			break;
		case 1:
			command = MainInterface.COMMAND1;
			break;
		case 2:
			command = MainInterface.COMMAND2;
			break;
		case 3:
			command = MainInterface.COMMAND3;
			break;
		case 4:
			command = MainInterface.COMMAND4;
			break;
		case 5:
			command = MainInterface.COMMAND5;
			break;
		case 6:
			command = MainInterface.COMMAND6;
			break;
		case 7:
			command = MainInterface.COMMAND7;
			break;
		case 8:// 继续游戏
			flag = 1;
			if (panel == null) {
				panel = MainPanel.getInstance();
			}
			panel.coreDefault();// 更新所有练练看布局
			break;
		case 9:// 返回菜单
			flag = 2;
			break;
		case 520:// 对于还未解锁的关卡的说明
			command = MainInterface.COMMAND8;
			break;
		}
	}

	public static ArrayList<Rectangle> getRectangles() {
		return list;
	}

	// 接收鼠标选择线
	public void setDraw(int i) {
		int in = ReadDataUtil.getPass();

		// 地图的最大下标
		if (i > in) {
			return;
		}

		switch (i) {
		case 1:
			re = new Rectangle(30, 35, 160, 120);
			break;
		case 2:
			re = new Rectangle(120, 215, 160, 120);
			break;
		case 3:
			re = new Rectangle(70, 395, 160, 120);
			break;
		case 4:
			re = new Rectangle(360, 355, 160, 120);
			break;
		case 5:
			re = new Rectangle(390, 85, 160, 120);
			break;
		case 6:
			re = new Rectangle(590, 225, 160, 120);
			break;
		case 7:
			re = new Rectangle(630, 435, 160, 120);
			break;
		}
	}

	public void paint(Graphics e) {

		// 数据库查询操作
		int in = ReadDataUtil.getPass();

		automatic(in);

		Graphics2D p2d = (Graphics2D) e;// 把图片画笔转换为2d画笔

		p2d.setColor(Color.black);
		p2d.fillRect(0, 0, MainInterface.GAMEMAP_SC_WIDTH,
				MainInterface.GAMEMAP_SC_HEIGHT);
		p2d.setFont(new Font(MainInterface.FONT2, Font.LAYOUT_NO_START_CONTEXT,
				12));

		p2d.drawImage(imgs.get(0), 50, 30, null);

		// 打开抗锯齿模式
		p2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		p2d.setColor(MainInterface.BORDER_COLOR_HYALINE);// 带透明

		p2d.setStroke(new BasicStroke(2.0f));

		// 状态栏(下)

		// 0

		p2d.fillRoundRect(8, 545, 530, 40, 30, 80);

		// 地图框

		// 1
		p2d.drawRoundRect(30, 35, 160, 120, 40, 30); // 游戏 第1关 风车村

		// 风车村图片
		p2d.drawImage(imgs.get(imIndex1), 40, 43, 140, 105, null);

		// 2
		p2d.drawRoundRect(120, 215, 160, 120, 40, 30);// 第 2 关

		// 3
		p2d.drawRoundRect(70, 395, 160, 120, 40, 30);

		// 4
		p2d.drawRoundRect(360, 355, 160, 120, 40, 30);

		// 5
		p2d.drawRoundRect(390, 85, 160, 120, 40, 30);

		// 6
		p2d.drawRoundRect(590, 225, 160, 120, 40, 30);

		// 7
		p2d.setColor(Color.RED);

		p2d.drawRoundRect(630, 435, 160, 120, 40, 30);

		p2d.setColor(Color.black);

		// 2填充
		p2d.fillRoundRect(121, 217, 158, 118, 40, 30);

		// 加雅岛
		p2d.drawImage(imgs.get(imIndex2), 130, 225, 140, 105, null);

		// 3填充
		p2d.fillRoundRect(71, 397, 158, 118, 40, 30);

		// 可可亚西村
		p2d.drawImage(imgs.get(imIndex3), 80, 405, 140, 105, null);

		// 4填充
		p2d.fillRoundRect(361, 357, 158, 118, 40, 30);

		// 罗格镇
		p2d.drawImage(imgs.get(imIndex4), 370, 365, 140, 105, null);

		// 5填充
		p2d.fillRoundRect(391, 87, 158, 118, 40, 30);

		// 魔之海
		p2d.drawImage(imgs.get(imIndex5), 400, 95, 140, 105, null);

		// 6填充
		p2d.fillRoundRect(591, 227, 158, 118, 40, 30);

		// 圣地
		p2d.drawImage(imgs.get(imIndex6), 600, 233, 140, 105, null);

		// 7填充
		p2d.fillRoundRect(631, 437, 158, 118, 40, 30);

		// 7填充
		p2d.fillRoundRect(631, 437, 158, 118, 40, 30);

		// 梦想之地!

		p2d.drawImage(imgs.get(imIndex7), 640, 443, 140, 105, null);

		//
		// p2d.drawImage(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8,
		// arg9)

		// 8 One

		// 绘制鼠标选择框
		if (re != null) {

			p2d.setColor(Color.green.brighter());

			p2d.drawRoundRect(re.x, re.y, re.width, re.height, 40, 30);// 画出
		}

		p2d.setColor(Color.WHITE.brighter());// 更明亮的白色

		if (command != null) {
			String str = "";

			if (command.length() > 45) {// 准备换行了
				str = command.substring(0, 45);
				p2d.drawString(str, 12, 563);
				p2d.drawString(command.substring(45, command.length()), 12, 580);
			} else {

				if (command.equals(MainInterface.COMMAND8)) {// 如果当前是还未解锁的地图
					p2d.setColor(Color.RED.brighter());
					p2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 20));
					p2d.drawString(command, 160, 575);
				} else if (command.equals(MainInterface.COMMAND9)) {// 鼠标未在地图范围之内
					p2d.setColor(Color.RED.brighter());
					p2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 20));
					p2d.drawString(command, 160, 575);
				} else {
					p2d.drawString(command, 12, 570);
				}
			}
		}

		p2d.setStroke(new BasicStroke());// 还原画笔大小

		switch (flag) {
		case 1:
			fontSize1 = 25;
			break;
		case 2:
			fontSize2 = 25;
			break;
		default:
			fontSize1 = 20;
			fontSize2 = 20;
			break;
		}

		p2d.setColor(Color.WHITE.brighter());// 更明亮的白色

		Shape gameContinue = new Font(MainInterface.FONT, Font.BOLD, fontSize1)
				.createGlyphVector(p2d.getFontRenderContext(), "继续游戏")
				.getOutline(580, 40);

		Rectangle gContinue = gameContinue.getBounds();// 获取字体宽高

		Shape gameBack = new Font(MainInterface.FONT, Font.BOLD, fontSize2)
				.createGlyphVector(p2d.getFontRenderContext(), "返回菜单")
				.getOutline(580, 80);

		Rectangle gBack = gameBack.getBounds();// 获取字体宽高

		p2d.fill(gameContinue);

		p2d.fill(gameBack);

		if (list.isEmpty()) {// 存入

			// 0
			list.add(new Rectangle(8, 545, 530, 40));

			// 1
			list.add(new Rectangle(30 + 10, 35, 160, 120));// x y w h

			// 2
			list.add(new Rectangle(120 + 10, 215, 160, 120));

			// 3
			list.add(new Rectangle(70 + 10, 395, 160, 120));

			// 4
			list.add(new Rectangle(360 + 10, 355, 160, 120));

			// 5
			list.add(new Rectangle(390 + 10, 85, 160, 120));

			// 6
			list.add(new Rectangle(590 + 10, 225, 160, 120));

			// 7
			list.add(new Rectangle(630 + 10, 435, 160, 120));

			// 字体

			// 8 --> 继续游戏
			list.add(gContinue);

			// 9 --> 返回菜单
			list.add(gBack);

		}
	}

	private void automatic(int in) {

		switch (in) {
		case 1:
			imIndex1 = 1;
			imIndex2 = 3;
			imIndex3 = 5;
			imIndex4 = 7;
			imIndex5 = 9;
			imIndex6 = 11;
			imIndex7 = 13;
			break;
		case 2:
			imIndex1 = 1;
			imIndex2 = 2;
			imIndex3 = 5;
			imIndex4 = 7;
			imIndex5 = 9;
			imIndex6 = 11;
			imIndex7 = 13;
			break;
		case 3:
			imIndex1 = 1;
			imIndex2 = 2;
			imIndex3 = 4;
			imIndex4 = 7;
			imIndex5 = 9;
			imIndex6 = 11;
			imIndex7 = 13;
			break;
		case 4:
			imIndex1 = 1;
			imIndex2 = 2;
			imIndex3 = 4;
			imIndex4 = 6;
			imIndex5 = 9;
			imIndex6 = 11;
			imIndex7 = 13;
			break;
		case 5:
			imIndex1 = 1;
			imIndex2 = 2;
			imIndex3 = 4;
			imIndex4 = 6;
			imIndex5 = 8;
			imIndex6 = 11;
			imIndex7 = 13;
			break;
		case 6:
			imIndex1 = 1;
			imIndex2 = 2;
			imIndex3 = 4;
			imIndex4 = 6;
			imIndex5 = 8;
			imIndex6 = 10;
			imIndex7 = 13;
			break;
		case 7:
			imIndex1 = 1;
			imIndex2 = 2;
			imIndex3 = 4;
			imIndex4 = 6;
			imIndex5 = 8;
			imIndex6 = 10;
			imIndex7 = 12;
			break;
		}
	}
}
