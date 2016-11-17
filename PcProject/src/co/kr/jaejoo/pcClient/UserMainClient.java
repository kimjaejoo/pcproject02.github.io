package co.kr.jaejoo.pcClient;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

import co.ko.jaejoo.dao.Member;
import co.ko.jaejoo.dao.MemberDTO;
import co.kr.jaejoo.model.UserDao;

public class UserMainClient extends JFrame {

	// 사용자 권한이 0인 유저에게 띄어지는 창을 설정합니다.
	public UserLoginClient loginClient = new UserLoginClient();
	Member member = new Member();
	
	//
	private UserDao UserDao;
	
	JLabel seatNo, name , time , coin;
	
	// 로그인시 사용자의 값을 받아와야합니다.
	
	public UserMainClient(){
		System.out.println("사용자 ui가 호출됩니다.");
		setTitle("사용자UI");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setSize(400, 700);
		
		
		// 위의 변수들을 초기화 합니다.
		seatNo = new JLabel();
		name = new JLabel();
		time = new JLabel();
		coin = new JLabel();
		
		// frame 위치를 설정
		Dimension framesize = this.getSize(); //jframe size를 가지고 옵니다
		Dimension windowsize = Toolkit.getDefaultToolkit().getScreenSize(); // 현재pc의 화면크기를 가지고옵니다.
		
		int width = (windowsize.width - framesize.width) - 30;
		int height = (windowsize.height - framesize.height) / 9;
		
		setLocation(width, height);
	
		// 사용자에게 보여지는 창을 설정합니다. 
		
		
	}

	public void setUserDao(UserDao userDao) {
		UserDao = userDao;
	}
	
	
	
}
