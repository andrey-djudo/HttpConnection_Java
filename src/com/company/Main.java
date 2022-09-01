package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        try {
            Random rand=new Random();
            int number=rand.nextInt(1000000)%55000;//Случайное число
            //Теперь реализуем запрос
            System.out.println("First task");
            first_Task(number);
            System.out.println("Second task *");
            second_Task(number);
        }
        catch (Exception w)
        {
            System.out.println(w.toString());
        }
    }
    //Решение задание 1
    public static void first_Task(int number) throws Exception
    {
        String host="http://numbersapi.com/"+String.valueOf(number)+"/trivia";
        URL url = new URL(host);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");//Отправим запрос
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();//Считиваем на потокобезопасную строку
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        Map<Character,Integer>frequency=new HashMap<>();//Создаем словарь
        con.disconnect();//Дизконнект
        for(int i=0;i<content.length();i++)
        {
            if(frequency.containsKey(content.charAt(i)))
            {
                frequency.put(content.charAt(i),frequency.get(content.charAt(i))+1);
            }
            else
                frequency.put(content.charAt(i),1);
        }
        System.out.println("Response: "+content.toString());//Выводим ответ от сервера
        //Выводим частота символов
        for(Map.Entry entry:frequency.entrySet())
        {
            System.out.println(entry.toString());
        }
        in.close();
    }
    //Решение задание с  *
    public static void second_Task(int number) throws Exception
    {
        String host="http://numbersapi.com/"+String.valueOf(number)+"/trivia";
        URL url = new URL(host);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");//Отправим запрос
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();//Считиваем на потокобезопасную строку
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        Map<Character,Integer>frequency=new HashMap<>();//Создаем словарь
        con.disconnect();//Дизконнект
        for(int i=0;i<content.length();i++)
        {
            if(frequency.containsKey(content.charAt(i)))
            {
                frequency.put(content.charAt(i),frequency.get(content.charAt(i))+1);
            }
            else
                frequency.put(content.charAt(i),1);
        }
        System.out.println("Response: "+content.toString());//Выводим ответ от сервера
        //Выводим частота символов

        for(Map.Entry entry:frequency.entrySet())
        {
            System.out.println(entry.toString());
        }
        //Найдем средное
        double average=(content.length()*1.0)/(frequency.size()*1.0);
        //Найдем число который по модулю ближе
        double minimum=Math.abs(frequency.get(content.charAt(0))-average);
        int ans=frequency.get(content.charAt(0));

        for(Map.Entry entry:frequency.entrySet())
        {

            if(Math.abs(Double.valueOf((int)entry.getValue())-average)<minimum)
            {
                minimum=Math.abs(Double.valueOf((int)entry.getValue())-average);
                ans=(int)entry.getValue();
            }
        }
        System.out.println("Average:"+average);
        for(Map.Entry entry:frequency.entrySet())
        {
            if(Integer.compare(ans,(int)entry.getValue())==0)
                System.out.print((char)entry.getKey()+"("+Integer.valueOf((char)entry.getKey())+")   ");
        }
        System.out.println();
        in.close();
    }
}
