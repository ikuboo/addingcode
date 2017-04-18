package com.ikuboo.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yuanchunsen on 2017/4/17.
 *
 */
public class MongoCRUD {

    public static void main(String[] args) {
        MongoDatabase demoDB = MongoConnection.getDatabase("demoDB");

        //create Collection
        demoDB.createCollection("user");

        //Create Document
        List<Document> documents = new ArrayList<Document>();

        Document document = new Document("name","张三").append("age", 28);
        documents.add(document);

        document = new Document("name","李四").append("email", "ikuboo@126.com");
        documents.add(document);

        //get Collection
        MongoCollection<Document> user = demoDB.getCollection("user");

        //insert
        user.insertMany(documents);

        //select
        FindIterable<Document> findIterable = user.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while(mongoCursor.hasNext()){
            Document next = mongoCursor.next();
            for(String key : next.keySet()){
                System.out.println(key + ":" + next.get(key));
            }
            System.out.println("--------------------------------------");
        }


    }

}
