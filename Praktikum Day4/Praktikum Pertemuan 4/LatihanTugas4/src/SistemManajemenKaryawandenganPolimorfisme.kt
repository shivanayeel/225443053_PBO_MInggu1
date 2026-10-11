open class Employee(
    val id: String,
    val name: String,
    val baseSalary: Double
) {
    open fun calculateSalary(): Double = baseSalary
    open fun calculateBonus(): Double = 0.0
    open fun getRole(): String = "Employee"

    fun displayInfo(): String {
        return "$id | $name | ${getRole()} | Gaji: Rp${calculateSalary().toInt()} | Bonus: Rp${calculateBonus().toInt()}"
    }
}

// class full time
class FullTimeEmployee(
    id: String,
    name: String,
    baseSalary: Double,
    val allowance: Double,
    val annualBonus: Double
) : Employee(id, name, baseSalary) {
    override fun calculateSalary(): Double = baseSalary + allowance
    override fun calculateBonus(): Double = annualBonus / 12
    override fun getRole(): String = "Full-Time Employee"
}

// class part time
class PartTimeEmployee(
    id: String,
    name: String,
    baseSalary: Double,
    val hourlyRate: Double,
    val hoursWorked: Int
) : Employee(id, name, baseSalary) {
    override fun calculateSalary(): Double = hourlyRate * hoursWorked
    override fun getRole(): String = "Part-Time Employee"
}

// class contract
class ContractEmployee(
    id: String,
    name: String,
    baseSalary: Double,
    val contractDuration: Int,
    val projectBonus: Double
) : Employee(id, name, baseSalary) {
    override fun calculateSalary(): Double = baseSalary
    override fun calculateBonus(): Double = projectBonus / contractDuration
    override fun getRole(): String = "Contract Employee"
}

// sealed class
sealed class EmployeeStatus {
    data object Active : EmployeeStatus()
    data object OnLeave : EmployeeStatus()
    data object Terminated : EmployeeStatus()

    fun display(): String {
        return when (this) {
            Active -> "Aktif"
            OnLeave -> "Cuti"
            Terminated -> "Berhenti"
        }
    }
}

// class company
class Company(val name: String) {
    private val employees = mutableListOf<Employee>()

    fun addEmployee(employee: Employee) {
        employees.add(employee)
    }

    fun findEmployee(id: String): Employee? {
        return employees.find { it.id == id }
    }

    fun getTotalSalary(): Double {
        return employees.sumOf { it.calculateSalary() }
    }

    fun getTotalBonus(): Double {
        return employees.sumOf { it.calculateBonus() }
    }

    fun getEmployeesByRole(role: String): List<Employee> {
        return employees.filter { it.getRole() == role }
    }

    fun displayAllEmployees() {
        println("\nPerusahaan: $name")
        println("===== DATA KARYAWAN =====")

        for (employee in employees) {
            println(employee.displayInfo())
        }
    }

    fun displaySalaryReport() {
        println("\n===== LAPORAN GAJI =====")

        for (employee in employees) {
            println("${employee.name} | Gaji: Rp${employee.calculateSalary().toInt()} | Bonus: Rp${employee.calculateBonus().toInt()}")
        }

        println("------------------------------")
        println("Total Gaji  : Rp${getTotalSalary().toInt()}")
        println("Total Bonus : Rp${getTotalBonus().toInt()}")
    }
}

// main

fun main() {
    val company = Company("PT Teknologi Maju")

    // polymorphic references
    val employees = mutableListOf<Employee>(
        FullTimeEmployee("A01", "Shiva", 8200000.0, 1800000.0, 10800000.0),
        FullTimeEmployee("A02", "Nael", 7800000.0, 1200000.0, 9600000.0),
        PartTimeEmployee("B01", "Sean", 0.0, 55000.0, 90),
        PartTimeEmployee("B02", "Mitch", 0.0, 40000.0, 110),
        ContractEmployee("C01", "Suzy", 6200000.0, 12, 10800000.0),
        ContractEmployee("C02", "Jaemin", 6800000.0, 8, 8000000.0)
    )

    for (employee in employees) {
        company.addEmployee(employee)
    }

    // menampilkan semua karyawan
    company.displayAllEmployees()
    // laporan gaji
    company.displaySalaryReport()

    println("\n===== SMART CASTING =====")

    for (employee in employees) {
        when (employee) {
            is FullTimeEmployee ->
                println("${employee.name} | Tunjangan: Rp${employee.allowance.toInt()}")

            is PartTimeEmployee ->
                println("${employee.name} | Jam kerja: ${employee.hoursWorked} jam")

            is ContractEmployee ->
                println("${employee.name} | Durasi kontrak: ${employee.contractDuration} bulan")
        }
    }

    // status karyawan
    println("\n===== STATUS KARYAWAN =====")
    val statuses = listOf(
        EmployeeStatus.Active,
        EmployeeStatus.OnLeave,
        EmployeeStatus.Terminated
    )

    for (status in statuses) {
        println("- ${status.display()}")
    }
}