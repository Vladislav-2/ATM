import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Tools {

    public static void load(){
        Scanner s = new Scanner("users.txt").useDelimiter(" ");
        while (s.hasNext()){
            String number = "";
            int PIN = 0;
            double balance = -1;
            boolean isBlocked = false;
            for (int i = 0; i < 4; i++){
                if (i == 0 && !s.next().equals("")){
                    number = s.next();
                } else if (i == 1){
                    PIN = s.nextInt();
                } else if (i == 2){
                    balance = s.nextDouble();
                } else if (i == 3){
                    isBlocked = s.nextBoolean();
                }
            }
            if (number != "" && PIN != 0 && balance != -1){
                DataBase.users.add(new User(number, PIN, balance, isBlocked));
            }
        }
        Scanner m = new Scanner("money.txt");
        DataBase.money = m.nextInt();
    }

    public static void save(){
        try {
            FileWriter fileWriter = new FileWriter("users.txt", true);
            FileWriter fileWriterMoney = new FileWriter("money.txt", true);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedWriter bufferedWriterMoney = new BufferedWriter(fileWriterMoney);
            for (int i = 0; i < DataBase.users.size(); i++) {
                bufferedWriter.write(DataBase.users.get(i).getNumberOfCard() + " ");
                bufferedWriter.write(DataBase.users.get(i).getPIN() + " ");
                bufferedWriter.write(DataBase.users.get(i).getbalance() + " ");
                bufferedWriter.write(DataBase.users.get(i).isBlocked() + " ");
                bufferedWriterMoney.write(DataBase.money);
            }
            bufferedWriter.close();
            bufferedWriterMoney.close();
        } catch(IOException e) {
            System.out.println("COULD NOT LOG!!");
        }
    }

    public static void clean(){
        File users = new File("users.txt");
        File money = new File("money.txt");
        users.delete();
        money.delete();
    }
}
