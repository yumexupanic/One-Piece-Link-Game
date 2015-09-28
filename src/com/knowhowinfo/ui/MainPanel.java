package com.knowhowinfo.ui;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.db4o.ObjectSet;
import com.knowhowinfo.interfece.MainInterface;
import com.knowhowinfo.util.AudioUtil;
import com.knowhowinfo.util.CoreUtil;
import com.knowhowinfo.util.DbUtil;
import com.knowhowinfo.util.List;
import com.knowhowinfo.util.Plan;
import com.knwohowinfo.page.*;

/**
 * @author admin 主面板
 */
public class MainPanel extends JPanel implements ActionListener {

	private GameMap map = new GameMap();

	private GameStop stop = new GameStop();// 暂停游戏

	private GameStart start = new GameStart();// 游戏开始界面

	private GameAbout about = new GameAbout();// 游戏关于

	private GameCoreMap core = new GameCoreMap();// 连连看布局

	private Welcome welcome = new Welcome();// 游戏欢迎界面

	private GameDialog dialog = new GameDialog();//

	private int auIndex;

	private GameList gList = new GameList();// 游戏排行榜

	private CoreUtil co = CoreUtil.getInstance();

	private GameSet set = new GameSet();// 游戏设置

	private AudioUtil audio = new AudioUtil();

	private BufferedImage buff;

	private Graphics pen;

	private Timer timer;

	public int flag = -1;// 流程控制

	private int index;

	private boolean boo2 = true;

	private boolean boo = true;

	private int music;

	private static MainPanel panle = new MainPanel();

	private MainPanel() {
		init();
		initComs();
	}

	// 使用单例模式
	public static MainPanel getInstance() {
		return panle;
	}

	private void initComs() {
		// 使用双缓冲技术
		buff = new BufferedImage(MainInterface.GAMEMAP_SC_WIDTH,
				MainInterface.GAMEMAP_SC_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		pen = buff.getGraphics();
	}

	// 改变当前画布
	public void control(int flag) {

		if (flag == 0) {// 地图界面
			playAudio(flag);
		}

		if (flag == -1) {// 停止全部的音乐
			audio.wave1(true);
			audio.wave2(true);
			audio.wave3(true);
			audio.wave4(true);
			audio.wave5(true);
			audio.wave6(true);
			audio.wave7(true);
			audio.map(true);
		}

		if (flag == 4) {
			DbUtil.getInstance().close();// 先关闭数据库
			System.exit(0);// 退出程序
			return;
		}

		this.flag = flag;
	}

	// 音效
	public void plauSound() {
		if (boo) {
			audio.sound();
		}
	}

	// 背景音
	public void playAudio(int index) {
		if (!boo2) {
			return;
		}
		this.music = index;

		switch (index) {
		case 0:// 播放地图音乐
			audio.wave1(true);
			audio.wave2(true);
			audio.wave3(true);
			audio.wave4(true);
			audio.wave5(true);
			audio.wave6(true);
			audio.wave7(true);

			audio.map(false);
			break;
		case 1:// 第一关
			audio.map(true);
			audio.wave2(true);
			audio.wave3(true);
			audio.wave4(true);
			audio.wave5(true);
			audio.wave6(true);
			audio.wave7(true);

			audio.wave1(false);
			break;
		case 2:// 第二关
			audio.map(true);
			audio.wave1(true);
			audio.wave3(true);
			audio.wave4(true);
			audio.wave5(true);
			audio.wave6(true);
			audio.wave7(true);

			audio.wave2(false);
			break;
		case 3:// 第三关
			audio.map(true);
			audio.wave1(true);
			audio.wave2(true);
			audio.wave4(true);
			audio.wave5(true);
			audio.wave6(true);
			audio.wave7(true);

			audio.wave3(false);
			break;
		case 4:// 第四关
			audio.map(true);
			audio.wave1(true);
			audio.wave2(true);
			audio.wave3(true);
			audio.wave5(true);
			audio.wave6(true);
			audio.wave7(true);
			audio.wave4(false);
			break;
		case 5:// 第五关
			audio.map(true);
			audio.wave1(true);
			audio.wave2(true);
			audio.wave3(true);
			audio.wave4(true);
			audio.wave6(true);
			audio.wave7(true);
			audio.wave5(false);
			break;
		case 6:// 第六关
			audio.map(true);
			audio.wave1(true);
			audio.wave2(true);
			audio.wave3(true);
			audio.wave4(true);
			audio.wave5(true);
			audio.wave7(true);
			audio.wave6(false);
			break;
		case 7:// 第七关
			audio.map(true);
			audio.wave1(true);
			audio.wave2(true);
			audio.wave3(true);
			audio.wave4(true);
			audio.wave5(true);
			audio.wave6(true);
			audio.wave7(false);
			break;
		}
	}

	private void init() {
		timer = new Timer(MainInterface.TIME, this);
		timer.setActionCommand("定时器");
		timer.start();
	}

	// 控制timer是否启动(停止)
	public void setTimer() {

		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}

	}

