package org.lgulab;

import java.util.Queue;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IdGenerator;

public class GettingStartedClient {
	
	public static void main(String[] args) {
		/*
		 * see http://docs.hazelcast.org/docs/3.5/manual/html/javaclientoverview.html 
		 */
		System.out.println("HAZELCAST CLIENT ");
        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance hazelcastClient = HazelcastClient.newHazelcastClient( clientConfig );
        
        IMap<Integer, String> map = hazelcastClient.getMap( "customers" );
        System.out.println( "Map Size:" + map.size() );
        System.out.println("get customer #1 : " + map.get(1) );
        
        
        Queue<String> queueCustomers = hazelcastClient.getQueue("customers");
        System.out.println("Queue size: " + queueCustomers.size());
        System.out.println("Poll : " + queueCustomers.poll());
        System.out.println("Queue size: " + queueCustomers.size());

        IdGenerator idGenerator = hazelcastClient.getIdGenerator( "newId" );
        long id = 0 ;
        for ( int i = 0 ; i < 20 ; i++ ) {
            id = idGenerator.newId();
            System.out.println("Id = " + id);
        }
        
        IAtomicLong counter = hazelcastClient.getAtomicLong( "counter" );
        System.out.println("counter.get : " + counter.get() ) ;
        for ( int i = 0 ; i < 20 ; i++ ) {
            System.out.println("counter.getAndIncrement() = " + counter.getAndIncrement() );
        }
        System.out.println("counter.get : " + counter.get() ) ;

        hazelcastClient.shutdown();
    }
}
