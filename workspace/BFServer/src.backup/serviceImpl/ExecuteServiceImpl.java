//请不要修改本文件�?
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		int LENGTH = code.toCharArray().length;
		// �?终结�?
		String result = "";

		// 标记第几个pc
		int count = 0;
		// 标记代码到哪�?
		int index = 0;
		int pointerCount = 0;
		// ',' 中第多少�?
		int scanNum = 0;
		char[] pc = new char[LENGTH];
//		Scanner scanner = new Scanner(System.in);
		
		String scanString = "";
		for (int i = 0; i < LENGTH; i++) {
			pc[i]=0;
		}
		int[] temp = new int[LENGTH];
		while(index != LENGTH) {
			switch (code.toCharArray()[index]) {
			case '[':
//				System.out.println(111);
//				System.out.print(pointerCount);
				temp[pointerCount] = index;
				pointerCount ++;
//				System.out.print(pointerCount);
				index++;
//				System.out.print("[");
				break;
				
			case ']':
				if (pc[count]!=0) {
//					System.out.print("]");
//					System.out.print(222 + " ");
//					System.out.print(pc[count] + " ");
//					System.out.print(111+ " " + count + " ");
//					System.out.print(index + " ");
//					System.out.println(s.toCharArray()[index]);
//					System.out.print(pointerCount);
					
					index = temp[pointerCount - 1];
					
//					System.out.println(pointerCount);
					
				}
				if (pc[count] == 0) {
					pointerCount --;
				}
				index++;
				break;
			case '+':
				pc[count]++;
//				System.out.print(pc[count]);
				index++;
//				System.out.print("+");
				break;
			case '-':
				index++;
				pc[count]--;
//				System.out.print("-");
				break;
			case '>':
				count ++;
				index++;
//				System.out.print(">");
				break;
			case '<':
				count --;
				index++;
//				System.out.print("<");
				break;
			case '.':
				result+=pc[count];
				index++;
//				System.out.print(".");
//				System.out.println(pc[count]);
				break;
			case ',':
//				pc[count] = scanner.next().toCharArray()[0];
//				if (scanNum == 0) {
//					scanString = scanner.nextLine();
//				}
//				pc[count] = scanString.charAt(scanNum);
				pc[count] = param.charAt(scanNum);
				scanNum ++;
				index++;
//				System.out.print(",");
				break;
			default:
				index++;
//				System.out.print(" ");
				break;
			}
//			scanner.close();
		}
		return result;
	}

}
