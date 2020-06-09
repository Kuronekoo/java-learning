package array;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: kuroneko
 * @create: 2020-06-04 23:47
 **/
public class TestArray {
    @Test
    public void testTwoDimensionalArray(){
        int[][] ints = new int[2][3];
        ints[0][0] = 1;
        ints[0][1] = 2;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(ints[i][j] + "  ");
            }
            System.out.println();
        }
    }

    @Test
    public void testTwoSum(){
        int[] nums = {2,3,1,8,1,1,9};
        int target = 2;
        int[] ints = twoSum(nums, target);
        System.out.println(JSON.toJSON(ints));
    }


    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}
