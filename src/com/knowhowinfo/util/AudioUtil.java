package com.knowhowinfo.util;

import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * @author Administrator 游戏中的音效 背景音乐
 */
public class AudioUtil {
	private Player player;
	private Thread th;// 创建一个线程
	private Thread th2;// 创建一个线程

	public AudioUtil() {
		init();
	}

	private void init() {
		th = new Thread();
		th2 = new Thread();
	}

	@SuppressWarnings("deprecation")
	public void close() {
		th.stop();// 停止音乐播放
	}

	/**
	 * 游戏中的音效
	 */
	public void sound() {
		th2 = new Thread(new Runnable() {
			@Override
			public void run() {
				// 循环播放
				try {
					player = new Player(new FileInputStream("Audio/sound.mp3"));
					player.play();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		th2.start();// 线程启动
	}

	/**
	 * 游戏地图中的音乐
	 */
	public void map(boolean boo) {
		if (boo) {
			th.stop();
			;

		} else {
			th.stop();
			;
			th = new Thread(new Runnable() {
				@Override
				public void run() {
					// 循环播放
					try {
						while (true) {
							player = new Player(new FileInputStream(
									"Audio/map.mp3"));
							player.play();
						}
					} catch (JavaLayerException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});

			th.start();// 线程启动
		}
	}

	// 第一关
	public void wave1(boolean boo) {

		if (boo) {
			th.stop();
		} else {
			th.stop();
			th = new Thread(new Runnable() {
				@Override
				public void run() {
					// 循环播放
					try {
						while (true) {
							player = new Player(new FileInputStream(
									"Audio/1.mp3"));
							player.play();
						}
					} catch (JavaLayerException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			th.start();// 线程启动
		}
	}

	// 第 二 关
	public void wave2(boolean boo) {

		if (boo) {
			th.stop();
		} else {
			th.stop();
			th = new Thread(new Runnable() {
				@Override
				public void run() {
					// 循环播放
					try {
						while (true) {
							player = new Player(new FileInputStream(
									"Audio/2.mp3"));
							player.play();
						}
					} catch (JavaLayerException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			th.start();// 线程启动
		}

	}

	// 第3关
	public void wave3(boolean boo) {
		if (boo) {
			th.stop();
		} else {

			th.stop();
			th = new Thread(new Runnable() {
				@Override
				public void run() {
					// 循环播放
					try {
						while (true) {
							player = new Player(new FileInputStream(
									"Audio/3.mp3"));
							player.play();
						}
					} catch (JavaLayerException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			th.start();// 线程启动
		}
	}

	// 第 四 关
	public void wave4(boolean boo) {

		if (boo) {
			th.stop();
		} else {
			th.stop();
			th = new Thread(new Runnable() {
				@Override
				public void run() {
					// 循环播放
					try {
						while (true) {
							player = new Player(new FileInputStream(
									"Audio/4.mp3"));
							player.play();
						}
					} catch (JavaLayerException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			th.start();// 线程启动
		}
	}

	// 第 五 关
	public void wave5(boolean boo) {

		if (boo) {
			th.stop();
		} else {
			th.stop();
			th = new Thread(new Runnable() {
				@Override
				public void run() {
					// 循环播放
					try {
						while (true) {
							player = new Player(new FileInputStream(
									"Audio/5.mp3"));
							player.play();
						}
					} catch (JavaLayerException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			th.start();// 线程启动
		}
	}

	// 第 六 关
	public void wave6(boolean boo) {
		if (boo) {
			th.stop();
		} else {
			th.stop();
			th = new Thread(new Runnable() {
				@Override
				public void run() {
					// 循环播放
					try {
						while (true) {
							player = new Player(new FileInputStream(
									"Audio/6.mp3"));
							player.play();
						}
					} catch (JavaLayerException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			th.start();// 线程启动
		}
	}

	// 第 七 关
	public void wave7(boolean boo) {
		if (boo) {
			th.stop();

		} else {
			th.stop();
			th = new Thread(new Runnable() {
				@Override
				public void run() {
					// 循环播放
					try {
						while (true) {
							player = new Player(new FileInputStream(
									"Audio/7.mp3"));
							player.play();
						}
					} catch (JavaLayerException e) {
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			});
			th.start();// 线程启动
		}
	}
}
