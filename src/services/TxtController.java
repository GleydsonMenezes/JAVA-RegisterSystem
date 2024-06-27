package services;

import entities.Person;
import entities.PersonExceptions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtController {


    static String formularyPath = "D:\\Workspace\\ws-IntelliJ\\JAVA-RegisterSystem\\src\\formulary.txt";
    static String filePath = "D:\\Workspace\\ws-IntelliJ\\JAVA-RegisterSystem\\src\\Pessoas";
    static File directory = new File(filePath);

    static List<String> questions = new ArrayList<>();
    static List<String> emails = new ArrayList<>();
    static List<String> names = new ArrayList<>();


    static Scanner sc = new Scanner(System.in);


    public static void preLoad(){

        for (File file : directory.listFiles()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                String line = br.readLine();
                while (line != null) {
                    line = br.readLine();
                    emails.add(line);
                    break;
                }
            } catch (IOException e) {
                System.out.println("Arquivo não encontrado");
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(formularyPath))) {

            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split("\\?, 1");
                questions.add(fields[0]);
                line = br.readLine();
            }

        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }
    }


    public static void personRegister(){
        int count = 1;
        for (File file : directory.listFiles()) {
            count++;
        }


        questions.forEach(System.out::println);

        String name = sc.nextLine();
        String email = sc.nextLine();
        Integer age = sc.nextInt();
        Double height = sc.nextDouble();

        if (emails.contains(email)) {
            throw new PersonExceptions("email já existe");
        }

        Person person = new Person(name, email, age, height);

        String archiveName = person.getName().replaceAll("\\s", "").toUpperCase();
        File archive = new File("D:\\Workspace\\ws-IntelliJ\\JAVA-RegisterSystem\\src\\Pessoas\\" + count + "-" + archiveName + ".txt");

        try {
            archive.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archive))) {
            bw.write(person.getName());
            bw.newLine();
            bw.write(person.getEmail());
            bw.newLine();
            bw.write(String.valueOf(person.getAge()));
            bw.newLine();
            bw.write(String.valueOf(person.getHeight()));


        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println(person);
    }


    public static List<String> allNames() {
        int aux = 1;

        for (File file : directory.listFiles()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                String line = br.readLine();
                while (line != null) {
                    names.add(aux + " - " + line);
                    aux++;
                    break;
                }
            } catch (IOException e) {
                System.out.println("Arquivo não encontrado");
            }
        }
        return names;
    }

    public static void searchUser(){
        allNames();
        System.out.print("Digite o nome:");
        String n = sc.nextLine();
        List<String> result = names.stream().filter(n2 -> n2.contains(n)).toList();
        result.forEach(System.out::println);
    }

    public static void formularyRegister(){
        int formularyAux = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(formularyPath))) {

            String line = br.readLine();
            while (line != null) {
                formularyAux++;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado");
        }

        System.out.print("Registre sua nova pergunta:");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(formularyPath, true))) {
            String question = sc.nextLine();
            if (!question.contains("?")) {
                throw new IllegalArgumentException("É necessário ter interrogação para registrar uma pergunta");
            }
            bw.newLine();
            bw.write(formularyAux + " - " + question);


        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void formularyDelete(){
        System.out.print("Digite o número da questão a ser apagada:");
        int c = sc.nextInt();
        List<String> remainQuestions = new ArrayList<>();
        if (c <= 4){
            throw new IllegalArgumentException("Não pode deletar as questões entre 1~4");
        }
        try (BufferedReader br = new BufferedReader(new FileReader(formularyPath))){
            String line = br.readLine();
            while (line != null){
                if (!line.contains(String.valueOf(c))){
                    remainQuestions.add(line);
                }
                line = br.readLine();
            }


        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        int Qcount = 0;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(formularyPath))) {
            for(String q : remainQuestions){
                if (!q.isEmpty()) {
                    StringBuilder sb = new StringBuilder(q);
                    sb.deleteCharAt(0);
                    bw.write( String.valueOf(Qcount + 1) + sb.toString());
                    Qcount++;
                    bw.newLine();
                }
            }


        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
