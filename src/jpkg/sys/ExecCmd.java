package jpkg.sys;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public final class ExecCmd {
	/**
	 * This class shouldn't be instiantated
	 */
	private ExecCmd() { throw new AssertionError(); }

	public static final int executeCommand(String command, File dirin, OutputStream out, OutputStream err) {
		Process p = null;
		
		try {

			p = Runtime.getRuntime().exec(command, null, dirin);

			InputStream errstream = p.getErrorStream();
			InputStream outstream = p.getInputStream();

			StreamRedirThread outthread = new StreamRedirThread(outstream, out);
			StreamRedirThread errthread = new StreamRedirThread(errstream, err);

			outthread.start();
			errthread.start();

			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return p.exitValue();
	}
	
	public static final int executeCommand(String command, File dirin) {
		return executeCommand(command, dirin, System.out, System.err);
	}
	
	public static final int executeCommand(String command) {
		return executeCommand(command, new File("."));
	}
}
