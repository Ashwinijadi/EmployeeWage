package com.capgemini.employeepayroll;

import java.util.ArrayList;
import java.util.HashMap;

public class EmpWageBuilder implements IComputeEmpWage {
	// static variables
	public static final int IS_PART_TIME = 2;
	public static final int IS_FULL_TIME = 1;

	private ArrayList<CompanyEmpWage> companyEmpWageList;
	private HashMap<String, CompanyEmpWage> companyEmpWageMap;

	public EmpWageBuilder() {
		companyEmpWageList = new ArrayList<>();
		companyEmpWageMap = new HashMap<>();
	}

	public void addCompanyEmpWage(int empWagePerHour, int workDaysPerMonth, int workHoursPerMonth, String companyName) {
		CompanyEmpWage companyEmpWage = new CompanyEmpWage(empWagePerHour, workDaysPerMonth, workHoursPerMonth,
				companyName);
		companyEmpWageList.add(companyEmpWage);
		companyEmpWageMap.put(companyName, companyEmpWage);
	}

	public void ComputeEmpWage() {
		for (CompanyEmpWage company : companyEmpWageList) {
			ComputeEmpWage(company);
			System.out
					.println("Employee wage of the " + company.getCompanyName() + "is : " + company.getTotalEmpWage());
		}
	}

	public int getTotalEmpWage(String company) {
		return companyEmpWageMap.get(company).totalEmpWage;
	}

	private int ComputeEmpWage(CompanyEmpWage companyEmpWage) {
		int empWage = 0;
		int totalHoursWorked = 0;
		int empHours = 0;
		int dayCount = 0;

		while ((totalHoursWorked) <= companyEmpWage.workHoursPerMonth && dayCount < companyEmpWage.workDaysPerMonth) {
			dayCount++;
			int empCheck = (int) (Math.floor(Math.random() * 10) % 3);
			switch (empCheck) {
			case 1:
				empCheck = IS_FULL_TIME;
				System.out.println("Employee is Present & Full Time");
				empHours = 8;
				break;

			case 2:
				empCheck = IS_PART_TIME;
				System.out.println("Employee is Present & Part Time");
				empHours = 4;
				break;

			default:
				System.out.println("Employee is Absent");
				empHours = 0;
				break;
			}
			// daily wage calculation
			empWage = empHours * companyEmpWage.empWagePerHour;
			companyEmpWage.setDailyEmpWage(empWage);
			// Tabular display of employee details
			System.out.println("company\t\tDay\tHours Worked");
            totalHoursWorked += empHours;
			System.out.println(" " + companyEmpWage.companyName + " \t" + dayCount + " \t" + totalHoursWorked );
			companyEmpWage.totalEmpWage += empWage;
		}	
		System.out.println("Total Wage"+ companyEmpWage.totalEmpWage);
		return companyEmpWage.totalEmpWage;
	}

	static void main(String[] args) {
		System.out.println("Welcome to Employee Wage Computation");
		EmpWageBuilder empWageBuilder = new EmpWageBuilder();
		empWageBuilder.addCompanyEmpWage(20, 15, 100, "Company A");
		empWageBuilder.addCompanyEmpWage(10, 20, 90, "Company B");
		empWageBuilder.ComputeEmpWage();
	}
}
