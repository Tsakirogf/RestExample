package com.tsakirogf.RestExample.unittest;

import com.tsakirogf.RestExample.Model.Contact;
import com.tsakirogf.RestExample.Model.ContactType;
import com.tsakirogf.RestExample.Model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ContactUnitTest {

    @Test
    public void createContact()
    {
        Contact testContact = new Contact(10, "Test_telephone", ContactType.MOBILE);
        Assertions.assertThat(testContact.getContactName()).isEqualTo("Test_telephone");
        Assertions.assertThat(testContact.getCode()).isEqualTo(10);
        Assertions.assertThat(testContact.getContactType()).isEqualTo(ContactType.MOBILE);
        testContact.setCode(15);
        Assertions.assertThat(testContact.getCode()).isEqualTo(15);
        testContact.setContactType(ContactType.EMAIL);
        Assertions.assertThat(testContact.getContactType()).isEqualTo(ContactType.EMAIL);
        Friend testFriend = new Friend("Test First Name", "Test Last Name");
        testContact.setFriend(testFriend);
        Assertions.assertThat(testContact.getFriend().getFirstName()).isEqualTo(testFriend.getFirstName());
        testContact.setContactName("NewContactName");
        Assertions.assertThat(testContact.getContactName()).isEqualTo("NewContactName");
    }

    @Test
    public void testGetternSetters()
    {
        Contact testContact = new Contact(10, "Test_telephone", ContactType.MOBILE);
        Friend testFriend = new Friend("Test First Name", "Test Last Name");
        testContact.setFriend(testFriend);
        testContact.setContactName("NewContactName");
        testContact.setContactType(ContactType.EMAIL);
        Assertions.assertThat(testContact.getContactType()).isEqualTo(ContactType.EMAIL);
        Assertions.assertThat(testContact.getFriend().getFirstName()).isEqualTo(testFriend.getFirstName());
        Assertions.assertThat(testContact.getFriend().getLastName()).isEqualTo(testFriend.getLastName());
        Assertions.assertThat(testContact.getContactName()).isEqualTo("NewContactName");
    }
}
