package Gobang;

import java.awt.*;
import java.awt.TextField;
import java.awt.event.*;
import java.util.Scanner;
import java.util.regex.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.*;
import java.io.*;

public class checkboard 
{    
	JFrame f =  new JFrame("五子棋");//主界面
	Chess_Board gobang = new Chess_Board();//棋盘区域
	private final int BOARD_WIDTH = 480;//棋盘宽度 
	private final int BOARD_HEIGHT = 480;//棋盘高度
	private final int rate = BOARD_WIDTH / 15 ;
	private TextField note = new TextField("点击开始，开始游戏");
	BufferedImage table ;//棋盘图像
	BufferedImage black ;//黑子
	BufferedImage white ;//白子
	private final int moveX = 5 ;//横坐标偏移
	private final int moveY = 5 ;//纵坐标偏移
	private int mouseX;
	private int mouseY;//鼠标选择坐标
	
	public void start()throws IOException
	{
		f.setBounds((2000 - BOARD_WIDTH - 30) / 2, (1000 - BOARD_HEIGHT) / 2, 480, 510);
	//	f.setLayout(new BoxLayout(f , BoxLayout.Y_AXIS));
		gobang.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		note.setBounds(new Rectangle(480, 30));
		table = ImageIO.read(new File("C:\\Users\\祥瑞御免雪亲王\\Desktop\\timg.jpg"));
		black = ImageIO.read(new File("C:\\Users\\祥瑞御免雪亲王\\Desktop\\black.jpg"));
		white =ImageIO.read(new File("C:\\Users\\祥瑞御免雪亲王\\Desktop\\white.jpg"));
		note.setText("请白方先落子,输入落子坐标   X，Y  以回车结束");	
		f.add(gobang);
		gobang.repaint();
		f.addWindowListener(new WindowAdapter(){

			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}		
		});
		f.add(note);
	//	f.pack();
		f.setVisible(true);
	}
	
	public void info() 
	{
		
		int winner = 0;
		for(int i = 0; i >= 0; i++)
		{	
			int who = (int) Math.cos(i * Math.PI );	
			gobang.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e )
				{
					mouseX = (int)((e.getX() - moveX) / rate);
					mouseY = (int)((e.getY() - moveY) / rate);
				}
			});
			this.playChess(who , mouseX ,mouseY);
			 winner = this.win(who);
			 if(winner != 0)
				 break;
		}	
		if(winner == 1)
			{
				note.setText("游戏结束，恭喜白方胜利");
				gobang.repaint();
			}
		else if(winner == -1)
			{
				note.setText("游戏结束，恭喜黑方胜利");
				gobang.repaint();
			}
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
/*	//打印棋盘
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
	}*/
	
	//下棋
	private void playChess(int who , int inX ,int inY) 
	{
		if(who > 0)
			note.setText("请白方落子,输入落子坐标   X，Y  以回车结束");	
		else
			note.setText("请黑方落子,输入落子坐标   X，Y  以回车结束");		
		gobang.repaint();		
		whit:
		while(1 == 1)
			{
	             if(inX < 16 && inX > 0 && inY < 16 && inY > 0)
	             {
			    	 if(board[16 - inX][inY].free != 0)
			    	 {
			    		 note.setText("此处已落子 \n 您输入的坐标有误，请从新输入");
			    	 }
			    	 else
			    	 {
			             break whit;
			    	 }
			     }
	             else
	             {
	            	 note.setText("您输入的坐标有误，请从新输入");
	             }
	             gobang.repaint();
	       }
		 if(who > 0)
		       board[16 - inX][inY].setFree(1);
		 else 
			   board[16 - inX][inY].setFree(-1);
		 gobang.repaint();
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
 
	public static void main(String[] args) throws IOException
	{

		checkboard match = new checkboard();
		match.starts();
		match.start();
		match.info();

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
}

