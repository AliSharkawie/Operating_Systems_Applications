public class Partition {
    public String name;
    public int size;
    public Process process;
    static int counter = 0;
    public int remainingSpace;

    public Partition(String name, int Size) {
        this.name = name;
        this.size = Size;
        this.remainingSpace = size;
        counter++;
    }

    public boolean isEmpty() {
        if (this.remainingSpace == this.size) return true;
        else return false;
    }

    public void print() {
        if (this.isEmpty())
            System.out.println(this.name + "( " + Integer.toString(this.size) + " )" + " => External fragment");
        else System.out.println(this.name + "( " + Integer.toString(this.size) + " )" + " => " + this.process.name);
    }

}
