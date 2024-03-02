package LLDQuestions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

/**
 * Design Snake & Ladders game
 * Requirements:
 * 1. Number of players
 * 2. Number of Dice
 * 3. Number of Winners?
 * 4. Start point of game
 * 5. End point of game OR winning condition
 * 6. Board size
 * 7. Number of snakes and Ladders
 * 8. Extra chance of number is 6?
 * 9. Extra chance of ladder?
 * 10. For extra chances, is it all at once or move as per number then next chance?
 */

/**
 * Objects: 
 * Cell
 * Board
 * Player
 * Dice
 * SpecialObject - Snake and Ladder
 */

class SpecialObject {
	Cell jumpTo; // destination if at special object
}

class Cell {
	int number;
	SpecialObject obj;
	
	Cell(int number) {
		this.number = number;
		this.obj = null;
	}
	
	Cell(int number, SpecialObject obj) {
		this.number = number;
		this.obj = obj;
	}
}

abstract class AbsBoard {
	protected Cell[][] board;
	abstract void initializeBoard(int numberOfSnakes, int numberOfLadders);
}

class Board extends AbsBoard{
	Board(int size, int numberOfSnakes, int numberOfLadders) {
		this.board = new Cell[size][size];
		this.initializeBoard(numberOfSnakes, numberOfLadders);
	}
	
	void initializeBoard(int numberOfSnakes, int numberOfLadders) {
		// algo to randomly put snakes and ladders at some positions
		Boolean leftToRight = true;
		int number = 1;
		for (int r = 0; r < board.length; r++) {
			if (leftToRight) {
				for (int c = 0; c < board.length; c++) { 
					board[r][c] = new Cell(number++);
				}
			}
			else {
				for (int c = board.length-1; c>=0; c--) { 
					board[r][c] = new Cell(number++);
				}
			}
			leftToRight = !leftToRight;
		}
	}
}

class Dice {
	int roll() {
		// return random number from 1 to 6
		Random rand = new Random();
		return rand.nextInt(6) + 1; // (random num 0 to 5) + 1
	}
}

class Player {
	String name;
	int position;
	
	Player(String name, int position) {
		this.name = name;
		this.position = position;
	}
	
	// make move and returns new position
	int makeMove(int jump) {
		if (position + jump <=100) {
			this.position += jump;
		}
		return this.position;
	}
}

// dice manager
// player manager
class Game {
	private Dice dice;
	private Board board;
	private Deque<Player> players;
	
	Game(int boardSize, int numberOfSnakes, int numberOfLadders, List<String> players) {
		this.dice = new Dice();
		this.board = new Board(boardSize, numberOfSnakes, numberOfLadders);
		this.players = new ArrayDeque<Player>();
		for (String player: players) this.players.add(new Player(player, 0));
	}
	
	void startGame() {
		System.out.println("Starting game !");
		Boolean winner = false;
		while(!winner) {
			// while no player wins
			Player movingPlayer = this.players.pollFirst();
			System.out.println(movingPlayer.name + " rolled a dice.");
			int number = this.dice.roll();
			System.out.println(movingPlayer.name + " got " + number);
			int newPosition = movingPlayer.makeMove(number);
			System.out.println(movingPlayer.name + " at " + newPosition);
			if (newPosition==100) {
				winner = true;
				System.out.println(movingPlayer.name + " won");
			}
			
			this.players.addLast(movingPlayer);
		}
	}
	
}



public class SnakeLadder {
	
	public static void main(String args[]) {
		List<String> players = new ArrayList<String>();
		players.add("Player1");
		players.add("Player2");
		players.add("Player3");
		Game game = new Game(10, 4, 4, players);
		game.startGame();
	}
}