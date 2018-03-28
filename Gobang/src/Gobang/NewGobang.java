package Gobang;

import java.awt.*;
import java.awt.TextField;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.*;
import java.io.*;


public class NewGobang 
{
	private JFrame window = new JFrame("Gobang"); //����
	private Piece[][] chess = new Piece[20][20]; //��������
	Board board= new Board();//��������
	private BufferedImage chessBoard;//����ͼ��
	private BufferedImage black;//����ͼ��
	private BufferedImage white;//����ͼ��
	private final int WIDTH = 800;//���̿�
	private final int HEIGHT =  838;//���̸�
	private final int deviation = 30;//ƫ����
	private final int widRate = (WIDTH - 48) / 15 ;//�����
	private final int heightRate = (HEIGHT - 74) / 15;//�ݱ���
	private TextField note = new TextField("��Ϸ��ʼ");
	private int inX = 0;
	private int inY = 0;
	int curcle = 0;
	int  p ;
	
	public void info() throws IOException
	{
		//���ô��ڲ���
		window.setLayout(new BorderLayout());
		
		//������������
		board.setPreferredSize(new Dimension(WIDTH , HEIGHT));
		
		//����ͼƬ��ַ
		chessBoard = ImageIO.read(new File("D:\\eclipse\\java\\Gobang\\src\\Gobang\\timg.jpg"));
		black = ImageIO.read(new File("D:\\eclipse\\java\\Gobang\\src\\Gobang\\black.jpg"));
		white =ImageIO.read(new File("D:\\eclipse\\java\\Gobang\\src\\Gobang\\white.jpg"));
		
		//��ʼ���������
		for(int i = 0 ; i < 15 ; i++)
		{
			for(int j = 0 ; j < 15 ; j++ )
			{
				chess[i][j] = new Piece();
				chess[i][j].setX(i);
				chess[i][j].setY(j);
	//	chess[i][j].free = 1;
			}
		}
		
		//���ùرհ�ť
		window.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		//������ʾ��
		note.setBackground(new Color(200,200,200));
		note.setForeground(Color.black);
		note.setFont(new Font("����" , Font.BOLD, 20));
		window.add(board);
		window.add(note , BorderLayout.SOUTH);
		window.setBounds(0 , 0 , 800, 860);
//		window.pack();
		window.setVisible(true);
	}
	
