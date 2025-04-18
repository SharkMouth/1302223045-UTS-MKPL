package lib;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee {

	private String employeeId;
	private PersonalData personalData;
	private EmploymentData employmentData;
	private FamilyData familyData;

	public Employee(String employeeId, PersonalData personalData, EmploymentData employmentData, FamilyData familyData) {
        this.employeeId = employeeId;
        this.personalData = personalData;
        this.employmentData = employmentData;
        this.familyData = familyData;
	}
	
	public String getEmployeeId() {
        return employeeId;
    }

    public String getIdNumber() {
        return personalData.getIdNumber();
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public EmploymentData getEmploymentData() {
        return employmentData;
    }

    public FamilyData getFamilyData() {
        return familyData;
    }

	public static class PersonalData {
        private String firstName;
        private String lastName;
        private String idNumber;
        private String address;

        public PersonalData(String firstName, String lastName, String idNumber, String address) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.idNumber = idNumber;
            this.address = address;
        }

        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getIdNumber() { return idNumber; }
        public String getAddress() { return address; }

        public void setAddress(String address) {
            this.address = address;
        }
    }

	public static class EmploymentData {
        private int yearJoined;
        private int monthJoined;
        private int dayJoined;
        private int monthWorkingInYear;
        private boolean isForeigner;
        private int monthlySalary;
        private int otherMonthlyIncome;
        private int annualDeductible;

        public EmploymentData(int yearJoined, int monthJoined, int dayJoined, boolean isForeigner) {
            this.yearJoined = yearJoined;
            this.monthJoined = monthJoined;
            this.dayJoined = dayJoined;
            this.isForeigner = isForeigner;
            this.monthWorkingInYear = 12;
        }

        public boolean isForeigner() { return isForeigner; }
        public int getMonthlySalary() { return monthlySalary; }
        public int getOtherMonthlyIncome() { return otherMonthlyIncome; }
        public int getAnnualDeductible() { return annualDeductible; }
        public int getMonthWorkingInYear() { return monthWorkingInYear; }

		/**
		 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
		 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
		 */
		
		public void setMonthlySalary(int grade) {	
			if (grade == 1) {
				monthlySalary = 3000000;
				if (isForeigner) {
					monthlySalary = (int) (3000000 * 1.5);
				}
			}else if (grade == 2) {
				monthlySalary = 5000000;
				if (isForeigner) {
					monthlySalary = (int) (3000000 * 1.5);
				}
			}else if (grade == 3) {
				monthlySalary = 7000000;
				if (isForeigner) {
					monthlySalary = (int) (3000000 * 1.5);
				}
			}
		}

        // public void setMonthlySalary(int salary) {
        //     this.monthlySalary = isForeigner ? salary * 2 : salary;
        // }

        public void setOtherMonthlyIncome(int otherIncome) {
            this.otherMonthlyIncome = otherIncome;
        }

        public void setAnnualDeductible(int deductible) {
            this.annualDeductible = deductible;
        }

		public void setAdditionalIncome(int income) {	
			this.otherMonthlyIncome = income;
		}

        public void setMonthWorkingInYear(int month) {
            this.monthWorkingInYear = month;
        }

		public int getYearJoined() {
			return yearJoined;
		}

		public int getMonthJoined() {
			return monthJoined;
		}

		public int getDayJoined() {
			return dayJoined;
		}
    }

	public static class FamilyData {
        private boolean gender; // true = male, false = female
        private String spouseName;
        private String spouseIdNumber;
        private List<String> childNames = new ArrayList<>();
        private List<String> childIdNumbers = new ArrayList<>();

        public FamilyData(boolean gender, String spouseIdNumber) {
            this.gender = gender;
			this.spouseIdNumber = spouseIdNumber;
        }

        public boolean isMarried() {
            return spouseName != null && !spouseName.isEmpty();
        }

        public int getNumberOfChildren() {
            return childNames.size();
        }

        public void addChild(String name, String id) {
            childNames.add(name);
            childIdNumbers.add(id);
        }

        public void setSpouse(String name, String idNumber) {
            this.spouseName = name;
            this.spouseIdNumber = idNumber;
        }

        public boolean getGender() {
            return gender;
        }

		public String getSpouseIdNumber(){
			return spouseIdNumber;
		}
    }
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == employmentData.yearJoined) {
			employmentData.monthWorkingInYear = date.getMonthValue() - employmentData.monthJoined;
		}else {
			employmentData.setMonthWorkingInYear(12);
		}
		
		return TaxFunction.calculateTax(employmentData.monthlySalary, employmentData.otherMonthlyIncome, employmentData.monthWorkingInYear, employmentData.annualDeductible, familyData.spouseIdNumber.equals(""), familyData.childIdNumbers.size());
	}
}
