package base;

import com.sun.deploy.util.StringUtils;

import java.util.*;

/**
 * Create with: base
 * author: songdongdong
 * E-mail: songdongdong@weidian.com
 * date: 2022/2/11 16:54
 * version: 1.0
 * description:
 */
public class JavaTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("1","2");
//        map.put("3","4");
//        map.put("5","6");
//        map.put("7","8");

        System.out.println(mapToString(map, "_", ":"));
        String coreWord = mapToString(map, "_", ":");
        if (coreWord.length() >= 1) {
            for (String map2 : coreWord.split("_")) {
                String value = map2.split(":")[1];
                System.out.println(value);
            }
        }
    }

    public static String mapToString(Map<String, String> map, String separator, String kvSplice) {
        List<String> result = Collections.synchronizedList(new ArrayList<>());
        map.entrySet().parallelStream().reduce(result, (first, second) -> {
            first.add(second.getKey() + kvSplice + second.getValue());
            return first;
        }, (first, second) -> {
            if (first == second) {
                return first;
            }
            first.addAll(second);
            return first;
        });

        return StringUtils.join(result, separator);
    }
}
