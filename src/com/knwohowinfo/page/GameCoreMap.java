package com.knwohowinfo.page;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import com.knowhowinfo.interfece.MainInterface;
import com.knowhowinfo.ui.MainPanel;
import com.knowhowinfo.util.CoreUtil;
import com.knowhowinfo.util.ImageUtil;
import com.knowhowinfo.util.MathUtil;
import com.knowhowinfo.util.ReadDataUtil;

/**
 * @author Administrator 游戏核心主类 (地图)
 */
public class GameCoreMap {

	private CoreUtil co = CoreUtil.getInstance();

	private MainPanel panel = MainPanel.getInstance();

	private ArrayList<BufferedImage> imgs = ImageUtil.cutImage("img/imgae.png",
			1, 16, 16);

	private ArrayList<BufferedImage> backImages = new ArrayList<BufferedImage>();

	private static ArrayList<Rectangle> list = new ArrayList<Rectangle>();

	private Random ran = new Random();

	private ArrayList<Point> po = new ArrayList<Point>();

	private ArrayList<Point> temp = new ArrayList<Point>();

	private int index = 0;// 相对应的每关游戏背景图片下标

	private static ArrayList<Point> line = new ArrayList<Point>();

	private List<Integer> iii = new ArrayList<Integer>();

	private static int maps[][] = {
			{ -1, -1, -1, -1, -1},
			{ -1, -1, -1, -1, -1},
			{ -1, -1, -1, -1, -1},
			{ -1, -1, -1, -1, -1},
			{ -1, -1, -1, -1, -1},

	};

	// 构造方法

	public GameCoreMap() {
		init();
		initIndex();
		setDefault();
	}

	// 重新设置坐标
	public void initIndex() {
		index = 0;
	}

	private void init() {
		// 加载游戏场景图片
		try {
			backImages.add(ImageIO.read(new File("scene/image.jpg")));// 第 1 关
			backImages.add(ImageIO.read(new File("scene/image2.jpg")));// 第 2 关
			backImages.add(ImageIO.read(new File("scene/image3.jpg")));// 第 3 关
			backImages.add(ImageIO.read(new File("scene/image4.jpg")));// 第 4 关
			backImages.add(ImageIO.read(new File("scene/image5.jpg")));// 第 4 关
			backImages.add(ImageIO.read(new File("scene/image6.jpg")));// 第 4 关
			backImages.add(ImageIO.read(new File("scene/image7.jpg")));// 第 4 关
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;// 转换2D画笔

		// 绘制游戏场景图片

		g2d.drawImage(backImages.get(index), 0, 82, null);

		// 绘制图片
		for (int i = 0; i < MainInterface.ROW; i++) {
			for (int j = 0; j < MainInterface.COL; j++) {
				// 非-1 画图
				if (maps[i][j] != MainInterface.VALUE) {

					g2d.drawImage(imgs.get(maps[i][j]), j
							* MainInterface.GAMECORE_IMAGE_WIDTH, i
							* MainInterface.GAMECORE_IMAGE_HEIGHT + 55,
							MainInterface.GAMECORE_IMAGE_WIDTH,
							MainInterface.GAMECORE_IMAGE_HEIGHT, null);
				}
			}
		}

		// 画出选择框
		for (Point p : po) {
			if (!temp.isEmpty()) {
				temp.clear();
			}
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(3.0f));
			if (maps[MathUtil.getRow(p.y)][MathUtil.getCol(p.x)] != MainInterface.VALUE) {
				g2d.drawRoundRect(p.x, p.y, MainInterface.GAMECORE_IMAGE_WIDTH,
						MainInterface.GAMECORE_IMAGE_HEIGHT, 10, 20);
			}
		}

		// 提示框
		for (Point p : temp) {
			g2d.setStroke(new BasicStroke(3.0f));
			g2d.drawRoundRect(p.x, p.y, MainInterface.GAMECORE_IMAGE_WIDTH,
					MainInterface.GAMECORE_IMAGE_HEIGHT, 10, 20);
		}

		// 画出连接线

		if (line.size() != 0) {
			Point p = line.get(0);
			for (int i = 0; i < line.size(); i++) {
				Point pp = line.get(i);
				g2d.drawLine(p.x + MainInterface.GAMECORE_IMAGE_WIDTH / 2, p.y
						+ MainInterface.GAMECORE_IMAGE_HEIGHT / 2, pp.x
						+ MainInterface.GAMECORE_IMAGE_WIDTH / 2, pp.y
						+ MainInterface.GAMECORE_IMAGE_HEIGHT / 2);
				p = pp;
			}
			line.clear();
		}

	}

	public static int[][] getMaps() {
		return maps;
	}

	// 得到当前游戏的关卡
	public int getIndex() {
		return index;
	}

	// 画出连接线
	public static void drawLine(ArrayList<Point> line) {
		GameCoreMap.line = line;
	}