	// 欢迎界面
	public void welcomeUpdata(int select) {
		welcome.updata(select);
	}

	// 地图界面
	public void mapUpdata(int select) {
		map.updata(select);
	}

	// 开始界面
	public void startUpdata(int select) {
		start.updata(select);
	}

	// 游戏关卡背景图下标
	public void coreMap(int index) {
		this.index = index;
		core.setIndex(index);

	}

	// 关于界面
	public void aboutUpdata(int select) {
		about.updata(select);
	}

	// 暂停界面
	public void stopUpdata(int select) {
		stop.updata(select);
	}

	// 提供每次点击的点
	public void coreUpdata(ArrayList<Point> po) {
		core.setUpdata(po);
	}

	// 更新默认
	public void coreDefault() {
		core.setDefault();// 布局默认
		start.setDefault();// 游戏时间默认
	}

	// 连连看提示
	public void corePrompt() {
		if (start.getPromptNum() == 0) {
			return;
		}
		start.setPromptNum();
		core.prompt();
	}

	// 连连看打乱
	public void coreUpNum() {
		if (start.getUpNum() == 0) {
			return;
		}
		start.setUpNum();
		core.upNum();
	}

	// 游戏对话框
	public void dialogUpdata(int select) {
		dialog.setCommand(select);
	}

	// 得到游戏的对话框显示信息
	public int getDiaNum() {
		return dialog.getSelect();
	}

	// 游戏排行榜
	public void listUpdata(int select) {
		gList.Updata(select);
	}

	// 设置地图边框
	public void mapBorder() {
		map.inire();
		core.initIndex();
	}

	public void diaSet(int option) {
		if (option == 4) {// 玩家赢了
			// 将分数存入到数据库中
			if (start.getMark() != 0) {
				DbUtil.getInstance().storeNum(new List(start.getMark()));
			}
			// 得到当前关卡
			int index = core.getIndex();

			index++;

			ObjectSet<Plan> set = DbUtil.getInstance().getPass();

			if (set.size() == 0) {

				DbUtil.getInstance().storePass(new Plan(index));
			} else {
				int number = set.get(0).getPass();
				if (index > number) {
					// 存入关卡
					DbUtil.getInstance().storePass(new Plan(index));
				}
			}
		}

		dialog.setDia(option);
	}

	// 得到连连看地图
	public int[][] getMaps() {
		return core.getMaps();
	}

	// 判断是否可以消除
	public boolean isDelete(Point a, Point b) {
		return co.isDelete(a, b);
	}

	public void gameMap(int i) {
		map.setDraw(i);
	}

	// 设置内容
	public void gameSetUpdata(int index) {
		set.Updata(index);
	}

	// 查看音乐 音效状态
	// 确认现在点击的内容
	public void setGame() {
		// 说明点击了确认

		boo = set.getIsDown();// 音效

		boo2 = set.getIsDown2();// 背景音

		if (false == boo2) {
			// 停止所有正在放的音乐
			audio.map(true);
			audio.wave1(true);
			audio.wave2(true);
			audio.wave3(true);
			audio.wave4(true);
			audio.wave5(true);
			audio.wave6(true);
			audio.wave7(true);
		} else {
			playAudio(music);
		}
	}

	public void gameSet(int index) {
		set.updata(index);

	}

	@Override
	public void paint(Graphics g) {

		switch (flag) {
		case -1:// 欢迎界面
			welcome.paint(pen);
			break;
		case 0:// 地图界面
			map.paint(pen);
			break;
		case 1:// 开始游戏界面(关卡数)
			start.paint(pen);
			core.paint(pen);
			break;
		case 2:// 新的旅程
			dialogUpdata(5);
			diaSet(5);
			flag = 6;
			break;
		case 3:// 关于游戏
			about.paint(pen);
			break;
		case 5:// 游戏暂停界面
			stop.paint(pen);
			break;
		case 6:// 游戏对话框
			dialog.paint(pen);
			break;
		case 8:
			gList.paint(pen);
			break;
		case 9:// 游戏设置
			set.paint(pen);
			break;
		}
		g.drawImage(buff, 0, 0, null);
	}

	// 监听
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if ("定时器".equals(cmd)) {
			repaint();
		}
	}

}
