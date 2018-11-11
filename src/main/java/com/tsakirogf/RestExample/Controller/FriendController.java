package com.tsakirogf.RestExample.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.tsakirogf.RestExample.services.FriendService;

@RestController
public class FriendController 
{
	@Autowired
    FriendService friendService;
}
