package com.tsakirogf.RestExample;

import com.tsakirogf.RestExample.Model.Address;
import com.tsakirogf.RestExample.Model.Contact;
import com.tsakirogf.RestExample.Model.ContactType;
import com.tsakirogf.RestExample.Model.Friend;
import com.tsakirogf.RestExample.services.FriendService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests
{
    @Autowired
    FriendService friendService;

    @Test
    public void testCreateReadDelete()
    {
        Friend friend = new Friend("Gordon",
                                   "Moore",
                                   new Address("Roumelis", 14,"Athens", "13562"),
                                   new ArrayList<Contact>(Arrays.asList(new Contact(1, "phone", ContactType.MOBILE))));
        friendService.save(friend);

        Iterable<Friend> friends=friendService.findAll();
        Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("Gordon");
        Assertions.assertThat(friends).extracting(Friend::getLastName).containsOnly("Moore");

        friendService.delete(friend);
        Assertions.assertThat(friendService.findAll()).isEmpty();
    }

}
