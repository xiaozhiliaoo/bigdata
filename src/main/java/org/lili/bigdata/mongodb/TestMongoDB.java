package org.lili.bigdata.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class TestMongoDB {
    /**
     * @param args
     */
    public static void main(String[] args) {
        insert();
        find();
        update();
        delete();
    }

    /**
     * ����ָ�����ݿ��е�ָ������
     *
     * @param dbname         ���ݿ���
     * @param collectionname ������
     * @return
     */
    //MongoDB����Ԥ�������ݿ�ͼ���,��ʹ�õ�ʱ����Զ�����
    public static MongoCollection<Document> getCollection(String dbname, String collectionname) {
        //ʵ����һ��mongo�ͻ���,��������ַ��localhost(����)���˿ںţ�27017
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //ʵ����һ��mongo���ݿ�
        MongoDatabase mongoDatabase = mongoClient.getDatabase(dbname);
        //��ȡ���ݿ���ĳ������
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionname);
        return collection;
    }

    /**
     * ��������
     */
    public static void insert() {
        try {
            //����MongoDB��ָ���������ݿ�����ָ�����ӱ�����
            MongoCollection<Document> collection = getCollection("School", "student");    //���ݿ���:School ������:student
            //ʵ����һ���ĵ�,�ĵ�����Ϊ{sname:'Mary',sage:25}��������������ֶΣ����Լ���׷��append
            Document doc1 = new Document("sname", "Mary").append("sage", 25);
            //ʵ����һ���ĵ�,�ĵ�����Ϊ{sname:'Bob',sage:20}
            Document doc2 = new Document("sname", "Bob").append("sage", 20);
            List<Document> documents = new ArrayList<Document>();
            //��doc1��doc2���뵽documents�б���
            documents.add(doc1);
            documents.add(doc2);
            //��documents���뼯��
            collection.insertMany(documents);
            System.out.println("����ɹ�");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * ��ѯ����
     */
    public static void find() {
        try {
            MongoCollection<Document> collection = getCollection("School", "student");  //���ݿ���:School ������:student
            //ͨ���α�������������ĵ����� 
//          MongoCursor<Document>  cursor= collection.find(new Document("sname","Mary")). projection(new Document("sname",1).append("sage",1).append("_id", 0)).iterator();   //find��ѯ������sname='Mary'��projectionɸѡ����ʾsname��sage,����ʾ_id(_idĬ�ϻ���ʾ)
            //��ѯ��������
            MongoCursor<Document> cursor = collection.find().iterator();
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * ��������
     */
    public static void update() {
        try {
            MongoCollection<Document> collection = getCollection("School", "student");  //���ݿ���:School ������:student
            //�����ĵ������ĵ���sname='Mary'���ĵ��޸�Ϊsage=22
            collection.updateMany(Filters.eq("sname", "Mary"), new Document("$set", new Document("sage", 22)));
            System.out.println("���³ɹ���");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * ɾ������
     */
    public static void delete() {
        try {
            MongoCollection<Document> collection = getCollection("School", "student");  //���ݿ���:School ������:student
            //ɾ�����������ĵ�һ���ĵ�
            collection.deleteOne(Filters.eq("sname", "Bob"));
            //ɾ�����з����������ĵ�  
            //collection.deleteMany (Filters.eq("sname", "Bob"));
            System.out.println("ɾ���ɹ���");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}