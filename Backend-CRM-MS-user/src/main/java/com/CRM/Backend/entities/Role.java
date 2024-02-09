package com.CRM.Backend.entities;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private RoleUser name;

	public Role() {

	}

	public Role(RoleUser name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleUser getName() {
		return name;
	}

	public void setName(RoleUser name) {
		this.name = name;
	}
}