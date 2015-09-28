package com.knowhowinfo.ui;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import com.knowhowinfo.util.DbUtil;
import com.knowhowinfo.util.Plan;
import com.knwohowinfo.page.GameAbout;
import com.knwohowinfo.page.GameCoreMap;
import com.knwohowinfo.page.GameDialog;
import com.knwohowinfo.page.GameList;
import com.knwohowinfo.page.GameMap;
import com.knwohowinfo.page.GameSet;
import com.knwohowinfo.page.GameStart;
import com.knwohowinfo.page.GameStop;
import com.knwohowinfo.page.Welcome;

/**
 * @author Administrator
 * 
 *         鼠标监听
 */
public class MouseHandler extends MouseAdapter {

	private MainPanel panel = MainPanel.getInstance();

	private ArrayList<Rectangle> wlList = Welcome.getRectangles();

	private ArrayList<Rectangle> maList = GameMap.getRectangles();

	private ArrayList<Rectangle> abList = GameAbout.getRectangle();

	private ArrayList<Rectangle> stList = GameStart.getRectangle();

	private ArrayList<Rectangle> soList = GameStop.getRectangle();// 游戏暂停

	private ArrayList<Rectangle> coreList = GameCoreMap.getRectangle();

	private ArrayList<Rectangle> diaList = GameDialog.getRectangle();

	private ArrayList<Rectangle> liList = GameList.getRectangle();

	private ArrayList<Rectangle> seList = GameSet.getRectangle();

	// 游戏开始后 点击的点
	private ArrayList<Point> po = new ArrayList<Point>();

	private int pass;// 记录关卡

	private boolean option;

	private boolean option3;

	public MouseHandler() {
		init();
	}

	private void init() {

	}

	// 按下
	@Override
	public void mousePressed(MouseEvent e) {

		// 按下只判断游戏开始
		if (panel.flag == 1) {
			core(e.getX(), e.getY());
		}

	}

