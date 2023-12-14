package hundirLaFlotaSokets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Leer implements Runnable{
	Socket canal=null;
	OutputStream streamSalida=null;
	InputStream streamEntrada=null;
	
	Leer (Socket canal) {
		this.canal=canal;
		try {
			this.streamSalida=canal.getOutputStream();
			this.streamEntrada=canal.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {//manda la pantalla inicial
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
		while(canal.isConnected()) {
			try{//recibe la accion y datos
				System.out.println("Servidor recibe datos");
	            var entrada = new BufferedReader(new InputStreamReader(streamEntrada));	            
	            String valorEntrada=null;
	            while((valorEntrada = entrada.readLine())==null)
	            //Obtemenos el objeto que representa el strean de entrada en el canal
	            //Lector con buffer, para no perder ningún dato
	            while ((valorEntrada = entrada.readLine())!=null){
	                //Mientras que haya datos que leer
	                System.out.println(valorEntrada);
	                System.out.println("");
	            }                  
	            }catch (Exception ex){
	               System.err.println("No se ha podido establecer conexión.");
	               System.err.println(ex.toString());
	            }//La informacion que me llegue que sea tipo disparo-A-8 o inicio-pepe-123
	            //asi la informacion puedo sacar primero la accion y luego la informacion
			try {
				switch (accion) {
				case inicio:
					escribir.loguear(informacion);//El metodo loguear que por ejemplo acceda a la informacion del usuarioo devuelva false si no existe
					break;
				case disparo:
					escribir.disparar(informacion);//El metodo de disparar por ejemplo que haga la escritura del disparo en la partida
					break;
				
				}
			}catch (Exception e) {
				// TODO: handle exception
			}
		}		
	}
	void loguear(){
		//hace sus comprobaciones y llama a escribir para que mande lo que sea
	}

}
