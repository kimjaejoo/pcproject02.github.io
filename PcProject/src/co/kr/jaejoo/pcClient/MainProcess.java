package co.kr.jaejoo.pcClient;

public class MainProcess {
	
	// 로그인
	UserLoginClient loginClient;
	// 회원가입
	UserJoinClient joinClient;
	// main controller 
	UserMainClient mainClient;
	
	public static void main(String[] args) {
		MainProcess main = new MainProcess();
		main.loginClient = new UserLoginClient();
		main.joinClient = new UserJoinClient();
		main.loginClient.setMain(main);
		
	}
	
	public void showFrame(){
		loginClient.dispose();
		this.mainClient = new UserMainClient();
		this.joinClient = new UserJoinClient();
	}
}