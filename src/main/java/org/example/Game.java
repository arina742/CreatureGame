package org.example;
import org.example.model.Creature;
import org.example.model.Monster;
import org.example.model.Player;
import org.example.service.ImplInputService;
import org.example.service.ImplOutputService;
import org.example.service.impl.InputServiceImpl;
import org.example.service.impl.OutputServiceImpl;
import org.example.gui.InfoWindow;
import org.example.gui.WinnerWindow;

import java.util.InputMismatchException;

import static java.util.concurrent.TimeUnit.*;

public class Game {
    static ImplOutputService output = new OutputServiceImpl();
    static ImplInputService input = new InputServiceImpl();
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame(){
        InfoWindow infoWindow = new InfoWindow();
        infoWindow.showInfoWindow();
        output.outputData("Игра началась!");
        output.outputData("Определяем здоровье существ случайным образом...");

        waitLoading();

        int health = (int) (Math.random() * (200 - 150) + 150);
        output.outputData("Здоровье игрока: " + health + "  Здоровье монстра: " + health);
        Monster monster = new Monster(health);
        Player player = new Player(health);

        int countRaund = 0;
        do{
            countRaund++;

            output.outputData("Атака игрока");
            applyAttackPlayer(player);
            int rangeTo = 0;
            int rangeFrom = 0;
            if(determiningSuccessAttack(player, monster)){
                applyProtectionMonster(monster);
                boolean check;
                do {
                    check = false;
                    try {
                        output.outputData("Введите диапзон урона (от и до)");
                        rangeFrom = input.entryIntData();
                        rangeTo = input.entryIntData();
                    } catch (InputMismatchException ex) {
                        check = true;
                        output.outputData("Вы ввели некорректные данные!");
                    }
                }while(check);
                monster.setDamage((int) (Math.random() * (rangeTo - rangeFrom) + rangeFrom));
                monster.setHealth(monster.getHealth() - monster.getDamage() + monster.getProtection());
            }

            if (monster.getHealth()<=0) {
                monster.setHealth(0);
                break;
            }

            output.outputData("\nАтака монстра");
            applyAttackMonster(monster);

            if(determiningSuccessAttack(monster, player)){
                player.setDamage((int) (Math.random() * (rangeTo - rangeFrom) + rangeFrom));
                applyProtectionPlayer(player);
                player.setHealth(player.getHealth() - player.getDamage() + player.getProtection());
                if(needApplyHealing()){
                    player.setHealth((int) (player.getHealth()*1.3));
                    player.setCountOfHealings();
                    output.outputData("Кол-во оставшихся исцелений: " + player.getCountOfHealings());
                } else{
                    player.setHealth(player.getHealth() - player.getDamage());
                }
            }

            if (player.getHealth()<=0) {
                player.setHealth(0);
            }

            output.outputData("Здоровье игрока: " + player.getHealth() + "  Здоровье монстра: " + monster.getHealth());
            output.outputData("Раунд " + countRaund + " закончился!\n");

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
        monster.setAttack((byte) (Math.random() * 29 + 1));
        output.outputData("Величина аттаки монстра: " + monster.getAttack());
    }

    public static void applyProtectionMonster(Monster monster){
        monster.setProtection((byte) (Math.random() * 29 + 1));
        output.outputData("Величина защиты монстра: " + monster.getProtection());
    }
    public static void applyAttackPlayer(Player player){
        boolean check;
        do{
            check =  false;
            output.outputData("Введите величину аттаки (от 1 до 30): ");
            try{
                player.setAttack(input.entryByteData());
                if(player.getAttack() < 1 || player.getAttack() > 30){
                    check = true;
                    output.outputData("Вы ввели число не из нужного диапазона! Повторите ввод!");
                }
            } catch (InputMismatchException ex) {
                check = true;
                output.outputData("Вы ввели неверный формат! Повторите ввод!");
            }
        } while(check);
    }

    public static void applyProtectionPlayer(Player player){
        boolean check;
        do{
            check =  false;
            output.outputData("Введите величину защиты (от 1 до 30): ");
            try{
                player.setProtection(input.entryByteData());
                if(player.getProtection() < 1 || player.getProtection() > 30){
                    check = true;
                    output.outputData("Вы ввели число не из нужного диапазона! Повторите ввод!");
                }
            } catch (InputMismatchException ex) {
                check = true;
                output.outputData("Вы ввели неверный формат! Повторите ввод!");
            }
        } while(check);
    }
    public static boolean needApplyHealing(){
        output.outputData("Хотите применить исцеление? \n1 - да\n2 - нет");
        byte choice = input.entryByteData();
        if(choice == 1)
            return true;
        return false;
    }
   public static boolean  determiningSuccessAttack(Creature creatureAttack, Creature creatureProtection){
       int modifierAttack = creatureAttack.getAttack() - creatureProtection.getProtection() + 1;
       output.outputData("Определяем успешность аттаки");
       int countCubes = modifierAttack;
       do{
           int number = (int) (Math.random() * 6 + 1);
           output.outputData("На кубике выпало число: " + number);
           if(number == 5 || number == 6){
               output.outputData("Аттака прошла успешно");
               return true;
           }
           countCubes--;
       } while (countCubes != 0);
       return false;

   }
    public static void waitLoading(){
        try {
            MILLISECONDS.sleep(500);
            output.outputData("3...");
            MILLISECONDS.sleep(500);
            output.outputData("2...");
            MILLISECONDS.sleep(500);
            output.outputData("1...");
            MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}