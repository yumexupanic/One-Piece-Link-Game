package com.knowhowinfo.util;

import java.awt.Point;

import com.knowhowinfo.interfece.MainInterface;

/**
 * @author admin 游戏中的数学工具类
 */
public class MathUtil {

	// 得到行
	public static int getRow(int y) {
		return y / MainInterface.GAMECORE_IMAGE_HEIGHT;
	}

	// 得到列
	public static int getCol(int x) {
		return x / MainInterface.GAMECORE_IMAGE_WIDTH;
	}

	// 得到点
	public static Point getPoint(int row, int col) {
		return new Point(col * MainInterface.GAMECORE_IMAGE_WIDTH, row
				* MainInterface.GAMECORE_IMAGE_HEIGHT);
	}

}
