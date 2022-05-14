package algorithms;

import java.util.*;

/*
 http://acm.timus.ru/problem.aspx?space=1&num=1072
*/

public class Routing {

  private List<Comp> computers;
  private Comp startComp;
  private Comp endComp;

  Routing(List<Comp> computers, int startComp, int endComp) {
    this.computers = computers;
    this.startComp = this.computers.get(startComp - 1);
    this.endComp = this.computers.get(endComp - 1);
  }

  static class Comp {
    private int numberComp;
    private List<Interface> interfaces;

    Comp(int numberComp, List<Interface> interfaces) {
      this.numberComp = numberComp;
      this.interfaces = interfaces;
    }

    @Override
    public String toString() {
      return "Comp{" +
              "numberComp=" + numberComp +
              ", interfaces=" + interfaces +
              '}';
    }
  }

  static class Interface {
    private String ip;
    private String mask;
    private List<Integer> subnet;

    public static Interface create(String ip, String mask) throws IllegalMaskSubnetException {
      isMask(mask);
      List<Integer> subnet = getSubnet(getSplitAdress(ip), getSplitAdress(mask));
      Interface inter = new Interface(subnet);
      inter.setIp(ip);
      inter.setMask(mask);
      return inter;
    }

    Interface(List<Integer> subnet) {
      this.subnet = subnet;
    }

    private void setIp(String ip) {
      this.ip = ip;
    }

    private void setMask(String mask) {
      this.mask = mask;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Interface that = (Interface) o;

      return subnet.equals(that.subnet);

    }

    @Override
    public int hashCode() {
      return subnet.hashCode();
    }

    private static List<Integer> getSplitAdress(String adress) {
      List<Integer> list = new ArrayList<>(4);
      String[] str = adress.split("\\.");
      for (String i : str) {
        list.add(Integer.valueOf(i));
      }
      return list;
    }

    private static List<Integer> getSubnet(List<Integer> ip, List<Integer> mask) {
      List<Integer> subnets = new ArrayList<>(4);
      if (ip.size() != mask.size() && ip.size() == 4) {
        throw new IndexOutOfBoundsException("Неверна задана сеть:" + ip + ", " + mask);
      }
      for (int i = 0; i < ip.size(); i++) {
        int j = ip.get(i) & mask.get(i);
        subnets.add(j);
      }
      return subnets;
    }

    private static void isMask(String mask) throws IllegalMaskSubnetException {
      List<Integer> list = getSplitAdress(mask);
      StringBuilder binary = new StringBuilder();
      for (int i : list) {
        if (i == 0) {
          binary.append(Integer.toBinaryString(i));
          binary.append("0000000");
        } else {
          binary.append(Integer.toBinaryString(i));
        }
      }
      int countOne = 0;
      int countZero = 0;
      String subStr = null;
      for (int i = 0; i < binary.length(); i++) {
        if (i == 0 && binary.charAt(0) == '0') {
          throw new IllegalMaskSubnetException("Неверна задана маска подсети: ", mask);
        }
        if (binary.charAt(i) == '1') {
          countOne++;
        } else if (binary.charAt(i) == '0') {
          countZero++;
          subStr = binary.substring(i);
          i += subStr.length();
        }
      }

      if (countOne == 32) {
        throw new IllegalMaskSubnetException("Неверна задана маска подсети: ", mask);
      }
      if (subStr != null) {
        if (subStr.split("1").length > 1) {
          throw new IllegalMaskSubnetException("Неверна задана маска подсети: ", mask);
        }
        countZero += subStr.length() - 1;
      }
      if (countOne + countZero != 32) {
        throw new IllegalMaskSubnetException("Неверна задана маска подсети: ", mask);
      }
    }

    @Override
    public String toString() {
      return "Interface{" +
              "ip='" + ip + '\'' +
              ", mask='" + mask + '\'' +
              '}';
    }
  }

  private Map<Comp, Set<Comp>> makeNetwork() throws IllegalMaskSubnetException {
    Map<Comp, Set<Comp>> network = new HashMap<>();
    for (Comp comp : this.computers) {
      Set<Comp> comps = new LinkedHashSet<>();
      for (Comp comp2 : this.computers) {
        if (!comp.equals(comp2)) {
          connectingComps(comp, comp2, comps);
          network.put(comp, comps);
        }
      }
    }
    return network;
  }

