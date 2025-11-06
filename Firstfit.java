import java.util.*;
public class Firstfit{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
         int []block=new int[5];
         int[]process=new int[4];
         System.out.println("Enetr the 5 block");
         for(int i=0;i<5;i++)
         block[i]=sc.nextInt();
         System.out.println("Enetr the 4 process");
         for(int i=0;i<4;i++)
         process[i]=sc.nextInt();
         for(int i=0;i<4;i++){
         boolean allocated=false;
         for(int j=0;j<5;j++){
            if(block[j]>=process[i]){
                System.out.println("Process"+process[i]+"->block"+(j+1));
                block[j]-=process[i];
                allocated=true;
                break;
            }
         }
         if(!allocated)
            System.out.println("process"+process[i]+"->Not allocated");
         }
    }
}