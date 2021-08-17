package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Main {
    private static final String COMMA_DELIMITER = ","; // Split by comma
    private static int Maxheap;
    private static int NumberOfHeap;
    private static int totalID = 0;
    public static void main(String[] args) throws IOException {
        System.out.println("Assignment1 - Complexity and Compute ");

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter Memory Size: ");
        Maxheap = keyboard.nextInt();
        System.out.print("Enter Number of Heap: ");
        NumberOfHeap = keyboard.nextInt();

        //System.out.println("Memory Size: "+ Maxheap + " " + NumberOfHeap + "");
        //Read and Sort ID
        ParseFile();
        switch (NumberOfHeap) {
            case 1: oneHeap();break;
            case 2: twoHeap();break;
            default:break;
        }

    }

    public static void twoHeap() throws IOException {

        BufferedReader brID = null;
        brID = new BufferedReader(new FileReader("input/ID.txt"));
        PriorityQueue<String> minHeap1 = new PriorityQueue<String>();
        PriorityQueue<String> minHeap2 = new PriorityQueue<String>();
        ArrayList<PriorityQueue> HeapList = new ArrayList<PriorityQueue>();
        HeapList.add(minHeap1);
        HeapList.add(minHeap2);

        int heap1Size = Math.round(Maxheap/2);
        int heap2Size = Maxheap - heap1Size;
        int currentHeap = 0;
        String lastValue = "";
        Boolean isNewRun = true;
        String tmptReadID = "";
        int runTime = 1;System.out.print("Run1 :");

        while(true)
        {
            Maxheap = (currentHeap==0)? heap1Size: heap2Size;
            //System.out.print(" switch " + HeapList.get(currentHeap).size() + " " + Maxheap );
            if (isNewRun) {
                while (HeapList.get(currentHeap).size() < Maxheap) {
                    if ((tmptReadID = brID.readLine()) != null) {
                        HeapList.get(currentHeap).add(tmptReadID);
                        totalID--;
                    }else break;
                }
                if (HeapList.get(currentHeap).isEmpty() == false) {
                    lastValue = (String) HeapList.get(currentHeap).poll();
                    System.out.print(" " + lastValue + " ");
                    isNewRun = false;
                }
            }

            if (tmptReadID.isEmpty()) {
                if ((tmptReadID = brID.readLine()) != null) {
                    totalID--;
                }
            }
            //System.out.println(" +" +tmptReadID + " + ");
            //System.out.println();

            if (lastValue.compareTo(tmptReadID) < 0)
            {
                HeapList.get(currentHeap).add(tmptReadID);
                tmptReadID = "";
                lastValue = (String) HeapList.get(currentHeap).poll();
                System.out.print(" " + lastValue);
            }else{ //End of run due minValue in heap < lastvalue in run
                int otherheap = (currentHeap+1)%2;
                if ((otherheap == 1 && HeapList.get(otherheap).size() < heap2Size)
                        || (otherheap == 0 && HeapList.get(otherheap).size() < heap1Size)
                )
                {
                    HeapList.get(otherheap).add(tmptReadID);
                    tmptReadID = "";
                }else {
                    while (HeapList.get(currentHeap).isEmpty() != true)
                        System.out.print(" " + HeapList.get(currentHeap).poll() + " ");
                    runTime++;
                    System.out.println();
                    System.out.print("Run" + runTime + " :");
                    currentHeap = otherheap;
                    isNewRun = true;
                }
            }

            if (totalID == 0){
                while (HeapList.get(currentHeap).isEmpty() == false) {
                    if (isNewRun)
                    {
                        System.out.println();
                        System.out.print("Run" + runTime + " :");
                    }
                    System.out.print(" " + HeapList.get(currentHeap).poll());
                }

                int otherheap = (currentHeap+1)%2;
                if (HeapList.get(otherheap).isEmpty() == false) {
                    runTime++;
                    System.out.println();
                    System.out.print("Run" + runTime + " :");
                    while (HeapList.get(otherheap).isEmpty() == false) {
                        System.out.print(" " + HeapList.get(otherheap).poll());
                    }
                }
                break;
            }
        }
        brID.close();
    }

    public static void oneHeap() throws IOException {

        BufferedReader brID = null;
        brID = new BufferedReader(new FileReader("input/ID.txt"));
        PriorityQueue<String> minHeap1 = new PriorityQueue<String>();
        String lastValue = "";
        Boolean isNewRun = true;
        String tmptReadID = "";
        int runTime = 1;
        //System.out.print("Run1 :");
        while(true) {
            if (isNewRun) {
                //System.out.println("tmptReadID :" + tmptReadID);
                if (tmptReadID.isEmpty() == false) minHeap1.add(tmptReadID);
                //
                while (minHeap1.size() < Maxheap) {
                    if ((tmptReadID = brID.readLine()) != null) {
                        minHeap1.add(tmptReadID);
                    }else break;
                }
                lastValue = minHeap1.poll();
                System.out.print("Run" + runTime +" : " + lastValue + " ");
                isNewRun = false;
            }
            while ((tmptReadID = brID.readLine()) != null) {
                //System.out.println("tmptReadID Read :" + tmptReadID);
                if (lastValue.compareTo(tmptReadID) < 0) {
                    minHeap1.add(tmptReadID);
                    lastValue = minHeap1.poll();
                    System.out.print(" " + lastValue);
                } else { //End of run due minValue in heap < lastvalue in run
                    //System.out.println("tmptReadID Last :" + tmptReadID);
                    while (minHeap1.isEmpty() != true)
                        System.out.print(" " + minHeap1.poll());
                    runTime++;
                    System.out.println();
                    isNewRun = true;
                    break;
                }
            }

            if (tmptReadID == null)
            {
                if (minHeap1.isEmpty())
                    break;
                else
                    while (minHeap1.isEmpty() != true){
                        if (isNewRun) {
                            runTime++;
                            System.out.print("Run" + runTime + " :" + lastValue);
                        }
                        tmptReadID = minHeap1.poll();
                        if (lastValue.compareTo(tmptReadID) < 0){
                            lastValue = tmptReadID;
                            System.out.print(" " + lastValue);
                        }else{
                            isNewRun = true;
                        }
                    }
                break;
            }
        }
        brID.close();
    }
    public static void ParseFile()
    {
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("input/Inputnum.csv"));
            PrintWriter writer = new PrintWriter("input/ID.txt", "UTF-8");

            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(COMMA_DELIMITER);
                String ID = "";
                for (String e: parts
                ) {
                    //System.out.print(e + " ");
                    ID = ID + e.substring(0,2);
                }
                writer.println(ID);
                totalID++;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
    }
}

