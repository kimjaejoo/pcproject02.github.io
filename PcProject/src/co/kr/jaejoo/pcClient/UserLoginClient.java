package co.kr.jaejoo.pcClient;

import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import co.ko.jaejoo.dao.Member;
import co.ko.jaejoo.dao.MemberDTO;
import co.kr.jaejoo.asset.Setting;
import co.kr.jaejoo.model.ReqSignUp;
import co.kr.jaejoo.panel.PanImgload;

@SuppressWarnings({ "serial", "static-access", "deprecation" })
public class UserLoginClient extends JFrame implements ActionListener {

	private MainProcess main;

	JOptionPane pane = new JOptionPane();
	private MemberDTO dto;
	// 사용자의 로그인 화면을 생성하기 위해서 사용하는 변수들 - 변수의 사용은 기본생성자 안쪽에서 new함으로 사용한다.
	private JTextField loginId;
	private JPasswordField loginPw;
	private JButton loginBtn, joinBtn, googleBtn;
	public JLayeredPane layeredPane = new JLayeredPane();
	private Pattern patternEmail;
	private Matcher matcherEmail;

	private PanImgload backGround = new PanImgload("img/mainHud_back.png");

	private Member mem = new Member();

	// 정규표현식 사용하기
	String emailregex = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
	String idregex = "^[a-zA-Z0-9]*$";
	String telregex = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$";

	ReqSignUp req = new ReqSignUp();

	// 로그인을 하면 사용자번호를 서버에 보내고 서버는 다른클라이언트에 값을 보냅니다

	public UserLoginClient() {

		// server가 열리면 loginClient가 작동합니다. 현재 접속은 성공했습니다.
		// clientBackground.setGui(this);
		// clientBackground.setNickname(dto.getName());
		// clientBackground.connet();

		// 사용장 로그인화면 만들기 - 사용자의 화면에 맟줘 적당한 크기로 중앙에 들어오게 한다.
		setLayout(null);
		setTitle("사용자 로그인 화면 입니다.");

		setSize(Setting.bDimen); // 창의 기본적인 크기를 설정합니다
	
		setLocation(Setting.locationX, Setting.locationY);
		// frame을 정 중앙에 오게하기위한 계산식

		// textfield 오 버튼의 생성
		loginId = new JTextField("test@test.com");
		loginPw = new JPasswordField("1111");
		loginBtn = new JButton("LOGIN");
		joinBtn = new JButton("JOIN");
		googleBtn = new JButton("GOOGLE SIGNUP");

		// 위치들을 설정합니다.
		loginId.setBounds(1100, 500, 200, 30);
		loginPw.setBounds(1100, 500 + 50, 200, 30);
		loginBtn.setBounds(1100, 500 + 50 + 50, 90, 30);
		joinBtn.setBounds(1100 + 110, 500 + 50 + 50, 90, 30);
		googleBtn.setBounds(1100, 500 + 50 + 50 + 50, 200, 30);
		layeredPane.setBounds(Setting.bpanRectangle);

		layeredPane.add(loginId);
		layeredPane.add(loginPw);
		layeredPane.add(loginBtn);
		layeredPane.add(joinBtn);
		layeredPane.add(googleBtn);

		add(setJLayered(backGround));
		add(layeredPane);
		loginBtn.addActionListener(this);
		joinBtn.addActionListener(this);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // 사용자가 x버튼을 눌렀을때 종료를 도와줍니다.

	}

	private JComponent setJLayered(Component... components) {
		int i = 0;
		for (Component component : components)
			layeredPane.add(component, new Integer(i++));
		return layeredPane;
	}

	@Override // actionlistener의 메소드의 재정의
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if (obj == loginBtn) {
			System.out.println("로그인 버튼눌림");
			// 제어문을 사용하여 아이디와 비번이 맞으면 로그인을 시도합니다.
			String email = loginId.getText();
			String pw = loginPw.getText();

			dto = mem.loginData(email, pw);
			if (dto != null) {
				
				int playerNo = dto.getMembernum();
				dto.setMembernum(dto.getMembernum());
				System.out.println(playerNo);
				// 아이디는 정규표현식을 사용하여 작성합니다.
				patternEmail = Pattern.compile(emailregex);
				matcherEmail = patternEmail.matcher(email);
				// System.out.println(matcherEmail.matches());
				Boolean match = matcherEmail.matches();
				System.out.println();

				// 사용자의 권한을 설정합니다 system = 1 , user = 0
				// 권한들 두개로 나누어 1이면 시스템UI으로 접속하고 0이면 사용자UI로 접속됩니다.
				// test 시스템계정은 kjj0710@naver.com 사용자계정은 test@test.com 입니다.

				if (match.booleanValue() == true) {
					System.out.println("형식이 일치"); // 형식이 일치하면 아래의 제어문을 실행함
					// admin의 값을 가지고오는 로그인형식을 추가하자!! 아이디와 비번을 입력했을 때 조회되는
					// admin값을
					// 이용하여 어느창을 띄울지 정한다.

					if (email.equals(dto.getEmail()) && pw.equals(dto.getPassword())) {
						System.out.println("아이디와 비밀번호가 일치하여 실행합니다.");

						if (dto.getAdmin() == 1) {
							System.out.println(dto.getAdmin());
							System.out.println(dto.getEmail() + " / " + dto.getPassword());
							System.out.println("관리자 계정으로 로그인을 시도합니다.");

							// 사용자가 로그인을 시도하면 다른 클래스에 적정한 값을 보내야합니다.
							// 다른클래스로 보내는 값은 사용자의 번호입니다 값을 넘기기 위해서는 이 클래스안에
							// setter,
							// getter가 필요합니다.

							try {
								main.mainFrame(playerNo);
							} catch (Exception e1) {
								e1.printStackTrace();
							}

						} else if (dto.getAdmin() == 0) {
							// System.out.println(dto.getEmail() + " / " +
							// dto.getPassword());

							// System.out.println(dto.getAdmin());
							System.out.println("사용자 계정으로 로그인을 시도합니다.");
							System.out.println("넘겨주어야할 사용자의 번호 : " + dto.getMembernum());

							try {
								main.userFrame(playerNo);
							} catch (Exception e1) {
								e1.printStackTrace();
							}

						}
						// 사용자의 정보가 일치하지 않거나 없으면 경고창을 대신 띄운다.
					}  else {
						System.out.println("실행");
					}
				} else if (match.booleanValue() == false || dto.getEmail().equals(null)
						|| dto.getPassword().equals(null)) {
					System.out.println("틀림");
					pane.showMessageDialog(this, "이메일 형식에 맞지않습니다");
				}
			} else if (obj == joinBtn) {
				System.out.println("회원가입 버튼을 누름");
				main.joinFrame();
			} else if (obj == googleBtn) {
				System.out.println("구글 아이디로 가입합니다.");
			} else{
				System.out.println("실행");
				pane.showMessageDialog(this, "아아디 또는 비밀번호가 일치하지 않습니다");
			}
		
		}
	}
	
	public void setMain(MainProcess main) {
		this.main = main;
	}

	// 로그인을 시도할때 성공을 하면 server가 로그인한 사용자의 번호를 systemMain과 userMain으로 보냅니다.

}
