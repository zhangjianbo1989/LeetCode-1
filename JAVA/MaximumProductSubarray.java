public class Solution {
    public int maxProduct(int[] A) {
        if (A.length == 1) return A[0];
        List<Integer> zeroIndex = new ArrayList<Integer>();
        List<Long> prods = new ArrayList<Long>();
        zeroIndex.add(-1);
        long prod = 1, maxProd = Long.MIN_VALUE;
        boolean hasZero = false;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                maxProd = 0;
                zeroIndex.add(i);
                prods.add(prod);
                prod = 1;
            } else prod *= A[i];
        }
        prods.add(prod);
        zeroIndex.add(A.length);
        
        for (int i = 0; i < zeroIndex.size() - 1; i++) {
            int low = zeroIndex.get(i) + 1, high = zeroIndex.get(i + 1);
            if (low >= high) continue;
            if (low + 1 == high) {
                maxProd = Math.max(maxProd, prods.get(i));
                continue;
            }
            if (prods.get(i) > 0) {
                maxProd = Math.max(maxProd, prods.get(i));
                continue;
            }
            
            // find from left
            long temp = prods.get(i);
            for (int j = low; j < high - 1; j++) {
                temp /= A[j];
                if (A[j] < 0) {
                    maxProd = Math.max(maxProd, temp);
                    break;
                }
            }
            
            // find from right
            temp = prods.get(i);
            for (int j = high - 1; j > low; j--) {
                temp /= A[j];
                if (A[j] < 0) {
                    maxProd = Math.max(maxProd, temp);
                    break;
                }
            }
        }
        
        return (int)maxProd;
    }
}

// neat code
public class Solution {
    public int maxProduct(int[] A) {
        if (A.length == 1) return A[0];
        int maxProd = 0, minProd = 0, res = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                maxProd = Math.max(maxProd * A[i], A[i]);
                minProd = minProd * A[i];
            } else if (A[i] == 0) {
                minProd = maxProd = 0;
            } else {
                int temp = maxProd;
                maxProd = minProd * A[i];
                minProd = Math.min(temp * A[i], A[i]); // handle the temp = 0 case
            }
            res = Math.max(maxProd, res);
        }
        return res;
    }
}