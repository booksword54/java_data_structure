public class ArrayDemo {
    public static void main(String[] args) {
        // 声明数组,声明一个长度为3，只能存放int类型的数据
        int[] myArray = new int[3];
        // 给myArray第一个元素赋值1
        myArray[0] = 1;
        // 访问myArray的第一个元素
        System.out.println(myArray[0]);

        // 声明数组2,声明一个数组元素为 1,2,3的int类型数组
        int [] myArray2 = {1,2,3};
        for(int i = 0 ; i < myArray2.length ; i++){
            System.out.println(myArray2[i]);
        }
    }
}
