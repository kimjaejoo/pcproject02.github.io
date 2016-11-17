package co.kr.jaejoo.asset;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Setting {
	// inner variables for those public Variables
	private static int width = 1600, height = 900;
	private static Point zeroPoint = new Point(0, 0);
	private static Point panelPoint = new Point(0, -30);
	private static Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();

	// prefix 'b' means Basic setting
	public static Font bFont = new Font("고딕", Font.BOLD, 12);
	public static Color bColor = new Color(36, 205, 198);
	public static Point bPoint = new Point(width, height); // 1600, 900
	public static Dimension bDimen = new Dimension(width, height);
	public static Rectangle bRectangle = new Rectangle(zeroPoint, bDimen); // 0,
																			// 0,
																			// 1600,
																			// 900
	public static Rectangle bpanRectangle = new Rectangle(panelPoint, bDimen); // 0,
																				// -30,
																				// 1600,
																				// 900

	// 이미지 시계와 글씨의 좌표를 설정한 좌표값이다.
	public static Rectangle imgClock = new Rectangle(15, 20, 179, 149);
	public static Rectangle clockMessage = new Rectangle(80, 53, 100, 100);

	// frame창을 중앙으로 자동으로 옮기기 위해서 사용한다.
	public static int locationX = (windowSize.width - width) / 2;
	public static int locationY = (windowSize.height - height) / 2;

	// Label Location
	public static Rectangle ampmLabel = new Rectangle(15, 20, 100, 30);
	public static Rectangle timeLabel = new Rectangle(0, 0, 100, 20);

	
	// ManageView 에 들어갈 것들
	public static Rectangle layeredPane = bRectangle;
	public static Rectangle backGround = bRectangle;
	public static Rectangle myStarPanel = bRectangle;
	public static Rectangle seat50 = new Rectangle(165, 109, 1368, 686);

	// singleTone : 클래스들이 객체를 하나씩만 가질수 있게 해준다. DB를 사용할때 singleton을 사용한것처럼 다른 클래스의 값들을 하나만 사용할수 있도록 도와준다.
	private static Setting instance = new Setting();

	public static Setting getInstance() {
		return instance;
	}

	private Setting() {
	}
}
