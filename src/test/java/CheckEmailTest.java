
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CheckEmailTest {

    @Test
    public void check() {

        List<String> listUserEmail = new ArrayList<>();
        listUserEmail.add("user1->xxx@ya.ru,foo@gmail.com,lol@mail.ru");
        listUserEmail.add("user2->foo@gmail.com,ups@pisem.net");
        listUserEmail.add("user3->xyz@pisem.net,vasya@pupkin.com");
        listUserEmail.add("user4->ups@pisem.net,aaa@bbb.ru");
        listUserEmail.add("user5->xyz@pisem.net");
        CheckEmail proc = new CheckEmail();
        List<String> listOutUserEmail = proc.check(listUserEmail);

        assertThat(listOutUserEmail.size(), is(2));
        assertTrue(listOutUserEmail.contains("user3->vasya@pupkin.com,xyz@pisem.net")
                || listOutUserEmail.contains("user3->vasya@xyz@pisem.net,pupkin.com"));
    //    listOutUserEmail.forEach(System.out::println);
    }
}

//    user1 ->xxx@ya.ru,foo@gmail.com,lol@mail.ru,ups@pisem.net,aaa@bbb.ru
//    user3 ->xyz@pisem.net,vasya@pupkin.com