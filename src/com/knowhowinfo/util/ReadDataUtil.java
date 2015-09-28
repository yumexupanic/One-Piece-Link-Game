package com.knowhowinfo.util;

import java.util.ArrayList;
import java.util.Collections;
import com.db4o.ObjectSet;
import com.knowhowinfo.util.DbUtil;
import com.knowhowinfo.util.List;
import com.knowhowinfo.util.Plan;

/**
 * @author admin 数据的读取操作类
 */
public class ReadDataUtil {

	// 获得玩家的分数
	public static ArrayList<Integer> getMark() {
		ArrayList<Integer> marks = new ArrayList<Integer>();
		ObjectSet<List> mark = DbUtil.getInstance().getMark();
		while (mark.hasNext()) {
			List i = mark.next();
			marks.add(i.getMark());
		}
		// 对数组进行排序操作
		Collections.sort(marks);

		return marks;
	}

	// 获得现在的游戏关卡
	public static int getPass() {
		int index = -1;
		ObjectSet<Plan> pa = DbUtil.getInstance().getPass();
		while (pa.hasNext()) {
			Plan p = pa.next();
			index = p.getPass();
			break;
		}

		// 解放关卡
		switch (index) {
		case -1:// 数据库中没有文件的时候
		case 0:
			index = 1;
			break;
		case 1:
			index = 2;
			break;
		case 2:
			index = 3;
			break;
		case 3:
			index = 4;
			break;
		case 4:
			index = 5;
			break;
		case 5:
			index = 6;
			break;
		case 6:
			index = 7;
			break;
		}

		return index;
	}
}
