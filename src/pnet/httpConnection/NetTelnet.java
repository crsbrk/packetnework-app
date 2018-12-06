package pnet.httpConnection;

import java.io.InputStream;
import java.io.PrintStream;
import org.apache.commons.net.telnet.TelnetClient;

public class NetTelnet {
	private TelnetClient telnet = new TelnetClient();
	private InputStream in;
	private PrintStream out;
	private String resultString = null;//命令的返回结果字符串
	private char prompt = '#';

	public String getResultStr () {
		return resultString;
	}
	// 普通用户结束
	public NetTelnet(String ip, int port, String user, String password) {
		try {
			telnet.connect(ip, port);
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());
			// 根据root用户设置结束符
		//	this.prompt = user.equals("root") ? '#' : '$';
			login(user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** * 登录 * * @param user * @param password */
	public void login(String user, String password) {
//		readUntil("Username:");
		write(user);
//		readUntil("Password:");
		write(password);
//		readUntil(prompt + "");
		
		 
	}

	/** * 读取分析结果 * * @param pattern * @return */
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

	/** * 写操作 * * @param value */
	public void write(String value) {
		try {
			out.println(value);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** * 向目标发送命令字符串 * * @param command * @return */
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

	/** * 关闭连接 */
	public void disconnect() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}








