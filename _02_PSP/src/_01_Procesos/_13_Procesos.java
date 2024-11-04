package _01_Procesos;

public class _13_Procesos {

	public static void main(String[] args) {
		ProcessBuilder pb = new ProcessBuilder("java", "-version");
		ProcessHandle ph = ProcessHandle.current();
		try {
			ProcessHandle.Info processInfo = ph.info();
			System.out.println("PID: " + ph.pid());
			System.out.println("Comando: " + processInfo.command());
			System.out.println("Argumentos: " + processInfo.arguments());
			System.out.println("Instante de comienzo: " + processInfo.startInstant());
			System.out.println("Tiempo total de CPU: " + processInfo.totalCpuDuration());
			System.out.println("Usuario: " + processInfo.user());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
