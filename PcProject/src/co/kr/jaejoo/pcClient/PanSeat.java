package co.kr.jaejoo.pcClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PanSeat extends JPanel {

	private BufferedImage image = null;
	private int numSeat;
	JLabel[] label = new JLabel[4];

	public PanSeat(int numSeat) {
		this.numSeat = numSeat;
		image("gameOff");
		setLayout(null);

		JPanel panImg = new InnerPanel();
		panImg.setBounds(0, 0, 99, 99);
		panImg.setOpaque(false);
		
		// 상태정보패널
		JPanel panContent = new JPanel();
		panContent.setLayout(null);
		panContent.setBounds(0, 0, 99, 99);
		int posLabel = 15;
		for(int i = 0 ; i < 4 ; i++){
			if(i == 0){
				label[i] = new JLabel((numSeat + 1) + ".빈자리");
			}else{
				label[i] = new JLabel("");
			}
			
			label[i].setBounds(20, posLabel, 80, 15);
			posLabel += 16;
			label[i].setForeground(new Color(36, 205, 198));
			label[i].setFont(new Font("고딕", 1, 12));
			panContent.add(label[i]);
		}
		
		panContent.setOpaque(false);
		
		JLayeredPane panLayered = new JLayeredPane();
        panLayered.setBounds(0, 0, 1600, 900);
        panLayered.setLayout(null);
        panLayered.setOpaque(false);
        panLayered.add(panImg, new Integer(0), 0);
        panLayered.add(panContent, new Integer(1), 0);
        add(panLayered);

		add(panImg);
		setVisible(true);
		setOpaque(false);
		setFocusable(true);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("좌석");
		frame.add(new PanSeat(1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(99, 144);
		frame.setVisible(true);
	}

	class InnerPanel extends JPanel {
		private static final long serialVersionUID = 1547128190348749556L;

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(image, 0, 0, null);
		}
	}

	private void image(String fileName) {
		try {
			image = ImageIO.read(new File("img/" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("이미지 불러오기 실패");
			System.exit(0);
		}
		repaint();
	}

}
