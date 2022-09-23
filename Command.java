import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Command {

    public void register(String name, String password, String product, String date_of_birth) throws FileNotFoundException, IOException {
        File f = new File("participants.txt");
        BufferedReader br = new BufferedReader(new FileReader(f));
        Object[] lines = br.lines().toArray();
         
        int exist = 0;
        int id = 0;
        for(int i=0; i<lines.length; i++){
            String line = lines[i].toString().trim();
            String[] column =  line.split(",");
            id = Integer.parseInt(column[0]) +1;

            if(name.equalsIgnoreCase(column[1])){
                exist = 1;
                System.out.println("----------------------------------");
                System.out.println("User alredy Exist, Try other names");
                System.out.println("----------------------------------");
            }
        }
        br.close();

        if(exist == 0){

            BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));
            PrintWriter pw = new PrintWriter(bw);
            pw.println(id+","+name+","+password+","+product+","+date_of_birth);
            pw.flush();
            pw.close();
            bw.close();

            System.out.println("Registration successfull");
            System.out.println("Here are your details");
            System.out.println(" ");
            System.out.println("------------------------------------------------");
            System.out.println("[id | name | password | product | date_of_birth]");
            System.out.println("------------------------------------------------");
            System.out.println("[ "+id+" | "+name+" |   "+password+" |     "+product+" |        "+date_of_birth+" ] ");
            System.out.println("---------------------------------------------------");
            System.out.println(" ");
        }
    }

    public void updateDesc(String product_name, String description) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Username Please");
        String uname = sc.nextLine();
        System.out.println("--------------------------");
        System.out.println(" ");
        System.out.println("Enter The total number of Products");
        int total = sc.nextInt();
        System.out.println("--------------------------");

        File k = new File("participants.txt");
        BufferedReader kr = new BufferedReader(new FileReader(k));
        Object[] obj = kr.lines().toArray();

        int available = 0;
        int track = 0;
        int pro_exist = 0;
        int proid = 0;

        for(int i=0; i<obj.length; i++){
            String single = obj[i].toString().trim();
            String[] row =  single.split(",");
            proid = Integer.parseInt(row[0]) + 1;

            if(product_name.equalsIgnoreCase(row[3]) && uname.equalsIgnoreCase(row[1])) {
                String user = row[1];
                File pr = new File("products.txt");

                BufferedReader ur = new BufferedReader(new FileReader(pr));
                Object[] ob = ur.lines().toArray();

                for(int j=0; j<ob.length; j++){
                    String li = ob[j].toString().trim();
                    String[] order =  li.split(",");
                    proid = Integer.parseInt(order[0]) + 1;

                    if(product_name.equalsIgnoreCase(order[2])){
                        pro_exist = 1;
                        track = 1;
                        System.out.println("------------------------------------------------------------");
                        System.out.println("Product already Exist, You can only post one kind of product");
                        System.out.println("------------------------------------------------------------");
                    }

                }
                ur.close();

                if(pro_exist == 0){

                    BufferedWriter wr = new BufferedWriter(new FileWriter(pr,true));
                    PrintWriter pw = new PrintWriter(wr);
                    pw.println(proid+","+user+","+product_name+","+total+","+description);
                    pw.flush();
                    pw.close();
        
                    available = 1;
                    System.out.println("---------------------------------------------------------");
                    System.out.println("Product Description Updated Successfully in products.txt");
                    System.out.println("---------------------------------------------------------"); 
                    System.out.println("------------------------------------------------");
                    System.out.println("[id | name | product | description ]");
                    System.out.println("------------------------------------------------");
                    System.out.println("[ "+proid+" | "+user+" |   "+product_name+" |     "+description+" ]");
                    System.out.println("---------------------------------------------------");
                    System.out.println(" ");
                }    
            }
        }
        kr.close();
        sc.close();

        if(available==0 && track==0){
            System.out.println("-------------------------------------------------");
            System.out.println("Either Product Doesn't Exist Or Username Is Wrong");
            System.out.println("-------------------------------------------------");
        }
        
    }

    public void checkRank(String part) throws FileNotFoundException, IOException {
        File per = new File("performance.txt");
        BufferedReader read = new BufferedReader(new FileReader(per));
        Object[] list = read.lines().toArray();

        int avail = 0;
        int counter = 0;
        for(int count=0; count<list.length;count++){
            counter++;
            //System.out.println(counter);
        }
        for(int i=0; i<list.length; i++){
            String perf = list[i].toString().trim();
            String[] ranks =  perf.split(",");
            
            if(part.equalsIgnoreCase(ranks[1])){
                String uid = ranks[0];
                int position = Integer.parseInt(ranks[2]);
                int points = Integer.parseInt(ranks[3]);
                int goods = Integer.parseInt(ranks[4]);
                int returns = Integer.parseInt(ranks[5]);
                avail = 1;
                System.out.println("---------------------------------------------------------------------");
                System.out.println("[ UserID | UserName | Rank | Points | Products Left | Return Buyers ]");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("[ "+uid+"         "+part+"       "+position+"/"+counter+"       "+points+"          "+goods+"              "+returns+" ]");
                System.out.println("-----------------------------------------------------------------------");
            }
        }
        read.close();

        if(avail==0){
            System.out.println("-------------------------------------------------------------");
            System.out.println("Sorry, No User Exist As "+part+" In Our Records");
            System.out.println("Enter Proper Username! or Records Not Sent From Server As Yet");
            System.out.println("-------------------------------------------------------------");
        }
    }


    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file1 = new File("participants.txt");
        File file2 = new File("products.txt");
        File file3 = new File("performance.txt");

        if(file1.exists()){
            System.out.println(" ");
            System.out.println(("File Is Existing as participants.txt"));
            System.out.println("---------------------------------------");
        }else {
            file1.createNewFile();
            System.out.println(" ");
            System.out.println("New File Is Created Successfully as participant.txt");
            System.out.println("------------------------------------------------------");
        }

        if(file2.exists()){
            System.out.println(" ");
            System.out.println(("File Is Existing as products.txt"));
            System.out.println("-----------------------------------");
        }else {
            file2.createNewFile();
            System.out.println(" ");
            System.out.println("New File Is Created Successfully as products.txt");
            System.out.println("-------------------------------------------------");
        }

        if(file3.exists()){
            System.out.println(" ");
            System.out.println(("File Is Existing as performance.txt"));
            System.out.println("--------------------------------------");
        }else {
            file3.createNewFile();
            System.out.println(" ");
            System.out.println("New File Is Created Successfully as performance.txt");
            System.out.println("-----------------------------------------------------");
        }

        

        Command arg = new Command();

        if(args.length != 0){
            if(args.length == 5 && args[0].equals("Register")){
                String name = args[1];
                String password = args[2];
                String product = args[3];
                String date_of_birth = args[4];

                arg.register(name, password, product, date_of_birth);


            }else if(args.length == 3 && args[0].equals("Post_product")){
                String product_name = args[1];
                String description = args[2];
                arg.updateDesc(product_name, description);

            }else if(args.length == 2 && args[0].equals("Performance")){
                String uname = args[1];
                arg.checkRank(uname);

            }else {
                System.out.println("Invalid Commands!");
                System.out.println("------------------------------------------------------------");
                System.out.println("Please follow this menu in trying work with the command-line");
                System.out.println(" ");
                System.out.println("Register <name> <password> <product> <date_of_birth>");
                System.out.println("Post_product <product_name>  <description> <name>");
                System.out.println("Performance <name>");
                System.out.println("-------------------------------------------------------------");
            }

        }else{

        }
    }
    
}