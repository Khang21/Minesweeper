/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS rog strix
 */


public class Board{
        
  private int FelderAnzahl = askFeld();
  public String[][] spielFeld = new String[FelderAnzahl][FelderAnzahl];   
  public String[][] display = new String[FelderAnzahl][FelderAnzahl]; 
  private Random rand = new Random();
  private int minegenone = rand.nextInt(10);
  private int minegentwo = rand.nextInt(10);
 

  public Boolean isDone = false;
  public Boolean isWin = false;
  
  private String unknown = " ? ";
  private String mine = " * ";
  private String empty = "   ";
  
  
        public int askFeld(){
  
          int Felder = 0;
        //KG sammeln
        do {
        String Felderangaben = JOptionPane.showInputDialog(null, "Geben sie wie viel Felder sie wollen",
                "Feld",
                JOptionPane.QUESTION_MESSAGE);
        
        Felder = Integer.parseInt(Felderangaben);
        
        }while(Felder == 0);
        
        return Felder;
      
  }

  public Board(){
    int reihe = 0;
    int column = 0;
    
    for(int x = 0; x < spielFeld.length; x++){
      for(int y = 0; y < spielFeld[0].length; y++){

        if((x == 0 || x == spielFeld.length - 1)||(y == 0 || y == spielFeld[0].length - 1)){
          spielFeld[x][y] = empty;
          display[x][y] = empty;
        }

        else{
          spielFeld[x][y] = unknown;
          display[x][y] = unknown;
        }
      }
    }
  }
  

  public static void printGame(String[][] str){
    for(int x = 1; x < str.length - 1; x++){   
      for(int y = 0; y < str[0].length ; y++){

        if(y > 0 && y < str[0].length)
          System.out.print("I");
        else
          System.out.println("");
        
        System.out.print(str[x][y]); 
      }
    }
  }
  

  public void update(){
    printGame(display);
    System.out.println("");
  }
  

  public void generateMines(int n){
    for(int m = 0; m < n; m++){

      while(true){
        int x, y = 0;  
        x = minegenone;
        y = minegentwo;
        
        
        if(x >= 1 && x <= 10){
          if(y >= 1 && y <= 10){
            
            if(!spielFeld[x][y].equals(mine)){
              spielFeld[x][y] = mine;
              break;
            }
          }
        }
      }
    }
  }
  
  public void clear(int x, int y){  
    for(int i = (x - 1); i <= (x + 1); i++){
      for(int j = (y - 1); j <= (y + 1); j++){
        if(spielFeld[i][j].equals(unknown) == true){
          display[i][j] = empty;
          spielFeld[i][j] = empty;
        }
      }
    }
  }
  
  
  public String getFeld(int x, int y){
    return spielFeld[x][y];
  }

  public void detect(){
    for(int x = 1; x < display.length - 2; x++){     
      for(int y = 1; y < display.length - 2; y++){
        if(spielFeld[x][y].equals(empty) == true){
          int num = 0;                              
          for(int i = (x - 1); i <= (x + 1); i++){
            for(int j = (y - 1); j <= (y + 1); j++){
              if(spielFeld[i][j].equals(mine) == true)
                num++;                            
            }
          }
          display[x][y] = " " + num + " ";
        }
      }
    }
  }
  
  
  public void umdrehen(int x, int y){
    if(spielFeld[x][y].equals(unknown) == true){           
      isDone = false;
      display[x][y] = empty;
      spielFeld[x][y] = empty;
    }else if(spielFeld[x][y].equals(mine) == true){        
      isDone = true;                                   
      isWin = false;                                   
      System.out.println("Woops Bombe getroffen");
    }else if(display[x][y].equals(empty) == true && spielFeld[x][y].equals(empty)){
      isDone = false;
      
    }
  }
 
  public void isVictory(){
    int feld = 0;                                      
    for(int i = 0; i < spielFeld.length; i++){
      for(int j = 0; j < spielFeld[0].length; j++){
        if(spielFeld[i][j].equals(unknown) == true)
          feld++;                                      
      }
    }
    if(feld != 0)
      isWin = false;  
    else{
      isWin = true;
      isDone = true;
    }
  }
  

  public Boolean getDone(){
    return isDone;
  }
  

  public Boolean getWin(){
    return isWin;
  }

  public void printFeld(){
    printGame(spielFeld);
  }
  
}