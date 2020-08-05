package main.java.za.co.roulette;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.sun.xml.internal.ws.util.StringUtils;

public class Roulette {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader reader = new BufferedReader(
				new FileReader(new File("G:/Roulette/src/main/java/za/co/roulette/PlayerNames.txt")));
		String line = null;
		int holderNum = 0;
		String betInput[] = null;
		String player = null;
		String bet = null;
		String amount = null;
		Random generator = new Random();
		int rouletteNum;
		int spin = 0;
		int result = 0;
		Map<Integer, String> map = new HashMap<Integer, String>();
		while ((line = reader.readLine()) != null) {
			map.put(holderNum++, line);
		}
		Scanner scan = new Scanner(System.in);

		try {
			while (true) {
				// prompt for the user's name
				System.out.print("Enter you bet: \n");

				String input = scan.nextLine();

				betInput = input.split(" "); // Split the word using space
				Integer position = 0;
				for (String val : betInput) {
					if (position == 0) {
						player = val;
					}
					if (position == 1) {
						bet = val;
					}
					if (position == 2) {
						amount = val;
					}
					position++;
				}

				rouletteNum = generator.nextInt(37);
				spin++;
				System.out.println("Roulette number: " + rouletteNum);
				if (bet.matches("[0-9]+")) {
					if (rouletteNum == new Integer(bet)) {
						result = 36;
					} else {
						result = 0;
					}
				} else {
					if (bet.equalsIgnoreCase("EVEN")) {
						if (rouletteNum % 2 == 0) {
							result = 2;
						} else {
							result = 0;
						}
					} else if (bet.equalsIgnoreCase("ODD")) {
						if (rouletteNum % 2 != 0) {
							result = 2;
						} else {
							result = 0;
						}
					}
				}

				System.out.println("Number: " + spin + "\n");
				System.out.println("Player   Bet    Outcome    Winnings");
				System.out.println("-------");
				System.out.println(player + "   " + bet + "   "
						+ (result > 0 ? "  WIN   " + (result * new Double(amount)) : "LOSE" + 0.0));

				Thread.sleep(30 * 1000);

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}