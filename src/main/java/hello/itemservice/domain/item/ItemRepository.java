package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();//실제는 hashMap 쓰면 안됨 - static
    //싱글톤으로 작성되때문에 여러 곳에서 동시 호출할경우 큰일난다!
//    꼭 써야겠다면 ConcurrentHashMap 를 쓰도록 하자
    private static long sequence = 0L;//static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 실무에서는 여기 보면 id 가 안쓰이니깐 dto 를 따로 구성하는게 좋다
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
