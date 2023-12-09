DAY=$1

touch input/day"${DAY}".txt

mkdir src/main/kotlin/day"${DAY}"
echo "package day${DAY}" > src/main/kotlin/day"${DAY}"/Day"${DAY}".kt

mkdir src/test/kotlin/day"${DAY}"
echo "package day${DAY}

class Day${DAY}Test {

}
" >> src/test/kotlin/day"${DAY}"/Day"${DAY}"Test.kt
