package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Sample {
	
	@NotEmpty
	@Size(max = 50)
	private String name;
	
	@NotEmpty
	@Size(max = 70)
	private String email;

	@Override
	public String toString() {
		return "Sample [name=" + name + ", email=" + email + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
}
