package com.example.Controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Role;
import com.example.Entity.User;
import com.example.Service.Impl.MyUserDetailService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SignUp {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private MyUserDetailService userDetailsService;

	/*@PostMapping(path = "/signup") //
	public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
		// Map<String, String> errors = new HashMap<>();
		// String field = null;
		// String message = null;
		// try {
		String pwd = user.getPassword();
		String bcryptpwd = passwordEncoder.encode(pwd);
		user.setPassword(bcryptpwd);
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(1, "USER"));
		user.setRoles(roles);
		User savedUser = userDetailsService.addUser(user);
		return new ResponseEntity<Object>(savedUser, HttpStatus.CREATED);
	}*/
	// } catch (Exception ex) {

	// field += 23000;

	// message += "Duplication of unique field";
//
	// }
//				}
//			}
	// }
	// errors.put(field, message);
	// return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);

	// }
    @PostMapping(path = "/signup")
    public ResponseEntity<Object> createUser(@RequestBody @Valid User user) {
        try {
            String pwd = user.getPassword();
            String bcryptpwd = passwordEncoder.encode(pwd);
            user.setPassword(bcryptpwd);
            
            // Assign roles as needed (e.g., "USER" role by default)
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(1, "USER"));
            user.setRoles(roles);

            User savedUser = userDetailsService.addUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error creating user: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}




//}
