package Top150;
/*Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
find two numbers such that they add up to a specific target number. Let these two numbers be
numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.*/
public class TwoSumArraySorted {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length-1;
        while(i<j){
            if(numbers[i]+numbers[j] < target)
                i++;
            if(numbers[i]+numbers[j] > target)
                j--;
            if(numbers[i]+numbers[j] == target)
                break;
        }
        return new int [] {i,j};
    }

    public static void main(String[] args) {
        int [] arr = {2,5,7,10, 11, 13, 14};
        int [] res = new TwoSumArraySorted().twoSum(arr, 20);
        for(int i : res){
            System.out.print(i + " ");
        }
    }
}
