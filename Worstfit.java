import java.util.*;
public class Worstfit{
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        int[]blocks=new int[5];
        int[]process=new int[4];
        System.out.println("Enter the size of 5 blocks");
        for(int i=0;i<5;i++)
        blocks[i]=sc.nextInt();
        System.out.println("Enter the number of process size");
        for(int i=0;i<4;i++)
        process[i]=sc.nextInt();
        for(int i=0;i<4;i++){
            int worst=-1;
            for(int j=0;j<5;j++){
                if(blocks[j]>=process[i]&&(worst==-1||blocks[j]>blocks[worst])){
                worst=j;
            }
        }
        if(worst!=-1){
            System.out.println("process"+process[i]+"->blocks"+(worst+1));
            blocks[worst]-=process[i];
        }
        else{
            System.out.println("process"+process[i]+"->Not allocated");
        }
    }
}
}