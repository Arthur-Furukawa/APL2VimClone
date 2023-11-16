package org.example;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        doubleLinkedList list = null;

        Scanner input = new Scanner(System.in);
        String command = "i";
        while(!command.equals("o")) {
            System.out.println("digite seu comando:");
            command = input.nextLine();

            if (command.startsWith(":e")) {
                try {
                    String[] commandContent = command.split(" ");

                    list = fileOperations.transferFileDataToDoubleLinkedList(commandContent[1]);
                }catch (FileNotFoundException e){
                    System.out.println("Epa! Arquivo nao encontrado. tente novamente\n");
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Epa digite um arquivo por favor\n");
                }
            }

            if (command.startsWith(":w")) {
                String[] commandContent = command.split(" ");

                    if(list == null){
                        System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                    }else {
                        System.out.println("transferindo conteudo da lista encadeada para o arquivo: " + commandContent[1]);

                        list.transferDataToFile(commandContent[1]);
                    }
            }

            // Colocar os dois ":/" para serem uma operação só, mas diferentes ao mesmo tempo
            if (command.startsWith(":/")){
                String element = command.split(" ")[1];
                if (list == null){
                    System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                }else{
                    System.out.println("Procurando pelo elemento: " + element);
                    Node current = list.getHead();
                    int line = 1;
                    do {
                        if (current.getData().contains(element)){
                            System.out.println(line + ". " + current.getData());
                        }
                        current = current.getRight();
                        line++;
                    } while (current != list.getHead());
                }
            }


            if (command.startsWith(":/")){
                String[] commandContent = command.split(" ");
                String elem = commandContent[1];
                String elemTroca = commandContent[2];
                if(list == null){
                    System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                }else{
                    System.out.println("Procurando pelo elemento: " + elem + " para substituir por: " + elemTroca);
                    Node current = list.getHead();
                    int line = 1;
                    do {
                        if (current.getData().contains(elem)){
                            current.setData(current.getData().replace(elem, elemTroca));
                            System.out.println("Elemento substituído na linha " + line + ". " + current.getData());
                        }
                        current = current.getRight();
                        line++;
                    } while (current != list.getHead());
                }
            }

            if (command.startsWith(":a")){
                int posLin = Integer.parseInt(command.split(" ")[1]);
                if (list == null){
                    System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                }else{
                    System.out.println("Digite as novas linhas. Após digitar as novas linhas, utilize ':a' em uma linha vazia.");
                    String newLine = input.nextLine();
                    while (!newLine.equals(":a")){
                        list.insertAt(posLin, newLine);
                        newLine = input.nextLine();
                        posLin++;
                    }
                    System.out.println("Novas linhas adicionadas com sucesso.");
                }
            }

            if (command.startsWith(":i")){
                String[] commandContent = command.split(" ", 3);
                int posLin = Integer.parseInt(commandContent[1]);
                String newLine = commandContent[2];
                if(list == null){
                    System.out.println("Lista ainda nao foi criada utilize o comando (:e) primeiro\n");
                }else{
                    System.out.println("Inserindo a nova linha na posição: " + posLin);
                    if(posLin > 1){
                        posLin = posLin - 2;
                    }
                    list.insertAt(posLin, newLine);
                    System.out.println("Novas linhas adicionadas com sucesso.");
                }
            }
        }
        System.out.println("\nObrigado por usar nosso programa :)\n");
    }
}