package Gobang;

import java.util.Scanner;

public class Piece 
{
	public int free = 0;
    //free=0������ 
    //free=1 �а���
    //free=-1�к���
	
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
			System.out.println("�˴���������");
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
