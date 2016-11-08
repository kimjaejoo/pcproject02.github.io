package co.kr.jaejoo.pcClient;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class UserLoginClient extends JFrame implements ActionListener {
	public static void main(String[] args) {
		new UserLoginClient();
	}

	private MainProcess main;

	// 사용자의 로그인 화면을 생성하기 위해서 사용하는 변수들 - 변수의 사용은 기본생성자 안쪽에서 new함으로 사용한다.
	JTextField loginId;
	JPasswordField loginPw;
	JButton loginBtn, joinBtn;
	JPanel loginPanel = new JPanel();

	Pattern patternEmail, patternId, patternTel;
	Matcher matcherEmail, matcherId, matcherTel;

	// 정규표현식 사용하기
	String emailregex = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
	String idregex = "^[a-zA-Z0-9]*$";
	String telregex = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$";

	public UserLoginClient() {
		// 사용장 로그인화면 만들기 - 사용자의 화면에 맟줘 적당한 크기로 중앙에 들어오게 한다.
		setTitle("사용자 로그인 화면 입니다.");
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 사용자가 x버튼을 눌렀을때 종료를 도와줍니다.
		setSize(1600, 900); // 창의 기본적인 크기를 설정합니다
		setLayout(null);
		loginPanel.setLayout(null);
		Dimension framesize = this.getSize();

		Dimension windowsize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (windowsize.width - framesize.width) / 2;
		int height = (windowsize.height - framesize.height) / 2;
		setLocation(width, height);
		// frame을 정 중앙에 오게하기위한 계산식

		// textfield 오 버튼의 생성
		loginId = new JTextField();
		loginPw = new JPasswordField();
		loginBtn = new JButton("LOGIN");
		joinBtn = new JButton("JOIN");
		loginId.setBounds(1100, 500, 200, 30);
		loginPw.setBounds(1100, 500 + 50, 200, 30);
		loginBtn.setBounds(1100, 500 + 50 + 50, 90, 30);
		joinBtn.setBounds(1100 + 110, 500 + 50 + 50, 90, 30);
		loginPanel.setBounds(width, height, 1600, 900);
		loginPanel.add(loginId);
		loginPanel.add(loginPw);
		loginPanel.add(loginBtn);
		loginPanel.add(joinBtn);
		add(loginPanel, "loginPanel");
		loginBtn.addActionListener(this);
		joinBtn.addActionListener(this);
		setVisible(true);

	}

	@Override // actionlistener의 메소드의 재정의
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		if (obj == loginBtn) {
			System.out.println("로그인 버튼눌림");
			// 제어문을 사용하여 아이디와 비번이 맞으면 로그인을 시도합니다.
			// 아이디는 정규표현식을 사용하여 작성합니다.
			String email = loginId.getText();
			String pw = loginPw.getText();
			System.out.println(email + " / " + pw);

			patternEmail = Pattern.compile(emailregex);
			matcherEmail = patternEmail.matcher(email);
			System.out.println(matcherEmail.matches());

			if (matcherEmail.matches() == true) {
				System.out.println("형식이 일치"); // 형식이 일치하면 아래의 제어문을 실행함
				if (email.equals("kjj0710@naver.com") && pw.equals("1234")) {
					System.out.println("로그인을 시도합니다.");
					System.out.println(main);
					main.showFrame();
				}
			} else {
				System.out.println("틀림");
			}
		} else if (obj == joinBtn) {
			System.out.println("회원가입 버튼을 누름");
		}
	}

	public void setMain(MainProcess main) {
		this.main = main;
	}

}