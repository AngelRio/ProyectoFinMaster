package es.pragsis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.pragsis.bl.EventMusic;

public class Productor {
	
	final static Logger logger = Logger.getLogger(Productor.class);
	
	public static void main(String[] args) {
		 
		/*Configuración de Kafka Properties*/
	     Properties props = new Properties();
	     //Servidor de Kafka
	     props.put("bootstrap.servers", "localhost:9092");
	     props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");	    	      
	     props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	     //props.put("partitioner.class","kafka.CustomPartitioner");
	    String topic = "ineventos";
	    
	    /**
	     * Apertura del fichero de su contenido.
	     */
	    logger.info("** Apertura del fichero **");
		File file = null;
		FileReader frEvents = null;
		BufferedReader brEvents = null;
		
		try {
			File dir = new File("/home/cloudera/Desktop/Proyecto/Datasets/");
			file = new File(dir.getCanonicalPath() + File.separator + "userid-timestamp-artid-artname-traid-traname.tsv");
			Charset charset = Charset.forName("US-ASCII");
			frEvents = new FileReader(file);
			brEvents = new BufferedReader(frEvents);
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Error en la apertura del fichero: " + e);
		}
	    
		
		/**
		 * Creación del productor Kafka
		 * */
	    //KafkaProducer<String, String> productor = new KafkaProducer<String, String>(props);
		
		/**
		 * Lectura del fichero.
		 */
		String evento = "";//Linea del documento.
		try {
			logger.info("** Lectura del fichero **");
			while( (evento = brEvents.readLine()) != null) {
				logger.info("Evento: " + evento);
				
				/*Escribimos evento en Kafka*/
				String[] event_parts = evento.split("//t", -1);
		    	//Si no tenemos un evento bien formado, lo descartamos.
		    	if(event_parts.length != 6){
//			    	userId = event_parts[0];
//			    	timestamp = event_parts[1];
//			    	artistId = event_parts[2];
//			    	artistName = event_parts[3]
//			    	trackId = event_parts[4];
//			    	trackName = event_parts[5];
//			    	key = userId;
//			    	value = timestamp.concat(artistId).concat(artistName).concat(trackId).concat(trackName);
//
//			   	 	//Enviar evento a la cola de kafka. 	
//		        	productor.send(new ProducerRecord<String, String>(topic,key,value))
		        	Thread.sleep(1000);
		    	}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("Error en la lectura del fichero: " + e);
		} finally {
			try {
				/*Aquí cerramos el fichero tanto si todo va bien como si no.*/
				if(frEvents != null) {
					logger.info("** Cerramos fichero **");
					frEvents.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
				logger.error("Error cerrando el fichero: " + e);
			}
			
		}
		
//		productor.close();
	}
	    
}
	
	
	
	
	
//	try (BufferedReader reader = file.BufferedReader(file, charset)) {
//		    
//		    String event, userId, timestamp, artistId, artistName, trackId, trackName;
//		    event = userId = timestamp = artistId= artistName = trackId = trackName = null;
//		    
//		    while ((event = reader.readLine()) != null) {
//		    	String[] event_parts = line.split("//t", -1)
//		    	//Si no tenemos un evento bien formado, lo descartamos.
//		    	if(event_parts.length() != 6){
//			    	userId = event_parts[0];
//			    	timestamp = event_parts[1];
//			    	artistId = event_parts[2];
//			    	artistName = event_parts[3]
//			    	trackId = event_parts[4];
//			    	trackName = event_parts[5];
//			    	key = userId;
//			    	value = timestamp.concat(artistId).concat(artistName).concat(trackId).concat(trackName);
//
//			   	 	//Enviar evento a la cola de kafka. 	
//		        	productor.send(new ProducerRecord<String, String>(topic,key,value))
//		        	Thread.sleep(1000);
//		    	}
//		    }
//		} catch (IOException x) {
//		    System.err.format("IOException: %s%n", x);
//		}
//	     productor.close();	      
//	 }
