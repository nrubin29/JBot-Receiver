package me.nrubin29.jbot.receiver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Remote {
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(1234);
			
			System.out.println("JBot-Receiver is running without errors.");
			
			Socket socket = server.accept();
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			// First thing received will be the code.
			String name = reader.readLine();
			String source = reader.readLine();

			// Export the file.
			File root = new File(".");
			File sourceFile = new File(root, name + ".java");
			new FileWriter(sourceFile).append(source).close();
			System.out.println("Wrote file.");

			// Compile source file.
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			compiler.run(null, null, null, sourceFile.getPath());
			System.out.println("Compiled");

			// Load and instantiate compiled class.
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
			Class<?> cls = Class.forName(name, true, classLoader);
			System.out.println("Loaded Class");
			
			Robot instance = (Robot) cls.newInstance();
			
			System.out.println("Initializing");
			
			instance.preInit();
			instance.init();
			instance.postInit();
			
			System.out.println("Running");
			
			instance.run();
			
			writer.write("ready");
			writer.newLine();
			writer.flush();
			
			while (true) {
				if (instance.isInputEnabled()) {
					String line = reader.readLine();
					
					if (line == null) {
						break;
					}
					
					String[] data = line.split(" ");
					Controller controller = Controller.get(Integer.valueOf(data[0]));
					controller.setXAxis(Float.valueOf(data[1]));
					controller.setYAxis(Float.valueOf(data[2]));
					
					System.out.println("X: " + controller.getXAxis() + "Y: " + controller.getYAxis());
					
					System.out.println("Setting left to " + (Math.round(((50.0 * (controller.getYAxis() + controller.getXAxis()) + 50.0) / 100) * 100.0) / 100.0));
					instance.getMotor("left").setPercent(Math.round(((50.0 * (controller.getYAxis() + controller.getXAxis()) + 50.0) / 100) * 100.0) / 100.0);
					
					System.out.println("Setting right to " + (1 - Math.round(((50.0 * (controller.getYAxis() - controller.getXAxis()) + 50.0) / 100) * 100.0) / 100.0));
					instance.getMotor("right").setPercent(1 - Math.round(((50.0 * (controller.getYAxis() - controller.getXAxis()) + 50.0) / 100) * 100.0) / 100.0);
				}
			}
			
			socket.close();
			server.close();
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}