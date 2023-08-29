package seatingchart;

public class NumberedBox {
    public static void main(String[] args) {
        int size = 10; // 각 열의 크기
        int rows = 7; // 행의 개수
        int startingNumber = 1;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < size; col++) {
                int num = startingNumber + col + (row * size);
                System.out.print("┌────┐");
            }
            System.out.println("");
            
            for (int col = 0; col < size; col++) {
                int num = startingNumber + col + (row * size);
                System.out.print("│ " + formatNumber(num) + " │");
            }
            System.out.println("");
            
            for (int col = 0; col < size; col++) {
                System.out.print("└────┘");
            }
            System.out.println("");
        } 
    }

    private static String formatNumber(int number) {
        if (number < 10) {
            return " " + number;
        } else {
            return Integer.toString(number);
        }
    }
}