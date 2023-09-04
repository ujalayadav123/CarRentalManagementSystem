package com.example.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Role;
import com.example.Entity.User;
import com.example.ExceptionHandling.UsernameNotFoundException;
import com.example.Service.Impl.MyUserDetailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users") // Define a base path for all user-related endpoints
public class UserController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private MyUserDetailService userDetailsService;

	@PostMapping(path = "/users") //
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
		Map<String, String> errors = new HashMap<>();
		String field = null;
		String message = null;
		try {
			String pwd = user.getPassword();
			String bcryptpwd = passwordEncoder.encode(pwd);
			user.setPassword(bcryptpwd);

			User savedUser = userDetailsService.addUser(user);
			return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
		} catch (Exception ex) {

			field += 23000;

			message += "Duplication of unique field";
//
			// }
//				}
//			}
			// }
			errors.put(field, message);
			return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("/users")
	public User updateUser(@RequestBody User user) throws UsernameNotFoundException {
		return userDetailsService.updateUser(user);
	}

	@GetMapping("/users")
	public List<User> findAllUsers() {
		return userDetailsService.getAllUser();
	}

	@GetMapping("/users/{username}")
	public User findByUsername() {
		return userDetailsService.getByUsername();
	}

}
