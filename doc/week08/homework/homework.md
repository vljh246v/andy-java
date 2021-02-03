# 1. 제네릭 컬렉션을 만드시오

# 정답 예제

```java
    public class CustomList {
        private List list = new ArrayList();

        public void add(Object element){
            list.add(element);
        }

        public Object get(int index) {
            return list.get(index);
        }
    }
```