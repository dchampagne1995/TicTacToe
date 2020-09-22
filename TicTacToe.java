package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		// Creating the gameboard
		char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}};
	
		
	//printing the game board	
	printBoard(gameBoard);	
	
	
	//initializing scanner
	Scanner scan = new Scanner(System.in);
	String result = "";
	
	while(result=="") {
		//accepting user input to choose next move
		System.out.println("Choose your next move (1-9) : ");
		int playerMove = scan.nextInt();
		System.out.println(playerMove);
		while(playerPositions.contains(playerMove) || cpuPositions.contains(playerMove)) {
			System.out.println("Move has already been taken, go again.");
			playerMove = scan.nextInt();
		}
		
		placePiece(gameBoard, playerMove, "player");
		
		//checking for winner after player move
		result = confirmWinner();
		if(result != "") {
			System.out.println(result);
			break;
		}
	
		//cpu's move on a random basis
		Random rand = new Random();
		int cpuMove = rand.nextInt(9)+1;
		while(playerPositions.contains(cpuMove) || cpuPositions.contains(cpuMove)) {
			cpuMove = rand.nextInt(9)+1;
		}
		
		placePiece(gameBoard, cpuMove, "cpu");

		//checking for winner after cpu move
		result = confirmWinner();
		System.out.println(result);
	}

	
	}
//Printing game board method	
public static void printBoard(char[][] gameBoard) {
	
	for(char[] row : gameBoard) {
		for(char c : row) {
			System.out.print(c);
		}
		System.out.println();
	}
}

//Method to insert piece on game board
public static void placePiece(char[][] gameBoard, int move, String user) {
	
	char symbol = 'X';
	
	if(user == "cpu") {
		symbol = 'O';
		cpuPositions.add(move);
	}
	
	else if(user == "player"){
		symbol = 'X';
		playerPositions.add(move);
	}
		
	
	switch(move) {
	
		case 1 : gameBoard[0][0] = symbol;
		break;
		
		case 2 : gameBoard[0][2] = symbol;
		break;
		
		case 3 : gameBoard[0][4] = symbol;
		break;
		
		case 4 : gameBoard[2][0] = symbol;
		break;

		case 5 : gameBoard[2][2] = symbol;
		break;
		
		case 6 : gameBoard[2][4] = symbol;
		break;

		case 7 : gameBoard[4][0] = symbol;
		break;

		case 8 : gameBoard[4][2] = symbol;
		break;

		case 9 : gameBoard[4][4] = symbol;
		break;
		
		default:
			break;
	}
		printBoard(gameBoard);
		
		
}

//All possible winning combinations + checking winner
public static String confirmWinner() {
	
	List topRow = Arrays.asList(1,2,3);
	List midRow = Arrays.asList(4,5,6);
	List botRow = Arrays.asList(7,8,9);
	List topCol = Arrays.asList(1,4,7);
	List midCol = Arrays.asList(2,5,8);
	List botCol = Arrays.asList(3,6,9);
	List Cross1 = Arrays.asList(1,5,9);
	List Cross2 = Arrays.asList(3,5,7);
	
	List<List> winner = new ArrayList<List>(); 
	winner.add(topRow);
	winner.add(midRow);
	winner.add(botRow);
	winner.add(topCol);
	winner.add(midCol);
	winner.add(botCol);
	winner.add(Cross1);
	winner.add(Cross2);
	
	for(List l : winner) {
		if(playerPositions.containsAll(l)) {
			return "Congratulations you have won!";
			
		}
		else if(cpuPositions.containsAll(l)) {
			return "Sorry the cpu has won";
			
		}
		else if(playerPositions.size() + cpuPositions.size()==9) {
			return "it's a tie!";
		}
		
	}
	
	return "";
	
}

}	
		
