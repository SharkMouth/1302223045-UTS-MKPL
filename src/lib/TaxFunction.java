package lib;

import java.time.LocalDate;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(Employee employee) {
		int monthsWorked;
		LocalDate currentDate = LocalDate.now();

		if (currentDate.getYear() == employee.getYearJoined()) {
			monthsWorked = currentDate.getMonthValue() - employee.getMonthJoined();
		} else {
			monthsWorked = 12;
		}

		if (monthsWorked > 12) {
			System.err.println("More than 12 months worked");
			monthsWorked = 12;
		}

		boolean isMarried = employee.getSpouseIdNumber() != null;
		int children = Math.min(3, employee.getChildCount());

		int totalIncome = (employee.getMonthlySalary() + employee.getOtherMonthlyIncome()) * monthsWorked;
		int ptkp = 54000000 + (isMarried ? 4500000 : 0) + (children * 1500000);

		int taxable = totalIncome - employee.getAnnualDeductible() - ptkp;
		if (taxable <= 0) return 0;

		return (int) Math.round(0.05 * taxable);
	}
	
}
