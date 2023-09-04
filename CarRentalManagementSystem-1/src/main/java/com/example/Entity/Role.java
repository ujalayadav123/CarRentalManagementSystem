package com.example.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Carrole1")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rid;
	@NotEmpty(message = "role can't be empty")
	private String rname;

}
