package com.knowhowinfo.util;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.ObjectSet;

/**
 * @author admin
 * 
 *         对数据库统一操作的类
 */
public class DbUtil {
	private String fileName = "data/data.db";

	private EmbeddedObjectContainer eoc;

	private static DbUtil util = new DbUtil();

	private DbUtil() {
		eoc = Db4oEmbedded.openFile(fileName);
	}

	public static DbUtil getInstance() {
		return util;
	}

	// 存入排名分数
	public void storeNum(List list) {
		ObjectSet<List> set = eoc.query(List.class);// 查询
		if (set.size() > 2) {
			while (set.hasNext()) {
				List se = set.next();
				if (list.getMark() > se.getMark()) {
					se.setMark(list.getMark());// 修改
					eoc.store(se);
					eoc.commit();// 提交
					break;
				}
			}
		} else {
			eoc.store(list);
		}
	}

	// 取出分数
	public ObjectSet<List> getMark() {
		return eoc.query(List.class);
	}

	// 存入当前游戏的进度
	public void storePass(Plan plan) {
		ObjectSet<Plan> set = eoc.query(Plan.class);// 查询
		if (set.size() != 0) {
			while (set.hasNext()) {
				Plan se = set.next();
				se.setPass(plan.getPass());// 修改
				eoc.store(se);// 保存
				eoc.commit();// 提交
			}
		} else {
			eoc.store(plan);// 保存
		}
	}

	// 读取游戏的进度
	public ObjectSet<Plan> getPass() {
		return eoc.query(Plan.class);// 查询;
	}

	// 关闭对象数据库 释放资源
	public void close() {
		eoc.close();
	}

}
