import java.util.*;

public class DriverClass {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();
  
    list.addNewEmployee(new Employee ("Kim Oz", 1235.5, 3));
    list.addNewEmployee(new Employee ("Rim Oz", 8235.5, 1));
    list.addNewEmployee(new Employee ("Dane Ali ", 3235.5, 0));
    list.addNewEmployee(new Employee ("Aidan Jones ", 2035.5, 2));
    list.addNewEmployee(new Employee ("Nadia Jones", 5035.5, 3));
    list.addNewEmployee(new Employee ("Ed Renu", 6035, 2));
    list.addNewEmployee(new Employee ("Naadi Jones", 36035.75, 5));
  
    //The TAs may use less or more names. 
  
    list.printAllEmployees();
  
    System.out.println("The highest net salary = " list.highestNetSalary());
  
    list.deleteEmployeeByName("Rim Oz");
    list.deleteEmployeeByName("Nadia Jones");
  
    System.out.println( list.searchByName("Gary D. Richardson") );
  
    list.printAllEmployees();
  
  
  }
}//end of DriverClass
  
  
  
  
  
  //____________________________
class LinkedList{
  Node company; 
  public LinkedList () {
    company = null;
  }
  public void printAllEmployees () {
  //...
  }
  
  
  public void addNewEmployee (Employee e) {
    Node temp = new Node();
    if (company == null){
      temp.setE(e);
      company.setNext(temp);
    }
  }
  public boolean searchByName (String name) {
  //...
  }
  public double highestNetSalary () {
  //...
  }
  public void deleteEmployeeByName (String name) {
  //...
  }
}
  //______________________________
  
  
  
  
  
  
  
  
class Employee {
  private String name; //Keep these fields private!
  private int id;
  private int numberOfDependent;
  private double salary;
  private double netSalary;
  
  //----------------------------------------------
  //----------------------------------------------
  //----------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getNumberOfDependent() {
    return numberOfDependent;
  }

  public void setNumberOfDependent(int numberOfDependent) {
    this.numberOfDependent = numberOfDependent;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public double getNetSalary() {
    return netSalary;
  }

  //takes every letter in a string and capitalizes it,
  //then adds the values to find the ID of the Employee
  private int calcID(String name){
    int rez = 0;
    name = name.toUpperCase();
    int len = name.length();
    
    for (int i = 0; i<len; i++){
      rez += name.charAt(i);
    }

    return rez;
  }
  public Employee (String name, double salary, int numberOfDependent){
    this.name = name;
    this.salary = salary;
    this.numberOfDependent = numberOfDependent;
    this.netSalary = salary*0.91+(numberOfDependent*0.01*salary);
    this.id = calcID(this.name);
  }

/*   public void setNetSalary(double netSalary) {
    this.netSalary = netSalary;
  } */

  //----------------------------------------------
  //----------------------------------------------
  //----------------------------------------------
  
  @Override
  public String toString () {
    return "...";
  }
  //...


}
  
//______________________________

class Node {
  private Employee e;     //Keep these fields private!
  private Node next;
  private Node below;
  
  public Employee getE() {
    return e;
  }
  public void setE(Employee e) {
    this.e = e;
  }
  public Node getNext() {
    return next;
  }
  public void setNext(Node next) {
    this.next = next;
  }
  public Node getBelow() {
    return below;
  }
  public void setBelow(Node below) {
    this.below = below;
  }
  
  
  
}
  