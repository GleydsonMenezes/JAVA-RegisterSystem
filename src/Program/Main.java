package Program;

import entities.Person;
import jdk.jshell.spi.ExecutionControl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
        sc.nextLine();
        Double height = sc.nextDouble();

        Person person = new Person(name, email, age, height);

        System.out.println(person);


        sc.close();
    }
}