import java.util.Random;

public class deque{
    private int[] array;
    private int front;
    private int rear;
    private int size;
    public deque(int capacity) {
        array = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    //adicionar cabeça e cauda
    public void addPrimeiro(int value) {
        front = (front - 1 + array.length) % array.length;
        array[front] = value;
        if (size == 0) {
            rear = front;
        }
        size++;
    }
    public void addUltimo(int value) {
        rear = (rear + 1 - array.length) % array.length;
        array[rear] = value;
        if (size == 0) {
            front = rear;
        }
        size++;
    }
    
    //remover cabeça e cauda
    public void remPrimeiro(){
        front = (front + 1 - array.length) % array.length;
        array[front] = value;
        if (size == 0) {
            rear = front;
        }
        size--;
    }
     public void remUltimo(){
        rear = (rear - 1 + array.length) % array.length;
        array[rear] = value;
        if (size == 0) {
            front = rear;
        }
        size--;
    }

    //obter cabeça e cauda (sequencia de returns muito irados)
    public int getPrimeiro() {
        return array[front];
    }
    public int getUltimo() {
        return array[rear];
    }
    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }

}