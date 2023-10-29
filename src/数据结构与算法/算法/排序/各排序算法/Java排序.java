package 数据结构与算法.算法.排序.各排序算法;

public class Java排序 {
    /*JDK7~13
    排序目标                            条件            采用算法

    int[] long[] float[] double[]    size<47        混合插入排序
                                     size<286       双基准点快排
                                     有序数高         归并排序
                                     有序数低         双基准点快排

    byte[]                          size>29         计数排序
                                    size<=29        插入排序
    char[] short[]                  size>3200       计数排序
                                    size<47         插入排序
                                    size<286        双基准点快排
                                    有序度高         归并排序
                                    有序度低         双基准点快排
    Object[]          useLegacyMergeSort=true      传统归并排序
                      useLegacyMergeSort=false     TimSort(归并+插入)
              //-Djava.util.Arrays.useLegacyMergeSort
     */

//---------------------------------------------------------------------------------------------
    /*JDK14~20
    排序目标                            条件                  采用算法

    int[] long[] float[] double[]    size<65(并不是最左侧)   混合插入排序(pin)
                                     size<44(并不是最右侧)   插入排序
                                     递归次数>384           堆排序
                            数组或非左侧size>4096,若接近有序   归并排序
                                    其他情况                双基准点快排

    byte[]                          size>64               计数排序
                                    size<=64              插入排序
    char[] short[]                  size>1750             计数排序
                                    size<44               插入排序
                                    递归次数超过384         计数排序
                                    其他情况               双基准点快排
    Object[]          useLegacyMergeSort=true            传统归并排序
                      useLegacyMergeSort=false     TimSort(归并+插入)
     */
//从JDK8开始支持Arrays.parallelSort并行排序(多线程)
}
