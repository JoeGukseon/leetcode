public class RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;
    Random random;

    public RandomizedSet() {
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        this.random = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }

        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int indexToRemove = map.get(val);

        int lastValue = list.get(list.size() - 1);
        list.set(indexToRemove, lastValue);
        map.put(lastValue, indexToRemove);

        list.remove(list.size() - 1);
        map.remove(val);

        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}