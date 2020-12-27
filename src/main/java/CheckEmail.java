import java.io.*;
import java.util.*;

public class CheckEmail {

    public List<String> check(List<String> list) {
        //вычленение имени user и списка email
        List<UserEmail> listUserEmail = new ArrayList<>();
        for (String line : list) {
            String[] uEm = line.split("->");
            listUserEmail.add(new UserEmail(uEm[0], uEm[1]));
        }

        Map<String, String> map = new HashMap<>();
        for (UserEmail u : listUserEmail) {             // создание списка нодов имя-емайл
            String name = u.getName();
            String[] emails = u.getEmailList().split(",");
            String n;
            for (int i = 0; i < emails.length; i++) {
                if (map.containsKey(emails[i])) { // если есть емайл
                    n = map.get(emails[i]);
                    if (!name.equals(n)) {
                        name = n;
                        for (int j = 0; j <= i; j++) {
                            map.put(emails[j], name);
                        }
                    }
                } else {
                    map.put(emails[i], name);
                }
            }
        }
//        map.keySet().forEach(n -> System.out.println(n + "->" + map.get(n)));
        Map<String, String> mapUserEmail = new HashMap<>();
        map.keySet().forEach(email -> {
            String userName = map.get(email);
            if (!mapUserEmail.containsKey(userName)) {
                mapUserEmail.put(userName, email);
            } else {
                mapUserEmail.put(userName, mapUserEmail.get(userName) + "," + email);
            }
        });
//        System.out.println(mapUserEmail);
        List<String> listOut = new ArrayList<>();
        mapUserEmail.keySet().forEach(n -> listOut.add(n + "->" + mapUserEmail.get(n)));
        return listOut;
    }


    public static void main(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        // Чтение данных из файла и запись в listUserEmail
        try (BufferedReader reader = new BufferedReader(new FileReader("inlist.txt"))) {
            reader.lines().forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Основная обработка
        CheckEmail proc = new CheckEmail();
        List<String> listOutUserEmail = proc.check(lines);

        // вывод в файл
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("outlist.txt"))) {
            listOutUserEmail.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class UserEmail {
    private String name;
    private String emailList;

    public UserEmail(String name, String emailList) {
        this.name = name;
        this.emailList = emailList;
    }

    public String getName() {
        return name;
    }

    public String getEmailList() {
        return emailList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserEmail userEmail = (UserEmail) o;
        return name.equals(userEmail.name)
                && emailList.equals(userEmail.emailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emailList);
    }
}

