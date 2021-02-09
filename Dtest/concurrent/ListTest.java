import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {

	public static void main(String[] args) {
		
		ListModifyThread t = null;
		int loop = 100;
		try {
			
			boolean concurrency = true;
			
			if(args != null && args.length >0) {
				concurrency = Boolean.parseBoolean(args[0]);
			}
			
			List list = null;
			
			if(concurrency) {
				list = new CopyOnWriteArrayList();
			}else {
				list = new ArrayList();
			}
			
			for(int i = 0; i < loop; i++) {
				list.add("Value" + i);
			}
			
			t = new ListModifyThread(list);
			t.start();
//			t.setStop(true);
			Iterator itr = list.iterator();
			int n = 0;
			
			while(itr.hasNext()) {
				Object value = itr.next();
				
				System.out.println(String.valueOf(value));
				
				++n;
				Thread.sleep(100);
			}
			System.out.println("Loop" + loop + ", Elements: "+ n);
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(t != null)
				t.setStop(true);
		}
	}

}
