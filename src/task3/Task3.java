package task3;

import java.io.*;
import java.util.*;

public class Task3 {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String fileLocation = System.getProperty("user.dir");
        String dataPath = fileLocation + File.separator + "sportscars.dat";
        String resultsPath = fileLocation + File.separator + "results.txt";
        BufferedReader br = new BufferedReader(new FileReader(dataPath));
        
        //GETS THE DAT FILE DATA INTO A STRING
        String mainLine = br.readLine();
        //ARRAY LIST FOR CAR OBJECTS
        ArrayList<Cars> cars = new ArrayList<Cars>();
        //COUNTERS USED TO PARSE INFORMATION
        int firstCounter = 0;
        int lastCounter = 128;
        
        //WHILE LOOP TO GET THE CAR INFORMATION
        while (firstCounter != mainLine.length()) {
            //GETS THE CURRENT CARS INFORMATION
            String currentCar = mainLine.substring(firstCounter, lastCounter);
            
            //FOR EACH CAR, GET THE CAR NAME AND CAR ORIGIN
            String carName = currentCar.substring(0, 64);
            String carOrigin = currentCar.substring(64, 96);

            //MAKES A CAR OBJECT WITH INFORMATION AND ADDS IT TO ARRAY
            cars.add(new Cars(carName, carOrigin));
            
            //INCREASE THE COUNTER BY CAR DATA FIELD LENGTH TO GET NEW CAR
            firstCounter = firstCounter + 128;
            lastCounter = lastCounter + 128;
        }
        
        //ARRAY LIST TO STORE UNIQUE ORIGINS
        ArrayList<String> origins = new ArrayList<>();
        
        //LOOPING THROUGH CAR ARRAY
        for (int i = 0; i < cars.size(); i++) {
            //GET CURRENT CAR'S ORIGINS
            String carOrigin = cars.get(i).carOrigin;
            
            //IF THE CURRENT CARS ORIGIN IS NOT IN THE ORIGINS ARRAY IT IS ADDED
            if (!origins.contains(carOrigin)) {
                origins.add(carOrigin);
            }
        }
        
        //ORIGINS ARRAY SORTED ALPHABETICALLY
        Collections.sort(origins, (String s1, String s2) -> s1.compareTo(s2));
        
        //ARRAY LIST TO SORT CARS FROM EACH ORIGIN
        ArrayList<String> carsInOrigin = new ArrayList<String>();
        int currentCar = 0;
        
        FileWriter fw = new FileWriter(resultsPath);
        PrintWriter pw = new PrintWriter(fw);
        
        //CHECKS EVERY CAR FOR EVERY ORIGIN IF THE ORIGINS MATCH
        //PRINTS ALL THE CARS FROM THAT ORIGIN
        for (int i = 0; i < origins.size(); i++) {
            //GET AN ORIGIN FROM ORIGIN ARRAY
            String currentOrigin = origins.get(i);
            
            //LOOPS THROUGH ALL THE CARS
            while (currentCar < cars.size()) {
                String carName = cars.get(currentCar).carName;
                String carOrigin = cars.get(currentCar).carOrigin;
                
                //IF THE ORIGINS MATCH THEN IT IS ADDED TO AN ARRAY
                if (carOrigin.equalsIgnoreCase(currentOrigin)) {
                    carsInOrigin.add(carName);
                }
                currentCar++;
            }
            
            //ARRAY SORTED ALPHABETICALLY
            Collections.sort(carsInOrigin, (String s1, String s2) -> s1.compareTo(s2));
            
            //ORIGIN AND ALL THE CARS IN THAT ORIGIN ARE PRINTED ON ONE LINE
            System.out.print(currentOrigin + " -> ");
            pw.print(currentOrigin + " -> ");
            System.out.println(carsInOrigin);
            pw.println(carsInOrigin);
            //ARRAY CLEARED
            carsInOrigin.clear();
            //CAR LOOP RESET
            currentCar = 0;
        }
        
        br.close();
        pw.close();
        fw.close();
    }
}

//CARS OBJECT
class Cars {

    public String carName;
    public String carOrigin;

    public Cars(String carName, String carOrigin) {
        this.carName = carName;
        this.carOrigin = carOrigin;
    }
}