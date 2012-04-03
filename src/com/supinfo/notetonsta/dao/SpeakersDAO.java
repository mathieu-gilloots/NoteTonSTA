package com.supinfo.notetonsta.dao;

import java.util.List;

import com.supinfo.notetonsta.entity.Speakers;

public interface SpeakersDAO {

	Speakers addSpeakers(Speakers speaker);
	Speakers getSpeakersById(Long SpeakerId);
	Speakers getSpeakerByEmail(String email);
	List<Speakers> getAllSpeakers();
	Speakers LoginSpeaker(String mail, String password);
	String SHAPassword(String password);
}
