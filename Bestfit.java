import java.util.*;
public class Bestfit{
    public static void main(String []args){
        Scanner sc=new Scanner(System.in);
        int[]blocks=new int[5];
        int[]process=new int[4];
        System.out.println("Enter th number of  size block");
        for(int i=0;i<5;i++)blocks[i]=sc.nextInt();
        System.out.println("enter the process size");
        for(int i=0;i<4;i++)
        process[i]=sc.nextInt();
        for(int i=0;i<4;i++){
            int best=-1;
            for(int j=0;j<5;j++){
                if(blocks[j]>=process[i]&&(best==-1||blocks[j]<blocks[best])){
                best=j;
            }

        }
        if(best!=-1){
            System.out.println("process"+process[i]+"->block"+(best+1));
            blocks[best]-=process[i];
        }else{
            System.out.println("process"+process[i]+"->not allocated");
        }
    }
}
}