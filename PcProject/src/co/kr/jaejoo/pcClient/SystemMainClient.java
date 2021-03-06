package co.kr.jaejoo.pcClient;

import java.awt.Component;
import java.awt.Rectangle;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import co.ko.jaejoo.dao.Member;
import co.ko.jaejoo.dao.MemberDTO;
import co.kr.jaejoo.asset.Setting;
import co.kr.jaejoo.chat.server.ServerBackground;
import co.kr.jaejoo.panel.ClockMessage;
import co.kr.jaejoo.panel.ImgClock;
import co.kr.jaejoo.panel.MyStarPanel;
import co.kr.jaejoo.panel.PanImgload;
import co.kr.jaejoo.panel.PanSeat;

@SuppressWarnings({"serial","unused"})
public class SystemMainClient extends JFrame {

	public JLayeredPane layeredPane = new JLayeredPane();
	// JPanels
	private PanImgload backGround = new PanImgload("img/mainHud_back.png");
	private MyStarPanel myStarPanel = new MyStarPanel();
	private ImgClock imgClock = new ImgClock();
	private ClockMessage clockMessage = new ClockMessage();

	// 좌석을 위한 변수를 선언합니다.
	int posXpanSeat, posYpanSeat;
	PanSeat[] pan = new PanSeat[50];
	JPanel seat50 = new JPanel();

	private static int playerNo;
	int userNo;
	private Member member;
	private MemberDTO dto = new MemberDTO();
	private UserMainClient userMain;
	private UserLoginClient loginClient;
	private MainProcess mainProcess;

	public void setMainProcess(MainProcess mainProcess) {
		this.mainProcess = mainProcess;
	}
	
	public static void main(String[] args) throws Exception {
		
	}

	@SuppressWarnings("static-access")
	public SystemMainClient(int playerNo) throws Exception {
		this.playerNo = playerNo;
		// 넘겨받은 사용자번호
		// System.out.println("사용자 번호 : " + playerNo);

		// 사용자 번호를 다른 panel을 상속받는 클래스에 전송합니다.

		System.out.println(" userFrame에서 넘겨받은 사용자번호 " + playerNo);

		setLayout(null);
		setVisible(true);
		setTitle("ManageView");
		setSize(Setting.bDimen);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(Setting.locationX, Setting.locationY);
		

		// 사용자들의 정보를 담기위한 공간을 생성합니다. 사용자가 로그인하면 사용자의 번호가 list에 저장됩니다.
		ArrayList<Integer> list = new ArrayList<Integer>();

		/*
		 * list.add(playerNo); for(Integer i : list){
		 * System.out.println(list.size()); System.out.println("저장된 사용자번호 : " +
		 * i); }
		 */

		// 좌석을 넣기위한작업을 합니다.
		for (int seat = 0; seat < 50; seat++) {
			pan[seat] = new PanSeat(seat);
			if (seat % 10 == 0 && seat != 0) {
				posXpanSeat = 0;
				posYpanSeat += 140;
			}
			pan[seat].setBounds(posXpanSeat, posYpanSeat, 99, 99);
			posXpanSeat += 135;
		}
		new SeatThread().start();
		
		
		// Settig class 안에 선언한 변수의 이름과 같게 설정해야한다.
		add(setJLayered(backGround, myStarPanel, imgClock, clockMessage, seat50));
		add(layeredPane);
	}

	// Reflection을 이용한 리팩토링을 하기위한 메소드 생성

	// Setting inner Methods
	private JComponent setJLayered(Component... components) {
		int i = 0;
		for (Component component : components)
			layeredPane.add(component, new Integer(i++));
		return layeredPane;
	}
	
	
	// Reflection Practice 다른 클래스의 정보를 가지고 와서 사용한다. 변수의 이름을 같게하면 된다
	public void setRectangles(Class<?> clazz, Object instance, Class<?> targetClass, Object target) throws Exception {
		Object tempObject = null;
		for (Field field : clazz.getDeclaredFields()) {
			if ((tempObject = field.get(instance)) instanceof JComponent) {
				((JComponent) tempObject)
						.setBounds((Rectangle) targetClass.getDeclaredField(field.getName()).get(target));
				((JComponent) tempObject).setOpaque(false);
				((JComponent) tempObject).setLayout(null);
			}
			if (tempObject instanceof Runnable)
				new Thread((Runnable) tempObject).start();
		}
	}

	// 좌석을 나타낼때 쓰레드로 랜덤으로 띄어주는 역할을하는 innerClass입니다.
	class SeatThread extends Thread {
		@Override
		public void run() {
			Set<Integer> randomNumbers = new LinkedHashSet<Integer>();
			for (; randomNumbers.size() < 50;) {
				int x = (int) (Math.random() * 50);
				randomNumbers.add(x);
			}
			int tmp = 0;
			try {
				for (Integer s : randomNumbers) {
					tmp++;
					if (tmp > 30) {
						Thread.sleep(5 * s);
					}
					if (tmp == 50) {
						Thread.sleep(1000);
						System.out.println("50번째 좌석을 불러왔습니다.");
					}
					seat50.add(pan[s]);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	SystemMainClient mainClient;

	public void setSystemMainClient(SystemMainClient mainClient) {
		this.mainClient = mainClient;
	}

	// 자석을 선택하면 사용자와의 chat창을 불러오기위한 것을 설정한다.

}
