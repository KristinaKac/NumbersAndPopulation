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
        Stream<Person> stream1 = persons.stream()
                .filter(x -> x.getAge() < 18);
        System.out.println(stream1.count());

        List<String> stream2 = persons.stream()
                .filter(x -> x.getAge() > 18 && x.getAge() < 27)
                .filter(x -> x.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect((Collectors.toList()));
        System.out.println(stream2);

        List<String> stream3 = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getSex().equals(Sex.WOMAN) && x.getAge() > 18 && x.getAge() < 60)
                .filter(x -> x.getSex().equals(Sex.MAN) && x.getAge() > 18 && x.getAge() < 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(stream3);
    }
}