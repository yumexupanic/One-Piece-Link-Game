package com.knowhowinfo.util;

import java.awt.Point;
import java.util.ArrayList;

import com.knowhowinfo.interfece.MainInterface;
import com.knowhowinfo.util.MathUtil;
import com.knwohowinfo.page.GameCoreMap;

/**
 * @author admin 练练看算法类
 */
public class CoreUtil {

	// 练练看的连线操作
	private ArrayList<Point> line = new ArrayList<Point>();

	private int maps[][] = GameCoreMap.getMaps();

	public static CoreUtil co = new CoreUtil();

	private boolean flag = true;

	private CoreUtil() {

	}

	public static CoreUtil getInstance() {
		return co;
	}

	public boolean isDelete(Point a, Point b) {
		if (maps[MathUtil.getRow(a.y)][MathUtil.getCol(a.x)] == MainInterface.VALUE
				|| maps[MathUtil.getRow(b.y)][MathUtil.getCol(b.x)] == MainInterface.VALUE) {
			return false;
		}

		flag = true;

		boolean boo = isBounds(a, b);
		GameCoreMap.drawLine(line);

		return boo;
	}

	// 设置图片打乱之后(更新)
	public void mapsUpdata(int[][] maps) {
		this.maps = maps;
	}

	private boolean isBounds(Point a, Point b) {
		int aRow = MathUtil.getRow(a.y);
		int aCol = MathUtil.getCol(a.x);

		int bRow = MathUtil.getRow(b.y);
		int bCol = MathUtil.getCol(b.x);

		if (maps[aRow][aCol] != maps[bRow][bCol]) {
			return false;
		}

		if (aRow == bRow && row(a, b)) {
			listClear();
			line.add(a);
			line.add(b);
			return true;
		}

		if (aCol == bCol && col(a, b)) {
			listClear();
			line.add(a);
			line.add(b);
			return true;
		}

		if (one(a, b)) {
			return true;
		}

		if (two(a, b)) {
			return true;
		}

		return false;
	}

	private boolean row(Point a, Point b) {
		int aRow = MathUtil.getRow(a.y);
		int aCol = MathUtil.getCol(a.x);
		int bCol = MathUtil.getCol(b.x);

		int start = Math.max(aCol, bCol);
		int end = Math.min(aCol, bCol);

		for (int i = start - 1; i > end; i--) {
			if (maps[aRow][i] != MainInterface.VALUE) {
				return false;
			}
		}
		return true;
	}

	private void listClear() {
		if (!line.isEmpty()) {
			line.clear();
		}
	}

	private boolean col(Point a, Point b) {
		int aCol = MathUtil.getCol(a.x);
		int aRow = MathUtil.getRow(a.y);
		int bRow = MathUtil.getRow(b.y);

		int start = Math.max(aRow, bRow);
		int end = Math.min(aRow, bRow);

		for (int i = start - 1; i > end; i--) {
			if (maps[i][aCol] != MainInterface.VALUE) {
				return false;
			}
		}

		return true;
	}

	private boolean one(Point a, Point b) {

		Point c = new Point(a.x, b.y);

		Point d = new Point(b.x, a.y);

		int cRow = MathUtil.getRow(c.y);
		int cCol = MathUtil.getCol(c.x);
		int dRow = MathUtil.getRow(d.y);
		int dCol = MathUtil.getCol(d.x);

		if (maps[cRow][cCol] == MainInterface.VALUE) {
			boolean boo = col(a, c) && row(b, c);
			if (boo && flag) {
				line.add(a);
				line.add(c);
				line.add(b);
			}
			return boo;
		}

		if (maps[dRow][dCol] == MainInterface.VALUE) {
			boolean boo = row(a, d) && col(b, d);
			if (boo && flag) {
				line.add(a);
				line.add(d);
				line.add(b);
			}
			return boo;
		}
		return false;
	}

	private boolean two(Point a, Point b) {
		flag = false;
		listClear();

		int aRow = MathUtil.getRow(a.y);
		int aCol = MathUtil.getCol(a.x);

		for (int y = aRow; y < MainInterface.ROW; y++) {
			if (maps[y][aCol] == MainInterface.VALUE
					&& col(MathUtil.getPoint(y, aCol), a)
					&& one(MathUtil.getPoint(y, aCol), b)) {// 按照1次转弯处理
				return true;
			}
			if (maps[y][aCol] == MainInterface.VALUE
					&& col(MathUtil.getPoint(y, aCol), a)
					&& one(b, MathUtil.getPoint(y, aCol))) {// 按照1次转弯处理
				return true;
			}
		}

		for (int y = aRow; y >= 0; y--) {
			if (maps[y][aCol] == MainInterface.VALUE
					&& col(MathUtil.getPoint(y, aCol), a)
					&& one(MathUtil.getPoint(y, aCol), b)) {// 按照1次转弯处理
				return true;
			}
			if (maps[y][aCol] == MainInterface.VALUE
					&& col(MathUtil.getPoint(y, aCol), a)
					&& one(b, MathUtil.getPoint(y, aCol))) {// 按照1次转弯处理
				return true;
			}
		}

		for (int x = aCol; x >= 0; x--) {
			if (maps[aRow][x] == MainInterface.VALUE
					&& row(a, MathUtil.getPoint(aRow, x))
					&& one(MathUtil.getPoint(aRow, x), b)) {// 按照1次转弯处理
				return true;
			}
			if (maps[aRow][x] == MainInterface.VALUE
					&& row(a, MathUtil.getPoint(aRow, x))
					&& one(b, MathUtil.getPoint(aRow, x))) {
				return true;
			}
		}

		for (int x = aCol; x < MainInterface.COL; x++) {
			if (maps[aRow][x] == MainInterface.VALUE
					&& row(a, MathUtil.getPoint(aRow, x))
					&& one(MathUtil.getPoint(aRow, x), b)) {// 按照1次转弯处理
				return true;
			}
			if (maps[aRow][x] == MainInterface.VALUE
					&& row(a, MathUtil.getPoint(aRow, x))
					&& one(b, MathUtil.getPoint(aRow, x))) {// 按照1次转弯处理
				return true;
			}
		}
		return false;
	}
}
