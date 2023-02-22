public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static int[] getPrimeNumbersUntil(int maximum) {
        if(maximum < 2) {
            throw new IllegalArgumentException("Numbers below 2 are not allowed");
        }

        if(maximum == 2){
            return new int[] {2};
        }

        boolean[] primeNumbers = new boolean[maximum-2];
        // index 0 represents prime number 2
        // false -> primeNumber
        for(int i = 0; i < primeNumbers.length; i++) {
            int number = i+2;
            if(!primeNumbers[i]) {
                // j start at next position, because i is the prime number and should therefore be not marked
                for (int j = i+number; j < primeNumbers.length; j+=number) {
                    primeNumbers[j] = true;
                }
            }
        }

        return null;
    }
}