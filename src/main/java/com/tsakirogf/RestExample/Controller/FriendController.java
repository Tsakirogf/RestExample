package com.tsakirogf.RestExample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsakirogf.RestExample.Model.Friend;
import com.tsakirogf.RestExample.services.FriendService;

@RestController
public class FriendController 
{
	@Autowired
    FriendService friendService;
	
	@PostMapping("/friend")
	Friend create(@RequestBody Friend friend)
	{
		return friendService.save(friend);
	}
	
	@GetMapping("/friend")
	Iterable<Friend> read()
	{
		return friendService.findAll();
	}
	
	@PutMapping("/friend")
	Friend update(@RequestBody Friend friend)
	{
		return friendService.save(friend);
	}
	
	@DeleteMapping("/friend")
	void delete(@RequestBody Friend friend)
	{
		friendService.delete(friend);
	}
}
