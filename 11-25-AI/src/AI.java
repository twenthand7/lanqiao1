import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;
import java.util.*;

public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas implements Runnable
{

	/*
	����������
	�﷨���������� �������ƣ���ʶ����;
	*/
    Thread thread;
	int heroX,heroY,bossX,bossY,i,j;
    int Flag;
	Image [][] heroImg=new Image[4][3];
	Image currentImg;
	Image bossImg;
	Random rd=new Random();
	public MainCanvas(){
		try
		{

			for(int i=0;i<heroImg.length;i++){
				for(int j=0;j<heroImg[i].length;j++){
					System.out.println("21135");
                            heroImg[i][j]=Image.createImage("/sayo"+i+j+".png");
				}
			
			}
                     bossImg=Image.createImage("/zuzu000.png");

                     currentImg=heroImg[3][1];
                     heroX=120;
			         heroY=100;

			         bossX=0;
			         bossY=0;

			         Flag=1;

                     thread=new Thread(this);
			         thread.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/*
	�ڶ��߳���дһ����ѭ������ͣ�ıȽ�boss��hero������
	*/
	public void run(){
		while(true){
			int rdNumber=rd.nextInt(10);
			
			try
			{
				Thread.sleep(200);//FPS����Ļˢ����
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(rdNumber%3==0){
				if(bossX<heroX){
					bossX++;
				}
				else{
					bossX--;
				}

				if(bossY<heroY){
					bossY++;
				}else{
					bossY--;
				}
			}
			repaint();
		}
	}

	public void paint(Graphics g){
		g.setColor(0,0,0);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);//120��X���ꡢ100��Y����
		g.drawImage(bossImg,bossX,bossY,0);
	}

	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);

		if(action==LEFT){
			changePicandTurn(0);
            heroX=heroX-10;
		}
            
        if(action==RIGHT){
			changePicandTurn(1);
            heroX=heroX+10;
		}
                
        if(action==UP){
			changePicandTurn(2);
            heroY=heroY-10;
		} 
              
		
        if(action==DOWN){
			changePicandTurn(3);
            heroY=heroY+10;
		}
       
        repaint();
	}
	//���巽��
	public void changePicandTurn(int direction){
			if(Flag==1){
				currentImg=heroImg[direction][0];
				Flag++;
				}
			else if(Flag==2){
				currentImg=heroImg[direction][2];
				Flag=1;
			}
			System.out.println("35");
                
	}
}