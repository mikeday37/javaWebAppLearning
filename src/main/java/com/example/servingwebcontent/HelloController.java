package com.example.servingwebcontent;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class HelloController {

	public RestTemplate getRestTemplate() {
		RestTemplateBuilder builder = new RestTemplateBuilder();
		return builder.build();
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(name="name", required=false, defaultValue="!") String name, Model model) {
		if (name.equals("!"))
		{
			RestTemplate restTemplate = getRestTemplate();
			Greeting greeting = restTemplate.getForObject(
				"http://localhost:9090/greeting", Greeting.class);

			name = greeting.toString();
		}
		model.addAttribute("name", name);
		return "hello";
	}

}
