package com.souqalmal.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.souqalmal.app.bean.TrackRequest;
import com.souqalmal.app.bean.TrackResponse;

@RestController
@RequestMapping("/track")
public class TrackController {

	private List<String> trackList = new ArrayList<>();

	@PostMapping("/trackValue")
	public TrackResponse getTrackValue(@RequestBody TrackRequest trackRequest) {
		String lastInput = "";
		if (!trackList.isEmpty())
			lastInput = trackList.get(trackList.size() - 1);
		trackList.add(trackRequest.getInput());
		return new TrackResponse(lastInput);
	}

	@GetMapping("/getHistory")
	public List<String> getTrackHistory() {
		return trackList;
	}

}
