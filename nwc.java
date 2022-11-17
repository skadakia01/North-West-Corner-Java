import java.io.*;
import java.util.*;

public class nwc {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i=0,j=0,ele=0,k=0;
        System.out.println("Enter number of rows: ");
        int rows = scan.nextInt();

        System.out.println("Enter number of columns: ");
        int columns = scan.nextInt();

        int[][] costMatrix = new int[50][50];
        System.out.println("Enter matrix elements: ");
        for(i=0; i<rows;i++){
            for(j=0;j<columns;j++){
                ele = scan.nextInt();
                costMatrix[i][j] = ele;
            }
        }
        int supply = 0, demand = 0;
        int[] demandArray = new int[50];
        int[] supplyArray = new int[50];
        System.out.println("Enter demand: ");

        for(i=0;i<columns;i++){
            demand = scan.nextInt();
            demandArray[i] = demand;
        }
        System.out.println("Enter supply: ");
        for(i=0;i<rows;i++){
            supply = scan.nextInt();
            supplyArray[i] = supply;
        }
        int sumDemand = 0, sumSupply = 0;
        for(i=0;i<columns;i++){
            sumDemand += demandArray[i];
        }

        for(j=0;j<rows;j++){
            sumSupply += supplyArray[j];
        }

        System.out.println(sumDemand);
        System.out.println(sumSupply);

        if(sumSupply>sumDemand){
            columns = columns+1;
            supplyArray[rows] = (sumSupply-sumDemand);

            for(i=0; i<rows;i++){
                costMatrix[i][columns] = 0;
            }
        }
        else if(sumDemand>sumSupply){
            rows = rows+1;
            demandArray[columns] = (sumDemand-sumSupply);
            for(j=0;j<columns;j++){
                costMatrix[rows][j] = 0;
            }
        }

        System.out.println("matrix elements: ");
        for(i=0; i<rows;i++){
            for(j=0;j<columns;j++){
                System.out.print(costMatrix[i][j]+",");
            }
            System.out.println("");
        }
        
        // Setting Static Question
        // int[][] costMatrix = { { 8, 5, 6 }, // {~8,~5,~6}
        //         { 15, 10, 12 }, // {~15,~10,~12}
        //         { 3, 9, 10 } }; // {~3, ~9, 10}

        // int demandArray[] = { 150, 80, 50 }; // {30,80,50}, {80,50} //{30,50} {50}
        // int supplyArray[] = { 120, 80, 80 }; // {80,80}, {50,80} // {80} {50}
        // int i = 0, j = 0, k = 0;
        int[] costAllocations = new int[50];
        i = 0; j = 0; k = 0;
        while (i != columns && j != rows) {
            if (demandArray[i] > supplyArray[j]) {
                System.out.println("Current NWC element=" + costMatrix[j][i] + ":" + supplyArray[j]);
                costAllocations[k] = supplyArray[j] * costMatrix[j][i];
                demandArray[i] = demandArray[i] - supplyArray[j];
                k++; 
                j++; 
            }

            else if (demandArray[i] < supplyArray[j]) // if supplyArray is more than multiply cost to demandArray value
            {
                System.out.println("Current NWC element=" + costMatrix[j][i] + ":" + demandArray[i]);
                costAllocations[k] = demandArray[i] * costMatrix[j][i];
                supplyArray[j] = supplyArray[j] - demandArray[i];
                k++;
                i++;
            }

            else {
                System.out.println("Current NWC element=" + costMatrix[j][i] + ":" + demandArray[i]);
                costAllocations[k] = demandArray[i] * costMatrix[j][i];
                supplyArray[j] = demandArray[i] = 0;
                k++;
                i++;
                j++;
            }

        }
        ORtools oRtools = new ORtools(costAllocations, costAllocations.length);
        System.out.println("Total Cost is:" + oRtools.CalcSum());
    }
}

class ORtools {
    int sum = 0, size;
    int[] arr;

    public ORtools(int[] arr, int size) {
        this.arr = arr;
        this.size = size;
    }

    // int i = 0;
    public int CalcSum() {
        for (int x : arr)
            sum = sum + x;
        // for (i = 0; i<=50 ; i++){
        //     sum = sum + arr[i];
        // }
        return sum;
    }
}
