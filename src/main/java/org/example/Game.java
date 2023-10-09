package org.example;
import org.example.creatures.Creature;
import org.example.creatures.Monster;
import org.example.creatures.Player;
import org.example.windows.InfoWindow;
import org.example.windows.WinnerWindow;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.util.concurrent.TimeUnit.*;

public class Game {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame(){
        InfoWindow infoWindow = new InfoWindow();
        infoWindow.showInfoWindow();

        System.out.println("Игра началась!");
        System.out.println("Определяем здоровье существ случайным образом...");

        waitLoading();

        int health = (int) (Math.random() * (200 - 150) + 150);
        System.out.println("Здоровье игрока: " + health + "  Здоровье монстра: " + health);
        Monster monster = new Monster(health);
        Player player = new Player(health);

        int countRaund = 0;
        do{
            countRaund++;

            System.out.println("Атака игрока");
            applyAttackPlayer(player);
            int rangeTo = 0, rangeFrom = 0;
            if(determiningSuccessAttack(player, monster)){
                applyProtectionMonster(monster);
                boolean check;
                do {
                    Scanner in = new Scanner(System.in);
                    check = false;
                    try {
                        System.out.println("Введите диапзон урона (от и до)");
                        rangeFrom = in.nextInt();
                        rangeTo = in.nextInt();
                    } catch (InputMismatchException ex) {
                        check = true;
                        System.out.println("Вы ввели некорректные данные!");
                    }
                }while(check);
                monster.setDamage((int) (Math.random() * (rangeTo - rangeFrom) + rangeFrom));
                monster.setHealth(monster.getHealth() - monster.getDamage() + monster.getProtection());
            }

            if (monster.getHealth()<=0) {
                monster.setHealth(0);
                break;
            }

            System.out.println("\nАтака монстра");
            applyAttackMonster(monster);

            if(determiningSuccessAttack(monster, player)){
                player.setDamage((int) (Math.random() * (rangeTo - rangeFrom) + rangeFrom));
                applyProtectionPlayer(player);
                player.setHealth(player.getHealth() - player.getDamage() + player.getProtection());
                if(applyHealing()){
                    player.setHealth((int) (player.getHealth()*1.3));
                    player.setCountOfHealings();
                    System.out.println("Кол-во щставшихся исцелений: " + player.getCountOfHealings());
                } else{
                    player.setHealth(player.getHealth() - player.getDamage());
                }
            }

            if (player.getHealth()<=0) {
                player.setHealth(0);
            }

            System.out.println("Здоровье игрока: " + player.getHealth() + "  Здоровье монстра: " + monster.getHealth());
            System.out.println("Раунд " + countRaund + " закончился!\n");

        } while(monster.getHealth() > 0 && player.getHealth() > 0 && player.getCountOfHealings() <= 4);

        String winner;
        if(monster.getHealth()<=0){
            winner = "игрок";
        } else {
            winner = "монстр";
        }
        WinnerWindow winnerWindow = new WinnerWindow(winner);
        winnerWindow.showWinnerWindow();
    }


    public static void applyAttackMonster(Monster monster){
        monster.setAttack((byte) (Math.random() * 30 + 1));
        System.out.println("Величина аттаки монстра: " + monster.getAttack());

    }

    public static void applyProtectionMonster(Monster monster){
        monster.setProtection((byte) (Math.random() * 30 + 1));
        System.out.println("Величина защиты монстра: " + monster.getProtection());

    }
    public static void applyAttackPlayer(Player player){
        boolean check;
        do{
            check =  false;
            Scanner in = new Scanner(System.in);
            System.out.print("Введите величину аттаки (от 1 до 30): ");
            try{
                player.setAttack(in.nextByte());
                if(player.getAttack() < 1 || player.getAttack() > 30){
                    check = true;
                    System.out.println("Вы ввели число не из нужного диапазона! Повторите ввод!");
                }
            } catch (InputMismatchException ex) {
                check = true;
                System.out.println("Вы ввели неверный формат! Повторите ввод!");
            }
        } while(check);
    }

    public static void applyProtectionPlayer(Player player){
        boolean check;
        do{
            check =  false;
            Scanner in = new Scanner(System.in);
            System.out.print("Введите величину защиты (от 1 до 30): ");
            try{
                player.setProtection(in.nextByte());
                if(player.getProtection() < 1 || player.getProtection() > 30){
                    check = true;
                    System.out.println("Вы ввели число не из нужного диапазона! Повторите ввод!");
                }
            } catch (InputMismatchException ex) {
                check = true;
                System.out.println("Вы ввели неверный формат! Повторите ввод!");
            }
        } while(check);
    }
    public static boolean applyHealing(){
        System.out.println("Хотите применить исцеление? \n1 - да\n2 - нет");
        Scanner in = new Scanner(System.in);
        byte choice = in.nextByte();
        if(choice == 1)
            return true;
        return false;
    }
   public static boolean  determiningSuccessAttack(Creature creatureAttack, Creature creatureProtection){
       int modifierAttack = creatureAttack.getAttack() - creatureProtection.getProtection() + 1;
       System.out.println("Определяем успешность аттаки");
       int countCubes = modifierAttack;
       do{
           int number = (int) (Math.random() * 6 + 1);
           System.out.println("На кубике выпало число: " + number);
           if(number == 5 || number == 6){
               System.out.println("Аттака прошла успешно");
               return true;
           }
           countCubes--;
       } while (countCubes != 0);
       return false;

   }
    public static void waitLoading(){
        try {
            MILLISECONDS.sleep(500);
            System.out.println("3...");
            MILLISECONDS.sleep(500);
            System.out.println("2...");
            MILLISECONDS.sleep(500);
            System.out.println("1...");
            MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}