package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        ChessMatch chessMatch = new ChessMatch();
        Scanner sc = new Scanner(System.in);
        List<ChessPiece> captured = new ArrayList<>();

        while (!chessMatch.getCheckMate()){
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();

                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }

                if (chessMatch.getPromoted() != null) {
                    System.out.print("Coloque qual peca quer (B/N/R/Q): ");
                    String type = sc.nextLine().toUpperCase();

                    while (!type.equals("N") && !type.equals("B") && !type.equals("R") && !type.equals("Q")){
                        System.out.print("Valor invalido! Coloque qual peca quer (B/N/R/Q): ");
                        type = sc.nextLine().toUpperCase();
                    }
                    chessMatch.replacePromotedPiece(type);

                }

            }
            catch(ChessException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);


    }
}
