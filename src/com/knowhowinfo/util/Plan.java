package com.knowhowinfo.util;

/**
 * @author Administrator 游戏关卡访问类(进度)
 */
public class Plan {

	private int pass;

	// 构造方法
	public Plan(int pass) {
		this.pass = pass;
	}

	// 得到关卡
	public int getPass() {
		return pass;
	}

	// 修改关卡
	public void setPass(int pass) {
		this.pass = pass;
	}
}
