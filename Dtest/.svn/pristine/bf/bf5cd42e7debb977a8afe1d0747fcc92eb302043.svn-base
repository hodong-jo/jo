import java.util.List;

public class ListModifyThread extends Thread{
	
	private List list;
	private boolean stop;
	
	public ListModifyThread(List list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		try {
			int index = 0;
			
			while(!this.stop) {
				
				if(index%3 == 0) {
					boolean remove = this.list.remove("Value" + index);
					if(remove)
						System.out.println("Removed : Value" + index);
				}
				++ index;
				Thread.sleep(50);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
}
