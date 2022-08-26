package com.souqalmal.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
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

		if (trackRequest.getInput() == null || trackRequest.getInput().equals(""))
			return new TrackResponse(null);

		String lastInput = null;
		if (!trackList.isEmpty())
			lastInput = trackList.get(trackList.size() - 1);
		trackList.add(trackRequest.getInput());
		return new TrackResponse(lastInput);
	}

	@GetMapping("/getHistory")
	public List<String> getTrackHistory() {
		List<String> reversedList = trackList.stream().collect(Collectors.collectingAndThen(Collectors.toList(), x -> {
			Collections.reverse(x);
			return x;
		}));
		return reversedList.stream().limit(10).collect(Collectors.toList());
	}

}
