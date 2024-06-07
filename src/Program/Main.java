package Program;

import entities.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<String> questions = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("D:\\Workspace\\ws-IntelliJ\\JAVA- RegisterSystem\\src\\formulary.txt"))){

            String line = br.readLine();
            while(line != null){
                String[] fields = line.split("\\?, 1");
                questions.add(fields[0]);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }

        questions.forEach(System.out::println);
        String name = sc.nextLine();
        String email = sc.nextLine();
        Integer age = sc.nextInt();
        Double height = sc.nextDouble();

        Person person = new Person(name, email, age, height);


        File files = new File("D:\\Workspace\\ws-IntelliJ\\JAVA- RegisterSystem\\src\\Pessoas");
        int count = 1;
        for (File file : files.listFiles()){
            count++;
        }

        String archiveName = person.getName().replaceAll("\\s", "").toUpperCase();

        File archive = new File("D:\\Workspace\\ws-IntelliJ\\JAVA- RegisterSystem\\src\\Pessoas\\"+count+"-"+archiveName+".txt");

        try{
            archive.createNewFile();
        }catch (IOException e){
            System.out.println(e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archive))){
                bw.write(person.getName());
                bw.newLine();
                bw.write(person.getEmail());
                bw.newLine();
                bw.write(String.valueOf(person.getAge()));
                bw.newLine();
                bw.write(String.valueOf(person.getHeight()));


        }catch (IOException e){
            System.out.println(e);
        }



        sc.close();
    }
}