package com.ceceps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import Array2DColumnCharSequnce;

public class Parkiran {

    public static void main(final String[] args) {

        // final Integer maxSlot = 100;
        BufferedReader br = null;
        String[][] dataInputs = null;
        int d = 0;
        Integer slot = 0;
        final int kolom = 3;
        
        try {

            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {

                // System.out.print("PARKING SYSTEM");

                final String input = br.readLine();
                // cek jumlah slot jika jumlah slot kurang dari array maks maka lanjut
                // jika tidak warning
                final String[] arrInput = input.split("\\s+");

                final String cmd = arrInput[0];

                if ("create_parking_lot".equals(cmd)) {
                    System.out.println("Create a Parking lot with " + arrInput[1] + " slots \n");

                    slot = Integer.parseInt(arrInput[1]);
                    dataInputs = new String[slot][kolom]; //set maksimal ukuran array 

                    if (slot == 0 || slot == null) {
                        System.out.println("Slot must fill \n");
                    }
                } else if ((d <= slot-1) && ("park".equals(cmd))) {

                    String nomer = arrInput[1];
                    String warna = arrInput[2];

                        int k = d + 1;
                        int idxfree = findEmpty(dataInputs, slot);
                        if(idxfree >= 0){
                            dataInputs[idxfree][0] = Integer.toString(k);
                            dataInputs[idxfree][1] = nomer;
                            dataInputs[idxfree][2] = warna;
   
                            System.out.println("Allocated slot number: " + Integer.toString(k));
                            d++;
    
                        }
                        
                    
                } else if ((d > slot-1) && ("park".equals(cmd))) {
                    System.out.println("Sorry Parking Slot is full");
                } else {
                    if ("status".equals(cmd)) {
                        printData(dataInputs, slot, 3);
                    }

                    if ("leave".equals(cmd)) {
                        int row = Integer.parseInt(arrInput[1]);
                        if(row>0 && row<=slot){
                            dataInputs[row-1][0] = null;
                            dataInputs[row-1][1] = null;
                            dataInputs[row-1][2] = null;
                            System.out.println("Slot number "+row+" is free");
                        }else{
                            System.out.println("Slot number "+row+" not find");

                        }
                        
                    }

                    if ("registration_numbers_for_cars_with_color".equals(input)) {
                        numberSearch(dataInputs, 2, arrInput[1]);

                    }

                    if ("slot_numbers_for_cars_with_color".equals(input)) {
                        slotSearch(dataInputs, arrInput[1]);
                    }

                    if ("exit".equals(input)) {
                        System.exit(0);
                    }
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void printData(String[][] matrix, int matrixRow, int matrixCol) {
        System.out.print("Slot No.\tRegistration No.\tColour");
        System.out.println();
        for (int i = 0; i < matrixRow; i++) {
            for (int j = 0; j < matrixCol; j++) {
                if (matrix[i][j] != null)
                    System.out.print(matrix[i][j] + "\t\t");
            }

            System.out.println();
        }
    }

    public static int findEmpty(String[][] list, int slot) {
        for (int i=0; i<=slot-1; i++) {
            if (list[i][0] == null) {
                return i;
            }
        }
        return 0;
        
    }

   
    public static void numberSearch(String[][] list, int key, String kata) {
        CharSequence charSequnce = new Array2DColumnCharSequnce(list, key);

        Pattern patttern = Pattern.compile(kata);
        Matcher matcher = patttern.matcher(charSequnce);

        while (matcher.find()) {
            String matchGroup = matcher.group();
            int start = matcher.start();
            int end = matcher.end() - 1;

            String msg = MessageFormat.format("{0} matched at: [{1}] - [{2}]", matchGroup, start, end);
            System.out.println(msg);
        }

    }

    public static String slotSearch(String[][] list, String key) {
        for (int i = 0; i < list.length; i++) {
            if (key.equals(list[i][2]))
                System.out.print(list[i][0] + ",");
        }
        return "";
    }

}