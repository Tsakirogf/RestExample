package com.tsakirogf.RestExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tsakirogf.RestExample.Controller.FriendController;
import com.tsakirogf.RestExample.Model.Address;
import com.tsakirogf.RestExample.Model.Contact;
import com.tsakirogf.RestExample.Model.ContactType;
import com.tsakirogf.RestExample.Model.Friend;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests
{
    @Autowired
    FriendController friendController;
    
    @Test
    public void testCreateReadDelete()
    {
        Friend friend = new Friend ("Gordon", 
                                    "Brown", 
                                    new Address("Dikaiarhou", 15, "Athens", "14560"), 
                                    new ArrayList<Contact> (Arrays.asList(new Contact(1, "phone", ContactType.MOBILE))));
        Friend friendResult = friendController.create(friend);
        Iterable<Friend> friends = friendController.read();
        Assertions.assertThat(friends).first().hasFieldOrPropertyWithValue("firstName", "Gordon");
        friendController.delete(friendResult.getId());
        Assertions.assertThat(friendController.read()).isEmpty();
    }
}
