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
        System.out.println(persons); //потом убрать

        List<Person> list = (List)persons;
        long count = list.stream()
                .filter(a -> a.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних : " + count);


        List<Person> list2 = (List)persons;
        Stream<Person> stream_1 = list2.stream();
        Stream<Person> stream_2 = stream_1.filter(s -> s.getSex() == Sex.MAN);
        Stream<Person> stream_3 = stream_2.filter(a -> a.getAge() >= 18);
        Stream<Person> stream_4 = stream_3.filter(a -> a.getAge() < 27);
        List<String> stream_5 = stream_4.map(f -> f.getFamily()).collect(Collectors.toList());
        System.out.println("Cписок призывников: \n" + stream_5);


        List<Person> list3 = (List)persons;
        Stream<Person> stream_6 = list3.stream();
        Stream<Person> stream_7 = stream_6.filter(a -> a.getAge() >= 18);
        Stream<Person> stream_8 = stream_7.filter(a -> a.getAge() < 65);
        Stream<Person> stream_9 = stream_8.filter(e -> e.getEducation() == Education.HIGHER);
        Stream<Person> stream_10 = stream_9.sorted((f1, f2) -> f1.getFamily().compareToIgnoreCase(f2.getFamily()));
        List<Person> stream_11 = stream_10.collect(Collectors.toList());
        System.out.println("Cписок потенциально работоспособных людей с высшим образованием: \n" + stream_11);

    }
}
