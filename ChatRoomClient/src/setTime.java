
public class setTime extends Thread{

	public void run()
	{ 
		
		int time = 60;
		while(time>0)
	{
			
		register.btnNewButton_1.setText(Integer.toString(time));
		register.btnNewButton_1.setEnabled(false);
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			time--;
			
	}
		if(time==0)
		{
			register.btnNewButton_1.setEnabled(true);

			register.btnNewButton_1.setText("点击重新发送验证码");
		}
		
	}
}
