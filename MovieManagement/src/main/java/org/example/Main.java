package org.example;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import org.apache.http.HttpException;
import java.io.IOException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        Client client = new Client();

        Scanner scanner = new Scanner(System.in);
        //System.out.println("Enter a question for the model:");
        //String query = scanner.nextLine();
        HashMap<String, ArrayList> movieMap = new HashMap<String,ArrayList>();
        while(true){

            System.out.println("Hello! Welcome to the movie manager!");
            System.out.println("Enter a number from the below options");
            System.out.println("1.Add movie and rating");
            System.out.println("2.View movies and description");
            System.out.println("3.Quit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){

                case 1:
                    System.out.println("Enter movie name: ");
                    String movieName = scanner.nextLine();
                    System.out.println("Enter rating ( 1- 5 ):");
                    int rating = scanner.nextInt();
                    String query = "Give me a short description of the movie "+movieName;
                    try
                    {
                        GenerateContentResponse response = client.models.generateContent("gemini-2.0-flash-001", query, null);
                        //System.out.println(response.text());
                        ArrayList<Object> movieDetails = new ArrayList<Object>();
                        movieDetails.add(response.text());
                        movieDetails.add("Rating: "+rating);
                        movieMap.put(movieName,movieDetails);
                        System.out.println("Movie successfully added!!");
                        break;
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                case 2:
                    if(!movieMap.isEmpty()){
                        for (Map.Entry entry : movieMap.entrySet())
                        {
                            System.out.println("Movie: " + entry.getKey());
                            System.out.println("Details: " + entry.getValue());
                        }
                    }
                case 3:
                    break;
            }
            break;

        }



    }
}