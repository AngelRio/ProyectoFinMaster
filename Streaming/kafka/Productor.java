package kafka;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Productor {
	 public static void main(String[] args) {
		 
	     Properties props = new Properties();
	     //Servidor de Kafka
	     props.put("bootstrap.servers", "localhost:9092");
	     props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");	    	      
	     props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	     //props.put("partitioner.class","kafka.CustomPartitioner");
	     
 	    String topic = "ineventos";
	    File dir = new File("/home/cloudera/Desktop/Proyecto/Datasets/");
		File file = new File(dir.getCanonicalPath() + File.separator + "userid-timestamp-artid-artname-traid-traname.tsv");

	    //Crear el productor de Kafka
 	    KafkaProducer<String, String> productor = new KafkaProducer<String, String>(props);
 
		//Leer fichero
	    Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
		    
		    String event, userId, timestamp, artistId, artistName, trackId, trackName;
		    event = userId = timestamp = artistId= artistName = trackId = trackName = null;
		    
		    while ((event = reader.readLine()) != null) {
		    	String[] event_parts = line.split("//t", -1)
		    	//Si no tenemos un evento bien formado, lo descartamos.
		    	if(event_parts.length() != 6){
			    	userId = event_parts[0];
			    	timestamp = event_parts[1];
			    	artistId = event_parts[2];
			    	artistName = event_parts[3]
			    	trackId = event_parts[4];
			    	trackName = event_parts[5];
			    	key = userId;
			    	value = timestamp.concat(artistId).concat(artistName).concat(trackId).concat(trackName);

			   	 	//Enviar evento a la cola de kafka. 	
		        	productor.send(new ProducerRecord<String, String>(topic,key,value))
		        	Thread.sleep(1000);
		    	}
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	     productor.close();	      
	 }
}
