package Gobang;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.TextField;
import java.util.Scanner;
import java.util.regex.*;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class checkboard 
{    
	JFrame f =  new JFrame("������");//������
	Chess_Board gobang = new Chess_Board();//��������
	private final int BOARD_WIDTH = 480//���̿�� 
	private final int BOARD_HEIGHT = 480//���̸߶�
	private final int rate = BOARD_WDTH / 15 ;
	private TextField note = new TextField("�����ʼ����ʼ��Ϸ");
	BufferImage table ;//����ͼ��
	BufferImage black ;//����
	BufferImage white ;//����
	private final int moveX = 5 ;//������ƫ��
	private final int moveY = 5 ;//������ƫ��
	
	
	public void info()
	{
		f.setBounds((2000 - BOARD_WIDTH - 30) / 2, (1000 - BOARD_HEIGHT) / 2, 480, 510);
		f.setLayout(new BoxLayout(f , BoxLayout.Y_AXIS));
		gobang.setPreferredSize(new Dimesion(2000 - BOARD_WIDTH - 30) / 2, (1000 - BOARD_HEIGHT) / 2));
		note.setBounds(new Rectangle(480, 30));
		table = ImageIO.read(new File("C:\\Users\\��������ѩ����\\Desktop\\timg.jpg"));
		black = ImageIO.read(new File("C:\\Users\\��������ѩ����\\Desktop\\black.jpg"));
		white =ImageIO.read(new File("C:\\Users\\��������ѩ����\\Desktop\\white.jpg"));
		
		
		
	}
	
	private Piece[][] board = new Piece[17][17];//��ʼ������
	public void starts()
	{

	    for(int i = 1 ; i <= 15 ; i++ )
    	{
	        for (int j = 1 ; j <= 15 ; j++ )
		    {
	        	board[i][j] = new Piece();//ÿһ������Ԫ�س�ʼ����
	        	board[i][j].setX(i);
	        	board[i][j].setY(j);
	        }
	    }	
	}
	//��ӡ����
	private void printCheckboard()
	{	
		for(int i = 1 ; i <= 15 ; i++)
		{
			System.out.print(16-i);  
			if(15 - i < 10)
		    {
		    	System.out.print(" ");
		    }
			for (int j = 1 ; j <= 15; j++ )
			{
				if(board[i][j].free == -1)
					System.out.print("�� ");
				else if (board[i][j].free == 1)
					System.out.print("�� ");
				else
					System.out.print("ʮ ");
			}
			System.out.println("");
		}	
		System.out.println("  1  2 3  4 5  6 7  8 9 10 11 12 13 14 15");
	}
	
	//����
	private void playChess(int who) 
	{
		if(who > 0)
			System.out.println("��׷�����,������������   X��Y  �Իس�����");	
		else
			System.out.println("��ڷ�����,������������   X��Y  �Իس�����");				
		int inX = 0;
		int inY = 0;
		String news = new String ("666");
	    String input = "666" ;
		Scanner playerX ;
		whit:
		while(1 == 1)
			{
				playerX = new Scanner(System.in);
	         	playerX.useDelimiter("\n");    
	         	input = playerX.next();
	         	Pattern pattern = Pattern.compile("\\d\\d?,\\d\\d?");
	         	Matcher first = pattern.matcher(input);
	         	while(first.find())
	         	{
	                  Matcher matcher = Pattern.compile("\\d\\d?").matcher(input);
	                  int i = 0;
	                  while (matcher.find())
	                  {   
	                		  if(i == 0)
	                		  {
	                			  inY = Integer.parseInt(matcher.group());
	                		  }
	                		  else
	                		  {
	                			  inX = Integer.parseInt(matcher.group());
	                		  }
	                		  i ++ ;
	                  }
	             }
	             if(inX < 16 && inX > 0 && inY < 16 && inY > 0)
	             {
			    	 if(board[16 - inX][inY].free != 0)
			    	 {
			    	     System.out.println("�˴������� \n ������������������������");
			    	 }
			    	 else
			    	 {
			             break whit;
			    	 }
			     }
	             else
	             {
	                 System.out.println("������������������������");
	             }
	       }
		 if(who > 0)
		       board[16 - inX][inY].setFree(1);
		 else 
			   board[16 - inX][inY].setFree(-1);
		 this.printCheckboard();
		 return ;   
    } 
	
    private int win(int who)
	{
		int whichChess;
		if(who == 1 )
			whichChess = 1;
		else 
		    whichChess = -1;
		for (int i = 1; i < 16;i++)
		{
			for(int j = 1;j < 16;j++)
			{
		        if(i < 8 && board[j][i].free == whichChess && board[j][i+1].free == whichChess &&
		           board[j][i+2].free == whichChess && board[j][i+3].free == whichChess && board[j][i+4].free == whichChess) 
		        		return  whichChess ;		       
		        if(j < 8 && board[j][i].free == whichChess && board[j+1][i].free == whichChess &&
				   board[j+2][i].free == whichChess && board[j+3][i].free == whichChess && board[j+4][i].free == whichChess) 
			        	return  whichChess ;
		        if(i < 8 && j < 8 && board[j][i].free == whichChess && board[j+1][i+1].free == whichChess &&
				   board[j+2][i+2].free == whichChess && board[j+3][i+3].free == whichChess && board[j+4][i+4].free == whichChess) 
				        return  whichChess ;
		        if(i < 8 && j > 4 && board[j][i].free == whichChess && board[j-1][i+1].free == whichChess &&
				   board[j-2][i+2].free == whichChess && board[j-3][i+3].free == whichChess && board[j-4][i+4].free == whichChess) 
				        return  whichChess ;
		    }
		}
		return 0;
	}
 
	public static void main(String[] args) 
	{
		int winner = 0;
		int who = 0;
		checkboard match = new checkboard();
		match.starts();
		match.printCheckboard();
		while(winner == 0)
		{
			for(int i = 0; i >= 0; i++)
			{
				who = (int) Math.cos(i * Math.PI );			
		        match.playChess(who);
		        winner = match.win(who);
		        if(winner != 0)
		        	break;
			}
		}
		if(who == 1)
			System.out.println("��Ϸ��������ϲ�׷�ʤ��");
		else if(who == -1)
		    System.out.println("��Ϸ��������ϲ�ڷ�ʤ��");
	}

	class Chess_Board extends JPanel
	{
		public void paint(Graphics g)
		{
			g.drawImage(table , 0, 0, null);
			for(int i = 1 ; i <= 15 ; i++)
			{
				for (int j = 1 ; j <= 15; j++ )
				{
					if(board[i][j].free == -1)
						g.drawImage(black, j * rate + moveX, i * rate + moveY, null);
					else if (board[i][j].free == 1)
						g.drawImage(white, j * rate + moveX, i * rate + moveY, null);	
				}
		}
	}
}


