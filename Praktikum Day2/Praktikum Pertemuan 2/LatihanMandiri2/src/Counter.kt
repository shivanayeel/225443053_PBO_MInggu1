class Counter(
    val maxValue: Int,
    val minValue: Int = 0
){
    var value: Int = minValue
        private set

    fun increment(){
        if (!isAtMax()){
            value++
            println("Value = $value")
        } else {
            println("Maximum value = $maxValue")
        }
    }

    fun decrement() {
        if (!isAtMin()) {
            value--
            println("Value = $value")
        } else {
            println("Minimum value = $minValue")
        }
    }

    fun reset(){
        value = minValue
        println("Reset, Value = $value")
    }

    fun isAtMax(): Boolean {
        return (value == maxValue)
    }

    fun isAtMin(): Boolean {
        return (value == minValue)
    }

    fun display(){
        println("--- VALUE ---")
        println("Maximum value = $maxValue")
        println("Minimum value = $minValue")
        println("Display value = $value\n")
    }
}

fun main(){
    print("Masukan Minimal Input: ")
    val minInput = readln().toInt()
    print("\nMasukan Maximal Input: ")
    val maxInput = readln().toInt()

    if (maxInput <= minInput) {
        println("Error")
        return
    }

    val counter = Counter(maxValue = maxInput, minValue = minInput)
    println("Berhasil masukan")

    var running = true
    while (running) {
        println("\n--- PILIH AKSI ---")
        println("1. Increment")
        println("2. Decrement")
        println("3. Reset")
        println("4. Display")
        println("5. Keluar\n--------------------")

        print("\nMasukan Aksi = ")
        when (readlnOrNull()?.toIntOrNull()){
            1 -> counter.increment()
            2 -> counter.decrement()
            3 -> counter.reset()
            4 -> counter.display()
            5 -> {
                println("Program Stopped")
                running = false
            }
            else -> println("Invalid")
        }
    }
}