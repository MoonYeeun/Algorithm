package LeetCode

// 1. Two Sum
class LC1_Two_Sum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        var map = mutableMapOf<Int, Int>()

        nums.forEachIndexed { index, value ->
            map[value]?.let {
                return intArrayOf(it, index)
            }
            map[target - value] = index
        }

        return intArrayOf()
    }
}