import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import puzzle.LinkedList;
import puzzle2.firehydrants.FireHydrants;
import puzzle2.firehydrants.FireHydrantsFactory;
import puzzle2.firehydrants.FireHydrantsTimerImpl;
import puzzle2.tester.Tester;
import puzzle2.tester.TesterFactory;
import puzzle2.FireHydrantsImplType;
import puzzle2.tester.TesterPolicy;

/**
 * Created by Bharath on 18-Apr-17.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Choose the puzzle number:\n 1. Puzzle 1 \n 2. Puzzle 2\n");
        Scanner scan = new Scanner(System.in);
        int data = scan.nextInt();
        if (data == 1) {
            puzzle1();
        } else if (data == 2) {
            puzzle2();
        }
    }

    public static void puzzle1() {
        System.out.println("Enter the list values separated by space (integers only):");
        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine();
        if (data == null || data.length() == 0) {
            System.out.println("List is empty.");
            return;
        }
        String[] dataArray = data.split("\\s+");
        int[] vals = new int[dataArray.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.parseInt(dataArray[i]);
        }
        LinkedList list = LinkedList.listBuild(vals);
        System.out.println("\nInput: \n" + list.toString());

        LinkedList result = list.moveEvensToEnd();
        System.out.println("\nOutput: \n" + result.toString());
    }

    public static void puzzle2() {
        List<Tester> testers = new ArrayList<>();
        TesterPolicy policy = new TesterPolicy.Builder().build();
        testers.add(TesterFactory.getTester(policy, FireHydrantsImplType.BASIC));
        FireHydrants fireHydrants = FireHydrantsFactory.getFireHydrants(testers, FireHydrantsImplType.BASIC);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("Select from two methods \n 1. For canSellHydrant() - type canSell \n 2. For sellHydrant() - type sell");
        Scanner scan = new Scanner(System.in);
        String data;
        while (scan.hasNext()) {
            data = scan.nextLine();
            if (data == null || data.length() == 0) {
                System.out.println("Please select right method.");
            }
            data = data.trim().toLowerCase();
            if (data.equals("cansell")) {
                System.out.println(dateFormat.format(new Date()) + " -- Can sell hydrant: " + fireHydrants.canSellHydrants());

            } else if (data.equals("sell")) {
                System.out.println(dateFormat.format(new Date()) + " -- Sell hydrant: " + fireHydrants.sellHydrants());
            }
        }
    }
}
