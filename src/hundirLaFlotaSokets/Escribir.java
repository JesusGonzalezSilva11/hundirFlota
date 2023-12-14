package hundirLaFlotaSokets;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Escribir implements Runnable{
	Socket canal=null;
	OutputStream streamSalida=null;
	
	Escribir(Socket canal,OutputStream streamSalida) {
		this.canal=canal;
		this.streamSalida=streamSalida;
	}
	@Override
	public void run() {
		try {//manda
			System.out.println("Servidor manda datos");
			//Esperamos hasta que se produzca una conexión al puerto
	        //El método ServerSocket.accept(); bloquea (hace dormir) el proceso hasta que se produce una conexión
			var salida = new PrintWriter(streamSalida);
	        //Creamos un objeto PrintWriter a partir del Stream de salidadel socket o canal de comunicación
	        //El objeto PrintWriter, nos permitirá utilizar los métodos
	        //print y write para mandar datos al proceso que está escuchando al otro lado del canal.
	        System.out.println("Conexión establecida, mandando datos al cliente...");
	        String palabra="Datos entrada";
	        salida.println(palabra); //Mandamos los datos
	        salida.flush();
	        System.out.println("Comunicación finalizada.");
	    }catch(Exception ex){
	        System.err.println("El cliente cerro");
	        System.err.print(ex.toString());
	    }
	}
}
