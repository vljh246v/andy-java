package practical.chapter11;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HouseExample {
    private List<House> housePool = new ArrayList<>();

    // 데이터 초기화
    private void initialize() {
        House house1 = new House();
        house1.setCountry("Korea");
        house1.setCity("Seoul");
        house1.setAddress("관악구 봉천동");
        house1.setDetailAddress("남부순환로202길 28");
        housePool.add(house1);

        House house2 = new House();
        house2.setCountry("Korea");
        house2.setCity("Seoul");
        house2.setAddress("관악구 봉천동");
        housePool.add(house2);

        House house3 = new House();
        house3.setCountry("Korea");
        house3.setCity("Suwon");
        housePool.add(house3);
    }

    public HouseExample() {
        initialize();
    }

    public List<House> getApartments(String city) {
        //도시 이름 필터링
        List<House> filteredList = housePool.stream()
                .filter((house -> house.getCity() != null && house.getCity().equals(city)))
                .collect(Collectors.toList());
        return  filteredList;
    }

    public static void main(String[] args) {
        HouseExample example = new HouseExample();
        List<House> resultList = example.getApartments("Seoul");
        resultList.forEach(System.out::println);
    }
}
