/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benal
 */
import java.util.*;

public class test2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Donne joure : ");
        int jour = sc.nextInt();
        switch (jour) {
            case 1:
                System.out.println("lundi");
                break;
            case 2:
                System.out.println("mardi");
                break;
            case 3:
                System.out.println("mercrede");
                break;
            case 4:
                System.out.println("jeudi");
                break;
            case 5:
                System.out.println("vondredi");
                break;
            case 6:
                System.out.println("samdi");
                break;
            case 7:
                System.out.println("dimanche");
                break;
            default:
                System.out.println("error");
                break;
        }
    }
}
