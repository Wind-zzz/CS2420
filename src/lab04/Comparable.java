//package lab04;
//
//import java.util.Comparator;
//
//public class Comparable<T> {
//
//    private Comparator<T> cmp;
//
//    public static <T extends Comparable <? super T>> T median(T[] arr) {
//        int compareTool = compareTo();
//    }
//
//    @SuppressWarnings("unchecked")
//    private int compareOrder(T o1, T o2) {
//        if (o1 == o2)
//            return 0;
//        if (o1 == null)
//            return -1;
//        if (o2 == null)
//            return 1;
//
//        if (this.cmp == null) {
//            return ((Comparable<? super T>) o1).compareTo(o2);
//        }
//        return this.cmp.compare(o1, o2);
//    }
//
//    private int compareTo(T o2) {
//    }
//}
