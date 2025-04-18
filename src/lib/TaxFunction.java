package lib;

public class TaxFunction {
	private TaxData taxData;

	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
	 * dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan
	 * bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan)
	 * dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena
	 * pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah
	 * sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya
	 * ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	public static class TaxData {
		public int monthlySalary;
		public int otherMonthlyIncome;
		public int numberOfMonthWorking;
		public int deductible;
		public boolean isMarried;
		public int numberOfChildren;

		public TaxData(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
			this.monthlySalary = monthlySalary;
			this.otherMonthlyIncome = otherMonthlyIncome;
			this.numberOfMonthWorking = numberOfMonthWorking;
			this.deductible = deductible;
			this.isMarried = isMarried;
			this.numberOfChildren = numberOfChildren;
		}
	}

	public int calculateTax(TaxData data) {

		int tax = 0;

		if (taxData.numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		if (taxData.numberOfChildren > 3) {
			taxData.numberOfChildren = 3;
		}

		if (taxData.isMarried) {
			tax = (int) Math.round(0.05 * (((taxData.monthlySalary + taxData.otherMonthlyIncome) * taxData.numberOfMonthWorking) - taxData.deductible
					- (54000000 + 4500000 + (taxData.numberOfChildren * 1500000))));
		} else {
			tax = (int) Math.round(
					0.05 * (((taxData.monthlySalary + taxData.otherMonthlyIncome) * taxData.numberOfMonthWorking) - taxData.deductible - 54000000));
		}

		if (tax < 0) {
			return 0;
		} else {
			return tax;
		}

	}

}
