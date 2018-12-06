package pnet.httpConnection;

import java.io.InputStream;
import java.io.PrintStream;
import org.apache.commons.net.telnet.TelnetClient;

public class NetTelnet {
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	private String resultString = null;//����ķ��ؽ���ַ���
	private char prompt = '#';

	public String getResultStr () {
		return resultString;
	}
	// ��ͨ�û�����
	public NetTelnet(String ip, int port, String user, String password) {
		try {
			telnet.connect(ip, port);
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());
			// ����root�û����ý�����
		//	this.prompt = user.equals("root") ? '#' : '$';
			login(user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** * ��¼ * * @param user * @param password */
	public void login(String user, String password) {
//		readUntil("Username:");
		write(user);
//		readUntil("Password:");
		write(password);
//		readUntil(prompt + "");
		
		 
	}

	/** * ��ȡ������� * * @param pattern * @return */
	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						
						System.out.println("-->" + sb.toString());
						
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** * д���� * * @param value */
	public void write(String value) {
		try {
			out.println(value);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** * ��Ŀ�귢�������ַ��� * * @param command * @return */
	public void sendCommand(String command) {
		try {
			write(command);
			String loginInfo = readUntil(prompt + "");
			resultString = readUntil(prompt + "");
			//return readUntil(prompt + "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	/** * �ر����� */
	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}








