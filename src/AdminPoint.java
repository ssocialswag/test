import java.util.*;
import java.io.*;

class Employee implements Serializable{
     int empno;
     String ename;

     int price;

     int date;

     String type;

     String workers;

    Employee(int empno, String ename, int price,int date, String type, String workers){
        this.empno = empno;
        this.ename = ename;
        this.price = price;
        this.date = date;
        this.type= type;
        this.workers= workers;

    }
    public String toString(){
        return empno+" "+ename+" "+price+" "+date+" "+type+" "+workers;
    }
}
class EmployeeDemo{
    public static void main(String[] args) throws Exception{
        int choice = -1;
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        File file = new File("employee.txt");
        ArrayList<Employee> al = new ArrayList<Employee>();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ListIterator li = null;
        if(file.isFile()){
            ois = new ObjectInputStream(new FileInputStream(file));
            al = (ArrayList<Employee>)ois.readObject();
            ois.close();
        }
        do{
            System.out.println("1.INSERT");
            System.out.println("2.DISPLAY");
            System.out.println("3.SEARCH");
            System.out.println("4.DELETE");
            System.out.println("5.UPDATE");
            System.out.println("6.SORT By EmpNo - On Screen");
            System.out.println("0.EXIT");
            System.out.print("Enter Your Choice : ");
            choice = s.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter how many contacts you want : ");
                    int n = s.nextInt();
                    for(int i=0;i<n;i++){
                        System.out.print("Enter Employee : ");
                        int empno = s.nextInt();

                        System.out.print("Enter Employee Name: ");
                        String ename = s1.nextLine();

                        System.out.print("Enter Rent price : ");
                        int price = s.nextInt();

                        System.out.print("Enter Days for rent : ");
                        int date = s.nextInt();

                        System.out.print("Enter Type : ");
                        String type = s1.nextLine();

                        System.out.print("Enter Workers : ");
                        String workers = s1.nextLine();

                        al.add(new Employee(empno,ename,price,date,type,workers));
                    }
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(al);
                    oos.close();
                    break;
                case 2:
                    if(file.isFile()){
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<Employee>)ois.readObject();
                        ois.close();

                        System.out.println("-------------------------------------");
                        li = al.listIterator();
                        while(li.hasNext())
                            System.out.println(li.next());
                        System.out.println("-------------------------------------");
                    }else{
                        System.out.println("File not Exists....!");
                    }
                    break;
                case 3:
                    if(file.isFile()){
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<Employee>)ois.readObject();
                        ois.close();

                        boolean found = false;
                        System.out.println("Enter empno to Search : ");
                        int empno = s.nextInt();
                        System.out.println("-------------------------------------");
                        li = al.listIterator();
                        while(li.hasNext()){
                            Employee e = (Employee)li.next();
                            if(e.empno == empno){
                                System.out.println(e);
                                found = true;
                            }
                        }
                        if(!found)
                            System.out.println("Record Not Found...!");
                        System.out.println("-------------------------------------");
                    }else{
                        System.out.println("File not Exists....!");
                    }
                    break;
                case 4:
                    if(file.isFile()){
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<Employee>)ois.readObject();
                        ois.close();

                        boolean found = false;
                        System.out.print("Enter empno to Delete : ");
                        int empno = s.nextInt();
                        System.out.println("-------------------------------------");
                        li = al.listIterator();
                        while(li.hasNext()){
                            Employee e = (Employee)li.next();
                            if(e.empno == empno){
                                li.remove();
                                found = true;
                            }
                        }
                        if(found){
                            oos = new ObjectOutputStream(new FileOutputStream(file));
                            oos.writeObject(al);
                            oos.close();
                            System.out.println("Record Deleted Successfully....!");
                        }
                        else{
                            System.out.println("Record Not Found...!");
                        }
                        System.out.println("-------------------------------------");
                    }else{
                        System.out.println("File not Exists....!");
                    }
                    break;
                case 5:
                    if(file.isFile()){
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<Employee>)ois.readObject();
                        ois.close();

                        boolean found = false;
                        System.out.print("Enter empno to Update : ");
                        int empno = s.nextInt();
                        System.out.println("-------------------------------------");
                        li = al.listIterator();
                        while(li.hasNext()){
                            Employee e = (Employee)li.next();
                            if(e.empno == empno){
                                System.out.print("Enter New EmpName : ");
                                String ename = s1.nextLine();

                                System.out.print("Enter Price : ");
                                int price = s.nextInt();

                                System.out.print("Enter Date : ");
                                int date = s.nextInt();

                                System.out.print("Enter Type : ");
                                String type = s1.nextLine();

                                System.out.print("Enter Workers : ");
                                String workers = s1.nextLine();

                                li.set(new Employee(empno,ename,price,date,type, workers));
                                found = true;
                            }
                        }
                        if(found){
                            oos = new ObjectOutputStream(new FileOutputStream(file));
                            oos.writeObject(al);
                            oos.close();
                            System.out.println("Record Updated Successfully....!");
                        }
                        else{
                            System.out.println("Record Not Found...!");
                        }
                        System.out.println("-------------------------------------");
                    }else{
                        System.out.println("File not Exists....!");
                    }
                    break;
                case 6:
                    if(file.isFile()){
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<Employee>)ois.readObject();
                        ois.close();

                        Collections.sort(al, new Comparator<Employee>(){
                            public int compare(Employee e1, Employee e2){
                                return e1.empno - e2.empno;
                            }
                        });

                        System.out.println("-------------------------------------");
                        li = al.listIterator();
                        while(li.hasNext())
                            System.out.println(li.next());
                        System.out.println("-------------------------------------");
                    }else{
                        System.out.println("File not Exists....!");
                    }
                    break;

            }
        }while(choice!=0);
    }
}
