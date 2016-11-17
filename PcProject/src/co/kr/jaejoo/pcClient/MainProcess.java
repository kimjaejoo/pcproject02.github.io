package co.kr.jaejoo.pcClient;

import java.sql.Connection;

import co.kr.jaejoo.asset.Setting;
import co.kr.jaejoo.model.UserDao;
import co.kr.jaejoo.util.U;

public class MainProcess {

	// 로그인
	private static UserLoginClient loginClient;
	// 회원가입
	private static UserJoinClient joinClient;
	// main controller
	private static SystemMainClient mainClient;
	// UserUi
	private static UserMainClient userClient; 
	
	private static UserDao dao;
	
	private Connection conn;
	
	public MainProcess() {
		
		conn = U.getInstance().getConnection();
	}
	
	public static void main(String[] args) {
		MainProcess main = new MainProcess();
		main.loginClient = new UserLoginClient();
		main.loginClient.setMain(main);
		
		// 프레임이 dao를 주입하는 것을 한다.
		main.loginClient.setDao(dao);
	}

	// 되돌아가는 버튼을 활성화합니다.
	public void loginFrame( UserJoinClient userJoinClient ){
		System.out.println("로그인 화면으로 되돌아갑니다.");
		joinClient.dispose();
		main(null);
		
	}

	@SuppressWarnings("static-access")
	public void mainFrame() throws Exception {
		loginClient.dispose();
		System.out.println("로그인성공");
		this.mainClient = new SystemMainClient();
		this.mainClient.setRectangles(SystemMainClient.class, mainClient, Setting.class, Setting.getInstance());
	}

	public void joinFrame() {
		loginClient.dispose();
		System.out.println("회원가입");
		this.joinClient = new UserJoinClient();
	}
	
	public void userFrame(){
		loginClient.dispose();
		System.out.println("사용자 권한으로 UserUI에 접속합니다");
		this.userClient = new UserMainClient();
	}

	
	
	
		
		
}


