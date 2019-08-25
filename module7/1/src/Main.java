/*1. Реализовать сортировку сотрудников по заработной плате и алфавиту одновременно.*/


import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class Main {
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args) {
        ArrayList<Employee> staff = loadStaffFromFile();

        /*Реализовать сортировку сотрудников по заработной плате и алфавиту одновременно.*/
        staff.sort((e1, e2) -> (e1.getSalary().equals(e2.getSalary())) ?
                e1.getName().compareTo(e2.getName()) : e1.getSalary().compareTo(e2.getSalary()));
        staff.forEach(System.out::println);
    }

    private static ArrayList<Employee> loadStaffFromFile() {
        ArrayList<Employee> staff = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for (String line : lines) {
                String[] fragments = line.split("\t");
                if (fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                        fragments[0],
                        Integer.parseInt(fragments[1]),
                        (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}
/*

         */
        /*В проекте с сотрудниками с помощью Stream API рассчитать максимальную зарплату тех, кто пришёл в 2017 году.*//*

        System.out.print("Сотрудник с самой большой зарплатой, принятый в 2017 году -  ");
                staff.stream().filter(e1 -> e1.getWorkStart().getYear() == 117)
                .max(Comparator.comparing(Employee::getSalary))
                .ifPresent(o1 -> System.out.println(o1.getName() + "\nЕго зарплата: " + o1.getSalary() + " руб."));
        */
/*заменила бы тут Date на LocalDate и все было бы хорошо, но... то, что дата приема на работу
        у сотрудника именно Date - прописано в задании... И я не смогла придумать как сделать так,
        чтобы не было предупреждений про "depricated", зачеркнутого .getYear() и "магии" у числа 117 (которое 2017 - 1900)
        Можно ли было здесь как-то по-другому сделать? */
