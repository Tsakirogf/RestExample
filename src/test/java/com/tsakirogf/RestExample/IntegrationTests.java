package com.tsakirogf.RestExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.tsakirogf.RestExample.Controller.FriendController;
import com.tsakirogf.RestExample.Model.Address;
import com.tsakirogf.RestExample.Model.Contact;
import com.tsakirogf.RestExample.Model.ContactType;
import com.tsakirogf.RestExample.Model.Friend;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.ValidationException;

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

    @Test(expected = ValidationException.class)
    public void errorHandlingValidationExceptionThrown()
    {
        friendController.somethingIsWrong();
    }

    @Test
    public void testErrorHandlingReturnServerError()
    {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/wrong";
        try
        {
            restTemplate.getForEntity(url, String.class);
        }catch(HttpServerErrorException e)
        {
            Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "500");
        }
    }

    @Test
    public void testErrorHandlingReturnBadRequest()
    {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/something";
        try
        {
            restTemplate.getForEntity(url, String.class);
        }catch(HttpClientErrorException e)
        {
            Assert.assertEquals(HttpStatus.BAD_REQUEST.toString(), "400");
        }
    }

    @Test
    public void testFindByFirstLastname()
    {
        Friend friend = new Friend ("Gordon",
                "Brown",
                new Address("Dikaiarhou", 15, "Athens", "14560"),
                new ArrayList<Contact> (Arrays.asList(new Contact(1, "phone", ContactType.MOBILE))));
        Friend friendResult = friendController.create(friend);
        Iterable<Friend> friends = friendController.findByFirstAndLastName("Gordon", "Brown");
        Assertions.assertThat(friends).first().hasFieldOrPropertyWithValue("firstName", "Gordon");
        friendController.delete(friendResult.getId());
        Assertions.assertThat(friendController.read()).isEmpty();
    }


}
