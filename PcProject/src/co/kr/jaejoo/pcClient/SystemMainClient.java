package co.kr.jaejoo.pcClient;

import java.awt.Component;
import java.awt.Rectangle;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import co.kr.jaejoo.asset.Setting;
import co.kr.jaejoo.panel.ClockMessage;
import co.kr.jaejoo.panel.ImgClock;
import co.kr.jaejoo.panel.MyStarPanel;
import co.kr.jaejoo.panel.PanImgload;

@SuppressWarnings("serial")
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

	public SystemMainClient() throws Exception {
		// Configure this Frame
		setLayout(null);
		setVisible(true);
		setTitle("ManageView");
		setSize(Setting.bDimen);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocation(Setting.locationX, Setting.locationY);

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
		
		add(setJLayered(backGround, myStarPanel, imgClock, clockMessage , seat50));
		add(layeredPane);
	}

	public static void main(String[] args) throws Exception {
		SystemMainClient manageView = new SystemMainClient();
		manageView.setRectangles(SystemMainClient.class, manageView, Setting.class, Setting.getInstance());

	}

	// Reflection을 이용한 리팩토링을 하기위한 메소드 생성

	// Setting inner Methods
	private JComponent setJLayered(Component... components) {
		int i = 0;
		for (Component component : components)
			layeredPane.add(component, new Integer(i++));
		return layeredPane;
	}

	// Reflection Practice
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

}
