package com.tsakirogf.RestExample.Controller;

import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tsakirogf.RestExample.Model.Friend;
import com.tsakirogf.RestExample.services.FriendService;

@RestController
public class FriendController 
{
	@Autowired
    FriendService friendService;
	
	@GetMapping("/friend")
	Iterable<Friend> read()
	{
		return friendService.findAll();
	}
	
	@GetMapping("/friend/search")
	Iterable<Friend> findByFirstAndLastName(@RequestParam("first") String firstName, @RequestParam("last") String lastName)
	{
		return friendService.findByFirstNameAndLastName(firstName, lastName);
	}
	
	@GetMapping("/friend/{id}")
	Optional<Friend> update(@PathVariable Integer id)
	{
		return friendService.findById(id);
	}
	
	@PostMapping("/friend")
	Friend create(@RequestBody Friend friend)
	{
		if(friend.getId() == 0 && friend.getFirstName() != null && friend.getLastName() != null)
		{
			return friendService.save(friend);
		}
		else
		{
			throw new ValidationException("Person cannot added.");
		}
	}
	
	@PutMapping("/friend")
	ResponseEntity<Friend> update(@RequestBody Friend friend)
	{
		if(friendService.findById(friend.getId()).isPresent())
		{
			return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);	
		}
	}
	
	@DeleteMapping("/friend/{id}")
	void delete(@PathVariable Integer id)
	{
		friendService.deleteById(id);
	}
}
