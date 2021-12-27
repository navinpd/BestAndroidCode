package com.big.ds_algo.leetcode

class ValidSudoku_36 {

    companion object {
        // [[".",".","4",".",".",".","6","3","."],
        // [".",".",".",".",".",".",".",".","."],
        // ["5",".",".",".",".",".",".","9","."],
        // [".",".",".","5","6",".",".",".","."],
        // ["4",".","3",".",".",".",".",".","1"],
        // [".",".",".","7",".",".",".",".","."],
        // [".",".",".","5",".",".",".",".","."],
        // [".",".",".",".",".",".",".",".","."],
        // [".",".",".",".",".",".",".",".","."]]
        @JvmStatic
        fun main(args: Array<String>) {
            var char1 = charArrayOf('8', '3', '.', '.', '7', '.', '.', '.', '.')
            var char2 = charArrayOf('6', '.', '.', '1', '9', '5', '.', '.', '.')
            var char3 = charArrayOf('.', '9', '7', '.', '.', '.', '.', '6', '.')
            var char4 = charArrayOf('8', '.', '.', '.', '6', '.', '.', '.', '3')
            var char5 = charArrayOf('4', '.', '.', '8', '.', '3', '.', '.', '1')
            var char6 = charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6')
            var char7 = charArrayOf('.', '6', '.', '.', '.', '.', '2', '8', '.')
            var char8 = charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '5')
            var char9 = charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
            var dd = arrayOf(char1, char2, char3, char4, char5, char6, char7, char8, char9)
            println("Value is ${isValidSudoku(dd)}")
        }

        var hashSet = HashSet<String>()
        var arr = emptyList<Boolean>()
        fun isValidSudoku(board: Array<CharArray>): Boolean {
            val hashMap = HashMap<Char, Int>()
            hashMap['1'] = 1
            hashMap['2'] = 2
            hashMap['3'] = 3
            hashMap['4'] = 4
            hashMap['5'] = 5
            hashMap['6'] = 6
            hashMap['7'] = 7
            hashMap['8'] = 8
            hashMap['9'] = 9
            for (i in 0..8) {
                for (j in 0..8) {
                    println("value $i and $j")
                    if (i % 3 == 0 && j % 3 == 0) {
                        arr = mutableListOf(
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false,
                            false
                        )
                        for (x in i..i + 2) {
                            for (y in j..j + 2) {
                                if (board[x][y] == '.') {
                                    continue
                                }
                                if (arr[hashMap[board[x][y]]!! - 1]) {
                                    return false
                                } else {
                                    (arr as MutableList<Boolean>)[hashMap[board[x][y]]!! - 1] = true
                                }
                            }
                        }
                    }

                    if (board[i][j] == '.') {
                        continue
                    }
                    val value = hashMap[board[i][j]]
                    val str1 = "{$i}_*_$value"
                    val str2 = "*_{$j}_$value"
                    if (hashSet.contains(str1) || hashSet.contains(str2)) {
                        return false
                    } else {
                        hashSet.add(str1)
                        hashSet.add(str2)
                    }
                }
            }
            return true
        }
    }

}