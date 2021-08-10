package com.company;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Assignment1 - Complexity and Compute");

// read ID output file
        String fileName= "input\\ID.txt";
        File file= new File(fileName);
        // this gives you a 2-dimensional array of strings
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;
        try{
            inputStream = new Scanner(file);
            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");
                // this adds the currently parsed line to the 2-dimensional string array
                lines.add(Arrays.asList(values));
            }
            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // try to show original array
//        int lineNo = 1;
//        for(List<String> line: lines) {
//                System.out.println(line);
//            }
//            lineNo++;
        String[] array = new String[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            array[i] = String.valueOf(lines.get(i));
            System.out.println(array[i]);
        }
        int n = array.length;
        sort(array, n);
        }
    static int x = -1;
    static String [] heap = new String[1000];
    static void heapForm(String k)
    {
        x++;
        heap[x] = k;
        int child = x;
        String tmp;
        int index = x / 2;
        // Iterative heapiFy
        while (index >= 0)
        {
            // Just swapping if the element
            // is smaller than already
            // stored element
            if (heap[index].compareTo(heap[child]) > 0)
            {
                // Swapping the current index
                // with its child
                tmp = heap[index];
                heap[index] = heap[child];
                heap[child] = tmp;
                child = index;
                // Moving upward in the
                // heap
                index = index / 2;
            }
            else
            {
                break;
            }
        }
    }
    static void sort(String k[], int n)
    {
        // To heapiFy
        for (int i = 0; i < n; i++)
        {
            heapForm(k[i]);
        }
        // Calling heap sort function
        heapSort();
    }
    // Defining heap sort
    static void heapSort()
    {
        int left1, right1;
        while (x >= 0)
        {
            String k;
            k = heap[0];
            // Taking output of
            // the minimum element
            System.out.print(k + " ");
            // Set first element
            // as a last one
            heap[0] = heap[x];
            // Decrement of the
            // size of the string
            x = x - 1;
            String tmp;
            int index = 0;
            int length = x;
            // Initilizing the left
            // and right index
            left1 = 1;
            right1 = left1 + 1;
            while (left1 <= length)
            {
                // Process of heap sort
                // If root element is
                // minimum than its both
                // of the child then break
                if (heap[index].compareTo(heap[left1]) <= 0 &&
                        heap[index].compareTo(heap[right1]) <= 0)
                {
                    break;
                }

                // Otherwise checking that
                // the child which one is
                // smaller, swap them with
                // parent element
                else
                {
                    // Swapping
                    if (heap[left1].compareTo(heap[right1])< 0)
                    {
                        tmp = heap[index];
                        heap[index] = heap[left1];
                        heap[left1] = tmp;
                        index = left1;
                    }
                    else
                    {
                        tmp = heap[index];
                        heap[index] = heap[right1];
                        heap[right1] = tmp;
                        index = right1;
                    }
                }
                // Changing the left index
                // and right index
                left1 = 2 * left1;
                right1 = left1 + 1;
            }
        }
    }

}