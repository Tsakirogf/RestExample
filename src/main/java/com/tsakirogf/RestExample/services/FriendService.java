package com.tsakirogf.RestExample.services;

import org.springframework.data.repository.CrudRepository;

import com.tsakirogf.RestExample.Model.Friend;

public interface FriendService extends CrudRepository<Friend, Integer> 
{
}
