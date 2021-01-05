import java.util.ArrayList;
import java.util.List;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //record path
        List<Integer> track = new ArrayList<>();
        backtTrack(result, nums, track);
        return result;
    }

    public void backtTrack(List<List<Integer>> result, int[] nums, List<Integer> track) {
        // trigger end condition
        if(track.size() == nums.length) {
            result.add(new ArrayList<Integer>(track));
            return;
        }
        //make selection
        for(int i = 0; i < nums.length; i++) {
            // exclude invalid selection
            if(track.contains(nums[i])) {
                continue;
            }
            // make selection
            track.add(nums[i]);
            // go to next level
            backtTrack(result, nums, track);
            // discard selection
            track.remove(track.size() - 1);
        }
    }
}