  private Set<Comp> connectingComps(Comp comp, Comp comp2, Set<Comp> comps) throws IllegalMaskSubnetException {
    for (Interface inter : comp.interfaces) {
      for (Interface inter2 : comp2.interfaces) {
        if (compatible(inter, inter2)) {
          comps.add(comp2);
          break;
        }
      }
    }
    return comps;
  }

  private boolean compatible(Interface inter, Interface inter2) throws IllegalMaskSubnetException {
    return inter.equals(inter2);
  }

  List<Integer> buildPath() throws IllegalMaskSubnetException {
    Map<Comp, Set<Comp>> network = makeNetwork();
    List<Integer> path = new ArrayList<>();
    path.add(this.startComp.numberComp);

    Map<Comp, Boolean> wasVisited = new HashMap<>();
    for (Comp comp : computers) {
      wasVisited.put(comp, false);
    }

    Stack<Comp> stack = new Stack<>();
    wasVisited.put(this.startComp, true);
    stack.push(this.startComp);
    while (!stack.isEmpty()) {
      Comp comp = getUnvisitedComp(stack.peek(), network, wasVisited);
      if (comp == null) {
        stack.pop();
      } else {
        path.add(comp.numberComp);
        if (comp.equals(this.endComp)) {
          break;
        }
        stack.push(comp);
      }
    }

    if (!path.contains(this.endComp.numberComp)) {
      path = null;
    }
    return path;
  }

  private Comp getUnvisitedComp(Comp comp, Map<Comp, Set<Comp>> network, Map<Comp, Boolean> wasVisited) {
    if (network.get(comp).contains(this.endComp)) {
      for (Comp comp2 : network.get(comp)) {
        if (comp2.equals(this.endComp)) {
          return comp2;
        }
      }
    }

    for (Comp comp2 : network.get(comp)) {
      if (!wasVisited.get(comp2) && comp2.interfaces.size() > 1) {
        wasVisited.put(comp2, true);
        return comp2;
      }
    }
    return null;
  }

  private void printNetwork() {
    for (Comp comp : this.computers) {
      System.out.print("comp: #" + comp.numberComp);
      int a = 0;
      for (Interface i : comp.interfaces) {
        System.out.print("   interface: #" + ++a + "  (ip: " + i.ip + ",   " + "mask: " + i.mask + ")");
      }
      System.out.println();
    }
  }

  private void debugTcpPrint(Map<Comp, Set<Comp>> network) {
    for (Map.Entry<Comp, Set<Comp>> map : network.entrySet()) {
      System.out.println("comp #" + map.getKey().numberComp + "  " + map.getKey().interfaces + ": ");
      for (Comp comp : map.getValue()) {
        System.out.println("          " + "comp #" + comp.numberComp + "     " + comp.interfaces);
      }
      for (int i = 0; i < 55; i++) {
        System.out.print("----");
      }
      System.out.println();
    }
  }

  String printMessage(List<Integer> path) {
    if (path == null) {
      return "No";
    }

    String str = "Yes" + "\n";
    for (int i : path) {
      str += ++i + " ";
    }
    return str;
  }

  static class IllegalMaskSubnetException extends Exception {

    IllegalMaskSubnetException(String message, String mask) {
      super(message + mask);
    }
  }

  public static void main(String[] args) throws IllegalMaskSubnetException {
    Scanner scanner = new Scanner(System.in);
    int amountComps = scanner.nextInt();

    int currentComp = 0;
    List<Comp> computers = new ArrayList<>(amountComps);
    while (currentComp < amountComps) {
      int amountInterface = scanner.nextInt();
      int currentInterface = 0;

      List<Interface> interfaces = new ArrayList<>(amountInterface);
      while (currentInterface < amountInterface) {
        String ip = scanner.next();
        String mask = scanner.next();
        Interface interfaceComp = Interface.create(ip, mask);
        interfaces.add(interfaceComp);
        currentInterface++;
      }
      Comp comp = new Comp(currentComp, interfaces);
      computers.add(comp);
      currentComp++;
    }

    int startComp = scanner.nextInt();
    int endComp = scanner.nextInt();

    Routing routing = new Routing(computers, startComp, endComp);
    List<Integer> path = routing.buildPath();
    String s = routing.printMessage(path);
    System.out.println(s);
  }
}
