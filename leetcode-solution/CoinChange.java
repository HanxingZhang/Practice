
public class CoinChange {
    /*322
    * We can use dynamic programming. We can define dp[i] as the fewest number of coins that we used to make up amount i.
    * So the initial value, dp[0] is 0. The length of dp is amount + 1;
    * To calculate the dp[i], I am finding the amount j which j + coinAmount = i;
    * if dp[j] has been calculated before, we knew that dp[i] = dp[j] + 1;
    * And because we have different coins of denominations, we need to keep the smallest number of dp[i] for each calculation
    * For those i cannot be made up by any combination of the coins, we assign the dp[i] with the max value of Integer.
    *
    * Time Complexity: O(S*n). On each step the algorithm finds the next dp[i] in n iteration, where 1 <= i <= S.
    * Therefore in total the iterations are S * n;
    * Space complexity: O(S). We use extra space for the memorization table.
     *
    * */

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;

        for(int i = 1; i <= amount; i++) {
            int ans = Integer.MAX_VALUE;
            for(int j = 0; j < coins.length; j++) {
                if(i - coins[j] >= 0 && dp[i - coins[j]] < ans) {
                    ans = dp[i - coins[j]] + 1;
                }
                dp[i] = ans;
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /*518
    * Add coins one-by-one, starting from base case dp[0], "no coins"
    * For each added coin, compute recursively the number of combinations for each amount of money from 0 to amount
    * Initiate number of combinations array with the base case "no coins": dp[0] = 1, and all the rest = 0.
    * Loop over all coins: For each coin, loop over all amounts from 0 to amount: For each amount x, compute the number of combinations: dp[x] += dp[x - coin]
    * return dp[amount]
    *
    * Time complexity: O(N×amount), where N is a length of coins array.
    * Space complexity: O(amount) to keep DP array
    *
    * */

    /*
    dp[i][j] : the number of combinations to make up amount j by using the first i types of coins
    State transition:

    not using the ith coin, only using the first i-1 coins to make up amount j, then we have dp[i-1][j] ways.
    using the ith coin, since we can use unlimited same coin, we need to know how many ways to make up amount j - coins[i-1]
    by using first i coins(including ith), which is dp[i][j-coins[i-1]]
    Initialization: dp[i][0] = 1
    */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int coin: coins) {
            for(int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
}
