package Gobang;

import java.util.Scanner;

public class Piece 
{
	public int free = 0;
    //free=0空棋盘 
    //free=1 有白子
    //free=-1有黑子
	
	public void setFree(int free)
	{
		this.free = free;
	}
	
	public int coordinateX;
	
	public void setX(int X)
	{
		 this.coordinateX = X;
	}
	
	public int coordinateY;
	
	public void setY(int Y)
	{
		this. coordinateY = Y;
	}
	
	
	
	public void playGobang(int who)
	{
		if(free != 0)
			System.out.println("此处已有棋子");
		if (who < 0)
		{
			free = -1;
		}
		else if(who > 0)
		{
			free = 1;
		}
	} 
}
