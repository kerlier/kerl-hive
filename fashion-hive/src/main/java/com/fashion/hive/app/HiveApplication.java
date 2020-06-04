package com.fashion.hive.app;

import java.io.*;
import java.util.Random;
import java.util.UUID;

public class HiveApplication {
    private static Random random = new Random();

    public static void main(String[] args) throws IOException {


        FileOutputStream outputStream = new FileOutputStream("E:\\git\\hive\\student.txt",true);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        try{
            for (int i=0;i<1000000;i++ ) {
                writer.write(createName()+"\t"+getAge()+"\t"+getAddress()+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writer.close();
            outputStream.close();
        }

    }
    public static String createName(){
        return UUID.randomUUID().toString().substring(0,10);
    }

    public static Integer getAge(){
        return random.nextInt(50);
    }

    public static  String getAddress(){
        return UUID.randomUUID().toString().substring(0,10);
    }
}
