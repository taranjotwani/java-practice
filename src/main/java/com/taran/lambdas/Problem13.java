package com.taran.lambdas;

import java.util.Arrays;
import java.util.List;

/**
 * Problem 13: Given a List<Employee> with fields name and salary, use streams
 * to find the employee with the highest salary using max().
 */
public class Problem13 {

	record Employee(String name, double salary) {}

	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(
				new Employee("Alice", 65000),
				new Employee("Bob", 72000),
				new Employee("Charlie", 68000),
				new Employee("Diana", 81000),
				new Employee("Ethan", 79000)
		);

       Employee highestPaidEmployee = employees.stream()
                        .max((employee1, employee2) -> Double.compare(employee1.salary(), employee2.salary()))
                        .get();                 

       System.out.println(highestPaidEmployee);                 
	}

}
