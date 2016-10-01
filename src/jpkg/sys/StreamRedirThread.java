package jpkg.sys;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamRedirThread extends Thread {
	private OutputStream out;
	private InputStream in;
	
	public StreamRedirThread(InputStream i, OutputStream o) {
		out = o;
		in = i;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				int ch = in.read();
				
				if(ch == -1) break;
				
				out.write(ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
