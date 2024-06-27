package program;

import java.util.*;

import static services.TxtController.*;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        List<String> menu = new ArrayList<>(Arrays.asList(
                "1 - Cadastrar o usuário",
                "2 - Listar todos usuários cadastrados",
                "3 - Cadastrar nova pergunta no formulário",
                "4 - Deletar pergunta do formulário",
                "5 - Pesquisar usuário por nome ou idade ou email"
        ));


        preLoad();

        menu.forEach(System.out::println);
        int select = sc.nextInt();
        sc.nextLine();

        switch (select) {
            case 1:
                personRegister();
                break;
            case 2:
                List<String> result = allNames();
                for (String name : result) {
                    System.out.println(name);
                }
                break;

            case 3:
                formularyRegister();
                break;

            case 4:
                formularyDelete();
                break;
            case 5:
                searchUser();
                break;

        }
            sc.close();
        }



}

