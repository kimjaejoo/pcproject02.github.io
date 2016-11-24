package co.kr.jaejoo.pcClient;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import co.ko.jaejoo.dao.Member;
import co.ko.jaejoo.dao.MemberDTO;
import co.kr.jaejoo.asset.Setting;
import co.kr.jaejoo.chat.client.ClientBackground;

@SuppressWarnings("serial")
public class UserMainClient extends JFrame implements ActionListener {
	// 사용자가 로그인을하고 userFrame을 띄우면 서버에 접속합니다. client가 되어지는 부분이다.
	
	private Member member = new Member();
	private MemberDTO dto;

	int playerNo;

	public JLayeredPane layeredPane = new JLayeredPane();

	// 사용자의 정보를 나타내는 곳을 정합니다.
	private JLabel playerSeatNo, playerName, playTime, playerCoin;
	private JButton chatOn;

	// 로그인시 사용자의 값을 받아와야합니다.
	public int SystemMainClient (int playerNo){
		System.out.println("사용자 번호를 보냅니다");
		return playerNo;
	}

	public UserMainClient(int playerNo) throws Exception {
		//super(playerNo);
		this.playerNo = playerNo;
		
		
		//background.setGui(this);
		//background.setNickname();
		//background.connet();
		
		System.out.println("userFrame으로 넘겨받은 사용자번호  " + playerNo);
		dto = member.selectOne(playerNo);

		System.out.println("사용자 ui가 호출됩니다.");
		setTitle("사용자UI");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setSize(Setting.cDimen);
		setLocation(Setting.userLocationX, Setting.userLocationY);

		// 사용자의 정보를 가지고 오는 곳입니다

		// 위의 변수들을 초기화 합니다.
		playerSeatNo = new JLabel("자리번호 : ");
		playerName = new JLabel("사용자 이름 : " + dto.getName());
		playerCoin = new JLabel("사용금액 : ");
		playTime = new JLabel("사용시간 : ");
		chatOn = new JButton("1:1 대화"); // chatting을 띄우는 버튼입니다.

		chatOn.addActionListener(this);
		add(setJLayered(playerSeatNo, playerName, playTime, playerCoin, chatOn));
		add(layeredPane);
		// 사용자에게 보여지는 창을 설정합니다.
	}

	private JComponent setJLayered(Component... components) {
		int i = 0;
		for (Component component : components)
			layeredPane.add(component, new Integer(i++));
		return layeredPane;
	}

	// Setting Class의 변수들을 사용하기 위하여 작성합니다.
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

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == chatOn) {
			System.out.println("chatting을 시작합니다."); // 사용자의 닉네임은 자리번호로 대체합니다.
			// 버튼을 누르면 채팅창을 띄웁니다.
		}
	}
}
