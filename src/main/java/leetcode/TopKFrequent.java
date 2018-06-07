package leetcode;

import java.util.*;

/**
 * @author Robin
 * @ClassName: TopKFrequent
 * @DESCRIPTION:
 * @date: 2016/6/2.
 */
public class TopKFrequent {
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer,Integer> map=new HashMap<>();
		List<Integer> resultList=new ArrayList<>(k);
		for (int i = 0; i < nums.length; i++) {
			if(map.containsKey(nums[i])){
				map.put(nums[i],map.get(nums[i])+1);
			}else {
				map.put(nums[i],0);
			}
		}
		Set<Map.Entry<Integer, Integer>> set = map.entrySet();
		Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();
		PriorityQueue<Map.Entry<Integer, Integer>> queue=new PriorityQueue(k, new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Map.Entry<Integer, Integer> left, Map.Entry<Integer, Integer> right) {
				return left.getValue().compareTo(right.getValue());
			}

		});
		while (iterator.hasNext()){
			queue.add(iterator.next());
		}
		for(int i = 0; i < k; i++){
			// in practice, we need check operation is null or not
			//System.out.println(kFrequent.poll());
			resultList.add(queue.poll().getKey());
		}
		return resultList;
	}

	public static void main(String[] args) {
		TopKFrequent t=new TopKFrequent();
		int[] nums={1,2,3,2,1,1,2};
		int k=2;
		List<Integer> integers = t.topKFrequent(nums, k);
		System.out.println(integers.toString());

	}
}