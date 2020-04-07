import java.util.*;
import java.util.Random;
class Rabbit {
    Random rand = new Random(0);
    int age = -1; // -1 for dead rabbit
    int gender = -1; // 0 for female, 1 for male
    public void setData(int a, int g) {
        age = a;
        gender = g;
    }
    public void getOlder() {
        age++;
    }
    public void dieRabbit() {
        age = -1;
    }
    public int[] getData() {
        int[] array = new int[2];
        array[0] = age;
        array[1] = gender;
        return array;
    }
}
public class Main {
    static int HowManyLiveRabbits (int n, int k, int m) {
        Random rand = new Random();
        int max = 100; // max expected amount of rabbits
        int sum = 2; // starting pair of rabbits
        Rabbit[] rabbitArray = new Rabbit[max];
        // create 200-element array of dead rabbits
        for (int y = 0; y < max; y++) {
            Rabbit deadRabbit = new Rabbit();
            deadRabbit.setData(-1, -1);
            rabbitArray[y] = deadRabbit;
        }
        // filling array with the starting pair of rabbits
        for (int s = 0; s < sum; s++) {
            Rabbit myRabbit = new Rabbit();
            myRabbit.setData(0, rand.nextInt(2));
            rabbitArray[s] = myRabbit;
        }

        for (int cycles = 0; cycles < n; cycles++) {
            int male_ctr = 0;
            int female_ctr = 0;
            int children = 0;

            for (int t = 0; t < max; t++) {
                if (rabbitArray[t].getData()[0] >= 0 && rabbitArray[t].getData()[0] <= m) {
                    if (rabbitArray[t].getData()[1] == 0) {
                        female_ctr++;
                    } else {
                        male_ctr++;
                    }
                } else {
                    rabbitArray[t].dieRabbit(); // death of old rabbits
                }
            }
            if (female_ctr > male_ctr) {
                children = male_ctr * k;
            } else {
                children = female_ctr * k;
            }
            //parents are getting older
            for (int t = 0; t < max; t++) {
                if (rabbitArray[t].getData()[0] >= 0) { // if rabbit is alive, its age will be incremented
                    rabbitArray[t].getOlder();
                }
            }
            //children are added to rabbitArray
            for (int ch = 0; ch < children; ch++) {
                int ptr = 0;
                for (int q = 0; rabbitArray[q].getData()[0] != -1; q++) {
                    ptr++;
                }
                Rabbit youngRabbit = new Rabbit();
                youngRabbit.setData(0, rand.nextInt(2));
                rabbitArray[ptr] = youngRabbit; // child is added in first free place
            }
            sum = children + male_ctr + female_ctr;
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(HowManyLiveRabbits(3,2,2));
    }
}