	// 接收每一次点击的点
	public void setUpdata(ArrayList<Point> po) {

		if (panel == null) {
			panel = MainPanel.getInstance();
		}
		this.po = po;
		if (po.size() == 2) {
			boolean option = isDelete();
			if (option) {// 可以消除

				panel.plauSound();

				if (maps[MathUtil.getRow(po.get(0).y)][MathUtil.getCol(po
						.get(0).x)] != MainInterface.VALUE
						&& maps[MathUtil.getRow(po.get(1).y)][MathUtil
								.getCol(po.get(1).x)] != MainInterface.VALUE) {
					GameStart.setMark(ran.nextInt(49) + 1);// 设置分数(分数随机 50 分之内)
				}

				maps[MathUtil.getRow(po.get(0).y)][MathUtil.getCol(po.get(0).x)] = MainInterface.VALUE;
				maps[MathUtil.getRow(po.get(1).y)][MathUtil.getCol(po.get(1).x)] = MainInterface.VALUE;

			}
			// 判断现在地图是否已经没有元素>
			boolean win = isWin();
			if (win) {// 玩家赢了
				panel.dialogUpdata(4);
				panel.diaSet(4);
				panel.control(6);
				return;
			}
		}
	}

	// 判断地图玩家是否赢了
	private boolean isWin() {
		for (int i = 0; i < MainInterface.ROW; i++) {
			for (int j = 0; j < MainInterface.COL; j++) {
				if (maps[i][j] != MainInterface.VALUE) {
					return false;
				}
			}
		}
		return true;
	}

	// 是否可以消除
	private boolean isDelete() {
		return co.isDelete(po.get(0), po.get(1));
	}

	// 返回当前所有图片的坐标
	public static ArrayList<Rectangle> getRectangle() {
		return list;
	}

	// 游戏关卡
	public void setIndex(int index) {
		if (!temp.isEmpty()) {
			temp.clear();
		}

		if (!po.isEmpty()) {
			po.clear();
		}

		// 如果当前选择的关卡还未开放
		if (index < ReadDataUtil.getPass()) {
			this.index = index;
		}

		if (panel == null) {
			panel = MainPanel.getInstance();
		}

		panel.playAudio(this.index + 1);// 播放

	}

	// 提示
	public void prompt() {
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 1; j >= 0; j--) {
				Point a = new Point(list.get(i).x, list.get(i).y);
				Point b = new Point(list.get(j).x, list.get(j).y);

				if (maps[MathUtil.getRow(a.y)][MathUtil.getCol(a.x)] != MainInterface.VALUE
						&& maps[MathUtil.getRow(b.y)][MathUtil.getCol(b.x)] != MainInterface.VALUE
						&& j != i) {
					if (co.isDelete(a, b)) {
						temp.add(a);
						temp.add(b);
						return;
					}
				}
			}
		}
	}

	// 设置图片
	public void setImage(int count) {

		int nn = 0;

		// 36

		// 存入每一张图片出现的次数
		ArrayList<Integer> nums = new ArrayList<Integer>();

		if (!(count <= 36)) {

			while (true) {
				while (nums.size() < 16) {
					int num = ran.nextInt(8);
					if (num >= 2 && num % 2 == 0) {
						nums.add(num);
					}
				}
				int num = 0;
				for (int in : nums) {
					num += in;
				}
				if (num == count) {
					break;
				} else {
					nums.clear();
				}
			}
		} else {// 当前画布上面图片小于36张

			while (true) {
				nn = 0;
				int num = ran.nextInt(5);// 出现次数
				if (num >= 2 && num % 2 == 0) {// 2 和 4
					nums.add(num);
				}
				for (int o : nums) {
					nn += o;
				}
				if (nn == count) {
					break;
				}
				if (nn > count) {
					nums.clear();// 清空
				}
			}
		}

		for (int i = 0; i < nums.size(); i++) {
			int num = nums.get(i);
			for (int j = 0; j < num; j++) {
				iii.add(i);
			}
		}

		// 使用集合打乱变长数组里面的元素
		Collections.shuffle(iii);
	}

	// 设置默认
	public void setDefault() {
		if (!list.isEmpty()) {
			list.clear();// 清空
		}

		setImage(84);// 设置出现的图片必须是偶数

		// 重新设置坐标
		for (int i = 0; i < MainInterface.ROW; i++) {
			for (int j = 0; j < MainInterface.COL; j++) {
				if (i == 0 || i == MainInterface.ROW - 1) {
					break;
				}

				if (j == 0 || j == MainInterface.COL - 1) {
					continue;
				}

				for (int y = 0; y < iii.size();) {
					maps[i][j] = iii.get(y);
					iii.remove(y);
					break;
				}

				list.add(new Rectangle(j * MainInterface.GAMECORE_IMAGE_WIDTH,
						i * MainInterface.GAMECORE_IMAGE_HEIGHT + 55,
						MainInterface.GAMECORE_IMAGE_HEIGHT,
						MainInterface.GAMECORE_IMAGE_HEIGHT));
			}
		}

		// panel.mapsUpdata(maps);//传入更新后的连连看布局
	}

	// 打乱
	public void upNum() {
		int number = 0;
		if (!temp.isEmpty()) {
			temp.clear();
		}
		for (int i = 0; i < MainInterface.ROW; i++) {
			for (int j = 0; j < MainInterface.COL; j++) {
				if (maps[i][j] == MainInterface.VALUE) {
					continue;//
				}
				number++;// 先做个统计 将现在屏幕上面图片个数算出来
			}
		}
		setImage(number);

		for (int i = 0; i < MainInterface.ROW; i++) {
			for (int j = 0; j < MainInterface.COL; j++) {
				if (maps[i][j] == MainInterface.VALUE) {
					continue;//
				}
				for (int y = 0; y < iii.size();) {
					maps[i][j] = iii.get(y);
					iii.remove(y);
					break;
				}

			}
		}

		co.mapsUpdata(maps);// 将打乱后的图片重新更新
	}

}
