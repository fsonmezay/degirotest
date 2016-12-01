package com.ferdisonmezay.degiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ferdisonmezay.degiro.dto.ResponseDto;
import com.ferdisonmezay.degiro.service.ProblemSolverService;

@RestController
@RequestMapping("/input")
public class InputController {

	@Autowired
	private ProblemSolverService problemSolverService;

	@RequestMapping(value = "send", method = RequestMethod.POST)
	public @ResponseBody ResponseDto send(@RequestBody String input) {
		input = input.substring(10, input.lastIndexOf("0"));
		ResponseDto result = problemSolverService.solve(input);
		System.out.println(result.getResponse());
		return result;
	}
}
