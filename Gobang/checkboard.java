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
	JFrame f =  new JFrame("五子棋");//主界面
	Chess_Board gobang = new Chess_Board();//棋盘区域
	private final int BOARD_WIDTH = 480//棋盘宽度 
	private final int BOARD_HEIGHT = 480//棋盘高度
	private final int rate = BOARD_WDTH / 15 ;
	private TextField note = new TextField("点击开始，开始游戏");
	BufferImage table ;//棋盘图像
	BufferImage black ;//黑子
	BufferImage white ;//白子
	private final int moveX = 5 ;//横坐标偏移
	private final int moveY = 5 ;//纵坐标偏移
	
	
	public void info()
	{
		f.setBounds((2000 - BOARD_WIDTH - 30) / 2, (1000 - BOARD_HEIGHT) / 2, 480, 510);
		f.setLayout(new BoxLayout(f , BoxLayout.Y_AXIS));
		gobang.setPreferredSize(new Dimesion(2000 - BOARD_WIDTH - 30) / 2, (1000 - BOARD_HEIGHT) / 2));
		note.setBounds(new Rectangle(480, 30));
		table = ImageIO.read(new File("C:\\Users\\祥瑞御免雪亲王\\Desktop\\timg.jpg"));
		black = ImageIO.read(new File("C:\\Users\\祥瑞御免雪亲王\\Desktop\\black.jpg"));
		white =ImageIO.read(new File("C:\\Users\\祥瑞御免雪亲王\\Desktop\\white.jpg"));
		
		
		
	}
	
	private Piece[][] board = new Piece[17][17];//初始化数组
	public void starts()
	{

	    for(int i = 1 ; i <= 15 ; i++ )
    	{
	        for (int j = 1 ; j <= 15 ; j++ )
		    {
	        	board[i][j] = new Piece();//每一个数组元素初始化类
	        	board[i][j].setX(i);
	        	board[i][j].setY(j);
	        }
	    }	
	}
	//打印棋盘
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
					System.out.print("黑 ");
				else if (board[i][j].free == 1)
					System.out.print("〇 ");
				else
					System.out.print("十 ");
			}
			System.out.println("");
		}	
		System.out.println("  1  2 3  4 5  6 7  8 9 10 11 12 13 14 15");
	}
	
	//下棋
	private void playChess(int who) 
	{
		if(who > 0)
			System.out.println("请白方落子,输入落子坐标   X，Y  以回车结束");	
		else
			System.out.println("请黑方落子,输入落子坐标   X，Y  以回车结束");				
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
			    	     System.out.println("此处已落子 \n 您输入的坐标有误，请从新输入");
			    	 }
			    	 else
			    	 {
			             break whit;
			    	 }
			     }
	             else
	             {
	                 System.out.println("您输入的坐标有误，请从新输入");
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
			System.out.println("游戏结束，恭喜白方胜利");
		else if(who == -1)
		    System.out.println("游戏结束，恭喜黑方胜利");
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


