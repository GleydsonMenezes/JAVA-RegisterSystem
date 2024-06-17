package Program;

import entities.Person;
import entities.PersonExceptions;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<String> questions = new ArrayList<>();

        File files = new File("D:\\Workspace\\ws-IntelliJ\\JAVA- RegisterSystem\\src\\Pessoas");
        int count = 1;
        for (File file : files.listFiles()){
            count++;
        }

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



        List<String> menu = new ArrayList<>(Arrays.asList(
                "1 - Cadastrar o usuário",
                "2 - Listar todos usuários cadastrados",
                "3 - Cadastrar nova pergunta no formulário",
                "4 - Deletar pergunta do formulário",
                "5 - Pesquisar usuário por nome ou idade ou email"
        ));

        menu.forEach(System.out::println);
        int select = sc.nextInt();
        sc.nextLine();

        switch(select){
            case 1:


                questions.forEach(System.out::println);

                String name = sc.nextLine();
                String email = sc.nextLine();
                Integer age = sc.nextInt();
                Double height = sc.nextDouble();

                Person person = new Person(name, email, age, height);

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

                System.out.println(person);


                break;
            case 2:

                for (File file : files.listFiles()){
                        try(BufferedReader br = new BufferedReader(new FileReader(file))){

                            List<String> names = new ArrayList<>();
                            String line = br.readLine();
                            String[] fields = line.split("\\n", 2);
                            names.add(fields[0]);
                            names.forEach(System.out::println);
                        } catch (IOException e) {
                            System.out.println("Arquivo não encontrado");
                        }

                }
                break;
            case 3:


                break;
            case 4:
                break;
            case 5:
                break;
        }


        sc.close();
    }
}