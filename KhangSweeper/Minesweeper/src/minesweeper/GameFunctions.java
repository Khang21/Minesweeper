/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS rog strix
 */
public class GameFunctions{
  
  
 public static void run(){  
     
    
    Board board = new Board();
    board.generateMines(1); 
    board.update();
    Scanner scan = new Scanner(System.in); 
    
    int x, y;
    System.out.println("Geben sie x Koordinate an.");
    x = scan.nextInt();
    System.out.println("Geben sie y Koordinate an.");
    y = scan.nextInt();
    
    
    if(board.getFeld(x,y).equals(" * ") == true){
      board.generateMines(1);
      board.spielFeld[x][y] = " ? ";
    }
    
    board.clear(x,y);
    board.detect();
    board.update();
    

    while(true){
      if(board.getDone() == true && board.getWin() == true){  
        System.out.println("Sieg!");
        board.printFeld();
        break;
      }else if(board.getDone() == true){                      
        board.printFeld(); 
        break;
      }else if(board.getDone() == false){                      
        x = -1;
        y = -1;     
        System.out.println("Geben sie x Koordinate an.");
        y = scan.nextInt();
        System.out.println("Geben sie y Koordinate an.");
        x = scan.nextInt();
        board.umdrehen(x,y);
        board.isVictory();
        board.detect();
        board.update();
      }
      
    }   
  }
 
       public int askMine(){
  
          int Mine = 0;
        
        do {
        String Felderangaben = JOptionPane.showInputDialog(null, "Geben sie wie viel Mine sie wollen",
                "Mine",
                JOptionPane.QUESTION_MESSAGE);
        
        Mine = Integer.parseInt(Felderangaben);
        
        }while(Mine == 0);
        
        return Mine;
      
  }
}