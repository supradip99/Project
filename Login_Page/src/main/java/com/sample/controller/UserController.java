package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.dao.UserRepository;
import com.sample.entity.User;

import jakarta.validation.Valid;

@Controller
public class UserController {

	@Autowired
	public UserRepository userRepository;

	@RequestMapping("/")
	public String showForm(Model model) {
		model.addAttribute("title", "Add User");
		model.addAttribute("user", new User());
		return "show";
	}

	@PostMapping("/process")
	public String processForm(@Valid @ModelAttribute User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "show";
		}

		this.userRepository.save(user);
		// model.addAttribute("user",u);
		return "redirect:/find";
	}

	@GetMapping("/find")
	public String findAll(Model model) {
		List<User> u = userRepository.findAll();
		model.addAttribute("users", u);
		System.out.println("Just check");
		return "sucess_page";
	}

	@GetMapping("/updateUser/{id}")
	public String update(@PathVariable("id") int id, Model model) {
		User us = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		model.addAttribute("user", us);
		return "update_user";
	}

	@PostMapping("/update/{id}")
	public String updateProcess(@PathVariable("id") int id, @Valid @ModelAttribute User user, BindingResult result,
			Model model) {
		
		if(result.hasErrors())
		{
			return "update_user";
		}
		
		userRepository.save(user);
		return "redirect:/find";
	}

	@RequestMapping("/deleteUser/{id}")
	public String delete(@PathVariable("id") int id, Model model) {
		User u = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(u);
		return "redirect:/find";
	}

}
