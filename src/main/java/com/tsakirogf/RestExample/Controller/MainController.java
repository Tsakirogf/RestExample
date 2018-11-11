package com.tsakirogf.RestExample.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsakirogf.RestExample.Model.Message;

@RestController
public class MainController {

	@GetMapping("/message")
	Message send()
	{
		return new Message("My first message.");
	}
	
	@PostMapping("/message")
	Message echo(@RequestBody Message message)
	{
		return message;
	}
}
