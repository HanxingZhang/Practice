// calculate each point distance and then sort: O(nlogn), O(N)

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        int length = points.length;
        int[] distances = new int[length];
        for(int i = 0; i < length; i++) {
            distances[i] = dist(points[i]);
        }

        Arrays.sort(distances);
        int lengthK = distances[K - 1];
        int[][] result = new int[K][2];
        int index = 0;
        for(int i = 0; i < length; i++) {
            if(distances[i] <= lengthK ) {
                result[index++] = points[i];
            }
        }
        return result;
    }

    private int dist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    // Divide and Conquer
    int[][] points;
    public int[][] kCloset2(int[][] points, int K) {
        this.points = points;
        sort(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void sort(int i, int j, int K) {
        if(i >= j) return;
        int k = ThreadLocalRandom.current().nextInt(i, j);
        swap(i, k);

        int mid = partition(i, j);
        int leftLength = mid - i + 1;
        if(K < leftLength) {
            sort(i, mid - 1, K);
        } else if (K > leftLength) {
            sort(mid + 1, j, K - leftLength);
        }
    }

    public int partition(int i, int j) {
        int oi = i;
        int pivot = dist(i);
        i++;

        while(true) {
            while(i < j && dist(i) < pivot) {
                i++;
            }
            while(i <= j && dist(j) > pivot) {
                j--;
            }
            if(i >= j) break;
            swap(i, j);
        }
        swap(oi, j);
        return j;
    }

    public int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][0] = t1;
    }
}
