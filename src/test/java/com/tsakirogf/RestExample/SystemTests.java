package com.tsakirogf.RestExample;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.tsakirogf.RestExample.Model.Address;
import com.tsakirogf.RestExample.Model.Friend;

public class SystemTests
{
    @Test
    public void testCreateReadDelete() 
    {
      RestTemplate restTemplate = new RestTemplate();
      String url = "http://localhost:8080/friend";

      Friend friend = new Friend("Gordon", "Moore", new Address("Dikaiarhou", 15, "Athens", "14560"), null);
      ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);

      Friend[] friends = restTemplate.getForObject(url, Friend[].class);
      Assertions.assertThat(friends).extracting(Friend::getFirstName).contains("Gordon");

      restTemplate.delete(url + "/" + entity.getBody().getId());
      Assertions.assertThat(restTemplate.getForObject(url, Friend[].class)).isEmpty();
    }

    @Test
    public void testCreateMultipleRecords()
    {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/friend";

        Friend friend = new Friend("Gordon", "Moore", new Address("Dikaiarhou", 15, "Athens", "14560"), null);
        Friend friend2 = new Friend("Fotis", "Tsakiroglou", new Address("Pileas", 12, "Athens", "14325"), null);
        Friend friend3 = new Friend("Eva", "Tsourou", new Address("Amalias", 455, "Chalandri", "16588"), null);
        Friend friend4 = new Friend("Liam", "Lowless", new Address("Ermitou", 8, "Vyronas", "12588"), null);
        ResponseEntity<Friend> entity = restTemplate.postForEntity(url, friend, Friend.class);
        ResponseEntity<Friend> entity2 = restTemplate.postForEntity(url, friend2, Friend.class);
        ResponseEntity<Friend> entity3 = restTemplate.postForEntity(url, friend3, Friend.class);
        ResponseEntity<Friend> entity4 = restTemplate.postForEntity(url, friend4, Friend.class);

        Friend[] friends = restTemplate.getForObject(url, Friend[].class);
        Assertions.assertThat(friends).extracting(Friend::getFirstName).contains("Gordon");
        Assertions.assertThat(friends).extracting(Friend::getFirstName).contains("Fotis");
        Assertions.assertThat(friends).extracting(Friend::getFirstName).contains("Eva");
        Assertions.assertThat(friends).extracting(Friend::getFirstName).contains("Liam");
        Assertions.assertThat(friends).extracting(Friend::getLastName).contains("Tsakiroglou");
        Assertions.assertThat(friends).extracting(Friend::getLastName).contains("Tsourou");

        restTemplate.delete(url + "/" + entity.getBody().getId());
        restTemplate.delete(url + "/" + entity2.getBody().getId());
        restTemplate.delete(url + "/" + entity3.getBody().getId());
        restTemplate.delete(url + "/" + entity4.getBody().getId());
        Assertions.assertThat(restTemplate.getForObject(url, Friend[].class)).isEmpty();
    }
    /*@Test
    public void testErrorHandlingReturnsBadRequest() {

      RestTemplate restTemplate = new RestTemplate();

      String url = "http://localhost:8080/wrong";

      try {
        restTemplate.getForEntity(url, String.class);
      } catch (HttpClientErrorException e) {
        Assert.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
      }
    }*/
}
