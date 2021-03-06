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
	private static int userFramewidth = 400, userframeHeight = 700;

	private static Point zeroPoint = new Point(0, 0);
	// systemMainFrame의 배경을 넣기위한 위치를 설정합니다.
	private static Point panelPoint = new Point(0, -30);

	// 현재의 윈도우 사이즈를 가지고 옵니다
	private static Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();

	// 변수앞의 b는 basicSetting을 의미합니다.
	public static Font bFont = new Font("고딕", Font.BOLD, 12);
	public static Color bColor = new Color(36, 205, 198);

	// frame의 크기를 설정합니다.
	public static Point bPoint = new Point(width, height); // 1600, 900
	// 사용자의 화면크기입니다.
	public static Point cpoint = new Point(userFramewidth, userframeHeight); // 400,
																				// 700

	public static Dimension bDimen = new Dimension(width, height);
	public static Dimension cDimen = new Dimension(userFramewidth, userframeHeight);

	public static Rectangle bRectangle = new Rectangle(zeroPoint, bDimen); // 0,
																			// 0,
																			// 1600,
																			// 900
	public static Rectangle bpanRectangle = new Rectangle(panelPoint, bDimen); // 0,
																				// -30,
																				// 1600,
																				// 900
	public static Rectangle userRectangle = new Rectangle(zeroPoint, cDimen);// 0,
																				// 0,
																				// 400,
																				// 700

	// 이미지 시계와 글씨의 좌표를 설정한 좌표값이다.
	public static Rectangle imgClock = new Rectangle(15, 20, 179, 149);
	public static Rectangle clockMessage = new Rectangle(80, 53, 100, 100);

	// frame창을 중앙으로 자동으로 옮기기 위해서 사용한다.
	public static int locationX = (windowSize.width - width) / 2;
	public static int locationY = (windowSize.height - height) / 2;

	// userFrame창의 위치를 설정하기 위한 계산
	public static int userLocationX = (windowSize.width - userFramewidth) - 7;
	public static int userLocationY = (windowSize.height - userframeHeight) / 8;

	// Label Location
	public static Rectangle ampmLabel = new Rectangle(15, 20, 100, 30);
	public static Rectangle timeLabel = new Rectangle(0, 0, 100, 20);

	// userFrameLabel Location - 변수들의 순서대로 프레임에 노출됩니다.
	public static Rectangle playerSeatNo = new Rectangle(10, 20 + 20 + 20, 200, 20);
	public static Rectangle playerName = new Rectangle(10, 10, 200, 20);
	public static Rectangle playerCoin = new Rectangle(10, 20 + 20, 200, 20);
	public static Rectangle playTime = new Rectangle(10, 20 + 20 + 20 + 20, 200, 20);
	public static Rectangle chatOn = new Rectangle(300, 20, 90, 20);
	public static Rectangle userMain = new Rectangle(10, 20, userFramewidth, userframeHeight);

	// userLoginFrame Location - 변수들은 순서대로 프레임에 노출됩니다.
	public static Rectangle loginId = new Rectangle(1100, 500, 200, 30);
	public static Rectangle loginPw = new Rectangle(1100, 500 + 50, 200, 30);
	public static Rectangle loginBtn = new Rectangle(1100, 500 + 50 + 50, 90, 30);
	public static Rectangle joinBtn = new Rectangle(1100 + 110, 500 + 50 + 50, 90, 30);
	public static Rectangle googleBtn = new Rectangle(1100, 500 + 50 + 50 + 50, 200, 30);
	public static Rectangle loginClient = new Rectangle(0,0, 1600, 900);
	
	// systemMainclient안에 들어갈 값들을 정의합니다.
	public static Rectangle layeredPane = bRectangle;
	public static Rectangle backGround = bRectangle;
	public static Rectangle myStarPanel = bRectangle;
	public static Rectangle seat50 = new Rectangle(165, 109, 1368, 686);

	// userMainClient안에 들어갈 값들을 정의합니다.
	public static Rectangle userLayeredPane = userRectangle;
	
	// userLoginClient안에 들어갈 값들을 정의합니다.

	// singleTone : 클래스들이 객체를 하나씩만 가질수 있게 해준다. DB를 사용할때 singleton을 사용한것처럼 다른
	// 클래스의 값들을 하나만 사용할수 있도록 도와준다.
	private static Setting instance = new Setting();

	public static Setting getInstance() {
		return instance;
	}

	private Setting() {}
}
