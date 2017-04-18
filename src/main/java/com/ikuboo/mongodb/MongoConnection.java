package com.ikuboo.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * Created by yuanchunsen on 2017/4/17.
 *
 */
public class MongoConnection {
    private static MongoDatabase database;
    private static final String host = "localhost";
    private static final int port = 27017;

    public static MongoDatabase getDatabase(String dbname){
        if(database == null){
            MongoClient mongoClient = new MongoClient(host, port);
            database = mongoClient.getDatabase(dbname);
        }
        return database;
    }

}
