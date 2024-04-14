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
  
    System.out.println("The highest net salary = " + list.highestNetSalary());
  
    list.deleteEmployeeByName("Rim Oz");
    list.deleteEmployeeByName("Nadia Jones");
  
    System.out.println( list.searchByName("Gary D. Richardson") );
  
    list.printAllEmployees();
  
  
  }
}//end of DriverClass
  
  
  
  
  
  //____________________________
class LinkedList{
  Node company; 
  int size = 0;

  ArrayList <Double> netSalArr = new ArrayList <Double>();
  public LinkedList () {
    company = null;
  }
  
  public void printAllEmployees () {
    Node travelNode = company;
    while (travelNode != null) {
      System.out.print(travelNode.getE());

      if(travelNode.getBelow() != null){
        Node travelBelowNode = travelNode.getBelow();
        while (travelBelowNode != null ) {
          System.out.print(travelBelowNode.getE());
          travelBelowNode = travelBelowNode.getBelow();
        }
        travelNode = travelNode.getNext();
      }

      else {
        travelNode = travelNode.getNext();
      }
    }
  }
  
  
  public void addNewEmployee (Employee e) {
    netSalArr.add(e.getNetSalary());
    Node temp = new Node();
    temp.setE(e);
    if (company == null){
      company = temp;
      company.setNext(null);
      size++;
      return;
    }
    
    Node traverseListNode = company;
    Node var = company;

    //if ID is found, the node gets added to below branch
    //otherwise it gets added to the next branch
    Node foundNode = searchByID(traverseListNode, e.getId());
    
    if (foundNode == null){
      while (var.getNext() != null) {
        var = var.getNext();
      }
      var.setNext(temp);
      var.getNext().setNext(null);
      size++;
    }
    else{
      while (foundNode.getBelow()!=null) {
        foundNode = foundNode.getBelow();
      }
      foundNode.setBelow(temp);
      foundNode.getBelow().setBelow(null);
      size++;
    }
  }


  public boolean searchByName (String name) {
    Node node = company;
    Employee temp = new Employee();
    int empID = temp.calcID(name);
    Node foundNode = searchByID(node, empID);

    if (foundNode == null){
      return false;
    }
    
    else{
      if(foundNode.getE().getName() == name){
        return true;
      }
      else{
        while(foundNode.getBelow()!=null){
          if(foundNode.getE().getName() == name){
            return true;
          }
          else{
            foundNode = foundNode.getBelow();
          }
        }
        return false;
      }
    }
  }
  
  public double highestNetSalary () {
    if (company == null){
      return 0.00;
    }
    else{
      double netSal = company.getE().getNetSalary();
      for(int i = 0; i<size; i++){
        if(netSal<netSalArr.get(i)){
          netSal = netSalArr.get(i);
        }
      }
      return netSal;
    }
  }

  public void deleteEmployeeByName (String name) {
    Employee temp = new Employee();
    int findID = temp.calcID(name);
    Node delNode = searchByID(company, findID);
    if (delNode == null){
      System.out.println(name+ " name not found.");
      return;
    }
    else{

      while(delNode.getBelow() != null && delNode.getE().getName() != name){
        delNode = delNode.getBelow();
      }

      Node delNext = returnPrevNode(company, delNode, name);

      //delnode is a tail
      if(delNode.getNext() == null && delNode.getBelow() == null){
        delNext.setNext(null);
        delNext.setBelow(null);
      }

      //delnode is in the middle with no bottom
      else if (delNode.getNext() != null && delNext.getBelow() == null){
        delNext.setNext(delNode.getNext());
      }

      //delnode is in the middle of a below list
      else if (delNode.getNext() == null && delNode.getBelow() != null){
        delNext.setBelow(delNode.getBelow());
      }

      //delnode in middle and has bottom
      else if (delNode.getNext()!= null && delNode.getBelow() != null){
        Node tempNode = delNode.getBelow();
        tempNode.setNext(delNode.getNext());
        delNext.setNext(tempNode);
        
      }
    }

  }

  //searches through a linked list
  //to find an ID
  private Node searchByID(Node head, int ID){
    Node temp = head;

    while(temp != null && temp.getE().getId() != ID){
      temp = temp.getNext();
    }
   return temp;

  }


  private Node returnPrevNode(Node head, Node node, String name){
    if(head == null || node == null){
      return null;
    }
    else{
      
      if(node.getBelow()!= null && node.getBelow().getE().getName() == name){
        return node;
      }

      Node temp = head;
      while (temp.getNext().getE().getId() != node.getE().getId()) {
        temp = temp.getNext();
      }
      if(temp.getNext().getE().getName() == name){
        return temp;
        
      }
      else{
        while (node.getBelow() != null && node.getE().getName() != name ){
          node = node.getBelow();
        }
        temp = temp.getNext();
        while(temp.getBelow().getE().getName() != node.getE().getName()){
          temp = temp.getBelow();
        }
        return temp;
      }
    }
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
  public int calcID(String name){
    int rez = 0;
    String str = "0, 1";
    name = name.toUpperCase();
    int len = name.length();
    if(name.charAt(len-1) == str.charAt(2)){
      name = name.trim();
      len = len-1;
    }
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

  public Employee(){}

/*   public void setNetSalary(double netSalary) {
    this.netSalary = netSalary;
  } */

  //----------------------------------------------
  //----------------------------------------------
  //----------------------------------------------
  
  @Override
  public String toString () {
    System.out.println("["+id+", "+name+", "+netSalary+"]");
    return"";
  }
  //...


}
  
//______________________________

class Node {
  private Employee e;     //Keep these fields private!
  private Node next = null;
  private Node below = null;
  
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
  