	// 释放
	@Override
	public void mouseReleased(MouseEvent e) {

		// 对应每一次的按下操作 对应不同的画布
		switch (panel.flag) {
		case -1:// 欢迎界面
			isBounds(e.getX(), e.getY());
			break;
		case 0:// 游戏地图界面
			map(e.getX(), e.getY());
			break;
		case 1:// 游戏界面
				// 游戏功能组件
			start(e.getX(), e.getY());
			size();
			break;
		case 2:
			break;
		case 3:// 关于游戏
			about(e.getX(), e.getY());
			break;
		case 5:// 暂停
			stop(e.getX(), e.getY());
			break;
		case 6:// 游戏对话框
			dialog(e.getX(), e.getY());
			break;
		case 8:// 排行榜
			list(e.getX(), e.getY());
			break;
		case 9:// 游戏设置
			set(e.getX(), e.getY());
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
		// 0 确认 //1 返回

		// 2开启音效 3 开启背景音

		switch (index) {
		case 0:// 确认
			panel.setGame();
			panel.control(1);
			break;
		case 1:
			panel.control(1);
			break;
		case 2:
			panel.gameSetUpdata(index);
			break;
		case 3:
			panel.gameSetUpdata(index);
			break;
		}
	}

	private void stop(int x, int y) {
		int index = -1;
		for (int i = 0; i < soList.size(); i++) {
			Rectangle r = soList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}

		if (index != -1) {
			panel.control(1);
		}
	}

	private void list(int x, int y) {

		int index = -1;
		for (int i = 0; i < liList.size(); i++) {
			Rectangle r = liList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		if (index != -1) {
			if (option3) {
				panel.control(1);
				option3 = false;
			} else {
				panel.control(-1);
			}
		}
	}

	// 游戏对话框
	private void dialog(int x, int y) {
		int index = -2;
		for (int i = 0; i < diaList.size(); i++) {
			Rectangle r = diaList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		switch (index) {
		case 0:// 确认
			index = getDiaNum(true);
			break;
		case 1:
			index = getDiaNum(false);
			break;// 取消
		default:
			return;
		}
		panel.control(index);
		if (index != 1) {
			panel.coreDefault();// 更新默认
		}

	}

	private int getDiaNum(boolean boo) {
		int index = 0;
		if (boo) {// 点击确认
			switch (panel.getDiaNum()) {
			case 2:// 主菜单
				index = -1;
				break;
			case 3:// 输了
				index = 0;// 进入游戏地图
				break;
			case 4:// 赢了
				index = 0;
				break;
			case 5:// 新的旅程()
				DbUtil.getInstance().storePass(new Plan(0));// 重新对数据库进行设置
				index = 0;// 进入游戏地图界面
				// 对于游戏地图里面的数据也要清空
				panel.mapBorder();
				break;
			}
		} else {// 点击取消
			switch (panel.getDiaNum()) {
			case 2:// 主菜单
				index = 1;
				break;
			case 3:// 输了
				index = 0;
				break;
			case 4:// 赢了
				index = 0;
				break;
			case 5:// 新的游戏
				index = -1;
				break;
			}
		}

		return index;
	}

	// 判断当前的点是否超过两个
	private void size() {
		if (po.size() == 2) {
			po.clear();
		}
	}

	// 游戏算法
	private void core(int x, int y) {
		for (int i = 0; i < coreList.size(); i++) {
			Rectangle r = coreList.get(i);

			if (r.contains(x, y - 30)) {
				Point p = new Point(r.x, r.y);

				// 只能存入不相同x y 的图片
				if (po.indexOf(p) == -1) {
					po.add(new Point(p));
				}
				panel.coreUpdata(po);
			}
		}
	}

	private void start(int x, int y) {
		int index = -1;
		for (int i = 0; i < stList.size(); i++) {
			Rectangle r = stList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;// 当前鼠标在此范围之类
			}
		}
		if (index == -1) {
			return;
		}

		switch (index) {
		// 游戏开始功能
		case 0:
			index = 5;// 暂停
			break;
		case 1:
			index = 6;// 主菜单
			panel.dialogUpdata(2);
			panel.diaSet(2);
			break;
		case 2:// 游戏设置
			index = 9;
			break;
		case 3:// 排行
			index = 8;
			option3 = true;
			break;
		case 4:// 关于
			index = 3;
			option = true;
			break;
		case 5:// 打乱
			panel.corePrompt();
			return;
		case 6:// 提示
			panel.coreUpNum();
			return;
		}
		panel.control(index);
	}

	// 游戏关于
	private void about(int x, int y) {
		int index = -1;
		for (int i = 0; i < abList.size(); i++) {
			Rectangle r = abList.get(i);
			if (r.contains(x, y - 30)) {
				index = i;
			}
		}
		if (index != -1) {
			if (option) {
				panel.control(1);
				option = false;
			} else {
				panel.control(-1);
			}
		}
	}

	private void map(int x, int y) {

		int index = -1;

		for (int i = 0; i < maList.size(); i++) {
			Rectangle p = maList.get(i);
			if (p.contains(x, y - 30)) {
				index = i;// 当前鼠标在此范围之内
			}
		}

		switch (index) {
		case -1:
			pass = 1;
			break;
		case 1:// 关卡
			pass = 1;
			panel.gameMap(1);// 绘制框框
			break;
		case 2:
			pass = 2;
			panel.gameMap(2);
			break;
		case 3:
			pass = 3;
			panel.gameMap(3);
			break;
		case 4:
			pass = 4;
			panel.gameMap(4);
			break;
		case 5:
			pass = 5;
			panel.gameMap(5);
			break;
		case 6:
			pass = 6;
			panel.gameMap(6);
			break;
		case 7:
			pass = 7;
			panel.gameMap(7);
			break;

		case 8:// 继续游戏
				// 玩家选择关卡数

			switch (pass) {
			case 0:// 如果不选择 默认风车村
			case 1:// 风车村
				panel.coreMap(0);
				break;
			case 2:// 加雅岛
				panel.coreMap(1);
				break;
			case 3:// 可可亚西村
				panel.coreMap(2);
				break;
			case 4:// 罗格镇
				panel.coreMap(3);
				break;
			case 5:// 魔之海
				panel.coreMap(4);
				break;
			case 6:
				panel.coreMap(5);
				break;
			case 7:
				panel.coreMap(6);
				break;
			}

			panel.control(1);
			break;
		case 9:// 返回菜单
			panel.control(-1);
			break;
		}
	}

	private void isBounds(int x, int y) {
		// 鼠标范围判断
		int index = -1;
		for (int i = 0; i < wlList.size(); i++) {
			Rectangle p = wlList.get(i);
			if (p.contains(x, y - 30)) {
				index = i;
			}
		}
		if (index == -1) {
			return;
		}

		switch (index) {
		case 1:
			index = 2;
			break;
		case 2:
			index = 8;
			break;
		}
		panel.control(index);
	}

}