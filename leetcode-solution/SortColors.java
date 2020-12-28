public class SortColors {
    public void sortColors(int[] nums) {
        int indexZero = 0;
        int indexOne = 0;
        int indexTwo = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) indexZero++;
            if(nums[i] == 1) indexOne++;
            if(nums[i] == 2) indexTwo++;
        }
        int index = 0;
        for(int i = 0; i < indexZero; i++) {
            nums[index++] = 0;
        }
        for(int i = 0; i < indexOne; i++) {
            nums[index++] = 1;
        }
        for(int i = 0; i < indexTwo; i++) {
            nums[index++] = 2;
        }
    }

    public void sortColors1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int index = 0;

        while(index <= right) {
            if(nums[index] == 0) {
                swap(nums, index, left);
                left++;
                index++;
            } else if (nums[index] == 2) {
                swap(nums, index, right);
                right--;
            } else {
                index++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
