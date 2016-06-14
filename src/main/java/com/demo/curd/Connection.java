package com.demo.curd;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by sdyang on 2016/6/14.
 */
public class Connection {

    private static MongoClient mongoClient;

    public static MongoDatabase getMongoDatabase(String host,int port,String databaseName){
        if(mongoClient == null) {
            mongoClient = new MongoClient(host, port);
        }
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        return database;
    }

    public static MongoCollection<Document> getMongoCollection(String collectionName){
        MongoDatabase database = getMongoDatabase("localhost",27017,"mydb");
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection;
    }

    public static void getConnection(){
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

        MongoDatabase database = mongoClient.getDatabase("mydb");

        MongoCollection<Document> collection = database.getCollection("customer");

//        Document doc = new Document("firstName", "firstName")
//                .append("lastName", "lastName");
//         collection.insertOne(doc);

        System.out.println(collection.count());

        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());

        for (Document cur : collection.find()) {
            System.out.println(cur.toJson());
        }

        DeleteResult deleteResult = collection.deleteOne(eq("firstName", "MongoDB"));
        System.out.println(deleteResult.toString());
    }

    public static void main(String[] args){
        try {
            getConnection();
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
