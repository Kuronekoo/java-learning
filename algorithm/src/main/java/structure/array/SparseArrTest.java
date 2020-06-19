package structure.array;

import java.io.*;
/**
 * @description: 稀疏数组
 *
 * 当二维数组存在大量的重复数据时，使用稀疏数组进行存储会更加节省空间
 * 稀疏数组的第一列是是二维数组的行，列，有值的列的数量
 * 后面的列是 行号 列号 值
 *
 * 1.遍历原始的二维数组，得到有效的数据个数sum
 * 2.根据sum创建稀疏数组 int[sum+1][3]
 * 3.将二维数组的有效数据存入到稀疏数组
 *
 * @author: kuroneko
 * @create: 2020-06-05 10:27
 **/
public class SparseArrTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //初始化二维数组
        int[][] chess = new int[11][11];
        chess[2][1] = 1;
        chess[2][4] = 2;
        //打印二维数组
        for (int[] row : chess) {
            for (int item : row) {
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
        System.out.println();
        //获取初始化稀疏数组的参数
        int sum = 0;
        for (int[] row : chess) {
            for (int item : row) {
               if(0!=item){
                   sum++;
               }
            }
        }
        //初始化稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        //初始化稀疏数组的第一行
        sparseArr[0][0] = chess.length;
        sparseArr[0][1] = chess[0].length;
        sparseArr[0][2] = sum;

        //遍历二维数组，给稀疏数组赋值
        int s = 1;
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if(chess[i][j] != 0 ){
                    sparseArr[s][0] = i;
                    sparseArr[s][1] = j;
                    sparseArr[s][2] = chess[i][j];
                    s++;
                    continue;
                }
            }
        }

        //打印稀疏数组
        for (int[] row : sparseArr) {
            for (int item : row) {
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
        System.out.println();

        //序列化，存盘
        FileOutputStream out = new FileOutputStream("ioFiles/sparseArr.data");
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(sparseArr);
        oos.flush();
        //关闭文件流
        oos.close();
        out.close();

        //读取文件
        FileInputStream in = new FileInputStream("ioFiles/sparseArr.data");
        ObjectInputStream ois = new ObjectInputStream(in);
        int[][] sparseArrData = (int[][]) ois.readObject();
        //关闭文件流
        ois.close();
        in.close();

//        new FileInputStream()

        //接下来把稀疏数组还原成二维数组、
        //初始化
        int[][] newChess = new int[sparseArrData[0][0]][sparseArrData[0][1]];
        //遍历赋值
        for (int i = 1; i < sparseArrData.length; i++) {
            newChess[sparseArrData[i][0]][sparseArrData[i][1]] = sparseArrData[i][2];
        }
        //打印h还原出来的二维数组
        for (int[] row : newChess) {
            for (int item : row) {
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //done！
    }
}
