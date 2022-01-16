import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        List<Person> list = (List)persons;
        long count = list.stream()
                .filter(a -> a.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних : " + count + "\n");


        List<Person> list1 = (List)persons;
        List<String> list2 = list1.stream()
                .filter(s -> s.getSex() == Sex.MAN)
                .filter(a -> a.getAge() >= 18)
                .filter(a -> a.getAge() < 27)
                .map(f -> f.getFamily())
                .collect(Collectors.toList());
        System.out.println("Cписок призывников: \n" + list2 + "\n");


        List<Person> list3 = (List)persons;
        list3.stream()
                .filter(a -> a.getAge() >= 18)
                .filter(a -> a.getAge() < 65)
                .filter(e -> e.getEducation() == Education.HIGHER)
                .sorted((f1, f2) -> f1.getFamily().compareToIgnoreCase(f2.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Cписок потенциально работоспособных людей с высшим образованием: \n" + list3);

    }
}

