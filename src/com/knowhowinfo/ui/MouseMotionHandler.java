package com.knowhowinfo.ui;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import com.knowhowinfo.util.ReadDataUtil;
import com.knwohowinfo.page.GameAbout;
import com.knwohowinfo.page.GameDialog;
import com.knwohowinfo.page.GameList;
import com.knwohowinfo.page.GameMap;
import com.knwohowinfo.page.GameSet;
import com.knwohowinfo.page.GameStart;
import com.knwohowinfo.page.GameStop;
import com.knwohowinfo.page.Welcome;

/**
 * @author admin
 * 
 *         鼠标动作监听
 */
public class MouseMotionHandler extends MouseMotionAdapter {

	private MainPanel panel = MainPanel.getInstance();

	private ArrayList<Rectangle> weList = Welcome.getRectangles();

	private ArrayList<Rectangle> maList = GameMap.getRectangles();

	private ArrayList<Rectangle> stList = GameStart.getRectangle();

	private ArrayList<Rectangle> abList = GameAbout.getRectangle();

	private ArrayList<Rectangle> soList = GameStop.getRectangle();// 游戏暂停

	private ArrayList<Rectangle> diaList = GameDialog.getRectangle();

	private ArrayList<Rectangle> liList = GameList.getRectangle();

	private ArrayList<Rectangle> seList = GameSet.getRectangle();

	private Integer num;

	public MouseMotionHandler() {
		init();
	}

	private void init() {

	}

	// 拖动
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	// 移动
	@Override
	public void mouseMoved(MouseEvent e) {
		// 得到鼠标当前的x y 坐标
		int x = e.getX();
		int y = e.getY();
		switch (panel.flag) {
		case -1:
			welcome(x, y);// 游戏欢迎界面
			break;
		case 0:
			map(x, y);
			break;
		case 1:// 游戏开始
			start(x, y);
			break;
		case 2:// 新的旅程
			break;
		case 3:// 关于游戏
			about(x, y);
		case 5:// 暂停游戏
			stop(x, y);
			break;
		case 6:// 游戏对话框
			dialog(x, y);
			break;
		case 7:// 排行榜
			list(x, y);
			break;
		case 8:// 关于
			list(x, y);
			break;
		case 9:// 游戏设置
			set(x, y);
			break;
		}
	}

	// 游戏设置
	private void set(int x, int y) {
		int index = -1;
		for (int i = 0; i < seList.size(); i++) {
			Rectangle r = seList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		if (index != 2 || index != 3) {
			panel.gameSet(index);
		}
	}

	// 游戏排行榜
	private void list(int x, int y) {
		int index = -1;
		for (int i = 0; i < liList.size(); i++) {
			Rectangle r = liList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		panel.listUpdata(index);
	}

	// 游戏对话框
	private void dialog(int x, int y) {
		int index = -1;
		for (int i = 0; i < diaList.size(); i++) {
			Rectangle r = diaList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		panel.dialogUpdata(index);
	}

	private void stop(int x, int y) {
		int index = -1;
		for (int i = 0; i < soList.size(); i++) {
			Rectangle r = soList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		panel.stopUpdata(index);
	}

	private void about(int x, int y) {
		int index = -1;
		for (int i = 0; i < abList.size(); i++) {
			Rectangle r = abList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		panel.aboutUpdata(index);
	}

	private void start(int x, int y) {
		int index = -1;
		for (int i = 0; i < stList.size(); i++) {
			Rectangle r = stList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		panel.startUpdata(index);
	}

	private void map(int x, int y) {

		int index = -1;

		int number = shift();

		for (int i = 0; i < maList.size(); i++) {
			Rectangle r = maList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}

		if (index <= number || index == 8 || index == 9 || index == -1) {
			panel.mapUpdata(index);
		} else {
			panel.mapUpdata(520);
		}

	}

	private int shift() {
		// 数据库的读取
		return ReadDataUtil.getPass();
	}

	// 鼠标范围判断
	private void welcome(int x, int y) {
		int index = -1;
		for (int i = 0; i < weList.size(); i++) {
			Rectangle r = weList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		if (num == null) {
			num = index;
			panel.welcomeUpdata(index);
		} else {
			if (num != index) {
				panel.welcomeUpdata(index);
				num = index;
			}
		}
	}
}
