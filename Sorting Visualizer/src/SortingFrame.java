import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SortingFrame extends Thread {

    public static final int BUBBLE_SORT=0;
    public static final int INSERTION_SORT=1;
    public static final int SELECTION_SORT=2;
    public static final int MERGE_SORT=3;

    public static final int RANDOM_ORDER=0;
    public static final int ASCENDING_ORDER =1;
    public static final int DESCENDING_ORDER=2;

    private int algo=0;
    private int barWidth=50;
    private int arraySize=10;
    private double speed=1;

    private ArrayList<Integer> barLengths;

    private JFrame frame;
    private JProgressBar bars[];

    private Color normalColor=new Color(0x11A9A9);
    private Color sortedColor=Color.ORANGE;
    private Color compareColor=Color.GREEN;

    SortingFrame(int algo, int order, int arraySize, int scale, double speed) {

        // setting frame;
        frame=new JFrame();
        frame.setSize(700, 500);
        frame.setLayout(new GridLayout(1, arraySize, 0, 0));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("project logo.png");
        frame.setIconImage(icon.getImage());

        // setting variables
        this.algo=algo;
        this.arraySize=arraySize;
        this.barWidth=700/arraySize;
        this.speed=speed;

        int diff=(90)/(arraySize-1);
        barLengths=new ArrayList<>(arraySize);
        barLengths.add(10);
        int l=10;
        for(int i=1; i<arraySize; i++) {
            l+=diff;
            barLengths.add(l);
        }

        setOrder(order);

        bars=new JProgressBar[arraySize];
        for(int i=0; i<bars.length; i++) {
            bars[i]=new JProgressBar(JProgressBar.VERTICAL, 0, 100);
            bars[i].setPreferredSize(new Dimension(barWidth, 50));
            bars[i].setValue(barLengths.get(i));
            bars[i].setBorderPainted(false);
            bars[i].setForeground(normalColor);
            bars[i].setBackground(Color.WHITE);

            frame.add(bars[i]);
        }
    }

    public void run() {
        frame.setVisible(true);
        startSorting(algo);
    }

    public void setOrder(int order) {
        switch (order) {
            case RANDOM_ORDER -> {
                Collections.shuffle(barLengths);
            }

            case ASCENDING_ORDER -> {
                // do nothing;
            }

            case DESCENDING_ORDER -> {
                Collections.reverse(barLengths);
            }
        }
    }

    public void startSorting(int algo) {

        switch (algo) {
            case BUBBLE_SORT -> {
                frame.setTitle("Bubble Sort");
                bubbleSort();
            }

            case INSERTION_SORT -> {
                frame.setTitle("Insertion Sort");
                insertionSort();
            }

            case SELECTION_SORT -> {
                frame.setTitle("Selection Sort");
                selectionSort();
            }

            case MERGE_SORT -> {
                frame.setTitle("Merge Sort");
                mergeSort();
            }
     }
    }

    public void regulateTime() {
        try { Thread.sleep((long) (1000/speed));}
        catch (InterruptedException e) {}
    }

    public void bubbleSort() {

        for(int i=0; i<barLengths.size(); i++) {
            for(int j=0; j<barLengths.size()-1-i; j++) {
                bars[j].setForeground(compareColor);
                bars[j+1].setForeground(compareColor);

                regulateTime();

                if(barLengths.get(j) > barLengths.get(j+1)) {
                    int temp=barLengths.get(j);
                    barLengths.set(j, barLengths.get(j+1));
                    barLengths.set(j+1, temp);
                }

                bars[j].setValue(barLengths.get(j));
                bars[j+1].setValue(barLengths.get(j+1));

                bars[j].setForeground(normalColor);
                bars[j+1].setForeground(normalColor);
            }

            bars[arraySize-i-1].setForeground(sortedColor);
        }
    }

    public void insertionSort() {

        bars[0].setForeground(sortedColor);

        for(int i=1; i<barLengths.size(); i++) {

            int curr=barLengths.get(i);
            bars[i].setForeground(compareColor);

            int j=i-1;

            if(barLengths.get(j) > curr) {
                bars[j].setForeground(compareColor);
            }

            while(j>=0 && barLengths.get(j) > curr) {

                regulateTime();

                barLengths.set(j+1, barLengths.get(j));
                bars[j+1].setValue(barLengths.get(j+1));
                bars[j+1].setForeground(sortedColor);

                barLengths.set(j, 0);
                bars[j].setValue(barLengths.get(j));
                j--;
            }

            barLengths.set(j+1, curr);
            bars[j+1].setValue(barLengths.get(j+1));
            bars[j+1].setForeground(compareColor);

            regulateTime();
            bars[j+1].setForeground(sortedColor);
        }
    }

    public void selectionSort() {

        for(int i=0; i<barLengths.size(); i++) {

            int minIdx=i;
            bars[minIdx].setForeground(compareColor);

            for(int j=i+1; j<barLengths.size(); j++) {

                bars[j].setForeground(compareColor);
                regulateTime();

                if(barLengths.get(minIdx) > barLengths.get(j)) {
                    bars[minIdx].setForeground(normalColor);
                    minIdx=j;
                } else {
                    bars[j].setForeground(normalColor);
                }
            }

            bars[minIdx].setForeground(normalColor);
            bars[bars.length-1].setForeground(normalColor);

            int temp=barLengths.get(minIdx);
            barLengths.set(minIdx, barLengths.get(i));
            barLengths.set(i, temp);

            bars[minIdx].setValue(barLengths.get(minIdx));
            bars[i].setValue(barLengths.get(i));
            bars[i].setForeground(sortedColor);
        }
    }

    private void mergeSort() {
        mergeSortHelper(0, bars.length - 1);
    }

    private void mergeSortHelper(int left, int right)  {
        if (left < right) {
        int mid = (left + right) / 2;
        mergeSortHelper(left, mid);
        mergeSortHelper(mid + 1, right);
        merge(left, mid, right);
        }
    }

    private void merge(int left, int mid, int right)  {
    int[] temp = new int[right - left + 1];
    int i = left;
    int j = mid + 1;
    int k = 0;

    while (i <= mid && j <= right) {
        bars[i].setForeground(Color.GREEN);
        bars[j].setForeground(Color.GREEN);
        regulateTime();

        if (bars[i].getValue() <= bars[j].getValue()) {
            temp[k++] = bars[i++].getValue();
        } else {
            temp[k++] = bars[j++].getValue();
        }

        if(i>0){bars[i - 1].setForeground(Color.BLUE);}
        if(i>0){bars[j - 1].setForeground(Color.BLUE);}
    }

    while (i <= mid) {
        bars[i].setForeground(Color.GREEN);
        regulateTime();
        temp[k++] = bars[i++].getValue();
        if(i>0){bars[i - 1].setForeground(Color.BLUE);}
    }

    while (j <= right) {
        bars[j].setForeground(Color.GREEN);
        regulateTime();
        temp[k++] = bars[j++].getValue();
        if(i>0){bars[j - 1].setForeground(Color.BLUE);}
    }

    for (int p = 0; p < temp.length; p++) {
        bars[left + p].setValue(temp[p]);
        bars[left + p].setForeground(Color.ORANGE); // Show sorted progress
        regulateTime();
        }
    }


    public static void main(String[] args) {

        new SortingFrame(SortingFrame.BUBBLE_SORT,
                SortingFrame.RANDOM_ORDER,30, 0, 10).start();
    }
}
