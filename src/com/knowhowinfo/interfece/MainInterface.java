package com.knowhowinfo.interfece;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

/**
 * @author Administrator 游戏中使用的常量
 */
public interface MainInterface {

	String FONT = "方正喵鸣个性版by小成_x!aocheng";

	String FONT2 = "宋体";

	int ROW = 9;
	int COL = 14;

	int GAMECORE_IMAGE_WIDTH = 60;
	int GAMECORE_IMAGE_HEIGHT = 60;

	// GameMap
	int GAMEMAP_SC_WIDTH = 830;

	int GAMEMAP_SC_HEIGHT = 622;

	// GameWelcomePanel

	String GAMEWELCOMEPANEL_BTN_KEEP = "继 续 游 戏";

	String GAMEWELCOMEPANEL_BTN_START = "新 的 旅 程";

	String GAMEWELCOMEPANEL_BTN_SET = "游 戏 设 置";

	String GAMEWELCOMEPANEL_BTN_ABOUT = "关 于 游 戏";

	String gAMEWELCOMEPANEL_BTN_EXIT = "退 出 游 戏";

	// Role
	int ROLE_SPEED = 5;
	int ROLE_IMAGE_HEIGHT = 160;

	// Color
	Color BORDER_COLOR = new Color(179, 108, 20);

	Color BORDER_COLOR_HYALINE = new Color(179, 108, 20, 50);

	Color Font_Color = new Color(51, 51, 51);

	// timer时间
	int TIME = 50;

	// 游戏延迟时间(Loading)
	int DELAY_TIME = 3000;// 3秒

	// 游戏中的声音

	// 游戏欢迎
	File WELCOME_BACKGROUND_AUDIO = new File("Audio/Audio2.wav");

	// 鼠标
	File MOUSE_AUDIO = new File("Audio/Audio3.wav");

	// 地图中背景音
	File MAP_BACKGROUND_AUDIO = new File("Audio/Audio.wav");

	// 游戏地图界面解说:
	String COMMAND1 = "风车村:路飞从小生活的家乡,我们冒险的起始地,也是最值得我们怀念的地方。";

	String COMMAND2 = "加雅群岛:一座不可思议的岛屿,也叫\"黄金岛、黄金的故乡\",在1000米高空的空岛(Sky Pyea)本是同一个岛,形状像个骷髅,400年前,加雅岛的一半被喷起来的海流冲上了高空。";

	String COMMAND3 = "可可亚西村:草帽海贼团航海士娜美的家乡,曾经是一个和平美好的村子,但后来被鱼人恶龙海贼团进行了残暴的统治,随后草帽海贼团打败了鱼人,村子又回到了美好的生活。";

	String COMMAND4 = "罗格镇:被称为:\"开始与结束的城镇\",海贼王罗杰的故乡,也是他牺牲的地方,这里有前海贼王的意志,海贼王里最神秘的人也会出现在这里。";

	String COMMAND5 = "魔之海:\"王下七武海\":月光莫利亚居住的地方,曾经和路飞一样对自己的能力充满自信, 但是在新世界中被\"四皇\"-百兽凯多打败,失去全部同伴,随后他更坚信认为:因为活着,才会失去。";

	String COMMAND6 = "圣地玛丽乔亚:是世界政府\"五老星\"总部的所在地,位于伟大航路前半段和后半段之间,红土大陆顶峰,此地的居民是世界贵族(天龙人)";

	String COMMAND7 = "One Piece:梦想之地-拉夫德鲁岛屿,伟大航路的终点站,是所有海贼趋之若鹜的地方,英文:\"ROUGH TALE\",译为:\"坚固的传言\",路飞一伙到达拉夫德鲁对于他们来说也就是实现了自己的梦想!";

	// 未解锁地图说明
	String COMMAND8 = "地图尚未解锁。 ";

	// 当鼠标为在关卡范围内
	String COMMAND9 = "Come On!";

	// ABOUT
	Font F = new Font("宋体", Font.BOLD, 15);

	// 游戏中消失的常量
	int VALUE = -1;
}
