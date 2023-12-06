import kotlin.math.ceil
import kotlin.math.floor

fun Double.ceilOrIncrement(): Int {
    if(ceil(this) == this) return (this + 1).toInt()
    return ceil(this).toInt()
}

fun Double.floorOrDecrement(): Int {
    if(floor(this) == this) return (this - 1).toInt()
    return floor(this).toInt()
}