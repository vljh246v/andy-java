package practical.chapter11;

import java.util.Optional;

public class OptionalHouse {

    // country 속성은 필수
    private String country;

    // 나머지 선택
    private Optional<String> city = Optional.empty();
    private Optional<String> address = Optional.empty();
    private Optional<String> detailAddress = Optional.empty();

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Optional<String> getCity() {
        return city;
    }

    public void setCity(Optional<String> city) {
        this.city = city;
    }

    public Optional<String> getAddress() {
        return address;
    }

    public void setAddress(Optional<String> address) {
        this.address = address;
    }

    public Optional<String> getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(Optional<String> detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(country).append(" ");

        //Optional.ifPresent
        /*
        public void ifPresent(Consumer<? super T> action) {
            if (this.value != null) {
                action.accept(this.value);
            }
        }
        */
        //Optional 기반 데이터 처리
        city.ifPresent((String s)-> sb.append(s).append(" "));
        address.ifPresent((String s)-> sb.append(s).append(" "));
        detailAddress.ifPresent((String s)-> sb.append(s).append(" "));

        return sb.toString();
    }
}
