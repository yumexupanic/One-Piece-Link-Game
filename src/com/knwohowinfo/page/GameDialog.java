package com.knwohowinfo.page;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import com.knowhowinfo.interfece.MainInterface;

/**
 * @author Administrator 游戏中的对话框
 */
public class GameDialog {

	private static ArrayList<Rectangle> list = new ArrayList<Rectangle>();

	private String str = "";

	private int fontSize = 15;

	private int fontSize2 = 15;

	private int select;

	private int option;

	private int x = 360;
	private int y = 280;

	public GameDialog() {

	}

	public static ArrayList<Rectangle> getRectangle() {
		return list;
	}

	public void setCommand(int select) {
		switch (select) {
		case -1://
			fontSize = 15;
			fontSize2 = 15;
			break;
		case 0:// 确认
			fontSize = 20;
			break;
		case 1:// 取消
			fontSize2 = 20;
			break;
		case 2:// 返回主菜单
			str = "您当前正在游戏,确认返回到主菜单吗??";
			break;
		case 3:// 玩家输了
			str = "您输了!";
			x = 360;
			y = 280;
			break;
		case 4:// 玩家赢了
			str = "您赢了!";
			x = 360;
			y = 280;
			break;
		case 5:
			str = "这样会清空游戏记录,确认继续吗?";
			break;
		}
	}

	public void setDia(int option) {
		this.option = option;
	}

	// 返回当前对话在显示哪个界面
	public int getSelect() {
		return option;
	}

	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		// 对话框
		g2d.setColor(MainInterface.BORDER_COLOR_HYALINE);// 颜色带透明
		g2d.fillRoundRect(260, 200, 320, 170, 30, 40);

		// 对话内容
		g2d.setColor(Color.RED.brighter());

		g2d.setFont(new Font(MainInterface.FONT, Font.BOLD, 20));

		if (str.length() > 13) {// 字准备换行
			g2d.drawString(str.substring(0, 13), 270, 230);
			g2d.drawString(str.substring(13, str.length()), 270, 256);
		} else {
			g2d.drawString(str, x, y);
		}

		g2d.setColor(Color.WHITE.brighter());

		Shape sh2 = new Font(MainInterface.FONT, Font.BOLD, fontSize)
				.createGlyphVector(g2d.getFontRenderContext(), "确定")
				.getOutline(300, 350);

		Rectangle resh2 = sh2.getBounds();

		Shape sh3 = new Font(MainInterface.FONT, Font.BOLD, fontSize2)
				.createGlyphVector(g2d.getFontRenderContext(), "取消")
				.getOutline(470, 350);

		Rectangle resh3 = sh3.getBounds();

		g2d.fill(sh2);
		g2d.fill(sh3);

		if (list.isEmpty()) {
			list.add(resh2);// 0
			list.add(resh3);// 1
		}
	}

}
