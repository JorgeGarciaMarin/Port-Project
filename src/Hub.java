import Exceptions.FullPriorityLevel;
import Exceptions.NoContainersException;
import Exceptions.NoPriorityLevel;

public class Hub {
    int hubID;
    Container[][] matrix;
    Hub(){
        matrix=new Container[10][12];
    }
    void setHubID(int hubID){
        this.hubID=hubID;
    }
    int getHubID(){
        return hubID;
    }
    void setMatrix(Container[][]matrix){
        this.matrix=matrix;
    }
    Container[][] getMatrix(){
        return matrix;
    }
    void stackContainer(Container toStack) throws FullPriorityLevel, NoPriorityLevel {
        int row = -1;
        int column = -1;
        if (toStack.priorityLevel==1) {
            column = 0;
            for (int i = 9; i >=0; i--) {
                if (matrix[i][column] == null) {
                    row = i;
                    matrix[row][column] = toStack;
                    System.out.println("Container Stacked:row"+row+" column:"+column);
                    break;
                }
            }
            if (row == -1)
                throw new FullPriorityLevel("Hub full for this Priority Level");
        } else if (toStack.priorityLevel == 2) {
            column = 1;
            for (int i = 9; i >=0; i--) {
                if (matrix[i][column] == null) {
                    row = i;
                    matrix[row][column] = toStack;
                    System.out.println("Container Stacked:row"+row+" column:"+column);
                    break;
                }
            }
            if (row == -1)
                throw new FullPriorityLevel("Hub full for this Priority Level");
        } else if (toStack.priorityLevel == 3) {
            for (int i = 2; i < 12; i++) {
                for (int j = 9; j >=0; j--) {
                    if (matrix[j][i] == null) {
                        row = j;
                        column = i;
                        matrix[row][column] = toStack;
                        System.out.println("Container Stacked:row"+row+" column:"+column);
                        break;
                    }
                }
                if(row!=-1)
                    break;
            }
            if (row == -1)
                throw new FullPriorityLevel("Hub full for this Priority Level");
        }
        else
            throw new NoPriorityLevel("Select a priority level");
    }
    void removeContainer(int column) throws NoContainersException {
        int count=0;
        for (int i=0;i<10;i++){
            if(matrix[i][column]!=null) {
                matrix[i][column] = null;
                count++;
                System.out.println("Container removed from row"+i+",column"+column);
                break;
            }
        }
        if(count==0)
            throw new  NoContainersException("No containers in this column");
    }
    String displayDataFromID(int ID){
        for (int i=0;i<10;i++){
            for (int j=0;j<12;j++){
                if(matrix[i][j]!=null &&matrix[i][j].ID==ID)
                    return matrix[i][j].toString();
            }
        }
        System.out.println("Container with ID"+ID+" not found in the hub");
        return null;
    }
    int containersByCountry(String country){
        int counter=0;
        for (int i=0;i<10;i++){
            for (int j=0;j<12;j++)
                if(matrix[i][j]!=null&&matrix[i][j].countryOrigin.equals(country))
                    counter++;
        }
        return counter;

    }
    public String toString(){
        StringBuilder builder=new StringBuilder();
        for (int i=0;i<10;i++){
            for (int j=0;j<12;j++){
                if(matrix[i][j]==null)
                    builder.append("|"+"E");
                else {
                    builder.append("|"+"C");
                }
            }
            builder.append("\n");
        }
        builder.append("\n"+"E:Empty"+"\n"+"C:Container");
        return builder.toString();
    }
    boolean full(){
        int counter=0;
        for (int i=0;i<10;i++){
            for (int j=0;j<12;j++){
                if(matrix[i][j]==null)
                    counter++;
            }
        }
        if(counter==0) {
            return true;
        } else
            return false;
    }
}