	public void play(int who)
	{
		if(who == 1)
			note.setText("��׷�����");
		else if (who == -1)
			note.setText("��ڷ�����");
		board.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{	
					inX = (e.getX() - 30) / widRate;
					inY = (e.getY() - 30) / heightRate;
					curcle = 1;

					if (inX <= 14 && inY <=14 && inX >= 0 && inY >= 0 && chess[14 - inX][inY].free != 0 && curcle == 1 )
					{	
						if(who > 0)
							chess[14 - inX][inY].setFree(1);
						else 
							chess[14 - inX][inY].setFree(-1);
						board.repaint();

					}
					else if (inX <= 14 && inY <=14 && inX >= 0 && inY >= 0 &&chess[14 - inX][inY].free == 0 && curcle == 1)
					{
						note.setText("�˴���������");
					}
				}
			});

	}
	
	
	public void play()
	{
		board.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{	
				    note.setText("��׷�����");
					inY = (e.getX() - 30) / widRate;
					inX = (e.getY() - 30) / heightRate;
					if (inX <= 14 && inY <=14 && inX >= 0 && inY >= 0 && chess[inX][inY].free == 0 )
					{	
						chess[inX][inY].setFree(1);
						while	(1 == 1)
						{
								inX = new Random().nextInt(14);
								inY = new Random().nextInt(14);
								if(chess[inX][inY].free == 0 )
									break;
						}
						chess[inX][inY].setFree(-1);
						board.repaint();
					}
					else if (inX <= 14 && inY <=14 && inX >= 0 && inY >= 0 && chess[inX][inY].free != 0)
					{
						note.setText("�˴���������");
					}
					for(int k = 0 ; k < 2 ; k++)
					{
						int whichChess;
						if(k == 0 )
							whichChess = 1;
						else
							whichChess = -1;
						for (int i = 0; i < 15;i++)
						{
							for(int j = 0;j < 15;j++)
							{
								if(i < 10 && chess[j][i].free == whichChess && chess[j][i+1].free == whichChess &&
										chess[j][i+2].free == whichChess && chess[j][i+3].free == whichChess && chess[j][i+4].free == whichChess) 
									{
										if(whichChess == 1)
											note.setText("��ϲ�׷�ʤ��") ;		       
										else if (whichChess == -1)
											note.setText("��ϲ�ڷ�ʤ��") ;
									}
								if(j < 10 && chess[j][i].free == whichChess && chess[j+1][i].free == whichChess &&
					        		chess[j+2][i].free == whichChess && chess[j+3][i].free == whichChess && chess[j+4][i].free == whichChess) 
									{
										if(whichChess == 1)
											note.setText("��ϲ�׷�ʤ��") ;		       
										else if (whichChess == -1)
											note.setText("��ϲ�ڷ�ʤ��") ;
									}
								if(i < 10 && j < 10 && chess[j][i].free == whichChess && chess[j+1][i+1].free == whichChess &&
					        		chess[j+2][i+2].free == whichChess &&chess[j+3][i+3].free == whichChess && chess[j+4][i+4].free == whichChess) 
									{
										if(whichChess == 1)
											note.setText("��ϲ�׷�ʤ��") ;		       
										else if (whichChess == -1)
											note.setText("��ϲ�ڷ�ʤ��") ;
									}
								if(i < 10 && j > 4 && chess[j][i].free == whichChess && chess[j-1][i+1].free == whichChess &&
					        		chess[j-2][i+2].free == whichChess &&chess[j-3][i+3].free == whichChess && chess[j-4][i+4].free == whichChess) 
									{
										if(whichChess == 1)
											note.setText("��ϲ�׷�ʤ��") ;		       
										else if (whichChess == -1)
											note.setText("��ϲ�ڷ�ʤ��") ;
									}
							}
						}
					}
					
				}
					
			});

	}
	
	 private int win(int who)
		{
			int whichChess;
			if(who == 1 )
				whichChess = 1;
			else 
			    whichChess = -1;
			for (int i = 0; i < 15;i++)
			{
				for(int j = 0;j < 15;j++)
				{
			        if(i < 10 && chess[j][i].free == whichChess && chess[j][i+1].free == whichChess &&
			        		chess[j][i+2].free == whichChess && chess[j][i+3].free == whichChess && chess[j][i+4].free == whichChess) 
			        		return  whichChess ;		       
			        if(j < 10 && chess[j][i].free == whichChess && chess[j+1][i].free == whichChess &&
			        		chess[j+2][i].free == whichChess && chess[j+3][i].free == whichChess && chess[j+4][i].free == whichChess) 
				        	return  whichChess ;
			        if(i < 10 && j < 10 && chess[j][i].free == whichChess && chess[j+1][i+1].free == whichChess &&
			        		chess[j+2][i+2].free == whichChess &&chess[j+3][i+3].free == whichChess && chess[j+4][i+4].free == whichChess) 
					        return  whichChess ;
			        if(i < 10 && j > 4 && chess[j][i].free == whichChess && chess[j-1][i+1].free == whichChess &&
			        		chess[j-2][i+2].free == whichChess &&chess[j-3][i+3].free == whichChess && chess[j-4][i+4].free == whichChess) 
					        return  whichChess ;
			    }
			}
			return 0;
		}
	 
	 public void whiteWin()
	 {
		 note.setText("��ϲ�׷�ʤ��");
	 }
	 
	 public void blackWin()
	 {
		 note.setText("��ϲ�ڷ�ʤ��");
	 }
	
	public static void main(String[] args) throws IOException
	{
		NewGobang gb = new NewGobang();
		gb.info();
		gb.play();
	/*	int winner = 0;
		int who = 0;
		int x = 0; 
		while (winner == 0 )
		{
	//		System.out.println("666");
			who =  (int) Math.cos(x * Math.PI );	
			gb.play(who);
			int wings;
			try
			{
				wings = gb.win(who);
			}
			catch(Exception e)
			{
				wings = 0;
			}
			
			if(wings == 1)
			{
				gb.whiteWin();
				return;
			}
			if(wings == -1)
			{
				gb.blackWin();
				return;
			}
			x ++;
		}*/
	}
	
	
	public static BufferedImage min(BufferedImage im)
	{
		int wid = im.getWidth();
		int height = im.getHeight();
		int rate = 5;
		wid = wid / rate;
		height = height / rate;
		return  new BufferedImage(wid , height , im.SCALE_SMOOTH);
	}
	
	class Board extends JPanel
	{
		public void paint(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(3.0f));
			g2.drawImage(chessBoard, 0, 0, null);
			g2.setColor(Color.black);
			g2.fillOval(7 * widRate + deviation +7, 7 *  heightRate + deviation +7, 16, 16);
			g2.fillOval(3 * widRate + deviation +9, 3 *  heightRate + deviation +9, 12, 12);
			g2.fillOval(3 * widRate + deviation +9, 11 *  heightRate + deviation +9, 12, 12);
			g2.fillOval(11 * widRate + deviation +9, 3 *  heightRate + deviation +9, 12, 12);
			g2.fillOval(11 * widRate + deviation +9, 11 *  heightRate + deviation +9, 12, 12);
			for(int i = 0 ; i <= 14 ;i++)
			{
				g2.drawLine(deviation + i * widRate +15 , deviation +15, i * widRate + deviation +15, 14 *  heightRate + deviation +15);
				g2.drawLine(deviation +15, deviation + i * heightRate +15, 14 *  widRate + deviation +15, i * heightRate + deviation +15);
			}
			g2.setStroke(new BasicStroke(1.0f));
			g2.setFont(new Font("����" , Font.BOLD, 20));
			g2.drawString("1", deviation , 14 *  heightRate + deviation + 35);
			for(int i = 0 ; i <= 14 ;i++)
			{
				if(i > 0)
				g2.drawString(Integer.toString(i+1),  i * widRate + deviation +5, 14 *  heightRate + deviation + 35);
				if(i < 14)
				g2.drawString(Integer.toString(15-i), deviation - 10, i *  heightRate + deviation + 35);
				
			}
			for(int i = 0 ; i < 15 ; i++)
			{
				for(int j = 0 ; j < 15 ; j++ )
				{
					if(chess[i][j].free == -1)
					{
					// 	g2.drawImage(min(black), j * widRate + deviation , i * heightRate + deviation  ,null);
						g2.setColor(Color.BLACK);
						g2.fillOval(j * widRate + deviation , i * heightRate + deviation, 30, 30);
					}
					else if(chess[i][j].free == 1)
					{
						g2.setColor(Color.WHITE);
						g2.fillOval(j * widRate + deviation , i * heightRate + deviation, 30, 30);
					}
				 	else 
						continue;
				}
				
			}
		}
	}

}
