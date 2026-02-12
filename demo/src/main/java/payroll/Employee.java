package payroll;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity  //实体类，映射到数据库表
class Employee {
    private @Id
    @GeneratedValue Long id;   //主键，自增
    private String name;
    private String role;
    private LocalDate joiningDate;

    Employee(String name, String role, LocalDate joiningDate) {
    	this.name = name;
    	this.role = role;
        this.joiningDate = joiningDate;
    }

    public Employee() {

    }

    public LocalDate getJoiningDate() {
		return joiningDate;
	}

    public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    //辅助方法

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
