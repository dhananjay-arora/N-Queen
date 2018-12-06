// Below program solves N queen problem 

import java.util.*;

public class NQueenPro {

	public static void main(String[] args) {
		int n;
		Scanner in = new Scanner(System.in);
		System.out.println("N-Queen Problem Solving");
		System.out.print("Enter  number \'N\' where \'N\' indicate numbers of queens in \"N * N\" chess board: ");
		n = in.nextInt();

		if (n < 4) {
			System.out.print("\'N\' must be uper than 3!");
			System.exit(0);
		}

		SolveNQueen(n);
	}

	// print solution in console
	static void printBoardinTerminal(ArrayList<Integer> board, int len) {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (j == board.get(i)) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}

	// Calculate the number of attacks between queens
	static int evaluate(ArrayList<Integer> board, int len) {
		int score = 0;
		for (int i = 0; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				if (board.get(i) == board.get(j)) {
					score++;
					continue;
				}
				if (board.get(i) - board.get(j) == i - j) {
					score++;
					continue;
				}
				if (board.get(i) - board.get(j) == j - i) {
					score++;
					continue;
				}
			}
		}
		return score;
	}

	// generate new state from current state
	static ArrayList<Integer> generateBoard(ArrayList<Integer> board, int len) {
		ArrayList<Integer> choice = new ArrayList<Integer>();

		int temp;
		int score;
		int eval = evaluate(board, len);
		int k;

		ArrayList<Integer> boardOut = new ArrayList<Integer>();

		for (int i = 0; i < len; i++) {
			boardOut.add(board.get(i));
		}

		for (int i = 0; i < len; i++) {
			choice.clear();

			choice.add(boardOut.get(i));
			temp = boardOut.get(i);

			for (int j = 0; j < len; j++) {
				boardOut.set(i, j);

				k = evaluate(boardOut, len);

				if (k == eval) {
					choice.add(j);
				}

				if (k < eval) {
					choice.clear();
					choice.add(j);
					eval = k;
				}
			}
			boardOut.set(i, choice.get((int) (Math.random() * 100 + 1) % choice.size()));
			
		}

		return boardOut;
	}

	// This function generates new state by previous function and if it has better value then replaces that by current state
	static Boolean findNextState(ArrayList<Integer> board, int len) {
		int maineval = evaluate(board, len);

		ArrayList<Integer> tempBoard;

		tempBoard = generateBoard(board, len);
		
		if (evaluate(tempBoard, len) < maineval) {
			for (int p = 0; p < len; p++) {
				board.set(p, tempBoard.get(p));
			}

			return true;
		}

		return false;
	}

	// Make random initial state by putting one queen in each row
	static void initialRandomBoard(ArrayList<Integer> board, int len) {
		for (int i = 0; i < len; i++) {
			board.add((int) (Math.random() * 100 + 1) % len); 
		}
	}

	// This method include a loop that call findNextState function and do that until reach solution is achieved, and if findNextState method return NULL then reset current state
	static void SolveNQueen(int len) {
		System.out.println("The program is under process! Wait!");

		ArrayList<Integer> board;

		board = new ArrayList<Integer>();
		initialRandomBoard(board, len);

		while (evaluate(board, len) != 0) {
			if (!findNextState(board, len)) {
				board = new ArrayList<Integer>();
				initialRandomBoard(board, len);
			}
		}

		System.out.println("Solution for " + len + " queens: ");
		printBoardinTerminal(board, len);
		System.out.println("\n 1 indicates where queen is placed on board \n 0 indicates empty space on board");

	}

}